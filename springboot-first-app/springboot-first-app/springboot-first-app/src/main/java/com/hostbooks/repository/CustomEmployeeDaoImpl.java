package com.hostbooks.repository;

import com.hostbooks.Dto.EmployeeDto;
import com.hostbooks.Dto.EmployeeResponse;
import com.hostbooks.entities.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class CustomEmployeeDaoImpl implements CustomEmployeeDao{
    @PersistenceContext
    EntityManager em;
    @Autowired
    ModelMapper mp;


    @Override
    public List<Employee> findEmployeeByFirstName(String firstName) {

        CriteriaBuilder cb =em.getCriteriaBuilder();

        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        Root<Employee> employee = cq.from(Employee.class);

        List<Predicate> predicates = new ArrayList<>();

        if(firstName!=null){
            predicates.add(cb.equal(employee.get("firstName"),firstName));
        }
//        if(designation!=null){
//            predicates.add(cb.equal(employee.get("designation"),designation));
//        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Employee> findEmployeeByFirstNameAndDesignation(String firstName, String designation) {

        CriteriaBuilder cb =em.getCriteriaBuilder();

        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        Root<Employee> employee = cq.from(Employee.class);

        List<Predicate> predicates = new ArrayList<>();

        if(firstName!=null){
            predicates.add(cb.equal(employee.get("firstName"),firstName));
        }
        if(designation!=null){
            predicates.add(cb.equal(employee.get("designation.grade"),designation));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Employee> findEmployeeInDescendingOrder() {

        Criteria criteria = em.unwrap(Session.class).createCriteria(Employee.class);


        criteria.addOrder(Order.desc("empId"));
        List<Employee> employees = criteria.list();

        return employees;
    }

    @Override
    public List<Employee> filterEmployeeBaseOnIdAndName(Integer empId, String name) {

        Criteria cr =  em.unwrap(Session.class).createCriteria(Employee.class);
        Criterion employeeId =  Restrictions.gt("empId", empId);

        Criterion employeeName =  Restrictions.like("firstName",name);
        LogicalExpression andExp =  Restrictions.and(employeeId,employeeName);

        cr.add(andExp);
        List<Employee> employees = cr.list();

        return employees;
    }

    @Override
    public boolean findEmployeeByMobile(String mobileNumber) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Employee> cq=cb.createQuery(Employee.class);

        Root<Employee> employeeRoot = cq.from(Employee.class);

        Predicate predicate = cb.equal(employeeRoot.get("mobileNumber"),mobileNumber);

        cq.where(predicate);
        List<Employee> employee= em.createQuery(cq).getResultList();

        if(employee.size()==0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public List<String> fingEmployeeByNameThroughHql() {

       Session s= em.unwrap(Session.class);

       String hql1 = "select e.firstName from Employee as e ORDER BY e.firstName ASC";
       String hql2 = "update Employee set firstName=:name where empId=:empId";
       String hql3 = " select d.grade from Employee e INNER JOIN e.designation d where  e.empId=1";
       String hql4 = "select e.firstName,e.lastName ,d.grade from Employee as e INNER JOIN e.designation as d";

       Query q= s.createQuery(hql3);

//       q.setParameter("name","Charter Accountant");
//       q.setParameter("empId",1);


      // Integer n =q.executeUpdate();
        //System.out.println(n);

       List<String> names =q.getResultList();

        return names;

         // q.getSingleResult();
         // return null;
//       List<Object[]> objects= q.getResultList();
//        System.out.println(objects);
       //return null;

    }

    @Override
    public List<Employee> findEmployeeByNameQuery() {
        Session s= em.unwrap(Session.class);

        Query q = s.createNamedQuery("findByFirstName");

        q.setParameter("name","Nikita");

        List<Employee> employees =q.getResultList();

        return employees;
    }

    @Override
    public EmployeeResponse paginationThroughCriteria(String name, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = cb.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);

        List<Predicate> predicateList = new ArrayList<>();

        if(!name.equals("")){
            predicateList.add(cb.like(employeeRoot.get("firstName"), "%" + name + "%"));
        }

        criteriaQuery.select(employeeRoot).where(predicateList.toArray(new Predicate[0]));
        if(sortDir.equalsIgnoreCase("asc")){
            criteriaQuery.orderBy(cb.asc(employeeRoot.get(sortBy)));
        }
        if(sortDir.equalsIgnoreCase("desc")){
            criteriaQuery.orderBy(cb.desc(employeeRoot.get(sortBy)));
        }

        TypedQuery<Employee> typedQuery= em.createQuery(criteriaQuery);
        CriteriaQuery<Long> countLong =  cb.createQuery(Long.class);
        countLong.select(cb.count(countLong.from(Employee.class))).where(predicateList.toArray(new Predicate[0]));
        Long count  = em.createQuery(countLong).getSingleResult();

        if(pageNumber.intValue()>0){
            typedQuery.setFirstResult((pageNumber.intValue()-1)*pageSize.intValue());
        }
        typedQuery.setMaxResults(pageSize.intValue());
        EmployeeResponse employeeResponse = new EmployeeResponse();
        List<Employee> employees = typedQuery.getResultList();
        List<EmployeeDto> employeeDtos = employees.stream().map((employee) -> mp.map(employee, EmployeeDto.class)).collect(Collectors.toList());

        employeeResponse.setContent(employeeDtos);
        employeeResponse.setTotalPages((int) ((count/pageSize)+1));
        return employeeResponse;
    }


}

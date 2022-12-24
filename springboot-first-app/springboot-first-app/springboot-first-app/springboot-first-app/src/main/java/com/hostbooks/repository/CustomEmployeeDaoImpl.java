package com.hostbooks.repository;

import com.hostbooks.Dto.EmployeeDto;
import com.hostbooks.entities.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Repository
public class CustomEmployeeDaoImpl implements CustomEmployeeDao{
    @PersistenceContext
    EntityManager em;


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
        Employee employee= em.createQuery(cq).getSingleResult();

        if(employee==null){
            return false;
        }else{
            return true;
        }
    }


}

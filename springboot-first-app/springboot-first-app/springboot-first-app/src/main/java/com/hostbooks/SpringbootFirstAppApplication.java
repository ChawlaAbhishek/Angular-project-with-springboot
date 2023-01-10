package com.hostbooks;

import com.hostbooks.entities.User;
import com.hostbooks.repository.UserDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringbootFirstAppApplication implements CommandLineRunner {
	@Autowired
	private UserDao userDao;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootFirstAppApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {

		User user=new User();
		user.setUsername("abhishek");
		user.setPassword(this.bCryptPasswordEncoder.encode("abhishek"));
		user.setRole("ROLE_ADMIN");

		this.userDao.save(user);

		User user1 = new User();
		user1.setUsername("bobby");
		user1.setPassword(this.bCryptPasswordEncoder.encode("bobby"));
		user1.setRole("ROLE_EMPLOYEE");
		this.userDao.save(user1);

	}
}

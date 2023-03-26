package com.fundamentosprueba.springboot.fundamentos;

import com.fundamentosprueba.springboot.fundamentos.bean.MyBean;
import com.fundamentosprueba.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosprueba.springboot.fundamentos.bean.MyBeanWithDependencyImplement;
import com.fundamentosprueba.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosprueba.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosprueba.springboot.fundamentos.entity.User;
import com.fundamentosprueba.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosprueba.springboot.fundamentos.repository.UserRepository;
import com.fundamentosprueba.springboot.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;

	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemploAnteriores();
		saveUserInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}

	private void saveWithErrorTransactional(){
		User test1 = new User("TestTransactional1", "TestTransactional1@gmail.com", LocalDate.now());
		User test2 = new User("TestTransactional2", "TestTransactional2@gmail.com", LocalDate.now());
		User test3 = new User("TestTransactional3", "TestTransactional1@gmail.com", LocalDate.now());
		User test4 = new User("TestTransactional4", "TestTransactional4@gmail.com", LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3, test4);
		try {
			userService.saveTransactional(users);
		}catch (Exception e){
			LOGGER.error("Esta es una excepcion dentro del metodo transaccional "+e);
		}
		userService.getAllUsers().stream()
				.forEach(user -> LOGGER.info("Este es el usuario del metodo transaccional " + user));
	}

	private void getInformationJpqlFromUser(){
		/* LOGGER.info("Usuario con el metodo finByUserEmail" +
				userRepository.finByUserEmail("angel@gmail.com").
						orElseThrow(()-> new RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("user", Sort.by("id").ascending()).stream()
				.forEach(user->LOGGER.info("Usuario con metodo sort" + user));

		userRepository.findByName("Angel")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con query method " + user));

		LOGGER.info("Usuario con query method findByEmailAndName" + userRepository.
				findByEmailAndName("robert@gmail.com", "Robert")
				.orElseThrow(()->new RuntimeException("Usuario no encontrado")));

		userRepository.findByNameLike("%a%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameLike "+ user));

		userRepository.findByNameOrEmail("user9", null)
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail "+ user)); */

		userRepository.
				findByBirthDateBetween(LocalDate.of(2023, 03, 01),
				LocalDate.of(2023,06, 02))
				.stream()
				.forEach(user -> LOGGER.info("Usuario con intervalo de fechas: " + user));

		userRepository.findByNameContainingOrderByIdDesc("user")
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con containing y ordenado: " + user));

		LOGGER.info("El usuario a partir del named parameter es: " + userRepository.getAllByBirthDateAndEmail(LocalDate.of(2023, 05, 28),
						"noelia@gmail.com")
				.orElseThrow(()->
						new RuntimeException("No se encontro el usuario a partir del named parameter")));
	}

	private void saveUserInDataBase(){
		User user1 = new User("Angel","angel@gmail.com", LocalDate.of(2023, 11, 02));
		User user2 = new User("Robert","robert@gmail.com", LocalDate.of(2023, 10, 02));
		User user3 = new User("Noelia","noelia@gmail.com", LocalDate.of(2023, 05, 28));
		User user4 = new User("user4","user4@gmail.com", LocalDate.of(2023, 06, 03));
		User user5 = new User("user5","user5@gmail.com", LocalDate.of(2023, 10, 27));
		User user6 = new User("user6","user6@gmail.com", LocalDate.of(2023, 06, 02));
		User user7 = new User("user7","user7@gmail.com", LocalDate.of(2023, 07, 01));
		User user8 = new User("user8","user8@gmail.com", LocalDate.of(2023, 03, 24));
		User user9 = new User("user9","user9@gmail.com", LocalDate.of(2023, 04, 29));
		User user10 = new User("user10","user10@gmail.com", LocalDate.of(2023, 01, 24));
		User user11 = new User("user11","user11@gmail.com", LocalDate.of(2023, 07, 20));
		User user12 = new User("user12","user12@gmail.com", LocalDate.of(2023, 11, 15));
		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10,user11,user12);
		list.stream().forEach(userRepository::save);
	}

	private void ejemploAnteriores(){
		componentDependency.Saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail()+"-"+userPojo.getPassword());
		try{
			//error
			int value = 10/0;
			LOGGER.debug("Mi valor: " + value);
		}catch (Exception e){
			LOGGER.error("Esto es un error del al dividir por cero " + e.getMessage());
		}
	}
}

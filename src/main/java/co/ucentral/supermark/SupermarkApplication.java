package co.ucentral.supermark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD

@SpringBootApplication
=======
//import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@SpringBootApplication
//@Slf4j
>>>>>>> develop
public class SupermarkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupermarkApplication.class, args);
	}

}

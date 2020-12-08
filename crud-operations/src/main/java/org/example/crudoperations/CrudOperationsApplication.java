package org.example.crudoperations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class CrudOperationsApplication {

	public static void main(String[] args) {

			SpringApplication.run(CrudOperationsApplication.class, args);


	}

}

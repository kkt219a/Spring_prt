package springt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springs2Application {

	public static void main(String[] args) {
		// 스트링부트 애플리케이션, 톰캣 웹서버를 내장하고 있어서
		// 실행을 하게 된다.
		// gradle 통하지 않게 하면 빠르게 된다?
		
		SpringApplication.run(Springs2Application.class, args);
	}

}

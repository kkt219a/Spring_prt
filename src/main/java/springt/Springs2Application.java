package springt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springs2Application {

	public static void main(String[] args) {
//		 톰캣 웹서버를 내장하고 있어서
//		 저 함수 실행하면, 클래스 넣으면 위에 @통해 실행을 한다 띄우면서 톰캣 웹서버를 내장하고 있어서
//		 자체적으로 스트링부트 애플리케이션이 올라온다
		
		SpringApplication.run(Springs2Application.class, args);
	}

}

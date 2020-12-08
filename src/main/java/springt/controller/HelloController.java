package springt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//controller 명시
@Controller
public class HelloController {

	/**  TEST */
	// http://localhost:8080/hello
	// web application에서 '/hello'로 들어오면 이 method를 호출
	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data","hello!!");
		return "hello";
	}

	/**  MVC와 템플릿 엔진 */
	//http://localhost:8080/hello-mvc?name=spring
	//RequestParam이 get으로 받아온 것을 쓰겠다는 것이다.
	//@RequestParam("name")일 때, required의 default는 true다.
	//필수로 넣지 않으면 오류가 발생하는 것이다. 그래서 아래 소스처럼
	//required를 false를 주면 get의 값을 넣지 않아도 오류가 발생하지 않는다.
	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){
		//view에서 rendering할때 쓰기위해 model에 attr 추가
		model.addAttribute("name",name);
		//html 이동
		return "hello-template";
	}

	/**  API 1 */
	@GetMapping("hello-string")
	@ResponseBody // http에서 head, body부에 이 데이터를
	// 직접 넣어주겠다는 뜻이다!(html의 body와 다르다!)
	// 템플릿 엔진과의 차이는 그냥 view 이런게 없다. 이 문제가 그대로 내려감
	public String helloSpring(@RequestParam("name") String name){
		return "hello "+name;
	}

	/**  API 2 */
	@GetMapping("hello-api")
	@ResponseBody
	public Hello helloApi(@RequestParam("name") String name) {
		//참고: Intellij에서 ctrl+shift+enter 하면 자동완성 된다.
		Hello hello = new Hello();
		hello.setName(name);
		hello.setcall(13);
		return hello; // 객체를 넘겼네. ..
		// 오 json으로 되서 넘어온다


	}
	//static 선언시 클래스 내부에서도 사용 가능
	static class Hello {
		private String name;
		private int call;
		private int call2;

		//참고: alt+insert 하면 get set이 자동으로 나온다
		// java bean 표준 방식이라고 하고 property 접근 방식,
		// getter, setter 라고도 한다. 
		public int getcall() { return call; }
		public void setcall(int calls){ this.call=calls; }
		public String getName() { return name; }
		public void setName(String name) { this.name = name; }
	}



}

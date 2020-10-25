package springt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	/**  TEST */
	// http://localhost:8080/hello
	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data","hello!!");
		return "hello";
	}

	/**  MVC와 템플릿 엔진 */
	//http://localhost:8080/hello-mvc?name=spring
	@GetMapping("hello-mvc")
	// RequestParam이 곧 get이고 get으로 받아온 것을 쓰겠다.
	// @RequestParam("name")이 기본인데 required는 기본이
	// true이다 그래서 아래처럼 required를 false를 주면
	// get의 값을 넣지 않아도 오류 발생 x
	// 위의 기본상태에서 쓰면 name value 안주면 오류
	public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){
		//view에서 랜더링할때 쓰기위해 model에 attr 추가
		model.addAttribute("name",name);
		//이 html로 고고! template의!
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
		Hello hello = new Hello();
		hello.setName(name);
		hello.setcall(13);
		return hello; // 객체를 넘겼네. ..
		// 오 json으로 되서 넘어온다


	}
	static class Hello {
		private String name;
		private int call;
		private int call2;

		public int getcall() { return call; }
		public void setcall(int calls){ this.call=calls; }
		public String getName() { return name; }
		public void setName(String name) { this.name = name; }
	}



}

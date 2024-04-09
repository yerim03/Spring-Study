package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")    //웹 어플리케이션에서 '/hello'라고 들어오면 아래의 메소드를 호출
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";     //실행시킬 html을 지정-> resources/templates 안에서 hello.html을 찾아서 실행
    }


    //mvc-템플릿엔진 사용
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {    //외부에서 파라미터를 받는다.
        model.addAttribute("name", name);
        return "hello-template";
    }


    //api 사용 1
    @GetMapping("hello-string")
    @ResponseBody   //http의 응답 body부분에 반환값을 넣어주겠다는 의미
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }


    //api 사용 2
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   //hello 그대로를 내려줌
    }

    //Hello 객체 생성
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

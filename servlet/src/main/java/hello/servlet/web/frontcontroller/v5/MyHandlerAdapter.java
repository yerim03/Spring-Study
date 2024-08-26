package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface MyHandlerAdapter {

    //어댑터가 해당 컨트롤러를 처리할 수 있는지 판단하는 메서드
    boolean supports(Object handler);

    //핸들러(컨트롤러) 호출하고, ModelView 반환
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;


}

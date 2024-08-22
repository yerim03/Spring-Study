package hello.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//회원 등록 폼 - 컨트롤러
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //jsp로 이동
        String viewPath = "/WEB-INF/views/new-form.jsp";    //jsp 위치 경로
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);  //controller -> view(jsp)로 이동할 때 사용하는 메서드
        dispatcher.forward(request, response);  //다른 jsp(viewPath) 호출(request, response 전달)
    }
}

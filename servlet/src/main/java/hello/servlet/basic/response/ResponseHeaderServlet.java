package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[status-line]
        response.setStatus(HttpServletResponse.SC_OK);  //200

        //[response-headers]
        response.setHeader("Content-Type", "text/plain");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");    //임의의 헤더 생성 가능

        //[Header 편의 메서드]
        content(response);
        cookie(response);
        redirect(response);

        //http 응답 데이터
        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }

    //content 편의 메서드
    private void content(HttpServletResponse response) {
        /*
        * Content-Type: text/plain;charset=utf-8
        * Content-Length: 2
        * */
        //response.setHeader("Content-Type", "text/plain;charset=utf-8"); 와 같은 의미
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
//        response.setContentLength(2);   //생략 시 자동 생성
    }

    //쿠키 편의 메서드
    private void cookie(HttpServletResponse response) {
        /*
        * Set-Cookie: myCookie=good; Max-Age=600;
        * */
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600"); 와 같은 의미
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600);  //600초
        response.addCookie(cookie);
    }

    //redirect 편의 메서드
    private void redirect(HttpServletResponse response) throws IOException{
        /*
        * Status Code 302
        * Location: /basic/hello-form.html
        * */

//        response.setStatus(HttpServletResponse.SC_FOUND);
//        response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}

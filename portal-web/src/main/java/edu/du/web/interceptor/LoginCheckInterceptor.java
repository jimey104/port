package edu.du.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // preHandle : 사전에 조작하다.
        // 컨트롤러에 가기 전 Interceptor에서 캐치해서 다른 작업을 수행하게 보냄.
        // HttpServletRequest : 각종 클래스를 요청하게 해주는 메서드. 스프링에서는 굳이 안써도 다 잡아주지만 근본적으로는 이 메서드가 필수이다.

        // 사용자가 요청한 주소
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        // 세션 가져옴
        HttpSession session = request.getSession();
        // 세션에 로그인 정보가 있는지 확인
        if (session.getAttribute("account")==null) {
            // 미 로그인 상태
            // 로그인을 하지 않은 경우 바로 로그인페이지로 보내고, 로그인을 하면 요청한 화면을 보여줌.
            session.setAttribute("redirectURL", requestURI);
            response.sendRedirect("/account/login");
            return false;
        } else {
            // 로그인 상태
            return true;
        }
    }
}


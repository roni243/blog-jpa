package shop.mtcoding.blog._core.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import shop.mtcoding.blog._core.error.ex.Exception401;
import shop.mtcoding.blog._core.error.ex.ExceptionApi401;
import shop.mtcoding.blog.user.User;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();

        HttpSession session = request.getSession(); // req로 세션에 접근할 수 있는 함수 제공
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            //throw new Exception401("인증이 필요합니다");

            if (uri.contains("/api")) {
                throw new ExceptionApi401("인증 필요");
            } else {
                throw new Exception401("인증 필요");
            }
        }

        return true;
    }

}

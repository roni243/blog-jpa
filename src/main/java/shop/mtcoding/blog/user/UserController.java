package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO){
        User sessionUser = userService.로그인(loginDTO);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    @GetMapping("/login-form")
    public String loginForm(){
        session.setAttribute("metacoding", "apple");
        return "user/login-form";
    }

    @GetMapping("/join-form")
    public String joinForm(){
        String value = (String) session.getAttribute("metacoding");
        return "user/join-form";

    }

    @PostMapping("/join")
    public String join(@ModelAttribute UserRequest.JoinDTO joinDTO ){
        userService.회원가입(joinDTO);
        return "redirect:/login-form";
    }




}

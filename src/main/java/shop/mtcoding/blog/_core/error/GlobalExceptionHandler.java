package shop.mtcoding.blog._core.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.mtcoding.blog._core.error.ex.*;
import shop.mtcoding.blog._core.util.Resp;
import shop.mtcoding.blog._core.util.Script;


@RestControllerAdvice //데이터 리턴 @ControlllerAdvice 파일 리턴
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception400.class)
    public String ex400(Exception400 e) {
        return Script.back(e.getMessage());
    }

    @ExceptionHandler(ExceptionApi400.class)
    public Resp<?> exApi400(ExceptionApi401 e) {
        return Resp.fail(400, e.getMessage());
    }

    //인증 안됨
    @ExceptionHandler(Exception401.class)
    public String ex401(Exception401 e) {
        return Script.href(e.getMessage(), "/login-form");
    }

    @ExceptionHandler(ExceptionApi401.class)
    public Resp<?> exApi401(ExceptionApi401 e) {

        return Resp.fail(401, e.getMessage());
    }

    // 권한없음
    @ExceptionHandler(Exception403.class)
    public Resp<?> ex403(Exception403 e) {
        return Resp.fail(403, e.getMessage());
    }

    @ExceptionHandler(ExceptionApi403.class)
    public Resp<?> exApi403(ExceptionApi403 e) {

        return Resp.fail(403, e.getMessage());
    }

    @ExceptionHandler(Exception404.class)
    public String ex404(Exception404 e) {
        return Script.back(e.getMessage());
    }

    @ExceptionHandler(ExceptionApi404.class)
    public Resp<?> exApi404(ExceptionApi404 e) {

        return Resp.fail(404, e.getMessage());
    }

//    @ExceptionHandler(Exception.class)
//    public String exUnKnown(Exception e) {
//        String html = """
//                <script>
//                    alert('${msg}');
//                    history.back();
//                </script>
//                """.replace("${msg}", "관리자에게 문의해 주세요");
//        System.out.println("관리자님 보세요 :" + e.getMessage());
//        return html;
//    }

}

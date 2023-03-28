package hello.spring.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {

    /**
     * HTTP 요청 파라미터 V1
     * HttpServletRequest
     * 요청 파라미터 이름을 다르게 쓰고싶다면 애노테이션에 파라미터명을 입력하고 변수명을 다르게 입력한다.
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        response.getWriter().write("OK");
    }

    /**
     * HTTP 요청 파라미터 V2
     * @RequestParam
     * 요청 파라미터 이름을 다르게 쓰고싶다면 애노테이션에 파라미터명을 입력하고 변수명을 다르게 입력한다.
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);

        return "ok";
    }

    /**
     * HTTP 요청 파라미터 V3
     * @RequestParam
     * 요청 파라미터 이름과 같다면 ("파라미터명") 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    /**
     * HTTP 요청 파라미터 V4
     * @RequestParam
     * 요청 파라미터 이름과 같다면 애노테이션 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username,
                                 int age) {
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    /**
     * HTTP 요청 파라미터 필수
     * @RequestParam
     * required [true/false] 기본값 true시 파라미터 반드시 필요(없으면 오류)
     * false시 해당 파라미터가 없어도 통신 허용
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                 @RequestParam(required = false) Integer age) { //int는 null이 들어갈 수 없으므로 Integer로 변경
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    /**
     * HTTP 요청 파라미터 필수
     * @RequestParam
     * defaultValue : [age=] 빈문자 형태 혹은 파라미터 생략시 대신할 값
     * required = true일때 => 값 필수이며 값이 없다면 guest로 대체
     * required = false일때 => 값 생략 가능하며 값이 없다면 guest로 대체
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamRequired(@RequestParam(required = true, defaultValue = "guest") String username,
                                       @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username={}, age={}", username, age);

        return "ok";
    }
}

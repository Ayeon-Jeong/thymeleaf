package mvc2.thymeleaf.basic;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic/")
public class BasicController {

    @GetMapping("/text-basic")
    public String textBasic(Model model){
        model.addAttribute("data", "Hello <b>Spring</b>");
        return "basic/text-basic";
    }

    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model){
        model.addAttribute("data", "Hello <b>Spring</b>");
        return "basic/text-unescaped";
    }

    /* SpringEL */
    @GetMapping("/variable")
    public String variable(Model model){
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();

        list.add(userA);
        list.add(userB);

        Map map = new HashMap();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";

    }
    /* 기본 객체들 */
    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session){
        session.setAttribute("sessionData", "Hello Session");
        return "basic/basic-objects";
    }

    /* 날짜 유틸리티 */
    @GetMapping("/date")
    public String date(Model model){
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @Component("helloBean")
    static class Hellobean{
        public String hello(String data){
            return "Hello" + data;
        }
    }

    /* URL 링크 */
    @GetMapping("link")
    public String link(Model model){
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");

        return "basic/link";
    }

    @Data
    static class User{
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }
}

package shop.onekorea.powerapp.controller;

//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.onekorea.powerapp.dto.ResponseDto;
import shop.onekorea.powerapp.service.UserService;

// @CrossOrigin(originPatterns = "http://localhost:3000")
// 2023.07.26 Modified. 각각의 Controller에서 처리할 것이 아니라, Project.main() 파일, SpringBootApplication에서 바로 처리한다.

@RestController
@RequestMapping("/")
public class MainController {

    /// 2.  생성자 주입 방식으로 처리
    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String login() {

        System.out.println("=====> MainController.login...");

        return "login.mustache";

    }

}

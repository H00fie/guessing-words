package bm.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("home")
    public String landingPage(){
        return "home";
    }

    @PostMapping("start")
    public String startTheGamePage(){
        return "start";
    }

    @PostMapping("firstguess")
}

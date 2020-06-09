package bm.app.controller;


import bm.app.model.Guess;
import bm.app.service.GuessService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
@org.springframework.web.bind.annotation.RestController
@CrossOrigin
public class RestController {

    private GuessService guessService;

    public RestController(GuessService guessService){
        this.guessService = guessService;
    }

    @GetMapping("/home")
    public String getHomePage(){
        return "home";
    }

    @GetMapping("/records")
    public List<Guess> getAllRecords(){
        return guessService.selectAllRecords();
    }

//    @PostMapping("/insert")
//    public String insertARecord(@RequestParam Guess guess){
//        guessService.insert(guess);
//        return "inserted";
//    }


}

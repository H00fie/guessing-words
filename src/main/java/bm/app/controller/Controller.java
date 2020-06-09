package bm.app.controller;

import bm.app.model.Guess;
import bm.app.service.GuessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
public class Controller {

    private GuessService guessService;
    public Controller(GuessService guessService){
        this.guessService = guessService;
    }

    public int numberOfRounds = 0;

    @GetMapping("home")
    public String landingPage(){
        return "home";
    }

    @PostMapping("start")
    public String startTheGamePage(@RequestParam int rounds){
        this.numberOfRounds = rounds;
        return "start";
    }

    @PostMapping("firstword")
    public String firstGuessPage(@RequestParam String word,
                                 @RequestParam int number){

        Guess guess = new Guess();
        guess.setWord(word);
        guess.setNumber(number);
        guessService.insert(guess);
        if (this.numberOfRounds > 1) {
            this.numberOfRounds--;
            return "start";
        }else {
            return "firstword";
        }
    }
}

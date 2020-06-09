package bm.app.controller;

import bm.app.model.Guess;
import bm.app.service.GuessService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

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
                                 @RequestParam int number,
                                 Model model){

        Guess guess = new Guess();
        guess.setWord(word);
        guess.setNumber(number);
        guessService.insert(guess);
        if (this.numberOfRounds > 1) {
            this.numberOfRounds--;
            return "start";
        }else {
            Optional<String> selectedWord = guessService.selectWordByNumber();
            String resultWord = String.valueOf(selectedWord);
            model.addAttribute("randomword", resultWord);
            return "firstword";
        }
    }
}

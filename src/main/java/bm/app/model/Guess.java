package bm.app.model;

public class Guess {

    private int id;
    private String word;
    private int number;

    public Guess() {
    }

    public Guess(String word, int number){
        this.word = word;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

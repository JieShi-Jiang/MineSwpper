package entity.Hero;

import entity.Player;

public class AI extends Player {

    public AI() {
        this.userName = "null";
        this.HeroId = 15;
        this.score = Integer.parseInt("0");
        this.mistake = Integer.parseInt("0");
        this.scoreEachTime = Integer.parseInt("0");
        this.bigBrotherEye = Integer.parseInt("0");
        this.E = Integer.parseInt("0");
        this.S = Integer.parseInt("0");
    }

    public AI(String userName, String score, String mistake, String scoreEachTime,
                    String bigBrotherEye, String E, String S) {
        this.userName = "null";
        this.HeroId = 15;
        this.score = Integer.parseInt("0");
        this.mistake = Integer.parseInt("0");
        this.scoreEachTime = Integer.parseInt("0");
        this.bigBrotherEye = Integer.parseInt("0");
        this.E = Integer.parseInt("0");
        this.S = Integer.parseInt("0");
    }
    @Override
    public void negative(int x, int y) {

    }

    @Override
    public void positive(int x) {

    }
}

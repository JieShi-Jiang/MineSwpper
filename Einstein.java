package entity.Hero;

import components.GridComponent;
import entity.Player;

public class Einstein extends Player {
    //被动:有机会清空负分
    //主动:每回合有小概率将一部分负分改成错误,
    public Einstein() {
        super(16);
    }

    public Einstein(String userName, String score, String mistake, String scoreEachTime,
                    String bigBrotherEye, String E, String S) {
        this.userName = userName;
        this.HeroId = 16;
        this.score = Integer.parseInt(score);
        this.mistake = Integer.parseInt(mistake);
        this.scoreEachTime = Integer.parseInt(scoreEachTime);
        this.bigBrotherEye = Integer.parseInt(bigBrotherEye);
        this.E = Integer.parseInt(E);
        this.S = Integer.parseInt(S);
    }

    @Override
    public void negative(int x, int y) {
        if (ran.nextInt(11) > 8) {
            this.setScore(0);
            System.out.println("被动技能成功");
            GridComponent.music.run(4);
        }
    }

    @Override
    public void positive(int x) {
        if (ran.nextInt(11) > 4 && this.getScore() < 0) {//主动技能
            int medium = ran.nextInt(this.getScore());
            this.setScore(this.getScore() - medium);
            this.setMistake(this.getMistake() + medium);
            System.out.println("主动技能成功");
        }
    }
}

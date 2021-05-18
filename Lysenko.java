package entity.Hero;

import components.GridComponent;
import entity.Player;
//this is our AI player
//被动: ScoreEachTime>=2
//主动: 把对手分数加到自己的分数上
public class Lysenko extends Player {
    public Lysenko(){
        super(114514);
    }
    @Override
    public void negative(int x,int y) {//保证每次得分不少于2
        if(this.getScoreEachTime()==1){
            this.setScoreEachTime(1+this.getScoreEachTime());
            System.out.println("被动技能成功");
            GridComponent.music.run(7);
        }
    }

    @Override
    public void positive(int x) {//把对手分数加到自己的分数上
        if(ran.nextInt(11)>4){
            this.setScore(this.getScore()+x);
            System.out.println("主动技能成功");
        }
    }
}

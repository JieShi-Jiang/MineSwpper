package entity.Hero;

import components.GridComponent;
import entity.Player;
import minesweeper.MainFrame;

//被动: 每回合概率获得一个D道具
public class Maxwell extends Player {
    public Maxwell(){
        super(17);
    }

    public Maxwell(String userName,String score,String mistake,String scoreEachTime,
                   String bigBrotherEye,String E,String S){
        this.userName=userName;
        this.HeroId=17;
        this.score=Integer.parseInt(score);
        this.mistake=Integer.parseInt(mistake);
        this.scoreEachTime=Integer.parseInt(scoreEachTime);
        this.bigBrotherEye=Integer.parseInt(bigBrotherEye);
        this.E=Integer.parseInt(E);
        this.S=Integer.parseInt(S);
    }

    @Override
    public void negative(int x,int y) {
        if(ran.nextInt(11)>5){
            int a=this.getScoreEachTime();
            this.setScoreEachTime(a+1);
            System.out.println("被动技能成功");
            GridComponent.music.run(5);
        }
    }

    @Override
    public void positive(int x) {
        if(this.E>=1&&this.S>=1){
            this.setScore(this.getScore()+1);
            this.E--;this.S--;
            System.out.println("主动技能成功");
        }
    }
    //被动:每局可以概率增加每次得分1
    //主动:可以用一个E和S换一分
}


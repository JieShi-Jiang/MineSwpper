package entity.Hero;

import components.GridComponent;
import entity.Player;

public class Newton extends Player{
    //主动: 一定几率加4分，否则减一分
    //被动: 要是分数比对手低,就拉平; 否则自己加两分
    public Newton(){
        super(18);
    }

    public Newton(String userName,String score,String mistake,String scoreEachTime,
                  String bigBrotherEye,String E,String S){
        this.userName=userName;
        this.HeroId=18;
        this.score=Integer.parseInt(score);
        this.mistake=Integer.parseInt(mistake);
        this.scoreEachTime=Integer.parseInt(scoreEachTime);
        this.bigBrotherEye=Integer.parseInt(bigBrotherEye);
        this.E=Integer.parseInt(E);
        this.S=Integer.parseInt(S);
    }

    @Override

    public void negative(int x,int y) {//x,y对应对手和我
        if (ran.nextInt(11) > 4) {
            if (x > y) {
                this.setScore(x);//不够就拉平
                System.out.println("被动技能成功");
                GridComponent.music.run(5);
            } else {
                int a=this.getScore();
                this.setScore(a+1);
                GridComponent.music.run(6);}//多就加两分
        }
    }


    @Override
    public void positive(int x){
        int a=this.score;
        if (ran.nextInt(11) > 6) {
            System.out.println("主动技能成功");
            this.setScore(a+4);
        }else {this.setScore(a-1);
            System.out.println("主动技能失败");
        }

    }
}
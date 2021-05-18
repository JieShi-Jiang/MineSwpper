package entity;

import java.util.Random;

public abstract class Player {
    protected static Random ran = new Random();
    protected String userName="User#"+(ran.nextInt(9000)+1000);;
    protected int HeroId;
    protected int score = 0;
    protected int mistake = 0;//触雷以及标记错误
    public int mistakeCopy=-1;
    public int scoreEachTime=1;
    public int bigBrotherEye=0;
    public int E=0;//S
    public int S=0;//R

    /**
     * 通过特定名字初始化一个玩家对象。
     * @param userName 玩家的名字
     */
    public Player(String userName){
        this.userName = userName;
    }

    public Player(int HeroId){
        this.HeroId=HeroId;
    }

    public Player(String userName,int score,int mistake,int scoreEachTime,
                  int bigBrotherEye,int E,int S){
        this.userName=userName;
        this.score=score;
        this.mistake=mistake;
        this.scoreEachTime=scoreEachTime;
        this.bigBrotherEye=bigBrotherEye;
        this.E=E;
        this.S=S;
    }

    /**
     * 通过默认名字初始化一个玩家对象。
     */
    public Player(){
        //userName = "User#"+(ran.nextInt(9000)+1000);
    }

    public void setMistake(int mistake) {
        this.mistake = mistake;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setMistakeCopy(int mistakeCopy) {
        this.mistakeCopy = mistakeCopy;
    }

    public void setBigBrotherEye(int bigBrotherEye) {
        this.bigBrotherEye = bigBrotherEye;
    }

    public void setE(int e) {
        E = e;
    }

    public void setS(int s) {
        S = s;
    }

    /**
     * 为玩家加一分。
     */
    public void addScore(){
        score=score+scoreEachTime;
    }

    public void setScoreEachTime(int scoreEachTime) {
        if(scoreEachTime<=0){
            this.scoreEachTime=1;//保证最低得分
        }else {
        this.scoreEachTime = scoreEachTime;
        }
    }

    public int getScoreEachTime() {
        return scoreEachTime;
    }

    /**
     * 为玩家扣一分。
     */
    public void costScore(){
        score--;mistake++;
    }

    /**
     * 为玩家增加一次失误数。
     */
    public void addMistake() { mistake++;}

    public int getScore(){
        return score;
    }

    public String getUserName(){ return userName; }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getMistake(){ return mistake; }

    public void addeyes(){bigBrotherEye++;}

    public int getBigBrotherEye(){return bigBrotherEye;}

    public void useEyes(){
        bigBrotherEye--;
    }

    public abstract void negative(int x,int y);

    public abstract void positive(int x);

    public int getE(){
        return this.E;
    }

    public int getS(){
        return this.S;
    }

    public int getHeroId() {
        return HeroId;
    }

    //密码道具
}

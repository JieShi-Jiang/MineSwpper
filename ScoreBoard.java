package minesweeper;

import components.GridComponent;
import controller.GameController;
import entity.Player;

import javax.swing.*;

/**
 * 此类的对象是一个计分板容器，通过传入玩家对象，
 * 可以用update()方法实时更新玩家的分数以及失误数。
 */
public class ScoreBoard extends JPanel {

    Player p1;
    Player p2;
    JLabel CurrMines=new JLabel();
    JLabel score1 = new JLabel();
    JLabel score1trea= new JLabel();
    JLabel score2 = new JLabel();
    JLabel score2trea=new JLabel();

    /**
     * 通过进行游戏的玩家来初始化计分板。这里只考虑了两个玩家的情况。
     * 如果想要2-4人游戏甚至更多，请自行修改(建议把所有玩家存在ArrayList)~
     *
     * @param p1 玩家1
     * @param p2 玩家2
     */
    public ScoreBoard(Player p1, Player p2, int xCount, int yCount) {
        //this.add(new JLabel("Score Board -     mines left: "+MainFrame.getCurrMineCount()));

        this.setSize(yCount * GridComponent.gridSize+100, 80);
        this.setLocation(0, xCount * GridComponent.gridSize);

        this.p1 = p1;
        this.p2 = p2;
        this.add(CurrMines);
        this.add(score1);
        this.add(score1trea);
        this.add(score2);
        this.add(score2trea);

        this.setLayout(new BoxLayout(this, 1));
        update();
    }

    /**
     * 刷新计分板的数据。
     * 计分板会自动重新获取玩家的分数，并更新显示。
     */
    public void update() {
        String xx="";
        String yy="";
        if(MainFrame.controller.getOnTurnPlayer().equals(p1)) {
            xx=" ***onTurn"+(GameController.counter);
        }else if(MainFrame.controller.getOnTurnPlayer().equals(p2)){
            yy=" ***onTurn"+(GameController.counter);
        }
        CurrMines.setText("Score Board -     mines left: "+MainFrame.getCurrMineCount());
        score1.setText(String.format("%s(%s): %d score (+ %d mis), "+p1.getBigBrotherEye()+" eyes left. \n"
                        +xx
                ,p1.getUserName(),Hero(p1),p1.getScore(),p1.getMistake()));
        score1trea.setText("Score "+p1.scoreEachTime+" each time. "+p1.E+" eras left. "+p1.S+" swaps left. ");
        score2.setText(String.format("%s(%s): %d score (+ %d mis), "+p2.getBigBrotherEye()+" eyes left. \n"
                        +yy
                ,p2.getUserName(),Hero(p2),p2.getScore(),p2.getMistake()));
        score2trea.setText("Score "+p2.scoreEachTime+" each time. "+p2.E+" eras left. "+p2.S+" swaps left. ");
    }
    public String Hero(Player p){
        switch (p.getHeroId()){
            case 15:return "null";
            case 18:return "Newton";
            case 17:return "Maxwell";
            case 16:return "Einstein";
            case 114514:return "Lysenco";
            default:return "Newton";
        }
    }
}

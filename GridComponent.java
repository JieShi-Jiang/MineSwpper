package components;

import controller.GameController;
import entity.GridStatus;
import minesweeper.GamePanel;
import minesweeper.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class GridComponent extends BasicComponent {//一个单独的网格
    public static int gridSize = 30;
    private int row;
    private int col;
    private GridStatus status = GridStatus.Covered;
    private int content = 0;//雷、数字、道具
    private static int bonusCounter=0;//计随机道具
    private static int thisTime=10;//获得道具本次需要点击的次数
    Random random=new Random();
    private static boolean first=true;
    public static Music music=new Music();

    public GridComponent(int x, int y) {
        this.setSize(gridSize, gridSize);
        this.row = x;
        this.col = y;
    }

    public GridComponent(int x, int y,GridStatus status) {
        this.setSize(gridSize, gridSize);
        this.row = x;
        this.col = y;
        this.status=status;
        this.repaint();
        repaint();
    }

    @Override
    public void onMouseLeftClicked() throws IOException {
        MainFrame.controller.getScoreBoard().update();
        if(first&&GamePanel.getfirstChessBoard(row,col)==-1){
        }else {
            System.out.printf("Gird (%d,%d) is left-clicked.\n", row, col);
            if (this.status == GridStatus.Covered) {
            this.status = GridStatus.Clicked;
            //if{扣分，放音乐}和计数器联系
            if(this.content==-1){//触雷&&!firstTime
                MainFrame.controller.getOnTurnPlayer().costScore();
                music.run(-1);
                MainFrame.setCurrMineCount(MainFrame.getCurrMineCount()-1);
            //音乐
            }else {
                bonusCounter++;
                }
            }//放音乐
            if(bonusCounter==thisTime){//第某次避开雷的玩家会得到奖励道具
                music.run(0);
                bonusCounter=0;thisTime= random.nextInt(8)+4;
                System.out.println(MainFrame.controller.getOnTurnPlayer().getUserName()+" get a Big-brother's eye");
                MainFrame.controller.getOnTurnPlayer().bigBrotherEye++;//给一个道具
            }else if(this.content!=-1&&this.content!=0&&this.content!=500&&this.content!=1000&&this.content!=9000&&this.content!=10000){
                music.run(1);
            }

            if(this.content==10000){
                music.run(0);
                MainFrame.controller.getOnTurnPlayer().E++;
                System.out.println(MainFrame.controller.getOnTurnPlayer().getUserName()+" get a chance to erasure mistake!");
            }if(this.content==9000){
                music.run(0);
                MainFrame.controller.getOnTurnPlayer().S++;
                System.out.println(MainFrame.controller.getAnotherPlayer().getUserName()+" ,watch out! Your mistake might be swapped!");
            }if(this.content==500){
                music.run(2);
                System.out.println(MainFrame.controller.getOnTurnPlayer().getUserName()+", you can add your store each time!");
                MainFrame.controller.getOnTurnPlayer().setScoreEachTime(MainFrame.controller.getOnTurnPlayer().scoreEachTime+1);
            }if(this.content==1000){
                music.run(2);
                System.out.println(MainFrame.controller.getOnTurnPlayer().getUserName()+", subtract your competitor's store each time!");
                MainFrame.controller.getAnotherPlayer().setScoreEachTime(MainFrame.controller.getAnotherPlayer().scoreEachTime-1);

            }recursion(MainFrame.controller.getGamePanel().getMineField(),
                    MainFrame.getController().getGamePanel().getChessboard(),
                    row,col);
        GameController.counter++;MainFrame.controller.getScoreBoard().update();
            if(GameController.counter==GameController.times){
            MainFrame.controller.nextTurn();
            }repaint();first=false;
            if(MainFrame.getCurrMineCount()==0){
                MainFrame.controller.nextTurn();
            }
        }}
    //TODO: 在左键点击一个格子的时候，还需要做什么？


    @Override
    public void onMouseRightClicked() throws IOException {
        MainFrame.controller.getScoreBoard().update();
        System.out.printf("Gird (%d,%d) is right-clicked.\n", row, col);
            if (this.status == GridStatus.Covered) {
                this.status=GridStatus.Clicked;
                if(this.content==-1){
                    System.out.println("Good Job!");
                    this.status = GridStatus.Flag;
                    MainFrame.controller.getOnTurnPlayer().addScore();
                    MainFrame.setCurrMineCount(MainFrame.getCurrMineCount()-1);
                    music.run(1);
                }else {
                    System.out.println("Wrong!");
                    MainFrame.controller.getOnTurnPlayer().addMistake();
                }
                recursion(MainFrame.controller.getGamePanel().getMineField(),
                        MainFrame.getController().getGamePanel().getChessboard(),
                        row,col);
                repaint();
            }
            GameController.counter++;MainFrame.controller.getScoreBoard().update();
        if(GameController.counter==GameController.times){
            repaint();MainFrame.controller.nextTurn();
        }repaint();
        if(MainFrame.getCurrMineCount()==0){
            MainFrame.controller.nextTurn();
        }
        //TODO: 在右键点击一个格子的时候，还需要做什么？
    }

    public void onMouseMidClicked(){
        if(this.status == GridStatus.Covered) {
            if (MainFrame.controller.getOnTurnPlayer().bigBrotherEye > 0) {
                MainFrame.controller.getOnTurnPlayer().useEyes();
                MainFrame.controller.getScoreBoard().update();
                if (content == -1) {
                    music.run(1);
                    JOptionPane.showMessageDialog(this,"Wow, you discover a mine! ");
                    }
                else if (content <= 9) {
                    JOptionPane.showMessageDialog(this,MainFrame.controller.getOnTurnPlayer().getUserName() + " uses a big-brother's eye.\n "+"There are "+content+" mines around. " );
                }else{
                    music.run(3);
                    JOptionPane.showMessageDialog(this,"Wow, you discover a treasure! ");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,MainFrame.controller.getOnTurnPlayer().getUserName() + " don't have enough eyes!");
        }
    }

    public void draw(Graphics g) {

        if (this.status == GridStatus.Covered) {
            g.setColor(Color.ORANGE);
            g.fillRect(0, 0, getWidth()-1, getHeight() - 1);
        }
        if (this.status == GridStatus.Clicked) {

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth()-1, getHeight() - 1);
            String xx="";
            switch (content){
                case -1:g.setColor(Color.RED);
                    g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
                    g.setColor(Color.YELLOW);xx="M";break;
                case 0:g.setColor(Color.white);xx="";break;
                case 1:g.setColor(Color.green);xx="1";break;
                case 2:g.setColor(Color.CYAN);xx="2";break;
                case 3:g.setColor(Color.blue);xx="3";break;
                case 4:g.setColor(Color.MAGENTA);xx="4";break;
                case 5:g.setColor(Color.black);xx="5";break;
                case 6:g.setColor(Color.black);xx="6";break;
                case 500:
                    g.setColor(Color.YELLOW);
                    g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
                    g.setColor(Color.black);xx="D";break;//给一个积分翻倍的道具
                case 1000:
                    g.setColor(Color.YELLOW);
                    g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
                    g.setColor(Color.black);xx="B";break;//给一个取消积分翻倍的道具
                case 9000:
                    g.setColor(Color.YELLOW);
                    g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
                    g.setColor(Color.black);xx="S";break;//给一个恢复对方错误数量的道具
                case 10000:
                    g.setColor(Color.YELLOW);
                    g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
                    g.setColor(Color.black);xx="E";break;//给一个限定数量的擦除错误的道具
                default:g.setColor(Color.black);xx=Integer.toString(content);
            }
            g.drawString(xx, getWidth() / 2 - 5, getHeight() / 2 + 5);
        }
        if (this.status == GridStatus.Flag) {

            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.setColor(Color.RED);
            g.drawString("F", getWidth() / 2 - 5, getHeight() / 2 + 5);
        }
    }

    public void setContent(int content) {
        this.content = content;
    }

    public void setStatus(GridStatus status) {
        this.status = status;
    }

    public static void setFirst(boolean first) {
        GridComponent.first = first;
    }

    public GridStatus getStatus() {
        return status;
    }

    public static void recursion(GridComponent[][] mineField,int[][]Chessboard,int row,int col){
        if(mineField[row][col].status.equals(GridStatus.Clicked)){//如果被点开
            if(row-1>=0&&row+1<mineField.length&&col+1<mineField[0].length&&col-1>=0){//如果不越界
                if(Chessboard[row+1][col+1]==0){//如果是空白才能执行
                    for (int i = row-1; i <=row+1 ; i++) {//对周边的九宫格执行
                        for (int j = col-1; j <=col+1 ; j++) {
                            if(mineField[i][j].status.equals(GridStatus.Covered)&&Chessboard[i+1][j+1]!=-1//如果不是雷或者道具
                                    &&Chessboard[i+1][j+1]!=500&&Chessboard[i+1][j+1]!=1000&&Chessboard[i+1][j+1]!=9000
                                    &&Chessboard[i+1][j+1]!=10000){//盖住的不是雷就打开
                                mineField[i][j].status=GridStatus.Clicked;mineField[i][j].repaint();
                                recursion(mineField, Chessboard, i, j);
                            }
                        }
                    }
                }
            }
        }
    }

                  /*

    if(mineField[row][col+1].status.equals(GridStatus.Covered)&&Chessboard[row][col+1]!=-1//如果不是雷或者道具
                            &&Chessboard[row][col+1]!=500&&Chessboard[row][col+1]!=1000&&Chessboard[row][col+1]!=9000
                            &&Chessboard[row][col+1]!=10000){//盖住的不是雷就打开
                        mineField[row][col+1].status=GridStatus.Clicked;mineField[row][col+1].repaint();
                        recursion(mineField, Chessboard,row,col+1);
                    }
                    if(mineField[row][col-1].status.equals(GridStatus.Covered)&&Chessboard[row][col-1]!=-1//如果不是雷或者道具
                            &&Chessboard[row][col-1]!=500&&Chessboard[row][col+1]!=1000&&Chessboard[row][col-1]!=9000
                            &&Chessboard[row][col-1]!=10000){//盖住的不是雷就打开
                        mineField[row][col-1].status=GridStatus.Clicked;mineField[row][col-1].repaint();
                        recursion(mineField, Chessboard,row,col-1);
                    }
                    if(mineField[row+1][col].status.equals(GridStatus.Covered)&&Chessboard[row+1][col]!=-1//如果不是雷或者道具
                            &&Chessboard[row+1][col]!=500&&Chessboard[row+1][col]!=1000&&Chessboard[row+1][col]!=9000
                            &&Chessboard[row+1][col]!=10000){//盖住的不是雷就打开
                        mineField[row+1][col].status=GridStatus.Clicked;mineField[row+1][col].repaint();
                        recursion(mineField, Chessboard,row+1,col);
                    }
                    if(mineField[row-1][col].status.equals(GridStatus.Covered)&&Chessboard[row-1][col]!=-1//如果不是雷或者道具
                            &&Chessboard[row-1][col]!=500&&Chessboard[row-1][col]!=1000&&Chessboard[row-1][col]!=9000
                            &&Chessboard[row-1][col]!=10000){//盖住的不是雷就打开
                        mineField[row-1][col].status=GridStatus.Clicked;mineField[row-1][col].repaint();
                        recursion(mineField, Chessboard,row-1,col);
                    }*/
    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        draw(g);
    }
}

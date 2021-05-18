package controller;

import minesweeper.*;
import entity.Player;

import java.io.IOException;
import java.util.Timer;

public class GameController {
    private MainFrame MainFrame1;
    public int positive=1;
    private Player p1;
    private Player p2;
    public static int counter = 0;
    public static int times = 5;
    private Player onTurn;
    private GamePanel gamePanel;
    private ScoreBoard scoreBoard;
    private TimeBorad timeBorad;
    public static MyTimer Mytimer = new MyTimer();

    public GameController(Player p1, Player p2, MainFrame mainFrame) {
        this.init(p1, p2);
        this.onTurn = p1;
        MainFrame1 = mainFrame;
    }

    public GameController(Player p1, Player p2, String[] mineField, String[] chessboard
            , int Xcount, int Ycount, MainFrame mainFrame, int mineCount, int E, int S, int D, int B) {
        this.init(p1, p2);
        this.onTurn = p1;
        MainFrame1 = mainFrame;
        GamePanel gamePanel = new GamePanel(Xcount, Ycount, mineCount,
                E, S, D, B, mineField, chessboard);
        gamePanel.repaint();
        this.gamePanel = gamePanel;
    }

    /**
     * 初始化游戏。在开始游戏前，应先调用此方法，给予游戏必要的参数。
     *
     * @param p1 玩家1
     * @param p2 玩家2
     */
    public void init(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.onTurn = p1;
        //TODO: 在初始化游戏的时候，还需要做什么？
    }

    /**
     * 进行下一个回合时应调用本方法。
     * 在这里执行每个回合结束时需要进行的操作。
     * <p>
     * (目前这里没有每个玩家进行n回合的计数机制的，请自行修改完成哦~）
     */
    public void nextTurn() throws IOException {
        if (onTurn == p1) {
            onTurn = p2;
        } else if (onTurn == p2) {
            onTurn = p1;
        }
        counter = 0;
        //TODO: 在每个回合结束的时候，还需要做什么 (例如...检查游戏是否结束？)
        //每次都重新开始计时
        if(getOnTurnPlayer().getHeroId()==15){
            nextTurn();
        }
        this.getOnTurnPlayer().negative(getOnTurnPlayer().getScore(),getAnotherPlayer().getScore());
        this.positive=1;
        EndTheGame.endTheGame(this.p1, this.p2, this.MainFrame1);
        MainFrame.controller.getScoreBoard().update();
        System.out.println("Now it is " + MainFrame.controller.getOnTurnPlayer().getUserName() + "'s turn.");
    }


    /**
     * 获取正在进行当前回合的玩家。
     *
     * @return 正在进行当前回合的玩家
     */
    public Player getOnTurnPlayer() {
        if (onTurn.equals(p1)) {
            return p1;
        } else return p2;
    }

    public Player getAnotherPlayer() {
        if (onTurn.equals(p1)) {
            return p2;
        } else return p1;
    }


    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public int getCurrTime() {
        return Mytimer.getCurrTime();
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void deleteGamePanel() {
        this.gamePanel = null;
    }

    public void setScoreBoard(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public void setTimeBorad(TimeBorad timeBorad) {
        this.timeBorad = timeBorad;
    }

    public TimeBorad getTimeBorad() {
        return timeBorad;
    }

    public void setTimer(MyTimer timer) {
        this.Mytimer = timer;
    }

    public void readFileData(String fileName) {
        //todo: read date from file

    }

    public void writeDataToFile(String fileName) {
        //todo: write data into file
    }

}

package minesweeper;

import components.GridComponent;
import controller.GameController;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeBorad extends JPanel {
    JPanel time=new JPanel();
    JLabel timeLeft=new JLabel();
    Timer timer = new Timer();

    public TimeBorad(int yCount){
        this.add(new JLabel("TimeBoard - "));
        this.setLocation(yCount * GridComponent.gridSize,0 );//yCount * GridComponent.gridSize
        this.add(time);
        this.setLayout(new BoxLayout(this, 1));
        this.setVisible(true);
        this.setBackground(Color.black);
        update();
        timeLeft.setBackground(Color.WHITE);
        timeLeft.setSize(120, 50);
        timeLeft.setVisible(true);
        add(timeLeft);
        update();
        //GameController.timer.start();
        //System.out.println(GameController.timer.getCurrTime());//如何在界面打印时间？
        //while (GameController.timer.getCurrTime()==0){//到时就下一个
        //MainFrame.controller.nextTurn();
        //System.out.println("time over");}
    }

    private void update() {
        String xx=String.valueOf(MainFrame.controller.getCurrTime());
        timeLeft.setText(MainFrame.controller.getOnTurnPlayer().getUserName()+" has "+xx+" Seconds left.");
        timer.schedule(new Task(), 1000l,1000l);
        //update();
    }
}

class Task extends TimerTask {
    @Override
    public void run() {
        //每隔sleepTime，输出一次当前时间
    }
}




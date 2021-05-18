package minesweeper;

import components.GridComponent;
import controller.GameController;
import entity.GridStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GamePanel extends JPanel {
    private GridComponent[][] mineField;//对应状态
    private int[][] chessboard;//有多少雷
    private static int[][]Chessboard;
    private final Random random = new Random();

        public GridComponent[][] readMineField(String[] mineField,int Xcount,int Ycount) {
            GridComponent[][] NewMineField = new GridComponent[Xcount][Ycount];
            for (int i = 0; i <Xcount ; i++) {
                String[] everyLine=mineField[i].split(",");
                for (int j = 0; j <Ycount ; j++) {
                    switch (everyLine[j]){
                        case "0":
                            NewMineField[i][j]=new GridComponent(i,j,GridStatus.Covered);
                            break;
                        case "1":
                            NewMineField[i][j]=new GridComponent(i,j,GridStatus.Flag);
                            break;
                        case "-1":
                            NewMineField[i][j]=new GridComponent(i,j,GridStatus.Clicked);
                            break;
                    }
                    repaint();
                }
            }
            return NewMineField;
        }

        public int[][] readChessboard(String[] chessboard,int Xcount,int Ycount) {
            int[][] NewChessboard=new int[Xcount+2][Ycount+2];
            for (int i = 1; i <Xcount+1 ; i++) {
                String[] everyLine=chessboard[i-1].split(",");
                for (int j = 1; j <Ycount+1 ; j++) {
                    NewChessboard[i][j]=Integer.parseInt(everyLine[j-1]);
                }
            }
            return NewChessboard;
        }


    /*
      初始化一个具有指定行列数格子、并埋放了指定雷数的雷区。

      @param xCount    count of grid in column
      @param yCount    count of grid in row
      @param mineCount mine count
     */

    public GamePanel(){}

    public void setGamePanel(int xCount, int yCount, int mineCount,int E,int S,int D,int B){
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setSize(GridComponent.gridSize * yCount, GridComponent.gridSize * xCount);
        initialGame(xCount, yCount, mineCount,E,S,D,B);
        repaint();
    }

    public GamePanel(int xCount, int yCount, int mineCount,int E,int S,int D,int B) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setSize(GridComponent.gridSize * yCount, GridComponent.gridSize * xCount);
        mineField = new GridComponent[xCount][yCount];
        initialGame(xCount, yCount, mineCount,E,S,D,B);
        repaint();
    }

    public GamePanel(int xCount, int yCount, int mineCount,int E,int S,int D,int B,
                     String[] mineField,String[] chessboard) {
        Chessboard=new int[xCount+2][yCount+2];
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setSize(GridComponent.gridSize * yCount, GridComponent.gridSize * xCount);
        this.mineField = readMineField(mineField,xCount,yCount);
        this.chessboard= readChessboard(chessboard,xCount,yCount);
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                GridComponent gridComponent = this.mineField[i][j];
                gridComponent.setContent(this.chessboard[i+1][j+1]);//重置棋盘
                gridComponent.setLocation(j * GridComponent.gridSize, i * GridComponent.gridSize);
                this.mineField[i][j] = gridComponent;
                this.add(this.mineField[i][j]);
            }
        }
        repaint();
        for (int i = 0; i <= xCount+1; i++) {
            for (int j = 0; j <= yCount+1; j++) {
                Chessboard[i][j]=this.chessboard[i][j];
            }
        }
    }

    public void initialGame(int xCount, int yCount, int mineCount,int E,int S,int D,int B) {
        generateChessBoard(xCount, yCount, mineCount,E,S,D,B);//生成地雷
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                GridComponent gridComponent = new GridComponent(i, j);
                gridComponent.setContent(chessboard[i+1][j+1]);//重置棋盘
                gridComponent.setLocation(j * GridComponent.gridSize, i * GridComponent.gridSize);
                mineField[i][j] = gridComponent;
                this.add(mineField[i][j]);
            }
        }
    }

    public void setTheMineField(int xCount, int yCount,int count,int treasure){
        while(count-- != 0) {
            int x = random.nextInt(xCount) + 1;
            int y = random.nextInt(yCount) + 1;
            //这里x和y的位置
            if (chessboard[x][y]!=0) {//只能在空白处生成
                count++;
                continue;
            }
            chessboard[x][y] = treasure;
            //写一个生成道具的程序
        }
    }

    public void setTheMineField(int xCount, int yCount,int count){
        while(count-- != 0) {
            int x = random.nextInt(xCount) + 1;
            int y = random.nextInt(yCount) + 1;
            //这里x和y的位置
            if (chessboard[x][y]!=-1) {//只能在空白处生成
                count++;
                continue;
            }
            chessboard[x][y] = -1;
            //写一个生成地雷的程序
        }
        for (int i = 1; i <=xCount; i++) {
            for (int j = 1; j <=yCount; j++){
                if(chessboard[i][j]!=-1){
                    chessboard[i][j]=0;//如果不是雷，就把他归零
                }
            }
        }//初始化，-1是雷，0是空
    }

    public boolean checkTheMineField(int xCount, int yCount){
        for (int i = 1; i <=xCount; i++) {
            for (int j = 1; j <=yCount; j++) {
                if(chessboard[i][j]==-1){//如果是雷
                    int counter=0;
                    for (int k = i-1; k<=i+1 ; k++) {
                        for (int l = j-1; l <=j+1; l++) {
                            if(chessboard[k][l]==-1){
                                counter++;//as3的数雷的算法
                            }
                        }
                    }if(counter==9){
                        return false;
                    }
                }
            }
        }return true;
    }

    public void ComputeMindNumber(int xCount, int yCount){
        for (int i = 1; i <=xCount; i++) {
            for (int j = 1; j <=yCount; j++) {
                if(chessboard[i][j]!=-1&&
                        chessboard[i][j]!=10000&&chessboard[i][j]!=9000&&
                        chessboard[i][j]!=1000&&chessboard[i][j]!=500){//如果不是雷和道具
                    int counter=0;//道具周围的地雷数不会显示，得到道具也不要太得意哦！
                    for (int k = i-1; k<=i+1 ; k++) {
                        for (int l = j-1; l <=j+1; l++) {
                            if(chessboard[k][l]==-1){
                                counter++;//as3的数雷的算法
                            }
                        }
                    }chessboard[i][j]=counter;
                }
            }
        }
    }

    public void generateChessBoard(int xCount, int yCount, int mineCount,int E,int S,int D,int B) {
        //todo: generate chessboard by your own algorithm
        chessboard = new int[xCount + 2][yCount + 2];//非静态
        Chessboard = new int[xCount + 2][yCount + 2];//静态拷贝
        /*int Xfirst=0;
        int Yfirst=0;
        boolean first=true;
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {//有没有点击
                super.mouseClicked(e);
                if(e.getButton()==1){//左键单击
                    //把点击的坐标返回，
                    //Xfirst=row;
                    //YFirst=col;
                }
            }

        });
        chessboard[Xfirst+1][Yfirst+1]=0;//对返回的mineField首次点击的坐标，认为没有地雷

         */
        do{
            while (mineCount-- != 0) {
                int x = random.nextInt(xCount) + 1;
                int y = random.nextInt(yCount) + 1;
                //这里x和y的位置
                if (chessboard[x][y] == -1) {//也避免传进来的坐标是雷
                    mineCount++;
                    continue;
                }
                //设置为有雷
                chessboard[x][y] = -1;
                //写一个生成雷的程序
            }
            for (int i = 1; i <= xCount; i++) {
                for (int j = 1; j <= yCount; j++) {
                    if (chessboard[i][j] != -1) {
                        chessboard[i][j] = 0;//如果不是雷，就把他归零
                    }
                }
            }//初始化，-1是雷，0是空
        }while (!checkTheMineField(xCount, yCount));
        setTheMineField(xCount, yCount, E, 10000);
        setTheMineField(xCount, yCount, S, 9000);
        setTheMineField(xCount, yCount, D, 500);
        setTheMineField(xCount, yCount, B, 1000);
        ComputeMindNumber(xCount,yCount);


        /*for (int i = 0; i <= xCount+1; i++) {
            for (int j = 0; j <= yCount+1; j++) {
                Chessboard[i][j]=chessboard[i][j];
                }
            }*/
        Chessboard=chessboard;
        }


    public GridStatus getGridstatus(int x, int y) {
        try {
            return mineField[x][y].getStatus();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getChessBoard(int x,int y){
        try {
            return Integer.toString(chessboard[x][y]);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getfirstChessBoard(int x,int y){
        try {
            return Chessboard[x+1][y+1];
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public GridComponent[][] getMineField() {
        return mineField;
    }

    public int[][] getChessboard() {
        return chessboard;
    }

    public GridComponent getMineField(int i, int j) {
        return mineField[i][j];
    }

    public void setGridStatus(int x, int y, GridStatus g) {
        try {
            mineField[x][y].setStatus(g);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
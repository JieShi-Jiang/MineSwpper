package minesweeper;
import DisplayPanel.Menu;
import components.GridComponent;
import components.Music;
import controller.GameController;
import entity.GridStatus;
import entity.Hero.AI;
import entity.Hero.Einstein;
import entity.Hero.Maxwell;
import entity.Hero.Newton;
import entity.Player;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MainFrame extends JFrame {
    public File file;
    public static GameController controller;
    public static boolean play = true;
    private int xCount;
    private int yCount;
    private int mineCount;//这个是初始设置的地雷数
    private static int currMineCount;//这个是现在剩下多少地雷
    private int E;
    private int S;
    private int D;
    private int B;


    public MainFrame() {}

    public static GameController getController() {
        return controller;
    }

    public int getMineCount() {
        return mineCount;
    }

    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }

    public static int getCurrMineCount() {
        return currMineCount;
    }

    public static void setCurrMineCount(int currMineCount) {
        MainFrame.currMineCount = currMineCount;
    }

    public int getE() {
        return E;
    }

    public MainFrame(int xCount, int yCount, int mineCount, int E, int S, int D, int B, Player p1, Player p2) {
        //todo: change the count of xCount, yCount and mineCount by passing parameters from constructor,done!
        this.xCount = xCount;//grid of row
        this.yCount = yCount;// grid of column
        this.mineCount = mineCount;// mine count
        currMineCount = mineCount;
        this.E = E;
        this.S = S;
        this.D = D;
        this.B = B;

        this.setTitle("2021 CS102A Project");
        this.setLayout(null);//??////////////////////////////////
        this.setSize(yCount * GridComponent.gridSize + 100, xCount * GridComponent.gridSize + 240);//给多横向100像素
        this.setLocationRelativeTo(null);//??
        controller = new GameController(p1, p2, this);
        ScoreBoard scoreBoard = new ScoreBoard(p1, p2, xCount, yCount);
        scoreBoard.setOpaque(false);
        GamePanel gamePanel = new GamePanel(xCount, yCount, mineCount, E, S, D, B);
        TimeBorad timeBorad = new TimeBorad(yCount);
        controller.setScoreBoard(scoreBoard);
        controller.setGamePanel(gamePanel);
        controller.setTimeBorad(timeBorad);
        this.add(gamePanel);
        this.add(scoreBoard);
        this.add(timeBorad);

        ImageIcon icon=new ImageIcon("C:\\Users\\zhouyy\\Desktop\\project\\essay\\新建文件夹\\微信图片_20210517001334.jpg");
        icon.setImage(icon.getImage().getScaledInstance(350+710,710+500,Image.SCALE_DEFAULT));
        JLabel label=new JLabel(icon);
        label.setBounds(-710,-710,2000+350,2000+500);
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        JPanel g=(JPanel)this.getContentPane();
        g.setOpaque(false);
        JPanel panel=new JPanel();
        //panel.setSize(350,500);

        String component= JOptionPane.showInputDialog(this, "input times of one round");
        try {
            if(Integer.parseInt(component)<=5&&Integer.parseInt(component)>=1){
                GameController.times=Integer.parseInt(component);
            }else {
                JOptionPane.showMessageDialog(this,"Invalid input, set default 5 times!");
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Invalid input, set default 5 times!");
        }catch (ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(this,"Invalid input, set default 5 times!");
        }

        JButton era = new JButton("erasure");//擦除错误
        era.setSize(90, 20);
        era.setLocation(90, gamePanel.getHeight() + scoreBoard.getHeight());
        add(era);
        era.addActionListener(f -> {
            if (controller.getOnTurnPlayer().E >= 1) {
                controller.getOnTurnPlayer().E--;//使用机会
                controller.getOnTurnPlayer().setMistakeCopy(MainFrame.controller.getOnTurnPlayer().getMistake());;//复制mistake
                controller.getOnTurnPlayer().setMistake(0);//mistake归0
                controller.getScoreBoard().update();
                System.out.println(MainFrame.controller.getOnTurnPlayer().getUserName() + " erasures her mistake.");
            } else {
                JOptionPane.showMessageDialog(this,"You don't has this treasure! ");}//打印一个警告框
        });//第二个格子，用来擦除错误//彩蛋:互助模式

        JButton eyes = new JButton("swap");
        eyes.setSize(70, 20);
        eyes.setLocation(180, gamePanel.getHeight() + scoreBoard.getHeight());
        add(eyes);
        eyes.addActionListener(f -> {
            if (MainFrame.controller.getOnTurnPlayer().S >= 1) {
                MainFrame.controller.getOnTurnPlayer().S--;
                int copy = MainFrame.controller.getOnTurnPlayer().getMistake();
                MainFrame.controller.getOnTurnPlayer().setMistake(MainFrame.controller.getAnotherPlayer().getMistake());
                MainFrame.controller.getAnotherPlayer().setMistake(copy);
                MainFrame.controller.getScoreBoard().update();
                JOptionPane.showMessageDialog(this,MainFrame.controller.getOnTurnPlayer().getUserName() + " swaps their mistakes.");
            } else if (MainFrame.controller.getOnTurnPlayer().S == 0) {
                JOptionPane.showMessageDialog(this,"Insufficient times of swaps!");
                //打印一个警告框
            }//交换错误
            System.out.println(getMineCount());
            System.out.println(getCurrMineCount());
        });

        JButton see = new JButton("See all");
        see.setSize(140, 20);
        see.setLocation(0, gamePanel.getHeight() + scoreBoard.getHeight() + 20);
        add(see);
        see.addActionListener(f -> {
            for (int i = 0; i < xCount; i++) {
                for (int j = 0; j < yCount; j++) {
                    if (gamePanel.getGridstatus(i, j).equals(GridStatus.Covered)) {
                        gamePanel.setGridStatus(i, j, GridStatus.Clicked);
                    }gamePanel.getMineField(i, j).repaint();
                }
            }//结束游戏
            MainFrame.setCurrMineCount(0);
            try {
                EndTheGame.endTheGame(controller.getP1(),controller.getP2(),this);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        JButton ability = new JButton("positive ability");
        ability.setSize(140, 20);
        ability.setLocation(140, gamePanel.getHeight() + scoreBoard.getHeight() + 20);
        add(ability);
        ability.addActionListener(f -> {
            if(controller.positive==1){
                controller.getOnTurnPlayer().positive(controller.getAnotherPlayer().getScore());
                JOptionPane.showMessageDialog(this,"Use positive ability!");
                GridComponent.music.run(7);
                controller.getScoreBoard().update();
                controller.positive=0;
            }else {
                JOptionPane.showMessageDialog(this,"Insufficient times of positive ability!");
            }
        });

        JButton Music = new JButton("Music");
        Music.setSize(140, 20);
        Music.setLocation(140, gamePanel.getHeight() + scoreBoard.getHeight() + 40);
        add(Music);
        Music.addActionListener(f -> {
            if(play){
                play=false;
                System.out.println("music on");
                GridComponent.music.run(1000);
            }else {
                System.out.println("music off");
                GridComponent.music.stopMusic();
                play=true;
            }
        });

        JButton Documents = new JButton("Documents");
        Documents.setSize(140, 20);
        Documents.setLocation(140, gamePanel.getHeight()+scoreBoard.getHeight()+60);
        add(Documents);
        Documents.addActionListener(f -> {
            String fileName = JOptionPane.showInputDialog(this, "input file name here");
            File file= new File("D:\\"+fileName+".txt");
            MainFrame mainFrame= null;
            try {
                mainFrame = MainFrame.ReadFile(file);dispose();
                mainFrame.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
                Menu.OpenMenu();
            } catch (NullPointerException e){
                JOptionPane.showMessageDialog(this,"Invalid name!");
                Menu.OpenMenu();
            }
        });

        JButton time = new JButton("Close all");
        time.setSize(140, 20);
        time.setLocation(0, gamePanel.getHeight() + scoreBoard.getHeight() + 40);
        add(time);
        time.addActionListener(f -> {//把所有点开的都关上
            for (int i = 0; i < xCount; i++) {
                for (int j = 0; j < yCount; j++) {
                    if (!gamePanel.getGridstatus(i, j).equals(GridStatus.Covered)) {
                        gamePanel.setGridStatus(i, j, GridStatus.Covered);
                    }gamePanel.getMineField(i, j).repaint();
                }
            }
            setCurrMineCount(getMineCount());
        });

        JButton Restartall = new JButton("Restart all");
        Restartall.setSize(140, 20);
        Restartall.setLocation(0, gamePanel.getHeight() + scoreBoard.getHeight() + 60);
        add(Restartall);
        Restartall.addActionListener(f -> {//多线程
            GridComponent.setFirst(true);
            //关闭当前的MainFrame
            new MainFrame(xCount, yCount, mineCount, E, S, D, B, getController().getP1(), getController().getP2());
            dispose();
        });

        JButton btnArchive = new JButton("Archive");
        btnArchive.setSize(90, 20);
        btnArchive.setLocation(0, gamePanel.getHeight() + scoreBoard.getHeight());
        add(btnArchive);
        btnArchive.addActionListener(e -> {
            String fileName = JOptionPane.showInputDialog(this, "input file name here");
            System.out.println("fileName :" + fileName);
            File file = new File("D:\\" + fileName + ".txt");
            try {
                if(!file.createNewFile()){
                    file.delete();
                }
                file.createNewFile();
                FileWriter fw = new FileWriter(file, false);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(controller.getP1().getUserName() + ";"
                        + controller.getP1().getScore() + ";"
                        + controller.getP1().getMistake() + ";"
                        + controller.getP1().getScoreEachTime() + ";"
                        + controller.getP1().getBigBrotherEye() + ";"
                        + controller.getP1().getE() + ";"
                        + controller.getP1().getS() + ";"
                        + controller.getP1().getHeroId()+ "\n");//输入P1的信息
                bw.write(controller.getP2().getUserName() + ";"
                        + controller.getP2().getScore() + ";"
                        + controller.getP2().getMistake() + ";"
                        + controller.getP2().getScoreEachTime() + ";"
                        + controller.getP2().getBigBrotherEye() + ";"
                        + controller.getP2().getE() + ";"
                        + controller.getP2().getS() + ";"
                        + controller.getP2().getHeroId()+ "\n");//输入P2的信息
                for (int i = 1; i < xCount + 1; i++) {
                    for (int j = 1; j < yCount + 1; j++) {
                        if (j != yCount) {
                            bw.write(controller.getGamePanel().getChessBoard(i, j) + ",");
                        } else {
                            bw.write(controller.getGamePanel().getChessBoard(i, j));
                        }
                    }
                    if (i != xCount) {
                        bw.write(";");
                    } else {
                        bw.write("\n");
                    }
                }//输入ChessBoard
                int state = 0;
                for (int i = 0; i < xCount; i++) {
                    for (int j = 0; j < yCount; j++) {

                        switch (controller.getGamePanel().getMineField(i, j).getStatus()) {
                            case Covered:
                                state = 0;
                                break;
                            case Flag:
                                state = 1;
                                break;
                            case Clicked:
                                state = -1;
                                break;
                        }

                        if (j != yCount - 1) {
                            bw.write(state + ",");
                        } else {
                            bw.write(state + "");
                        }

                    }
                    if (i != xCount - 1) {
                        bw.write(";");
                    } else {
                        bw.write("\n");
                    }
                }//输入MineField
                bw.write(this.mineCount + ","
                        + this.E + ","
                        + this.S + ","
                        + this.D + ","
                        + this.B + ","
                +currMineCount);
                bw.flush();
                bw.close();
                fw.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            //controller.readFileData(fileName);
            //controller.writeDataToFile(fileName);
        });//第一个格子，用来存档


        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public MainFrame(int xCount, int yCount,
                     Player p1, Player p2,String[] mineField,String[] chessboard,
                     int mineCount, int E, int S, int D, int B,int currMineCountNow) {
        //todo: change the count of xCount, yCount and mineCount by passing parameters from constructor,done!
        this.xCount = xCount;//grid of row
        this.yCount = yCount;// grid of column
        this.mineCount = mineCount;// mine count
        currMineCount = currMineCountNow;//需修改！！！！！！！！！
        this.E = E;
        this.S = S;
        this.D = D;
        this.B = B;

        this.setTitle("2021 CS102A Project");
        this.setLayout(null);//??////////////////////////////////
        this.setSize(yCount * GridComponent.gridSize + 100, xCount * GridComponent.gridSize + 240);//给多横向100像素
        this.setLocationRelativeTo(null);//??
        controller = new GameController(p1, p2, mineField,chessboard
            ,xCount,yCount,this,mineCount,E,S,D,B);
        ScoreBoard scoreBoard = new ScoreBoard(p1, p2, xCount, yCount);
        scoreBoard.setOpaque(false);
        GamePanel gamePanel = controller.getGamePanel();
        TimeBorad timeBorad = new TimeBorad(yCount);
        controller.setScoreBoard(scoreBoard);
        controller.setTimeBorad(timeBorad);
        this.add(gamePanel);
        this.add(scoreBoard);
        this.add(timeBorad);

        ImageIcon icon=new ImageIcon("C:\\Users\\zhouyy\\Desktop\\project\\essay\\新建文件夹\\微信图片_20210517001334.jpg");
        icon.setImage(icon.getImage().getScaledInstance(350+710,710+500,Image.SCALE_DEFAULT));
        JLabel label=new JLabel(icon);
        label.setBounds(-710,-710,2000+350,2000+500);
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        JPanel g=(JPanel)this.getContentPane();
        g.setOpaque(false);
        JPanel panel=new JPanel();
        //panel.setSize(350,500);

        String component= JOptionPane.showInputDialog(this, "input times of one round");
        try {
            if(Integer.parseInt(component)<=5&&Integer.parseInt(component)>=1){
                GameController.times=Integer.parseInt(component);
            }else {
                JOptionPane.showMessageDialog(this,"Invalid input, set default 5 times!");
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Invalid input, set default 5 times!");
        }catch (ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(this,"Invalid input, set default 5 times!");
        }

        JButton era = new JButton("erasure");//擦除错误
        era.setSize(90, 20);
        era.setLocation(90, gamePanel.getHeight() + scoreBoard.getHeight());
        add(era);
        era.addActionListener(f -> {
            if (controller.getOnTurnPlayer().E >= 1) {
                controller.getOnTurnPlayer().E--;//使用机会
                controller.getOnTurnPlayer().setMistakeCopy(MainFrame.controller.getOnTurnPlayer().getMistake());;//复制mistake
                controller.getOnTurnPlayer().setMistake(0);//mistake归0
                controller.getScoreBoard().update();
                JOptionPane.showMessageDialog(this,MainFrame.controller.getOnTurnPlayer().getUserName() + " erasures her mistake.");
            } else JOptionPane.showMessageDialog(this,"You don't has this treasure! ");//打印一个警告框
        });//第二个格子，用来擦除错误//彩蛋:互助模式

        JButton eyes = new JButton("swap");
        eyes.setSize(70, 20);
        eyes.setLocation(180, gamePanel.getHeight() + scoreBoard.getHeight());
        add(eyes);
        eyes.addActionListener(f -> {
            if (MainFrame.controller.getOnTurnPlayer().S >= 1) {
                MainFrame.controller.getOnTurnPlayer().S--;
                int copy = MainFrame.controller.getOnTurnPlayer().getMistake();
                MainFrame.controller.getOnTurnPlayer().setMistake(MainFrame.controller.getAnotherPlayer().getMistake());
                MainFrame.controller.getAnotherPlayer().setMistake(copy);
                MainFrame.controller.getScoreBoard().update();
                JOptionPane.showMessageDialog(this,MainFrame.controller.getOnTurnPlayer().getUserName() + " swaps their mistakes.");
            } else if (MainFrame.controller.getOnTurnPlayer().S == 0) {
                JOptionPane.showMessageDialog(this,"You don't has this treasure! ");;//打印一个警告框
            }//交换错误
            System.out.println(getMineCount());
            System.out.println(getCurrMineCount());
        });

        JButton see = new JButton("See all");
        see.setSize(140, 20);
        see.setLocation(0, gamePanel.getHeight() + scoreBoard.getHeight() + 20);
        add(see);
        see.addActionListener(f -> {
            for (int i = 0; i < xCount; i++) {
                for (int j = 0; j < yCount; j++) {
                    if (gamePanel.getGridstatus(i, j).equals(GridStatus.Covered)) {
                        gamePanel.setGridStatus(i, j, GridStatus.Clicked);
                    }gamePanel.getMineField(i, j).repaint();
                }
            }//结束游戏
            MainFrame.setCurrMineCount(0);
            try {
                EndTheGame.endTheGame(controller.getP1(),controller.getP2(),this);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        JButton ability = new JButton("positive ability");
        ability.setSize(140, 20);
        ability.setLocation(140, gamePanel.getHeight() + scoreBoard.getHeight() + 20);
        add(ability);
        ability.addActionListener(f -> {
            if(controller.positive==1){
                controller.getOnTurnPlayer().positive(controller.getAnotherPlayer().getScore());
                GridComponent.music.run(7);
                controller.getScoreBoard().update();
                controller.positive=0;
                JOptionPane.showMessageDialog(this,"Use positive ability!");
            }else {
                JOptionPane.showMessageDialog(this,"Insufficient times of ability!");
            }
        });

        JButton Music = new JButton("Music");
        Music.setSize(140, 20);
        Music.setLocation(140, gamePanel.getHeight() + scoreBoard.getHeight() + 40);
        add(Music);
        Music.addActionListener(f -> {
            if(play){
                play=false;
                System.out.println("music on");
                GridComponent.music.run(1000);
            }else {
                System.out.println("music off");
                GridComponent.music.stopMusic();
                play=true;
            }
        });

        JButton Documents = new JButton("Documents");
        Documents.setSize(140, 20);
        Documents.setLocation(140, gamePanel.getHeight()+scoreBoard.getHeight()+60);
        add(Documents);
        Documents.addActionListener(f -> {
            String fileName = JOptionPane.showInputDialog(this, "input file name here");
            File file= new File("D:\\"+fileName+".txt");
            MainFrame mainFrame= null;
            try {
                mainFrame = MainFrame.ReadFile(file);dispose();mainFrame.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
                Menu.OpenMenu();
            }catch (NullPointerException e){
                JOptionPane.showMessageDialog(this,"Invalid name!");
                Menu.OpenMenu();
            }
        });

        JButton time = new JButton("Close all");
        time.setSize(140, 20);
        time.setLocation(0, gamePanel.getHeight() + scoreBoard.getHeight() + 40);
        add(time);
        time.addActionListener(f -> {//把所有点开的都关上
            for (int i = 0; i < xCount; i++) {
                for (int j = 0; j < yCount; j++) {
                    if (!gamePanel.getGridstatus(i, j).equals(GridStatus.Covered)) {
                        gamePanel.setGridStatus(i, j, GridStatus.Covered);
                    }gamePanel.getMineField(i, j).repaint();
                }
            }
            setCurrMineCount(getMineCount());
        });

        JButton Restartall = new JButton("Restart all");
        Restartall.setSize(140, 20);
        Restartall.setLocation(0, gamePanel.getHeight() + scoreBoard.getHeight() + 60);
        add(Restartall);
        Restartall.addActionListener(f -> {//多线程
            GridComponent.setFirst(true);
            //关闭当前的MainFrame
            new MainFrame(xCount, yCount, mineCount, E, S, D, B, getController().getP1(), getController().getP2());
            dispose();
        });

        JButton btnArchive = new JButton("Archive");
        btnArchive.setSize(90, 20);
        btnArchive.setLocation(0, gamePanel.getHeight() + scoreBoard.getHeight());
        add(btnArchive);
        btnArchive.addActionListener(e -> {
            String fileName = JOptionPane.showInputDialog(this, "input file name here");
            System.out.println("fileName :" + fileName);
            File file = new File("D:\\" + fileName + ".txt");
            try {
                if(!file.createNewFile()){
                    file.delete();
                }
                file.createNewFile();
                FileWriter fw = new FileWriter(file, false);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(controller.getP1().getUserName() + ";"
                        + controller.getP1().getScore() + ";"
                        + controller.getP1().getMistake() + ";"
                        + controller.getP1().getScoreEachTime() + ";"
                        + controller.getP1().getBigBrotherEye() + ";"
                        + controller.getP1().getE() + ";"
                        + controller.getP1().getS() + ";"
                        + controller.getP1().getHeroId()+ "\n");//输入P1的信息
                bw.write(controller.getP2().getUserName() + ";"
                        + controller.getP2().getScore() + ";"
                        + controller.getP2().getMistake() + ";"
                        + controller.getP2().getScoreEachTime() + ";"
                        + controller.getP2().getBigBrotherEye() + ";"
                        + controller.getP2().getE() + ";"
                        + controller.getP2().getS() + ";"
                        + controller.getP2().getHeroId()+ "\n");//输入P2的信息
                for (int i = 1; i < xCount + 1; i++) {
                    for (int j = 1; j < yCount + 1; j++) {
                        if (j != yCount) {
                            bw.write(controller.getGamePanel().getChessBoard(i, j) + ",");
                        } else {
                            bw.write(controller.getGamePanel().getChessBoard(i, j));
                        }
                    }
                    if (i != xCount) {
                        bw.write(";");
                    } else {
                        bw.write("\n");
                    }
                }//输入ChessBoard
                int state = 0;
                for (int i = 0; i < xCount; i++) {
                    for (int j = 0; j < yCount; j++) {

                        switch (controller.getGamePanel().getMineField(i, j).getStatus()) {
                            case Covered:
                                state = 0;
                                break;
                            case Flag:
                                state = 1;
                                break;
                            case Clicked:
                                state = -1;
                                break;
                        }

                        if (j != yCount - 1) {
                            bw.write(state + ",");
                        } else {
                            bw.write(state + "");
                        }

                    }
                    if (i != xCount - 1) {
                        bw.write(";");
                    } else {
                        bw.write("\n");
                    }
                }//输入MineField
                bw.write(this.mineCount + ","
                        + this.E + ","
                        + this.S + ","
                        + this.D + ","
                        + this.B + ","
                        +currMineCount);
                bw.flush();
                bw.close();
                fw.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            //controller.readFileData(fileName);
            //controller.writeDataToFile(fileName);
        });//第一个格子，用来存档

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static MainFrame ReadFile(File file) throws IOException {

        MainFrame mainFrame = null;

        if (file.exists()) {
            String Player1Info = "";
            String Player2Info = "";
            String ChessBoardInfo = "";
            String MineFieldInfo = "";
            String Info = "";

            String txt = "";
            FileReader fr = null;
            LineNumberReader reader = null;

            try {
                fr = new FileReader(file);
                reader = new LineNumberReader(fr);

                int lines = 0;

                while (txt != null) {
                    lines++;
                    txt = reader.readLine();

                    if (lines == 1) {
                        Player1Info = txt;
                    } else if (lines == 2) {
                        Player2Info = txt;
                    } else if (lines == 3) {
                        ChessBoardInfo = txt;
                    } else if (lines == 4) {
                        MineFieldInfo = txt;
                    } else if (lines == 5) {
                        Info = txt;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            String[] Player1 = Player1Info.split(";");
            Player P1 = new Player() {
                @Override
                public void negative(int x, int y) {

                }

                @Override
                public void positive(int x) {

                }
            };
            if (Player1[7].equals("16")) {
                P1 = new Einstein(Player1[0], Player1[1], Player1[2],
                        Player1[3], Player1[4], Player1[5], Player1[6]);

            } else if (Player1[7].equals("17")) {
                P1 = new Maxwell(Player1[0], Player1[1], Player1[2],
                        Player1[3], Player1[4], Player1[5], Player1[6]);
            } else if (Player1[7].equals("18")) {
                P1 = new Newton(Player1[0], Player1[1], Player1[2],
                        Player1[3], Player1[4], Player1[5], Player1[6]);
            } else if (Player1[7].equals("15")) {
                P1 = new AI(Player1[0], Player1[1], Player1[2],
                        Player1[3], Player1[4], Player1[5], Player1[6]);
            }

            String[] Player2 = Player2Info.split(";");
            Player P2 = new Player() {
                @Override
                public void negative(int x, int y) {

                }

                @Override
                public void positive(int x) {

                }
            };
            if (Player2[7].equals("16")) {
                P2 = new Einstein(Player2[0], Player2[1], Player2[2],
                        Player2[3], Player2[4], Player2[5], Player2[6]);
            } else if (Player2[7].equals("17")) {
                P2 = new Maxwell(Player2[0], Player2[1], Player2[2],
                        Player2[3], Player2[4], Player2[5], Player2[6]);
            } else if (Player2[7].equals("18")) {
                P2 = new Newton(Player2[0], Player2[1], Player2[2],
                        Player2[3], Player2[4], Player2[5], Player2[6]);
            }else if (Player1[7].equals("15")) {
                P2 = new AI(Player1[0], Player1[1], Player1[2],
                        Player1[3], Player1[4], Player1[5], Player1[6]);
            }

            String[] information = Info.split(",");

            String[] ChessBoardLine = ChessBoardInfo.split(";");
            //无框
            String[] MineFieldLine = MineFieldInfo.split(";");//无框
            int xCount = ChessBoardLine.length;
            int yCount = ChessBoardLine[0].split(",").length;

            mainFrame = new MainFrame( xCount, yCount,
            P1, P2,MineFieldLine,ChessBoardLine,
                    Integer.parseInt(information[0]),Integer.parseInt(information[1]),
                    Integer.parseInt(information[2]),Integer.parseInt(information[3]),
                    Integer.parseInt(information[4]),Integer.parseInt(information[5]));

            mainFrame.repaint();
            //现在爱因斯坦的ID是16，麦克斯韦17，牛顿18，因此记录的时候要记录下对应的id
            //然后在读取的时候检索记录下的ID，写一个方法，根据ID来重新创建新新英雄给重载的游戏
        }
        return mainFrame;
    }


}
/*
        JPanel Timer=new JPanel();
        Timer.setBackground(Color.WHITE);
        Timer.setSize(120, 50);
        Timer.setLocation(yCount * GridComponent.gridSize, 0);
        Timer.setVisible(true);
        add(Timer);
        timer.start();
        System.out.println(timer.getCurrTime());//如何在界面打印时间？
        while (timer.getCurrTime()==0){//到时就下一个
            MainFrame.controller.nextTurn();
            System.out.println("time over");
        }

            public void actionPerformed(ActionEvent e) {
        if(ifFirst){
            //gamePanel.setGamePanel();
            ifFirst=false;

        }
    }
*/
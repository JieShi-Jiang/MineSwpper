package minesweeper;

import DisplayPanel.ClosePanel;
import DisplayPanel.GradeList;
import DisplayPanel.Menu;
import entity.Hero.Newton;
import entity.Player;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.Map;

public class EndTheGame {
    public static void endTheGame(Player p1, Player p2, MainFrame mainFrame) throws IOException {
        int level = 0;

        if (mainFrame.getE() == 2) {
            level = 1;
        } else if (mainFrame.getE() == 4) {
            level = 2;
        } else if (mainFrame.getE() == 8) {
            level = 3;
        }

        if (MainFrame.getCurrMineCount() == 0) {
            if (p2.getHeroId() != 15 && p1.getHeroId() != 15) {
                if (p1.getScore() > p2.getScore()) {
                    //1获胜
                    System.out.println("Player1 wins the game");
                    ClosePanel closePanel = new ClosePanel(p1, mainFrame);
                    ClosePanel.OpenClosePanel(closePanel);
                    resetGradeList(p1, level);
                } else if (p1.getScore() < p2.getScore()) {
                    //2获胜
                    System.out.println("Player2 wins the game");
                    ClosePanel closePanel = new ClosePanel(p2, mainFrame);
                    ClosePanel.OpenClosePanel(closePanel);
                    resetGradeList(p2, level);
                } else if (p1.getMistake() > p2.getMistake()) {
                    //1获胜
                    System.out.println("Player1 wins the game");
                    ClosePanel closePanel = new ClosePanel(p1, mainFrame);
                    ClosePanel.OpenClosePanel(closePanel);
                    resetGradeList(p1, level);
                } else if (p1.getMistake() < p2.getMistake()) {
                    //2获胜
                    System.out.println("Player2 wins the game");
                    ClosePanel closePanel = new ClosePanel(p2, mainFrame);
                    ClosePanel.OpenClosePanel(closePanel);
                    resetGradeList(p2, level);
                } else {
                    //平局
                    System.out.println("平局");
                    Player p3 = new Newton();
                    p3.setUserName("No body");
                    p3.setScore(100000);
                    ClosePanel closePanel = new ClosePanel(p3, mainFrame);
                    ClosePanel.OpenClosePanel(closePanel);
                    resetGradeList(p1, level);
                    resetGradeList(p2, level);
                }
            } else {//AI
                ClosePanel closePanel = new ClosePanel(p1, mainFrame);
                ClosePanel.OpenClosePanel(closePanel);
                resetGradeList(p1, level);
            }
        }
    }

    public static void resetGradeList(Player player, int level) throws IOException {
        if (level == 1) {
            File file = new File("D:\\Easy GradeList.txt");
            GradeList.GradeList1 = GradeList.getGradeList(file);
            System.out.println(GradeList.GradeList1);
            file.delete();
            file.createNewFile();
            if (GradeList.GradeList1.containsKey(player.getUserName())) {
                if (GradeList.GradeList1.get(player.getUserName()) <= player.getScore()) {
                    GradeList.GradeList1.remove(player.getUserName());
                    GradeList.GradeList1.put(player.getUserName(), player.getScore());
                    EndTheGame.writeFile(file,GradeList.GradeList1);
                    /*try {
                        FileWriter fw = new FileWriter(file, false);
                        BufferedWriter bw = new BufferedWriter(fw);
                        for (String key : GradeList.GradeList1.keySet()
                        ) {
                            bw.write(key + "," + GradeList.GradeList1.get(key) + ";");
                        }
                        bw.flush();
                        bw.close();
                        fw.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }*/
                }
            } else {
                GradeList.GradeList1.put(player.getUserName(), player.getScore());
                EndTheGame.writeFile(file,GradeList.GradeList1);
            }
        } else if (level == 2) {
            File file = new File("D:\\Medium GradeList.txt");
            GradeList.GradeList2 = GradeList.getGradeList(file);
            file.delete();
            file.createNewFile();
            if (GradeList.GradeList2.containsKey(player.getUserName())) {
                if (GradeList.GradeList2.get(player.getUserName()) <= player.getScore()) {
                    GradeList.GradeList2.remove(player.getUserName());
                    GradeList.GradeList2.put(player.getUserName(), player.getScore());
                    EndTheGame.writeFile(file,GradeList.GradeList2);
                }
            } else {
                GradeList.GradeList2.put(player.getUserName(), player.getScore());
                EndTheGame.writeFile(file,GradeList.GradeList2);
            }
        } else if (level == 3) {
            File file = new File("D:\\Hard GradeList.txt");
            GradeList.GradeList3 = GradeList.getGradeList(file);
            file.delete();
            file.createNewFile();
            if (GradeList.GradeList3.containsKey(player.getUserName())) {
                if (GradeList.GradeList3.get(player.getUserName()) <= player.getScore()) {
                    GradeList.GradeList3.remove(player.getUserName());
                    GradeList.GradeList3.put(player.getUserName(), player.getScore());
                    EndTheGame.writeFile(file,GradeList.GradeList3);
                }
            } else {
                GradeList.GradeList3.put(player.getUserName(), player.getScore());
                EndTheGame.writeFile(file,GradeList.GradeList3);
            }
        }

    }

    public static void writeFile(File file,Map<String, Integer> gradelist){
        try {
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String key : gradelist.keySet()
            ) {
                bw.write(key + "," + gradelist.get(key) + ";");
            }
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }



    }
}

package DisplayPanel;

import minesweeper.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Map;

public class Menu extends JFrame implements ActionListener{

    private JLabel title;
    private JButton btnNewGame, btnDocuments, btnGradeList;


    public Menu() {
        super("MineSweeper");//C:\\Users\\zhouyy\\Desktop\\essay\\新建文件夹\\微信图片_20210517001320.jpg

        ImageIcon icon=new ImageIcon("C:\\Users\\zhouyy\\Desktop\\project\\essay\\新建文件夹\\微信图片_20210517001324.jpg");
        JLabel label=new JLabel(icon);
        label.setBounds(-410,-410,350+410,410+500);
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        JPanel j=(JPanel)this.getContentPane();
        j.setOpaque(false);
        JPanel panel=new JPanel(new GridLayout(2, 1));

        setLayout(new GridLayout(2, 1));//上下两个
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel(new GridLayout(3, 1, 40, 20));
        panel.add(panel1);
        panel.add(panel2);
        panel.setOpaque(false);
        this.add(panel);
        this.add(panel);
        title = new JLabel("Menu");
        title.setFont(new Font("San Serif", Font.PLAIN, 50));
        panel1.add(title);

        btnNewGame = new JButton("NewGame");
        panel2.add(btnNewGame);
        btnNewGame.addActionListener(this);

        btnDocuments = new JButton("Documents");
        panel2.add(btnDocuments);
        btnDocuments.addActionListener(this);


        btnGradeList = new JButton("Ranking List");
        panel2.add(btnGradeList);
        btnGradeList.addActionListener(this);

        panel1.setOpaque(false);
        panel2.setOpaque(false);
    }

    public static void OpenMenu() {
       Menu menu = new Menu();
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(350, 500);//随便写的 后面改吧
        menu.setLocation(800,250);
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
            if (event.getSource()==btnNewGame){
                SelectBoard selectBoard=new SelectBoard();
                SelectBoard.OpenSelectBoard(selectBoard);
                this.setVisible(false);
            }else if (event.getSource() == btnDocuments) {
                String fileName = JOptionPane.showInputDialog(this, "input file name here");
                File file= new File("D:\\"+fileName+".txt");
                MainFrame mainFrame= null;
                try {
                    mainFrame = MainFrame.ReadFile(file);
                    mainFrame.setVisible(true);
                    this.setVisible(false);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NullPointerException e){
                    JOptionPane.showMessageDialog(this,"Invalid name!");
                }
            } else if (event.getSource() == btnGradeList) {
                GradeList gradeList=new GradeList();
                GradeList.OpenGradeLis(gradeList);
            }

    }

    /*public class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == btnStart) {
                chooseLevel=new ChooseLevel();
                ChooseLevel.OpenChooseLevel(chooseLevel);
            } else if (event.getSource() == btnDocuments) {
                documents=new DisplayPanel.Documents();

            } else if (event.getSource() == btnGradeList) {
                gradeList=new DisplayPanel.GradeList();

            } //mainFrame.setVisible(true);
            //关掉
        }
    }*/
}

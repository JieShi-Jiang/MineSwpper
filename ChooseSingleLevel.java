package DisplayPanel;

import minesweeper.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseSingleLevel extends JFrame implements ActionListener {
    private JLabel title;
    private JButton btnEasy, btnMedium, btnHard, btnPer;

    public ChooseSingleLevel() {
        super("MineSweeper");
        ImageIcon icon=new ImageIcon("C:\\Users\\zhouyy\\Desktop\\project\\essay\\新建文件夹\\微信图片_20210517001334.jpg");
        icon.setImage(icon.getImage().getScaledInstance(350+710,710+500,Image.SCALE_DEFAULT));
        JLabel label=new JLabel(icon);
        label.setBounds(-710,-710,350+910,1910+500);
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        JPanel j=(JPanel)this.getContentPane();
        j.setOpaque(false);
        JPanel panel=new JPanel();
        panel.setSize(350,500);

        setLayout(new GridLayout(2, 1));//上下两个
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel(new GridLayout(4, 1, 40, 20));
        panel.add(panel1);
        panel.add(panel2);
        panel.setOpaque(false);
        this.add(panel);
        this.add(panel);
        panel1.setOpaque(false);
        panel2.setOpaque(false);

        title = new JLabel("Please choose a level");
        title.setFont(new Font("San Serif", Font.PLAIN, 25));
        panel1.add(title);

        btnEasy = new JButton("esay");
        panel2.add(btnEasy);
        btnEasy.addActionListener(this);
        btnEasy.setActionCommand("open");

        btnMedium = new JButton("medium");
        panel2.add(btnMedium);
        btnMedium.addActionListener(this);
        btnMedium.setActionCommand("open");

        btnHard = new JButton("hard");
        panel2.add(btnHard);
        btnHard.addActionListener(this);
        btnHard.setActionCommand("open");

        btnPer = new JButton("personalization");
        panel2.add(btnPer);
        btnPer.addActionListener(this);
        btnPer.setActionCommand("open");
    }

    public static void OpenChooseSingleLevel(ChooseSingleLevel chooseLevel) {
        chooseLevel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooseLevel.setSize(300, 500);//随便写的 后面改吧
        chooseLevel.setLocation(800,250);
        chooseLevel.setVisible(true);
    }

    public static void CloseChooseLevel(ChooseSingleLevel chooseLevel) {
        chooseLevel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String cmd = event.getActionCommand();
        if (cmd.equals("open")) {
            if (event.getSource() == btnEasy) {
                MainFrame mainFrame = new MainFrame(9, 9, 10, 2, 2, 3, 4,ChooseAI.player,ChooseAI.p2);
                mainFrame.setVisible(true);
                dispose();
            } else if (event.getSource() == btnMedium) {
                MainFrame mainFrame = new MainFrame(16, 16, 40, 4, 4, 7, 8,ChooseAI.player,ChooseAI.p2);
                mainFrame.setVisible(true);
                dispose();
            } else if (event.getSource() == btnHard) {
                MainFrame mainFrame = new MainFrame(16, 30, 99, 8, 10, 8, 8,ChooseAI.player,ChooseAI.p2);
                mainFrame.setVisible(true);
                dispose();
            } else if (event.getSource() == btnPer) {
                try {
                    String component = JOptionPane.showInputDialog(this, "input X,Y,Mines here,separated by '，'");
                    String[] aa = component.split("，");
                    int[] bb = new int[3];
                    for (int i = 0; i < 3; i++) {
                        bb[i] = Integer.parseInt(aa[i]);
                    }
                    if (bb[0] * bb[1] <= 2 * bb[2] || bb[0] < 9 || bb[1] < 9 || bb[0] > 24 || bb[1] > 30) {
                        JOptionPane.showMessageDialog(this, "Wrong input!");
                    } else {
                        MainFrame mainFrame = new MainFrame(bb[0], bb[1], bb[2], 5, 0, 3, 0, ChooseAI.player, ChooseAI.p2);
                        mainFrame.setVisible(true);
                        dispose();
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    JOptionPane.showMessageDialog(this,"Invalid input!");
                }catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(this,"Invalid input!");
                }
            }

        }

       /* public class ButtonClickListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (event.getSource() == btnEasy) {
                    MainFrame mainFrame=new MainFrame(9,9,10,2,2,3,4);
                    mainFrame.setVisible(true);
                } else if (event.getSource() == btnMedium) {
                    MainFrame mainFrame=new MainFrame(16,16,40,4,4,7,8);
                    mainFrame.setVisible(true);
                } else if (event.getSource() == btnHard) {
                    MainFrame mainFrame=new MainFrame(16,30,99,8,10,8,8);
                    mainFrame.setVisible(true);
                } else if (event.getSource() == btnPer) {
                    MainFrame mainFrame=new MainFrame(9,9,10,1,2,3,4);
                    mainFrame.setVisible(true);
                }
                //mainFrame.setVisible(true);
                //关掉
            }*/
    }
}


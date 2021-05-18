package DisplayPanel;

import DisplayPanel.ChooseLevel;
import controller.GameController;
import entity.Hero.Lysenko;
import entity.Player;
import minesweeper.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ClosePanel extends JFrame implements ActionListener {
    Player player;

    private JLabel title, result,surprise;
    private JButton btnMenu,Secret;
    private MainFrame MainFrame;
    Random ran=new Random();

    public ClosePanel(Player player, MainFrame mainFrame) {
        super("MineSweeper");
        MainFrame=mainFrame;
        this.player = player;

        ImageIcon icon=new ImageIcon("C:\\Users\\zhouyy\\Desktop\\project\\essay\\新建文件夹\\微信图片_20210517001312.jpg");
        icon.setImage(icon.getImage().getScaledInstance(350+710,710+500,Image.SCALE_DEFAULT));
        JLabel label=new JLabel(icon);
        label.setBounds(-710,-710,2000+350,2000+500);
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        JPanel g=(JPanel)this.getContentPane();
        g.setOpaque(false);
        JPanel panel=new JPanel();
        //panel.setSize(350,500);

        setLayout(new GridLayout(4, 2));//上下两个
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel(new GridLayout(4, 1, 180, 5));
        panel2.setSize(200,100);
        panel2.setBackground(Color.white);
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        panel4.setOpaque(false);


        int xx=0;

        title = new JLabel("GameOver!");
        title.setFont(new Font("San Serif", Font.PLAIN, 40));
        panel1.add(title);

        if(player.getHeroId()!=15){
            if(player.getScore()==100000){
                result = new JLabel("平局！");
                xx=1;
            }else {
                result = new JLabel(player.getUserName() + "Win! \nScore:" + player.getScore());
            }
        }else {result = new JLabel(player.getUserName() + "Win! \nScore:" + player.getScore()); }
        result.setFont(new Font("San Serif", Font.PLAIN, 15));
        result.setSize(200,100);
        panel2.add(result);

        if(ran.nextInt(11)>1&&xx==0){//保证非平局
            surprise=new JLabel("Surprise! a secret mode!");
            surprise.setFont(new Font("San Serif", Font.PLAIN, 20));
            panel2.add(surprise);
            Secret = new JButton("Secret mode");
            panel4.add(Secret);
            Secret.addActionListener(this);
            Secret.setActionCommand("open");
        }

        btnMenu = new JButton("Menu");
        panel3.add(btnMenu);
        btnMenu.addActionListener(this);
        btnMenu.setActionCommand("open");

    }

    public static void OpenClosePanel(ClosePanel closePanel) {
        closePanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        closePanel.setSize(300, 500);//随便写的 后面改吧
        closePanel.setLocation(800, 250);
        closePanel.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        String cmd = event.getActionCommand();
        if (cmd.equals("open")) {
            if (event.getSource() == btnMenu) {
                Menu.OpenMenu();
            }else if(event.getSource() == Secret){
                player.setScore(0);
                player.setScoreEachTime(1);
                player.setMistake(0);
                player.setS(0);
                player.setE(0);
                player.setBigBrotherEye(0);//初始化数据
                GameController.counter=0;
                MainFrame mainFrame = new MainFrame(10, 16, 60, 8, 8, 8, 8,player,new Lysenko());
                mainFrame.setVisible(true);
                dispose();
                JOptionPane.showMessageDialog(this,"In this mode, the winner is going to fight with the Soviet Science Devil Lysenko!");
            }MainFrame.dispose();
            dispose();
        }
    }
}

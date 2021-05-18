package DisplayPanel;

import entity.Hero.Einstein;
import entity.Hero.Maxwell;
import entity.Hero.Newton;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoosePlayer extends JFrame implements ActionListener {

    private final JLabel title, SetName1, SetName2;
    private final JButton btnNext,NewTonP1,MaxwellP1,EinsteinP1,NewTonP2,MaxwellP2,EinsteinP2;
    public JTextField Name1, Name2;
    public static Player p1=new Newton();
    public static Player p2=new Newton();


    public ChoosePlayer() {
        super("MineSweeper");

        ImageIcon icon=new ImageIcon("C:\\Users\\zhouyy\\Desktop\\project\\essay\\新建文件夹\\微信图片_20210517001316.jpg");
        JLabel label=new JLabel(icon);
        label.setBounds(-710,-710,350+1710,1710+500);
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        JPanel j=(JPanel)this.getContentPane();
        j.setOpaque(false);
        JPanel panel=new JPanel();
        setLayout(new GridLayout(4, 1));
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel(new GridLayout(2, 3, 20, 20));
        JPanel panel3 = new JPanel(new GridLayout(4, 1, 20, 20));
        JPanel panel4 = new JPanel();
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        panel4.setOpaque(false);


        title = new JLabel("Please set the players");
        title.setFont(new Font("San Serif", Font.PLAIN, 30));
        panel1.add(title);

        NewTonP1 = new JButton("Hero1: NewTon");
        NewTonP1.setLocation(20, 20);
        NewTonP1.setSize(60, 20);
        panel2.add(NewTonP1);
        NewTonP1.addActionListener(this);

        MaxwellP1 = new JButton("Hero1: Maxwell");
        MaxwellP1.setLocation(80, 20);
        MaxwellP1.setSize(60, 20);
        panel2.add(MaxwellP1);
        MaxwellP1.addActionListener(this);

        EinsteinP1 = new JButton("Hero1: Einstein");
        EinsteinP1.setLocation(140, 20);
        EinsteinP1.setSize(60, 20);
        panel2.add(EinsteinP1);
        EinsteinP1.addActionListener(this);

        NewTonP2 = new JButton("Hero2: NewTon");
        NewTonP2.setLocation(20, 50);
        NewTonP2.setSize(60, 20);
        panel2.add(NewTonP2);
        NewTonP2.addActionListener(this);

        MaxwellP2 = new JButton("Hero2: Maxwell");
        MaxwellP2.setLocation(80, 50);
        MaxwellP2.setSize(60, 20);
        panel2.add(MaxwellP2);
        MaxwellP2.addActionListener(this);

        EinsteinP2 = new JButton("Hero2: Einstein");
        EinsteinP2.setLocation(140, 50);
        EinsteinP2.setSize(60, 20);
        panel2.add(EinsteinP2);
        EinsteinP2.addActionListener(this);

        SetName1 = new JLabel("Please type the player1's name(Enter to determine!)");
        SetName1.setFont(new Font("San Serif", Font.PLAIN, 15));
        panel3.add(SetName1);

        Name1 = new JTextField("");
        panel3.add(Name1);
        Name1.addActionListener(this);

        SetName2 = new JLabel("Please type the player2's name(Enter to determine!)");
        SetName2.setFont(new Font("San Serif", Font.PLAIN, 15));
        panel3.add(SetName2);

        Name2 = new JTextField("");
        panel3.add(Name2);
        Name2.addActionListener(this);

        btnNext = new JButton("Next");
        btnNext.setSize(300,200);
        panel4.add(btnNext);
        btnNext.addActionListener(this);

    }

    public static void OpenChoosePlayer(ChoosePlayer choosePlayer) {
        choosePlayer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        choosePlayer.setSize(500, 500);//随便写的 后面改吧
        choosePlayer.setLocation(800, 250);
        choosePlayer.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == Name1) {
            p1.setUserName(Name1.getText());
        }
        if (event.getSource() == Name2) {
            p2.setUserName(Name2.getText());
        }
        if (event.getSource() == NewTonP1) {
            p1 = new Newton();
        }
        if (event.getSource() == MaxwellP1) {
           p1 = new Maxwell();
        }
        if (event.getSource() == EinsteinP1) {
            p1 = new Einstein();
        }
        if (event.getSource() == NewTonP2) {
            p2 = new Newton();
        }
        if (event.getSource() == MaxwellP2) {
            p2 = new Maxwell();
        }
        if (event.getSource() == EinsteinP2) {
            p2 = new Einstein();
        }
        if (event.getSource() == btnNext) {
            ChooseLevel chooseLevel = new ChooseLevel();
            ChooseLevel.OpenChooseLevel(chooseLevel);
            dispose();
        }
    }


}

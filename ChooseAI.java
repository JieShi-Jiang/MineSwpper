package DisplayPanel;

import entity.Hero.AI;
import entity.Hero.Einstein;
import entity.Hero.Maxwell;
import entity.Hero.Newton;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseAI extends JFrame implements ActionListener {
private JLabel title,SetName;
    private JButton NewTon;
    private JButton Maxwell;
    private JButton Einstein;
    public JTextField Name;
    private JButton btnNext;
    public static Player player=new Newton();
    public static Player p2=new AI();

    public ChooseAI() {
        super("MineSweeper");

        ImageIcon icon=new ImageIcon("C:\\Users\\zhouyy\\Desktop\\project\\essay\\新建文件夹\\微信图片_20210517001312.jpg");
        JLabel label=new JLabel(icon);
        label.setBounds(-710,-710,350+1610,1110+500);
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        JPanel j=(JPanel)this.getContentPane();
        j.setOpaque(false);
        setLayout(new GridLayout(4, 1));
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel(new GridLayout(1, 3, 20, 20));
        JPanel panel3 = new JPanel(new GridLayout(2, 1, 20, 20));//
        JPanel panel4 = new JPanel();
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        panel4.setOpaque(false);

        title = new JLabel("Please set the player");
        title.setFont(new Font("San Serif", Font.PLAIN, 30));
        panel1.add(title);

        NewTon = new JButton("Hero1: NewTon");
        NewTon.setLocation(20, 20);
        NewTon.setSize(60, 20);
        panel2.add(NewTon);
        NewTon.addActionListener(this);

        Maxwell = new JButton("Hero1: Maxwell");
        Maxwell.setLocation(80, 20);
        Maxwell.setSize(60, 20);
        panel2.add(Maxwell);
        Maxwell.addActionListener(this);

        Einstein = new JButton("Hero1: Einstein");
        Einstein.setLocation(140, 20);
        Einstein.setSize(60, 20);
        panel2.add(Einstein);
        Einstein.addActionListener(this);

        SetName = new JLabel("Please type the name");
        SetName.setFont(new Font("San Serif", Font.PLAIN, 30));
        panel3.add(SetName);

        Name = new JTextField("");
        panel3.add(Name);
        Name.addActionListener(this);

        btnNext = new JButton("Next");
        panel4.add(btnNext);
        btnNext.addActionListener(this);

    }

    public static void OpenChoosePlayer(ChooseAI chooseAI) {
        chooseAI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooseAI.setSize(500, 500);//随便写的 后面改吧
        chooseAI.setLocation(800, 250);
        chooseAI.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == Name) {
            player.setUserName(Name.getText());
        }
        if (event.getSource() == NewTon) {
            player = new Newton();
        }
        if (event.getSource() == Maxwell) {
            player = new Maxwell();
        }
        if (event.getSource() == Einstein) {
            player = new Einstein();
        }
        if (event.getSource() == btnNext) {
            ChooseSingleLevel chooseLevel = new ChooseSingleLevel();
            ChooseSingleLevel.OpenChooseSingleLevel(chooseLevel);
            dispose();
        }

    }

}

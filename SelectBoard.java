package DisplayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

public class SelectBoard extends JFrame implements ActionListener {
    private JLabel title;
    private JButton btnHuman, btnAI;

    public SelectBoard() {
        super("MineSweeper");
        ImageIcon icon=new ImageIcon("C:\\Users\\zhouyy\\Desktop\\project\\essay\\新建文件夹\\微信图片_20210517001320.jpg");
        JLabel label=new JLabel(icon);
        label.setBounds(-410,-410,350+410,410+500);
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        JPanel j=(JPanel)this.getContentPane();
        j.setOpaque(false);
        JPanel panel=new JPanel();

        setLayout(new GridLayout(2, 1));//上下两个
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel(new GridLayout(2, 1, 40, 20));
        panel.add(panel1);
        panel.add(panel2);
        panel.setOpaque(false);
        this.add(panel);
        this.add(panel);
        panel1.setOpaque(false);
        panel2.setOpaque(false);

        title = new JLabel("Please choose a mode");
        title.setFont(new Font("San Serif", Font.PLAIN, 25));
        panel1.add(title);

        btnHuman = new JButton("Double Players");
        panel2.add(btnHuman);
        btnHuman.addActionListener(this);
        btnHuman.setActionCommand("open");

        btnAI = new JButton("Single Player");
        panel2.add(btnAI);
        btnAI.addActionListener(this);
        btnAI.setActionCommand("open");
    }

    public static void OpenSelectBoard(SelectBoard selectBoard) {
        selectBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        selectBoard.setSize(350, 500);//随便写的 后面改吧
        selectBoard.setLocation(800,250);
        selectBoard.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        String cmd = event.getActionCommand();
        if (cmd.equals("open")) {
            if (event.getSource() == btnHuman) {
                ChoosePlayer choosePlayer=new ChoosePlayer();
                ChoosePlayer.OpenChoosePlayer(choosePlayer);
                dispose();
            } else if (event.getSource() == btnAI) {
               ChooseAI chooseAI=new ChooseAI();
               ChooseAI.OpenChoosePlayer(chooseAI);
               dispose();
                }


    }
}}

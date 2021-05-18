package DisplayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Documents extends JFrame {
    private JLabel title;
    private JTextField name;
    private JButton btnStart;

public Documents(){
    super("MineSweeper");

    setLayout(new GridLayout(2, 1));//上下两个
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel(new GridLayout(1, 3, 40, 20));
    add(panel1);
    add(panel2);

    title = new JLabel("Documents");
    title.setFont(new Font("San Serif", Font.PLAIN, 20));
    panel1.add(title);



}



}

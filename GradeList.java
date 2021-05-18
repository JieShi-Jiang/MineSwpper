package DisplayPanel;

import com.sun.javafx.collections.MappingChange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class GradeList extends JFrame implements ActionListener {

    private JLabel title, easy, medium, hard, easy1, easy2, easy3, medium1, medium2, medium3, hard1, hard2, hard3;
    private JLabel Level,First,Second,Third;
    private JButton back;
    private String easy1Name, easy2Name, easy3Name, medium1Name, medium2Name, medium3Name, hard1Name, hard2Name, hard3Name;
    private Set<String> easyList, mediumList, hardList;

    public static Map<String, Integer> GradeList1= new HashMap<>();
    public static Map<String, Integer> GradeList2= new HashMap<>();
    public static Map<String, Integer> GradeList3= new HashMap<>();

    public static Map<String, Integer> getGradeList(File file) {
        Map<String, Integer> GradeList = new HashMap<>();
        FileReader fr;
        LineNumberReader reader = null;
        String txt = null;

        try {
            fr = new FileReader(file);
            reader = new LineNumberReader(fr);
            txt = reader.readLine();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        assert txt != null;
        String[] Couples = txt.split(";");

        for (int i = 0; i < Couples.length; i++) {
            String[] everyCouple = Couples[i].split(",");
            //System.out.println(everyCouple[0] + " " + everyCouple[1]);
            GradeList.put(everyCouple[0], Integer.parseInt(everyCouple[1]));
        }
        return GradeList;
    }

    public GradeList() {
        super("MineSweeper");

        ImageIcon icon=new ImageIcon("C:\\Users\\zhouyy\\Desktop\\project\\essay\\新建文件夹\\微信图片_20210517001337.jpg");
        icon.setImage(icon.getImage().getScaledInstance(350+710,710+500,Image.SCALE_DEFAULT));
        JLabel label=new JLabel(icon);
        label.setBounds(-710,-710,2000+350,2000+500);
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        JPanel g=(JPanel)this.getContentPane();
        g.setOpaque(false);
        JPanel panel=new JPanel();
        //panel.setSize(350,500);


        File file1 = new File("D:\\Easy GradeList.txt");
        File file2 = new File("D:\\Medium GradeList.txt");
        File file3 = new File("D:\\Hard GradeList.txt");

        GradeList1=getGradeList(file1);
        GradeList2=getGradeList(file2);
        GradeList3=getGradeList(file3);

        easyList = GradeList1.keySet();
        System.out.println(easyList);
        mediumList = GradeList2.keySet();
        hardList = GradeList3.keySet();

        String[] easyName = new String[easyList.size()];
        easyList.toArray(easyName);
        //System.out.println(easyName);
        for (int i = 0; i < easyName.length - 1; i++) {
            for (int j = 0; j < easyName.length - 1 - i; j++) {
                if (GradeList1.get(easyName[j]) < GradeList1.get(easyName[j + 1])) {
                    String mid = easyName[j];
                    easyName[j] = easyName[j + 1];
                    easyName[j + 1] = mid;
                }
            }
        }
        easy1Name = easyName[0];
        easy2Name = easyName[1];
        easy3Name = easyName[2];

        String[] mediumName = new String[mediumList.size()];
        mediumList.toArray(mediumName);
        for (int i = 0; i < mediumName.length - 1; i++) {
            for (int j = 0; j < mediumName.length - 1 - i; j++) {
                if (GradeList2.get(mediumName[j]) < GradeList2.get(mediumName[j + 1])) {
                    String mid = mediumName[j];
                    mediumName[j] = mediumName[j + 1];
                    mediumName[j + 1] = mid;
                }
            }
        }
        medium1Name = mediumName[0];
        medium2Name = mediumName[1];
        medium3Name = mediumName[2];

        String[] hardName = new String[hardList.size()];
        hardList.toArray(hardName);
        for (int i = 0; i < hardName.length - 1; i++) {
            for (int j = 0; j < hardName.length - 1 - i; j++) {
                if (GradeList3.get(hardName[j]) < GradeList3.get(hardName[j + 1])) {
                    String mid = hardName[j];
                    hardName[j] = hardName[j + 1];
                    hardName[j + 1] = mid;
                }
            }
        }
        hard1Name = hardName[0];
        hard2Name = hardName[1];
        hard3Name = hardName[2];

        setLayout(new GridLayout(3, 1));//上下两个
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel(new GridLayout(4, 4, 40, 20));
        JPanel panel3=new JPanel();
        add(panel1);
        add(panel2);
        add(panel3);
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);

        title = new JLabel("Ranking List");
        title.setFont(new Font("San Serif", Font.PLAIN, 20));
        panel1.add(title);

        Level= new JLabel("Level");
        Level.setFont(new Font("San Serif", Font.PLAIN, 20));
        panel2.add(Level);

        easy = new JLabel("easy");
        easy.setFont(new Font("San Serif", Font.PLAIN, 20));
        panel2.add(easy);

        medium = new JLabel("medium");
        medium.setFont(new Font("San Serif", Font.PLAIN, 20));
        panel2.add(medium);

        hard = new JLabel("hard");
        hard.setFont(new Font("San Serif", Font.PLAIN, 20));
        panel2.add(hard);

        First = new JLabel("First");
        First.setFont(new Font("San Serif", Font.PLAIN, 20));
        panel2.add(First);

        easy1 = new JLabel(easy1Name);
        easy1.setFont(new Font("San Serif", Font.PLAIN, 20));
        panel2.add(easy1);

        medium1 = new JLabel(medium1Name);
        medium1.setFont(new Font("San Serif", Font.PLAIN, 20));
        panel2.add(medium1);

        hard1 = new JLabel(hard1Name);
        hard1.setFont(new Font("San Serif", Font.PLAIN, 20));
        panel2.add(hard1);

        Second = new JLabel("Second");
        Second.setFont(new Font("San Serif", Font.PLAIN, 20));
        panel2.add(Second);

        easy2 = new JLabel(easy2Name);
        easy2.setFont(new Font("San Serif", Font.PLAIN, 20));
        panel2.add(easy2);

        medium2 = new JLabel(medium2Name);
        medium2.setFont(new Font("San Serif", Font.PLAIN, 20));
        panel2.add(medium2);

        hard2 = new JLabel(hard2Name);
        hard2.setFont(new Font("San Serif", Font.PLAIN, 20));
        panel2.add(hard2);

        Third = new JLabel("Third");
        Third.setFont(new Font("San Serif", Font.PLAIN, 20));
        panel2.add(Third);

        easy3 = new JLabel(easy3Name);
        easy3.setFont(new Font("San Serif", Font.PLAIN, 20));
        panel2.add(easy3);

        medium3 = new JLabel(medium3Name);
        medium3.setFont(new Font("San Serif", Font.PLAIN, 20));
        panel2.add(medium3);

        hard3 = new JLabel(hard3Name);
        hard3.setFont(new Font("San Serif", Font.PLAIN, 20));
        panel2.add(hard3);

        back=new JButton("return to menu");
        back.setFont(new Font("San Serif", Font.PLAIN, 20));
        back.addActionListener(this);
        panel3.add(back);

    }

    public static void OpenGradeLis(GradeList gradeList) {
        gradeList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gradeList.setSize(600, 600);//随便写的 后面改吧
        gradeList.setLocation(800,250);
        gradeList.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            Menu.OpenMenu();
            dispose();
        }
    }
}
package components;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Random;

public class Music extends Thread{
    private File f;
    private URI uri;
    private URL url;
    Random random=new Random();
    AudioClip aau;


    public void run(int x) { // 注意，java只能播放无损音质，如.wav这种格式
        try {
            if(x==-1){
                if(random.nextInt(2)>=1) {
                    f = new File("C:\\Users\\zhouyy\\Desktop\\project\\essay\\倒下了.wav"); // 绝对路径
                }else {
                    f = new File("C:\\Users\\zhouyy\\Desktop\\project\\essay\\中弹了.wav");
                }// 绝对路径
            }else if(x==0){
                if(random.nextInt(2)>=1) {
                    f = new File("C:\\Users\\zhouyy\\Desktop\\project\\essay\\牛逼啊.wav"); // 绝对路径
                }else {
                    f = new File("C:\\Users\\zhouyy\\Desktop\\project\\essay\\你丫行啊.wav");
                }// 绝对路径
            }else if(x==1) {
                if (random.nextInt(2) >= 1) {
                    f = new File("C:\\Users\\zhouyy\\Desktop\\project\\essay\\差点把小命丢了.wav"); // 绝对路径
                } else {
                    f = new File("C:\\Users\\zhouyy\\Desktop\\project\\essay\\差点就炸着我了.wav");
                }// 绝对路径
            }else if(x==2) {
                if (random.nextInt(2) >= 1) {
                    f = new File("C:\\Users\\zhouyy\\Desktop\\project\\essay\\感激不尽.wav"); // 绝对路径
                } else {
                    f = new File("C:\\Users\\zhouyy\\Desktop\\project\\essay\\感谢.wav");
                }// 绝对路径
            }else if(x==3) {
                f = new File("C:\\Users\\zhouyy\\Desktop\\project\\essay\\漂亮.wav"); // 绝对路径
            }else if(x==1000) {
                f = new File("C:\\Users\\zhouyy\\Desktop\\project\\essay\\欢乐斗地主背景音乐全收录.wav"); // 绝对路径
            }else if(x==4) {
                f = new File("C:\\Users\\zhouyy\\Desktop\\project\\essay\\治疗完毕.wav"); // 绝对路径
            }else if(x==5) {
                f = new File("C:\\Users\\zhouyy\\Desktop\\project\\essay\\你可以继续战斗啦.wav"); // 绝对路径
            }else if(x==6) {
                f = new File("C:\\Users\\zhouyy\\Desktop\\project\\essay\\谢了.wav"); // 绝对路径
            }else if(x==7) {
                f = new File("C:\\Users\\zhouyy\\Desktop\\project\\essay\\尝尝我的厉害.wav"); // 绝对路径
            }
            uri = f.toURI();
            url = uri.toURL(); // 解析路径
            aau = Applet.newAudioClip(url);
            aau.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopMusic(){
        aau.stop();
    }

}
//f = new File("C:\\Users\\zhouyy\\Desktop\\essay\\中弹了.wav"); // 绝对路径
//f = new File("C:\\Users\\zhouyy\\Desktop\\essay\\漂亮.wav"); // 绝对路径
//f = new File("C:\\Users\\zhouyy\\Desktop\\essay\\差点把小命丢了.wav"); // 绝对路径




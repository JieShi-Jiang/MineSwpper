package components;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public abstract class BasicComponent extends JComponent {
    public BasicComponent() {
        initial();
    }

    private void initial(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//有没有点击
                super.mouseClicked(e);
                if(e.getButton()==1){//点击左键
                    try {
                        onMouseLeftClicked();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                if(e.getButton()==2){//点击中间
                    onMouseMidClicked();
                }
                if(e.getButton()==3){//点击右键
                    try {
                        onMouseRightClicked();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * invoke this method when mouse left clicked
     */
    public abstract void onMouseLeftClicked() throws IOException;

    /**
     * invoke this method when mouse right clicked
     */
    public abstract void onMouseRightClicked() throws IOException;

    public abstract void onMouseMidClicked();

}

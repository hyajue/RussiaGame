package russiagame;

/**
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PreviewPanel extends Panel {
    private GameTable gPreviewTable;
    private Image myImage0, myImage1; //加载两个图片，用来覆盖背景和组成方块
    private int intWhich;

    public PreviewPanel(PreviewTable gPreviewTable) {
        intWhich = 0;
        myImage0 = getToolkit().getImage("b0.jpg");
        myImage1 = getToolkit().getImage("b1.jpg");
    }

    public void PreviewGraph(int intWhich) {
        this.intWhich = intWhich;
        this.repaint();
    }

    public void paint(Graphics g) { //画下一个提前显示的方块
        for (int i = 0; i < 5; i++) //先全部用白方块覆盖
            for (int j = 0; j < 4; j++)
                g.drawImage(myImage0, 0 + i * (15 + 2), 0 + j * (15 + 2), this);

        switch (intWhich) { //根据标志决定画哪个方块，画蓝方块
            case 1:
                g.drawImage(myImage1, 34, 0, this);
                g.drawImage(myImage1, 34, 17, this);
                g.drawImage(myImage1, 34, 34, this);
                g.drawImage(myImage1, 34, 51, this);

                break;
            case 2:
                g.drawImage(myImage1, 17, 17, this);
                g.drawImage(myImage1, 17, 34, this);
                g.drawImage(myImage1, 34, 17, this);
                g.drawImage(myImage1, 34, 34, this);
                break;
            case 3:
                g.drawImage(myImage1, 17, 17, this);
                g.drawImage(myImage1, 17, 34, this);
                g.drawImage(myImage1, 34, 0, this);
                g.drawImage(myImage1, 34, 17, this);
                break;
            case 4:
                g.drawImage(myImage1, 17, 0, this);
                g.drawImage(myImage1, 17, 17, this);
                g.drawImage(myImage1, 34, 17, this);
                g.drawImage(myImage1, 34, 34, this);
                break;
            case 5:
                g.drawImage(myImage1, 17, 0, this);
                g.drawImage(myImage1, 17, 17, this);
                g.drawImage(myImage1, 17, 34, this);
                g.drawImage(myImage1, 34, 0, this);
                break;
            case 6:
                g.drawImage(myImage1, 17, 0, this);
                g.drawImage(myImage1, 34, 0, this);
                g.drawImage(myImage1, 34, 17, this);
                g.drawImage(myImage1, 34, 34, this);
                break;
            case 7:
                g.drawImage(myImage1, 34, 17, this);
                g.drawImage(myImage1, 34, 34, this);
                g.drawImage(myImage1, 17, 34, this);
                g.drawImage(myImage1, 51, 34, this);
                break;
            case 8:
                g.drawImage(myImage1, 34, 17, this);
                break;
            default:
                break;
        }
    }
}

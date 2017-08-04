package russiagame;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PreviewPanel extends Panel {
    private GameTable gPreviewTable;
    private Image myImage0, myImage1; //��������ͼƬ���������Ǳ�������ɷ���
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

    public void paint(Graphics g) { //����һ����ǰ��ʾ�ķ���
        for (int i = 0; i < 5; i++) //��ȫ���ð׷��鸲��
            for (int j = 0; j < 4; j++)
                g.drawImage(myImage0, 0 + i * (15 + 2), 0 + j * (15 + 2), this);

        switch (intWhich) { //���ݱ�־�������ĸ����飬��������
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
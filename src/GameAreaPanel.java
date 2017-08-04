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

public class GameAreaPanel extends Panel {
    private Timer timer; //��ʱ�������ڿ�������ʱ����
    private int nTime = 1000; //�ٶȣ�������ʱ����ʹ��
    private Root root; //Ҫ���µķ�������ã�������
    private GameTable gTable; //����һ����Ϸ��
    private int nWhich; //��־������һ��Ҫ����ķ���
    private int tempnWhich; //��־��һ��Ҫ�����ķ��飬����ǰ��ʾ�����
    private int nScore = 0; //ÿ��һ�м�10�֣�������¼�ܷ���
    private boolean canMove = false; //��־�Ƿ���Ӧ����
    private Image myImage0, myImage1; //��������ͼƬ���������Ǳ�������ɷ���
    private boolean isRun = true; //��־�ǿ�ʼ������ͣ��һť����

    public GameAreaPanel(GameTable gTable) {
        myImage0 = getToolkit().getImage("b0.jpg");
        myImage1 = getToolkit().getImage("b1.jpg");

        gTable.myTable = new int[gTable.x][gTable.y];
        for (int i = 0; i < gTable.x; i++)
            for (int j = 0; j < gTable.y; j++)
                gTable.myTable[i][j] = 0;
    }

    public void Init() {
        for (int i = 0; i < gTable.x; i++) //���¸���Ϸ����0��־
            for (int j = 0; j < gTable.y; j++) {
                gTable.myTable[i][j] = 0;
            }
        repaint();
    }

    public void paint(Graphics g) {
        for (int i = 0; i < gTable.x; i++) //���������ϵı�Ǿ����Ƿ񻭷���
            for (int j = 0; j < gTable.y; j++) {
                if (gTable.myTable[i][j] == 1) { //�У���������
                    g.drawImage(myImage1, 0 + i * (15 + 2) + 2,
                                0 + j * (15 + 2) + 2, this);
                }
                else if (gTable.myTable[i][j] == 0) { //�ޣ����׷���
                    g.drawImage(myImage0, 0 + i * (15 + 2) + 2,
                                0 + j * (15 + 2) + 2, this);
                }
            }
    }

    public void actionPerformed(ActionEvent e) {
        if (isRun) { //�ǿ�ʼ
            timer.start();
            isRun = !isRun;
            canMove = true; //������Ӧ����
        }
        else { //����ͣ
            timer.stop();
            isRun = !isRun;
            canMove = false; //��������Ӧ����
        }
    }

    public void UpdateGraph(GameTable gameTable) {
        this.gTable = gameTable;
        this.repaint();
    }

    public void update(Graphics g) {
        paint(g); //��ֹ��˸
    }
}
package russiagame;

/**

 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameAreaPanel extends Panel {
    private Timer timer; //记时器，用于控制下落时间间隔
    private int nTime = 1000; //速度，创建记时器是使用
    private Root root; //要落下的方块的引用，即根类
    private GameTable gTable; //创建一个游戏桌
    private int nWhich; //标志创建哪一个要下落的方块
    private int tempnWhich; //标志下一个要创建的方块，即提前显示下落块
    private int nScore = 0; //每消一行加10分，用来记录总分数
    private boolean canMove = false; //标志是否响应键盘
    private Image myImage0, myImage1; //加载两个图片，用来覆盖背景和组成方块
    private boolean isRun = true; //标志是开始还是暂停，一钮两用

    public GameAreaPanel(GameTable gTable) {
        myImage0 = getToolkit().getImage("b0.jpg");
        myImage1 = getToolkit().getImage("b1.jpg");

        gTable.myTable = new int[gTable.x][gTable.y];
        for (int i = 0; i < gTable.x; i++)
            for (int j = 0; j < gTable.y; j++)
                gTable.myTable[i][j] = 0;
    }

    public void Init() {
        for (int i = 0; i < gTable.x; i++) //重新给游戏桌置0标志
            for (int j = 0; j < gTable.y; j++) {
                gTable.myTable[i][j] = 0;
            }
        repaint();
    }

    public void paint(Graphics g) {
        for (int i = 0; i < gTable.x; i++) //根据桌子上的标记决定是否画方块
            for (int j = 0; j < gTable.y; j++) {
                if (gTable.myTable[i][j] == 1) { //有，画蓝方块
                    g.drawImage(myImage1, 0 + i * (15 + 2) + 2,
                                0 + j * (15 + 2) + 2, this);
                }
                else if (gTable.myTable[i][j] == 0) { //无，画白方块
                    g.drawImage(myImage0, 0 + i * (15 + 2) + 2,
                                0 + j * (15 + 2) + 2, this);
                }
            }
    }

    public void actionPerformed(ActionEvent e) {
        if (isRun) { //是开始
            timer.start();
            isRun = !isRun;
            canMove = true; //可以响应键盘
        }
        else { //是暂停
            timer.stop();
            isRun = !isRun;
            canMove = false; //不可以响应键盘
        }
    }

    public void UpdateGraph(GameTable gameTable) {
        this.gTable = gameTable;
        this.repaint();
    }

    public void update(Graphics g) {
        paint(g); //防止闪烁
    }
}

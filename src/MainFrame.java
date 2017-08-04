package russiagame;

import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.*;
import java.awt.Toolkit;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MainFrame
    extends JFrame {
    private Timer timer; //方块下落时间间隔控制类
    private int nTime = 1000; //方块下落速度
    private Root root; //方块
    private GameTable gGameAreaTable; //方块下落区
    private PreviewTable gPreviewTable; //方块预览区

    private int intGraph; //标志创建哪一个要下落的方块
    private int intNextGraph; //标志下一个要创建的方块
    private int intTotalScore = 0; //用来记录总分数
    private boolean bCanMove = false; //标志是否响应键盘
    private int intSpeed = 1; //速度等级

    private Panel jMainPanel = new Panel(); //添加键盘监听的Panel
    private GameAreaPanel jGamePanel; //方块下落区域
    private PreviewPanel jNextGraphPanel; //方块预览区域

    private XYLayout xyLayout = new XYLayout(); //界面布局
    private JButton jStartButton = new JButton();
    private JButton jRestartButton = new JButton();
    private JButton jExitButton = new JButton();
    private JLabel jSpeedLabel = new JLabel();
    private JSpinner jSpeedSpinner = new JSpinner();
    private JLabel jScoreLabel = new JLabel();
    private JTextField jScoreTextField = new JTextField();
    private JLabel jNextGraphLabel = new JLabel();
    private TitledBorder titledBorder3;
    private JLabel jCopyRightLabel = new JLabel();
    private TitledBorder titledBorder1;
    private TitledBorder titledBorder4;
    private TitledBorder titledBorder5;
    private TitledBorder titledBorder6;
    private TitledBorder titledBorder7;
    private TitledBorder titledBorder8;

    public MainFrame() {
        try {
            setTitle("俄罗斯方块游戏");
            setLocation(200, 130);
            setSize(600, 400);
            jbInit();
            getContentPane().add(jMainPanel, "North");
            getContentPane().addKeyListener(new KeyAdapter() { //主窗口添加键盘监听器
                public void keyPressed(KeyEvent e) {
                    ProcessKeyEvent(e);
                }
            });

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    dispose();
                }
            });

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MainFrame objMainFrame = new MainFrame();
        objMainFrame.pack();
        objMainFrame.setVisible(true);
        objMainFrame.validate();
        objMainFrame.setResizable(false);
    }

    private void jbInit() throws Exception {
        jMainPanel.setBackground(new Color(72, 112, 112));
        jMainPanel.setForeground(UIManager.getColor(
            "TextArea.selectionBackground"));
        jMainPanel.setLayout(xyLayout);

        gGameAreaTable = new GameTable(15, 26);
        gPreviewTable = new PreviewTable(5, 4);

        jGamePanel = new GameAreaPanel(gGameAreaTable);
        jNextGraphPanel = new PreviewPanel(gPreviewTable);

        jStartButton.setBackground(Color.pink);
        jStartButton.setFont(new java.awt.Font("Dialog", 0, 14));
        jStartButton.setForeground(SystemColor.desktop);
        jStartButton.setBorder(titledBorder1);
        jStartButton.setBorderPainted(true);
        jStartButton.setText("开始游戏");
        jStartButton.addActionListener(new MainFrame_jStartButton_actionAdapter(this));

        jRestartButton.setBackground(Color.pink);
        jRestartButton.setFont(new java.awt.Font("Dialog", 0, 14));
        jRestartButton.setForeground(SystemColor.desktop);
        jRestartButton.setBorder(titledBorder1);
        jRestartButton.setText("重新游戏");
        jRestartButton.addActionListener(new
                                         MainFrame_jRestartButton_actionAdapter(this));

        jExitButton.setBackground(Color.pink);
        jExitButton.setFont(new java.awt.Font("Dialog", 0, 14));
        jExitButton.setForeground(SystemColor.desktop);
        jExitButton.setBorder(titledBorder1);
        jExitButton.setText("退出游戏");
        jExitButton.addActionListener(new MainFrame_jExitButton_actionAdapter(this));
        jExitButton.addActionListener(new MainFrame_jExitButton_actionAdapter(this));

        jSpeedLabel.setFont(new java.awt.Font("SansSerif", 0, 14));
        jSpeedLabel.setForeground(Color.orange);
        jSpeedLabel.setBorder(titledBorder6);
        jSpeedLabel.setText("速度选择");
        jSpeedSpinner.setValue(new Integer(1));
        jSpeedSpinner.addChangeListener(new SpeedChangeListener());

        jScoreLabel.setText("分数记录");
        jScoreLabel.setForeground(Color.orange);
        jScoreLabel.setBorder(titledBorder6);
        jScoreLabel.setDebugGraphicsOptions(0);
        jScoreLabel.setFont(new java.awt.Font("SansSerif", 0, 14));
        jScoreTextField.setBackground(Color.pink);
        jScoreTextField.setBorder(null);
        jScoreTextField.setEditable(false);
        jScoreTextField.setText("0");
        jNextGraphLabel.setFont(new java.awt.Font("SansSerif", 0, 14));
        jNextGraphLabel.setForeground(Color.orange);
        jNextGraphLabel.setBorder(titledBorder6);
        jNextGraphLabel.setText("下一个图形");

        jGamePanel.setBackground(new Color(0, 118, 125));
        jCopyRightLabel.setText("Savage制作，2003-2010 Copyright...");
        jCopyRightLabel.setForeground(Color.orange);
        jCopyRightLabel.setFont(new java.awt.Font("SansSerif", 0, 14));

        xyLayout.setWidth(407);
        xyLayout.setHeight(504);
        jSpeedSpinner.setBackground(SystemColor.desktop);
        jSpeedSpinner.setBorder(BorderFactory.createLoweredBevelBorder());
        jMainPanel.add(jGamePanel, new XYConstraints(20, 21, 257, 447));
        jMainPanel.add(jNextGraphLabel, new XYConstraints(299, 159, 78, 28));
        jMainPanel.add(jCopyRightLabel, new XYConstraints(62, 472, 246, 28));
        jMainPanel.add(jRestartButton, new XYConstraints(299, 367, 88, 30));
        jMainPanel.add(jStartButton, new XYConstraints(299, 311, 88, 30));
        jMainPanel.add(jNextGraphPanel, new XYConstraints(299, 183, 78, 88));
        jMainPanel.add(jScoreLabel, new XYConstraints(299, 79, 68, 28));
        jMainPanel.add(jSpeedLabel, new XYConstraints(299, 7, 78, 28));
        jMainPanel.add(jExitButton, new XYConstraints(299, 419, 88, 31));
        jMainPanel.add(jScoreTextField, new XYConstraints(299, 110, 68, 24));
        jMainPanel.add(jSpeedSpinner, new XYConstraints(300, 32, 69, 25));

        intGraph = ( ( (int) Math.round(Math.random() * 12345)) % 8) + 1; //产生随机方块
        switch (intGraph) { //初始化产生的方块
            case 1:
                root = new One();
                break;
            case 2:
                root = new Two();
                break;
            case 3:
                root = new Three();
                break;
            case 4:
                root = new Four();
                break;
            case 5:
                root = new Five();
                break;
            case 6:
                root = new Six();
                break;
            case 7:
                root = new Seven();
                break;
            case 8:
                root = new Eight();
                break;
            default:
                break;
        }

        jNextGraphPanel.PreviewGraph(intGraph);
        root.begin(); //占据桌子，详见root类
        intNextGraph = ( ( (int) Math.round(Math.random() * 12345)) % 8) + 1; //产生下一个提前显示的方块
        intGraph = intNextGraph;
        timer = new Timer(nTime, new MyRun()); //创建记时器，调用MyRun

        addKeyListener(new KeyAdapter() { //主窗口添加键盘监听器
            public void keyPressed(KeyEvent e) {
                ProcessKeyEvent(e);
            }
        });
    }

    public class MyRun
        implements ActionListener { //记时器调用的监听器
        public void actionPerformed(ActionEvent e) {
            if (!bCanMove)
                return;

            if (!root.down()) { //将方块下落一格，如果不能再下降了，就调用下面语句
                DeleteLine(); //消行

                jNextGraphPanel.PreviewGraph(intGraph);
                switch (intGraph) { //产生新的方块
                    case 1:
                        root = new One();
                        break;
                    case 2:
                        root = new Two();
                        break;
                    case 3:
                        root = new Three();
                        break;
                    case 4:
                        root = new Four();
                        break;
                    case 5:
                        root = new Five();
                        break;
                    case 6:
                        root = new Six();
                        break;
                    case 7:
                        root = new Seven();
                        break;
                    case 8:
                        root = new Eight();
                        break;
                    default:
                        break;
                }
                intNextGraph = ( ( (int) Math.round(Math.random() * 12345)) % 8) +
                    1;
                intGraph = intNextGraph;
                if (!root.begin()) { //如果失败，游戏结束
                    jGamePanel.UpdateGraph(gGameAreaTable);
                    GameResetGamePara();
                }
                else
                    jGamePanel.UpdateGraph(gGameAreaTable);
            }
            else {
                jGamePanel.UpdateGraph(gGameAreaTable);
            }
        }
    }

    public void ProcessKeyEvent(KeyEvent e) { //判断输入的是哪个键，并做出响应
        jNextGraphPanel.PreviewGraph(intGraph);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN: //如果是向下键，就一下到底，并产生一个新的方块
                root.downTo();
                DeleteLine(); //消去添满的一行，并加10分
                switch (intGraph) { //产生新的方块
                    case 1:
                        root = new One();
                        break;
                    case 2:
                        root = new Two();
                        break;
                    case 3:
                        root = new Three();
                        break;
                    case 4:
                        root = new Four();
                        break;
                    case 5:
                        root = new Five();
                        break;
                    case 6:
                        root = new Six();
                        break;
                    case 7:
                        root = new Seven();
                        break;
                    case 8:
                        root = new Eight();
                        break;
                    default:
                        break;
                }
                intNextGraph = ( ( (int) Math.round(Math.random() * 12345)) % 8) +
                    1;
                intGraph = intNextGraph;
                if (!root.begin()) { //如果新的方块产生失败，则游戏结束
                    //jGamePanel.UpdateGraph(gGameAreaTable);
                    GameResetGamePara();
                }
                else {
                    jGamePanel.UpdateGraph(gGameAreaTable);
                }
                break;
            case KeyEvent.VK_LEFT:
                root.left();
                jGamePanel.UpdateGraph(gGameAreaTable);
                break; //详见root类
            case KeyEvent.VK_RIGHT:
                root.right();
                jGamePanel.UpdateGraph(gGameAreaTable);
                break;
            case KeyEvent.VK_UP:
                root.change();
                jGamePanel.UpdateGraph(gGameAreaTable);
                break;
            default:
                break;
        }
    }

    public void DeleteLine() { //消去添满的一行，并加10分
        boolean isCan; //标志是否循环消一行
        boolean isContinue = true; //标志是否还有待消的行
        int k = gGameAreaTable.y - 1;

        while (isContinue) {
            isCan = true;
            while (isCan) {
                for (int i = 0; i < gGameAreaTable.x; i++) {
                    if (gGameAreaTable.myTable[i][k] != 1)
                        isCan = false;
                }
                if (isCan) { //满足条件，开始消行
                    for (int i = 0; i < gGameAreaTable.x; i++)
                        gGameAreaTable.myTable[i][k] = 0;
                    for (int j = k - 1; j >= 0; j--)
                        for (int i = 0; i < gGameAreaTable.x; i++) {
                            if (gGameAreaTable.myTable[i][j] == 1) {
                                gGameAreaTable.myTable[i][j + 1] = 1;
                                gGameAreaTable.myTable[i][j] = 0;
                            }
                        }
                    jGamePanel.UpdateGraph(gGameAreaTable);
                    intTotalScore += 10; //加成绩
                    UpdateScore(intTotalScore);
                }
            }
            k--;
            if (k < 1)
                isContinue = false;
        }
    }

    public void GameResetGamePara() { //游戏结束
        timer.stop();
        bCanMove = false;
        JOptionPane anOptionPane = new JOptionPane(); //弹出对话框
        anOptionPane.showMessageDialog(this, "游戏结束，再来一次？", "Game Over！",
                                       JOptionPane.WARNING_MESSAGE);
        bCanMove = false;
        ResetGamePara(); //处理各个变量，以便重新开始
    }

//游戏结束后，处理各个变量，以便重新开始
    public void ResetGamePara() {
        timer.stop();
        for (int i = 0; i < gGameAreaTable.x; i++) //重新给游戏桌置0标志
            for (int j = 0; j < gGameAreaTable.y; j++) {
                gGameAreaTable.myTable[i][j] = 0;
            }
        jGamePanel.UpdateGraph(gGameAreaTable);

        bCanMove = false;
        intTotalScore = 0;
        jScoreTextField.setText( (new Integer(intTotalScore)).toString());
        nTime = 1000;
        intSpeed = 1;
        jSpeedSpinner.setValue(new Integer(intSpeed));

        switch (intGraph) { //产生新的方块
            case 1:
                root = new One();
                break;
            case 2:
                root = new Two();
                break;
            case 3:
                root = new Three();
                break;
            case 4:
                root = new Four();
                break;
            case 5:
                root = new Five();
                break;
            case 6:
                root = new Six();
                break;
            case 7:
                root = new Seven();
                break;
            case 8:
                root = new Eight();
                break;
            default:
                break;
        }
        intNextGraph = ( ( (int) Math.round(Math.random() * 12345)) % 8) + 1;
        intGraph = intNextGraph;
        jGamePanel.UpdateGraph(gGameAreaTable);
        root.begin();
    }

    void jStartButton_actionPerformed(ActionEvent e) {
        jStartButton.setFocusable(false);
        jRestartButton.setFocusable(false);
        jExitButton.setFocusable(false);
        this.getContentPane().setFocusable(true);
        this.getContentPane().setFocusCycleRoot(true);
        bCanMove = true;

        timer = new Timer(nTime, new MyRun()); //根据选择的nTime间隔重新设置记时器，以变换速度
        timer.start();
    }

    void jRestartButton_actionPerformed(ActionEvent e) {
        timer.stop();
        jGamePanel.Init();
        bCanMove = false;
    }

    void jExitButton_actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    public class SpeedChangeListener
        implements ChangeListener { //速度控件监听器
        public void stateChanged(ChangeEvent e) {
            if (e.getSource() == jSpeedSpinner) {
                String strSpeed = new String(jSpeedSpinner.getValue().toString());
                intSpeed = Integer.parseInt(strSpeed);
                if (intSpeed < 1)
                    intSpeed = 1;
                if (intSpeed > 9)
                    intSpeed = 9;
                jSpeedSpinner.setValue(new Integer(intSpeed));
            }
            UpdateSpeed();
        }
    }

    public void UpdateSpeed() {
        jSpeedSpinner.setValue(new Integer(intSpeed));
        String strSpeed = new String(jSpeedSpinner.getValue().toString());
        intSpeed = Integer.parseInt(strSpeed);

        nTime = 1000 - (intSpeed * 110);
        timer = new Timer(nTime, new MyRun()); //根据选择的nTime间隔重新设置记时器，以变换速度
        if (bCanMove)
            timer.start();
    }

    public void UpdateScore(int intScore) {
        if (intScore > intSpeed * 10)
            intSpeed++;
        if (intSpeed > 9)
            intSpeed = 1;

        UpdateSpeed();
        jScoreTextField.setText( (new Integer(intScore)).toString());
    }
}

class MainFrame_jStartButton_actionAdapter
    implements java.awt.event.ActionListener {
    MainFrame adaptee;

    MainFrame_jStartButton_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jStartButton_actionPerformed(e);
    }
}

class MainFrame_jRestartButton_actionAdapter
    implements java.awt.event.ActionListener {
    MainFrame adaptee;

    MainFrame_jRestartButton_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jRestartButton_actionPerformed(e);
    }
}

class MainFrame_jExitButton_actionAdapter
    implements java.awt.event.ActionListener {
    MainFrame adaptee;

    MainFrame_jExitButton_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jExitButton_actionPerformed(e);
    }
}
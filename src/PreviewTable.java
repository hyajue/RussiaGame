package russiagame;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PreviewTable {
    public static int[][] myTable; //储存标志0表示无，1表示有
    public static int x; //横坐标
    public static int y; //纵坐标

    public PreviewTable() {
    }

    public PreviewTable(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
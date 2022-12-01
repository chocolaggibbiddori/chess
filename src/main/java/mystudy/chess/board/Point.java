package mystudy.chess.board;

public class Point {

    private int x;
    private int y;

    public Point(String point) {
        char x = point.toUpperCase().charAt(0);
        char y = point.charAt(1);
        this.x = '8' - y;
        this.y = x - 'A';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPoint(String point) {
        char x = point.toUpperCase().charAt(0);
        char y = point.charAt(1);
        this.x = '8' - y;
        this.y = x - 'A';
    }
}

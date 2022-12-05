package mystudy.chess.board;

public class Point {

    private int x;
    private int y;

    public Point(String point) {
        setPoint(point);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point point) {
            return this.x == point.getX() && this.y == point.getY();
        }
        return false;
    }
}

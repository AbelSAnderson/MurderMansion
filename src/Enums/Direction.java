package Enums;

public enum Direction {

    UP(-1),
    DOWN(1),
    LEFT(-1),
    RIGHT(1);

    private int direction;

    Direction(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}

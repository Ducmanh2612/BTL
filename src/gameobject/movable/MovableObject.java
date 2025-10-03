package gameobject.movable;


import gameobject.GameObject;


public abstract class MovableObject extends GameObject {
    protected double dx, dy; // Movement speed in x and y direction

    public MovableObject(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.dx = 0;
        this.dy = 0;
    }

    public double getDx() { return dx; }
    public double getDy() { return dy; }
    public void setDx(int dx) { this.dx = dx; }
    public void setDy(int dy) { this.dy = dy; }

    public abstract void move();
}

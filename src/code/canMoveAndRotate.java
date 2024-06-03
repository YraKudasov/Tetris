package code;

public interface canMoveAndRotate {
    boolean canMove(Direction direction);
    void move(Direction direction);
    boolean canRotate(int[] waitingCubes);

    void rotate();
}
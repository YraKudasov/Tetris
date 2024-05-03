public class Wall {

    private Direction direction;


    public Wall (Direction direction){
        this.direction = direction;
    }

    // Метод для получения направления стены
    public Direction getDirection() {
        return direction;
    }
}


    public class GameModel {
        private Glass glass;
        private FactoryFigures factoryFigures;
        private int score;

        public GameModel() {
            this.glass = new Glass(5,5);
            this.factoryFigures = new FactoryFigures(glass);
            this.score = 0;
        }


/*
        public void initiateFigureGeneration(int points) {
            // Инициируем генерацию фигур фабрикой фигур
            Figure figure = factoryFigures.generate(points);
            glass.setFigure(figure);
        }
*/
        public void updateScore(int points) {
            // Обновляем счет
            score += points;
        }

        public void figureFell(Figure figure, Stack stack){
            Cube[] fellCubes = figure.getCubes();
            for (Cube cube : fellCubes) {
                cube.setMovable(false); // Set the movable property to false
                stack.addCube(cube); // Add the cube to the Stack object
            }
            glass.deleteFigure();
        }

        /*
        public boolean isGameOver() {
            // Определяем окончание игры
            return glass.isFull();
        }

 */

        // Геттеры и сеттеры для доступа к свойствам
        public Glass getGlass() {
            return glass;
        }

        public FactoryFigures getFactoryFigures() {
            return factoryFigures;
        }

        public int getScore() {
            return score;
        }



        // Другие методы и свойства...
    }


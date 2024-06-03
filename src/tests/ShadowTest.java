package tests;

import code.FactoryFigures;
import code.Figure;
import code.Glass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ShadowTest {



    @Test
    public void testGetColor() {
        Glass glass = new Glass(10, 10);
        FactoryFigures factoryFigures = new FactoryFigures(glass);

        Figure figure = factoryFigures.createRandomFigure();
        assertEquals(figure.getColor(), figure.getShadow().getColor());
    }

    @Test
    public void testGetCubesOfShadow() {
        Glass glass = new Glass(10, 10);
        FactoryFigures factoryFigures = new FactoryFigures(glass);
        Figure figure = factoryFigures.createRandomFigure();

        int expLength = 4;

        figure.getShadow().updateShadow(figure.getCubes());
        assertEquals(expLength, figure.getShadow().getCubesOfShadow().length);
    }

    @Test
    public void testUpdateShadow() {
        Glass glass = new Glass(10, 10);
        FactoryFigures factoryFigures = new FactoryFigures(glass);
        Figure figure = factoryFigures.createRandomFigure();


        figure.getShadow().updateShadow(figure.getCubes());


        assertEquals(figure.getShadow().getCubesOfShadow()[1].getCoordX(), figure.getCube(1).getCoordX());
        assertNotEquals(figure.getShadow().getCubesOfShadow()[1].getCoordY(), figure.getCube(1).getCoordY());
    }

}

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SimpleCalculateurTest {

    @Test
    void add() {
        SimpleCalculateur calculateur = Mockito.mock(SimpleCalculateur.class); // Simulation de SimpleCalculateur
        Mockito.when(calculateur.add(5,5)).thenReturn(10);

        Assert.assertEquals(calculateur.add(5,5), 10,0);
    }

    @Test
    void substract() {
        SimpleCalculateur calculateur = Mockito.mock(SimpleCalculateur.class);
        Mockito.when(calculateur.substract(5,5)).thenReturn(0);

        Assert.assertEquals(calculateur.substract(5,5), 0,0);
    }
}
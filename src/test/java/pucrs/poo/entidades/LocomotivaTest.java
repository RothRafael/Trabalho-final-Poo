package pucrs.poo.entidades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocomotivaTest {

    @Test
    public void testGetIdentificador() {
        Locomotiva loc1 = new Locomotiva(100, 200);
        Locomotiva loc2 = new Locomotiva(100, 200);

        assertEquals(loc1.getIdentificador() + 1, loc2.getIdentificador());
    }

    @Test
    public void testGetPesoMaximo() {
        Locomotiva loc = new Locomotiva(100, 200);

        assertEquals(100, loc.getPesoMaximo());
    }

    @Test
    public void testGetQtdadeMaxVagoes() {
        Locomotiva loc = new Locomotiva(100, 200);

        assertEquals(200, loc.getQtdadeMaxVagoes());
    }

    @Test
    public void testGetComposicaoIsNull() {
        Locomotiva loc = new Locomotiva(100, 200);

        assertNull(loc.getComposicao());
    }

    @Test
    public void testSetComposicao() throws LocomotivaEmOutraComposicaoException {
        Locomotiva loc = new Locomotiva(100, 200);
        loc.setComposicao(new Composicao(loc));

        assertNotNull(loc.getComposicao());
    }
}

package pucrs.poo.entidades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VagaoTest {

    @Test
    public void testGetIdentificador() {
        Vagao vagao1 = new Vagao(10);
        Vagao vagao2 = new Vagao(10);

        assertEquals(vagao1.getIdentificador() + 1, vagao2.getIdentificador());
    }
}

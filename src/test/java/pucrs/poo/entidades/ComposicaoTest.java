package pucrs.poo.entidades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComposicaoTest {
    @Test
    public void testGetIdentificador() throws LocomotivaEmOutraComposicaoException {
        Locomotiva loc1 = new Locomotiva(100, 200);
        Composicao comp1 = new Composicao(loc1);
        Locomotiva loc2 = new Locomotiva(300, 400);
        Composicao comp2 = new Composicao(loc2);

        assertEquals(comp1.getIdentificador() + 1, comp2.getIdentificador());
    }

    @Test
    public void testGetQuantidadeLocomotivasIsOne() throws LocomotivaEmOutraComposicaoException {
        Locomotiva loc = new Locomotiva(100, 200);
        Composicao comp = new Composicao(loc);

        assertEquals(1, comp.getQtdadeLocomotivas());
    }

    @Test
    public void testGetQuantidadeLocomotivasIsTwo() throws LocomotivaEmOutraComposicaoException, LocomotivaAposVagaoException {
        Locomotiva loc1 = new Locomotiva(100, 200);
        Locomotiva loc2 = new Locomotiva(300, 400);
        Composicao comp = new Composicao(loc1);
        comp.engataLocomotiva(loc2);

        assertEquals(2, comp.getQtdadeLocomotivas());
    }

    @Test
    public void testGetLocomotivaNumberOne() throws LocomotivaEmOutraComposicaoException {
        Locomotiva loc = new Locomotiva(100, 200);
        Composicao comp = new Composicao(loc);

        assertEquals(loc, comp.getLocomotiva(1));
    }

    @Test
    public void testGetLocomotivaNumberTwo() throws LocomotivaEmOutraComposicaoException, LocomotivaAposVagaoException {
        Locomotiva loc1 = new Locomotiva(100, 200);
        Locomotiva loc2 = new Locomotiva(300, 400);
        Composicao comp = new Composicao(loc1);
        comp.engataLocomotiva(loc2);

        assertEquals(loc2, comp.getLocomotiva(2));
    }

    @Test
    public void testGetQtdadeVagoesIsZero() throws LocomotivaEmOutraComposicaoException {
        Locomotiva loc = new Locomotiva(100, 200);
        Composicao comp = new Composicao(loc);

        assertEquals(0, comp.getQtdadeVagoes());
    }

    @Test
    public void testGetQtdadeVagoesIsOne() throws LocomotivaEmOutraComposicaoException, PesoMaximoExcedidoException, MaximoDeVagoesExcedidoException, VagaoEmOutraComposicaoException {
        Locomotiva loc = new Locomotiva(100, 200);
        Composicao comp = new Composicao(loc);
        Vagao vagao = new Vagao(2);
        comp.engataVagao(vagao);

        assertEquals(1, comp.getQtdadeVagoes());
    }

    @Test
    public void testMaximoDeVagoesExcedidoExceptionThrow() throws LocomotivaEmOutraComposicaoException, PesoMaximoExcedidoException, MaximoDeVagoesExcedidoException, VagaoEmOutraComposicaoException {
        Locomotiva loc = new Locomotiva(100, 1);
        Composicao comp = new Composicao(loc);
        Vagao vagao1 = new Vagao(2);
        comp.engataVagao(vagao1);
        assertThrows(MaximoDeVagoesExcedidoException.class, () -> {
            Vagao vagao2 = new Vagao(2);
            comp.engataVagao(vagao2);
        });
    }

    @Test
    public void testPesoMaximoExcedidoExceptionThrow2() throws LocomotivaEmOutraComposicaoException, PesoMaximoExcedidoException, MaximoDeVagoesExcedidoException, VagaoEmOutraComposicaoException {
        Locomotiva loc = new Locomotiva(1, 10);
        Composicao comp = new Composicao(loc);
        Vagao vagao1 = new Vagao(2);
        comp.engataVagao(vagao1);
        assertThrows(PesoMaximoExcedidoException.class, () -> {
            Vagao vagao2 = new Vagao(2);
            comp.engataVagao(vagao2);
        });
    }
}


package pucrs.poo.repositorios;

import pucrs.poo.entidades.Locomotiva;

import java.util.ArrayList;

/**
 * @author marco.mangan@pucrs.br
 */
public class GaragemLocomotivas {

    /**
     *
     */
    private final ArrayList<Locomotiva> locomotivas;

    /**
     *
     */
    public GaragemLocomotivas() {
        locomotivas = new ArrayList<>();
    }

    /**
     * @param identificador
     * @return
     */
    public Locomotiva getLocomotiva(int identificador) {
        return locomotivas.get(identificador - 1);
    }

    /**
     * Obtém a lista de locomotivas livres.
     *
     * @return a lista de locomotivas livres
     */
    public ArrayList<Locomotiva> getLocomotivasLivres() {
        ArrayList<Locomotiva> livres = new ArrayList<>();
        for (Locomotiva locomotiva : locomotivas) {
            if (locomotiva.getComposicao() == null) {
                livres.add(locomotiva);
            }
        }
        return livres;
    }

    /**
     *
     */
    public void preencheGaragem() {
        final int PESO_MAXIMO = 300;
        final int QTDADE_MAX_VAGOES = 100;
        locomotivas.add(new Locomotiva(PESO_MAXIMO, QTDADE_MAX_VAGOES));
    }

}

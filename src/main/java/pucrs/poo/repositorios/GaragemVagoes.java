package pucrs.poo.repositorios;

import pucrs.poo.entidades.Vagao;

import java.util.ArrayList;

/**
 * @author marco.mangan@pucrs.br
 */
public class GaragemVagoes {

    /**
     *
     */
    private final ArrayList<Vagao> vagoes;

    /**
     * @param identificador
     * @return
     */
    public Vagao getVagao(int identificador) {
        return vagoes.get(identificador - 1);
    }

    /**
     *
     */
    public GaragemVagoes() {
        vagoes = new ArrayList<>();
    }

    /**
     * @return
     */
    public java.util.ArrayList<Vagao> getVagoesLivres() {
        ArrayList<Vagao> livres = new ArrayList<>();
        for (Vagao vagao : vagoes) {
            if (vagao.getComposicao() == null) {
                livres.add(vagao);
            }
        }
        return livres;
    }

    /**
     *
     */
    public void preencheGaragem() {
        final int CAPACIDADE_CARGA = 2;
        vagoes.add(new Vagao(CAPACIDADE_CARGA));
    }

}

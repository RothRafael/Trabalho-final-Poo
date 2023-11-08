package pucrs.poo.entidades;

import java.util.StringJoiner;

/**
 * A classe Locomotiva representa o veículo que movimenta uma composição.
 *
 * @author marco.mangan@pucrs.br
 */
public class Locomotiva {

    /**
     * O identificador da próxima instância de locomotiva.
     */
    private static int NEXT_ID = 1;

    /**
     * O identificador desta composição.
     */
    private final int identificador;

    /**
     *
     */
    private final double pesoMaximo;

    /**
     *
     */
    private final int qtdadeMaxVagoes;

    /**
     *
     */
    private Composicao composicao;

    /**
     *
     * @param pesoMaximo
     * @param qtdadeMaxVagoes
     */
    public Locomotiva(double pesoMaximo, int qtdadeMaxVagoes) {
        identificador = NEXT_ID++;
        this.pesoMaximo = pesoMaximo;
        this.qtdadeMaxVagoes = qtdadeMaxVagoes;
    }

    /**
     *
     * @return
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     *
     * @return
     */
    public double getPesoMaximo() {
        return pesoMaximo;
    }

    /**
     *
     * @return
     */
    public int getQtdadeMaxVagoes() {
        return qtdadeMaxVagoes;
    }

    /**
     *
     * @return
     */
    public Composicao getComposicao() {
        return composicao;
    }

    /**
     *
     * @param composicao
     */
    public void setComposicao(Composicao composicao) {
        //if (composicao == null) {
        //    throw new IllegalArgumentException();
        //}
        this.composicao = composicao;
    }

    /**
     *
     * @return dados da locomotiva em uma string
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", Locomotiva.class.getSimpleName() + "[", "]")
                .add("identificador=" + identificador)
                .add("pesoMaximo=" + pesoMaximo)
                .add("qtdadeMaxVagoes=" + qtdadeMaxVagoes)
                //.add("composicao=" + composicao)
                .toString();
    }
}

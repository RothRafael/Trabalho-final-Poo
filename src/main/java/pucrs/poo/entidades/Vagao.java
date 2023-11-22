package pucrs.poo.entidades;

import java.util.StringJoiner;

/**
 * @author marco.mangan@pucrs.br
 */
public class Vagao extends Carro{

    /**
     * O identificador da próxima instância de vagão.
     */
    private static int NEXT_ID = 1;

    /**
     * O identificador do vagão.
     */
    private int identificador;

    /**
     * A capacidae de carga do vagão em quilogramas
     */
    private final double capacidadeCarga;

    /**
     * A composição do vagão ou null
     */
    private Composicao composicao;

    /**
     *
     * @param capacidadeCarga a capacidade de carga em quilogramas
     */
    public Vagao(double capacidadeCarga) {
        identificador = getIdentificador();
        this.capacidadeCarga = capacidadeCarga;
    }

    /**
     *
     * @return o identificador do vagão
     */
    public void setId(int id)
    {
        this.identificador = id;
    }
    public int getIdentificador() {
        return identificador;
    }

    /**
     *
     * @return a capacidade de carga do vagão
     */
    public int getCapacidadeCarga() {
        return (int) capacidadeCarga;
    }

    /**
     *
     * @return a compasição associada ao vagão ou null
     */
    public Composicao getComposicao() {
        return composicao;
    }

    /**
     *
     * @param composicao a composicao do vagão ou null
     */
    public void setComposicao(Composicao composicao) {
        this.composicao = composicao;
    }

    /**
     *
     * @return dados do vagão em uma string
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", Vagao.class.getSimpleName() + "[", "]")
                .add("Id=" + identificador)
                .add("Cpc=" + capacidadeCarga)
                //.add("composicao=" + composicao)
                .toString();
    }
}

package pucrs.poo.entidades;

import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * A classe Composicao representa um trem com ao menos uma locomotiva.
 *
 * @author marco.mangan@pucrs.br
 */
public class Composicao {

    /**
     * O identificador da próxima instância de composição.
     */
    private static int NEXT_ID = 1;

    /**
     * O identificador desta composição.
     */
    private final int identificador;

    /**
     * A lista de vagões da composição.
     */
    private final ArrayList<Vagao> vagoes;

    /**
     * A lista de locomotivas da composição.
     */
    private final ArrayList<Locomotiva> locomotivas;

    /**
     * Cria uma nova composição.
     * A composição necessita de uma locomotiva livre.
     *
     * @param locomotiva a primeira locomotiva da composição
     */
    public Composicao(Locomotiva locomotiva) throws LocomotivaEmOutraComposicaoException {
        if (locomotiva == null) {
            throw new IllegalArgumentException();
        }
        if (locomotiva.getComposicao() != null) {
            throw new LocomotivaEmOutraComposicaoException();
        }
        identificador = NEXT_ID++;
        vagoes = new ArrayList<>();
        locomotivas = new ArrayList<>();
        locomotivas.add(locomotiva);
        locomotiva.setComposicao(this);
    }

    /**
     * @return o identificador da composição
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     * @return a quantidade de locomotivas desta composição
     */
    public int getQtdadeLocomotivas() {
        return locomotivas.size();
    }

    /**
     * @param posicao a posição da locomotiva na composição, iniciando em 1
     * @return a locomotiva na posição indicada
     */
    public Locomotiva getLocomotiva(int posicao) {
        return locomotivas.get(posicao - 1);
    }

    /**
     * @return a quantidade de vagões desta composição
     */
    public int getQtdadeVagoes() {
        return vagoes.size();
    }

    /**
     * Engata locomotiva ao final da composição.
     * Caso a locomotiva pertença a uma outra compsição, uma exceção será lançada.
     * Caso um vagão esteja conectado, uma exceção será lançada.
     *
     * @param locomotiva a locomotiva a ser engatada.
     */
    public void engataLocomotiva(Locomotiva locomotiva) throws LocomotivaAposVagaoException, LocomotivaEmOutraComposicaoException {
        if (locomotiva == null) {
            throw new IllegalArgumentException();
        }
        if (locomotiva.getComposicao() != null) {
            throw new LocomotivaEmOutraComposicaoException();
        }
        if (!vagoes.isEmpty()) {
            throw new LocomotivaAposVagaoException();
        }
        locomotivas.add(locomotiva);
    }

    /**
     * Engata vagão ao final da composição.
     *
     * @param vagao o vagão a ser engatado
     */
    public void engataVagao(Vagao vagao) throws PesoMaximoExcedidoException, MaximoDeVagoesExcedidoException, VagaoEmOutraComposicaoException {
        if (vagao == null) {
            throw new IllegalArgumentException();
        }
        if (vagao.getComposicao() != null) {
            throw new VagaoEmOutraComposicaoException();
        }
        int total = 0;
        for (Locomotiva locomotiva : locomotivas) {
            total += locomotiva.getQtdadeMaxVagoes();
        }
        if (total <= vagoes.size()) {
            throw new MaximoDeVagoesExcedidoException();
        }

        double pesoMax = 0;
        for (Locomotiva locomotiva : locomotivas) {
            pesoMax += locomotiva.getPesoMaximo();
        }
        double peso = 0;
        for (Vagao v : vagoes) {
            peso += v.getCapacidadeCarga();
        }
        if (pesoMax <= peso) {
            throw new PesoMaximoExcedidoException();
        }

        vagoes.add(vagao);
    }

    /**
     * Desengata a locomotiva ao final da composição.
     * A locomotiva retorna para sua garagem, se puder ser desengatada.
     */
    public void desengataLocomotiva() throws PrimeiraLocomotivaNaoPodeSerRemovidaException, VagaoAposLocomotivaException {
        if (!vagoes.isEmpty()) {
            throw new VagaoAposLocomotivaException();
        }
        if (locomotivas.size() <= 1) {
            throw new PrimeiraLocomotivaNaoPodeSerRemovidaException();
        }

        Locomotiva locomotiva = locomotivas.remove(locomotivas.size() - 1);
        locomotiva.setComposicao(null);
    }

    /**
     * Desengata o vagão ao final da composição.
     * O vagão retorna para sua garagem, se puder ser desengatado.
     * Caso não existe vagão para desengatar, uma exceção será lançada.
     */
    public void desengataVagao() throws NenhumVagaoNaComposicaoException {
        if (vagoes.isEmpty()) {
            throw new NenhumVagaoNaComposicaoException();
        }
        Vagao vagao = vagoes.remove(vagoes.size() - 1);
        vagao.setComposicao(null);
    }

    /**
     * Desengata o último elemento da composição.
     * O elemento pode ser um vagão ou uma locomotiva. O elemento retorna para sua garagem se
     * puder ser removido.
     * Se a composição for formada somente por uma locomotiva, uma exceção será lançada.
     */
    public void desengataUltimoElemento() throws VagaoAposLocomotivaException, PrimeiraLocomotivaNaoPodeSerRemovidaException, NenhumVagaoNaComposicaoException {
        if (vagoes.isEmpty()) {
            desengataLocomotiva();
        } else {
            desengataVagao();
        }
    }

    /**
     * @return dados da composição em uma string
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", Composicao.class.getSimpleName() + "[", "]")
                .add("identificador=" + identificador)
                .add("vagoes=" + vagoes)
                .add("locomotivas=" + locomotivas)
                .toString();
    }
}

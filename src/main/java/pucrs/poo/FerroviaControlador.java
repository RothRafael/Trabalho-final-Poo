package pucrs.poo;

import pucrs.poo.entidades.*;
import pucrs.poo.repositorios.IdentificadorNaoEncontradoExceptioin;
import pucrs.poo.repositorios.PatioComposicoes;
import pucrs.poo.repositorios.GaragemLocomotivas;
import pucrs.poo.repositorios.GaragemVagoes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author marco.mangan@pucrs.br
 */
public class FerroviaControlador {

    /**
     *
     */
    private final PatioComposicoes patioComposicoes;

    /**
     *
     */
    private final GaragemLocomotivas garagemLocomotivas;

    /**
     *
     */
    private final GaragemVagoes garagemVagoes;

    /**
     *
     */
    public FerroviaControlador() {
        patioComposicoes = new PatioComposicoes();
        garagemLocomotivas = new GaragemLocomotivas();
        garagemVagoes = new GaragemVagoes();
    }

    /**
     *
     */
    public void preencheGaragens() throws IOException {

        try {
            garagemLocomotivas.preencheGaragem();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            garagemVagoes.preencheGaragem();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        patioComposicoes.preencheGaragem();
    }

    /**
     *
     * @param identificador o identificador da locomotiva
     * @return a locomotiva com o identificador procurado ou null
     */
    public Locomotiva getLocomotiva(int identificador) {
        return garagemLocomotivas.getLocomotiva(identificador);
    }

    /**
     * @param locomotiva a primeira locomotiva
     */
    public void criaComposicao(Locomotiva locomotiva) throws LocomotivaEmOutraComposicaoException {
        patioComposicoes.criaComposicao(locomotiva);
    }

    /**
     * @param composicao a composição
     * @param locomotiva a locomotiva a ser engatada
     */
    public void engataLocomotiva(Composicao composicao, Locomotiva locomotiva)
            throws LocomotivaEmOutraComposicaoException, LocomotivaAposVagaoException
    {
        if (composicao.getQtdadeVagoes() == 0){
            composicao.engataLocomotiva(locomotiva);
        }
    }


    /**
     * @param composicao a composição
     * @param vagao o vagão a ser engatado
     */
    public void engataVagao(Composicao composicao, Vagao vagao) throws PesoMaximoExcedidoException, MaximoDeVagoesExcedidoException, VagaoEmOutraComposicaoException {
        composicao.engataVagao(vagao);
    }

    /**
     * @return a lista de locomotivas livres
     */
    public ArrayList<Locomotiva> listaLocomotivasLivres() {
        return garagemLocomotivas.getLocomotivasLivres();
    }

    /**
     * @return a lista de vagões livres
     */
    public ArrayList<Vagao> listaVagoesLivres() {
        return garagemVagoes.getVagoesLivres();
    }

    /**
     * @return a lista de composições
     */
    public ArrayList<Composicao> listaComposicoes() {
        return patioComposicoes.getComposicoes();
    }

    /**
     * @param composicao a composição a ser desfeita
     */
    public void desfazComposicao(Composicao composicao) throws VagaoAposLocomotivaException, NenhumVagaoNaComposicaoException, PrimeiraLocomotivaNaoPodeSerRemovidaException {
        patioComposicoes.desfazComposicao(composicao);
    }

    /**
     *
     * @param identificador o identificador da composição
     * @return a composição
     */
    public Composicao getComposicao(int identificador) throws IdentificadorNaoEncontradoExceptioin {
        return patioComposicoes.getComposicao(identificador);
    }
    public Composicao getComposicao(Composicao composicao)
    {
        return composicao;
    }

    /**
     *
     * @param identificador o identificador do vagão
     * @return o vagão
     */
    public Vagao getVagao(int identificador) {
        return garagemVagoes.getVagao(identificador);
    }

    public String getComposicaoSimplificada()
    {
        StringBuilder sB = new StringBuilder();

        ArrayList<Composicao> comp = listaComposicoes();

        for (Composicao composicao: comp)
        {
            sB.append(composicao.compSimples());
        }
        return sB.toString();
    }
    public void salvarVagoes() throws IOException {
        garagemVagoes.salvarGaragem();
    }
    public void salvarLocomotivas() throws IOException {
        garagemLocomotivas.salvarGaragem();
    }
    public void salvarComposicoes() throws IOException {
        patioComposicoes.salvaPatio();
    }
}

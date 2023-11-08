package pucrs.poo.repositorios;

import pucrs.poo.entidades.*;

import java.util.ArrayList;

/**
 * @author marco.mangan@pucrs.br
 */
public class PatioComposicoes {

	/**
	 *
	 */
	private final ArrayList<Composicao> composicoes;

	/**
	 *
	 */
	public PatioComposicoes() {
		composicoes = new ArrayList<>();
	}

	/**
	 *
	 * @param identificador
	 * @return
	 */
	public Composicao getComposicao(int identificador) throws IdentificadorNaoEncontradoExceptioin {
		for (Composicao composicao: composicoes) {
			if (composicao.getIdentificador() == identificador) {
				return composicao;
			}
		}
		throw new IdentificadorNaoEncontradoExceptioin();
	}

	/**
	 *
	 * @return
	 */
	public ArrayList<Composicao> getComposicoes() {
		return composicoes;
	}

	/**
	 *
	 * @param composicao
	 */
	public void desfazComposicao(Composicao composicao) throws VagaoAposLocomotivaException, NenhumVagaoNaComposicaoException, PrimeiraLocomotivaNaoPodeSerRemovidaException {
		while (composicao.getQtdadeVagoes() > 0) {
			composicao.desengataUltimoElemento();
		}
		while (composicao.getQtdadeLocomotivas() > 1) {
			composicao.desengataUltimoElemento();
		}
		Locomotiva locomotiva = composicao.getLocomotiva(1);
		locomotiva.setComposicao(null);
		composicoes.remove(composicao);
	}

	/**
	 *
	 * @param locomotiva
	 */
	public void criaComposicao(Locomotiva locomotiva) throws LocomotivaEmOutraComposicaoException {
		if (locomotiva.getComposicao() != null) {
			throw new LocomotivaEmOutraComposicaoException();
		}
		Composicao composicao = new Composicao(locomotiva);
		composicoes.add(composicao);
	}

}

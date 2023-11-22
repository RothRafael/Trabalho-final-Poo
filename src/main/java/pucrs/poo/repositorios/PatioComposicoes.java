package pucrs.poo.repositorios;

import pucrs.poo.entidades.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
	public void salvaPatio() throws IOException {
		FileWriter arquivo = new FileWriter("src/main/java/pucrs/poo/repositorios/Patio.csv");

		for (Composicao composicao : composicoes) {

			arquivo.write(composicao.compArquivo() + "\n");

		}

		arquivo.close();
	}
	public void lePatio() throws FileNotFoundException {
		File arquivo = new File("src/main/java/pucrs/poo/repositorios/Patio.csv");

		Scanner scanner = new Scanner(arquivo);

		while (scanner.hasNextLine())
		{
			String comp;
			String loc;
			String vag;

			String linha = scanner.nextLine();

			for (int i = 0; i < linha.length(); i++)
			{

			}
		}
	}

}

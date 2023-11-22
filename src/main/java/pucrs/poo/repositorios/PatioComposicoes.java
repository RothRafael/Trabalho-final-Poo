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
			String linha = scanner.nextLine();
			String[] aux = linha.split(",");

			String comp;
			String loc;
			String vag;

			for (int i = 0; i < linha.length(); i++)
			{
//				int identificador = Integer.parseInt(aux[0]);
//
//				String peloamordedeus = Integer.toString(Integer.parseInt(aux[1]));
//				String aaaa = Integer.toString(Integer.parseInt(aux[2]));
//
//
//				String[] locomotivaAux = new String[3];
//				locomotivaAux = peloamordedeus.split(" ");
//
//				int identificadorLocomotiva =
//				int pesoMaximoLocomotiva =
//				int qtdadeMaxVagoes =
//
//				Locomotiva locomotiva = new Locomotiva (pesoMaximoLocomotiva, qtdadeMaxVagoes);
//				locomotiva.setId(identificadorLocomotiva);
//
//				try {
//					Composicao composicao = new Composicao (locomotiva);
//					composicao.setId(identificador);
//				} catch (LocomotivaEmOutraComposicaoException e) {
//					throw new RuntimeException(e);
//				}
//
//				String[] vagaoAux = new String[2];
//				vagaoAux = aaaa.split(" ");
//
//				int identificadorVagao =
//				int capacidadeCarga =
//
//				Vagao vagao = new Vagao (capacidadeCarga);
//				vagao.setId(identificadorVagao);

			}
		}

//		File arquivo = new File("src/main/java/pucrs/poo/repositorios/GaragemLocomotivas.csv");
//		Scanner scanner = new Scanner (arquivo);
//
//		while (scanner.hasNextLine())
//		{
//			String linha = scanner.nextLine();
//			String[] aux = linha.split(";");
//
//			int pesoMax = Integer.parseInt(aux[0]);
//			int qtdadeMaxVagoes =  Integer.parseInt(aux[1]);
//			int id = Integer.parseInt(aux[2]);
//
//			Locomotiva locomotiva = new Locomotiva (pesoMax, qtdadeMaxVagoes);
//			locomotiva.setId(id);
//			locomotivas.add(locomotiva);
//		}


	}

}

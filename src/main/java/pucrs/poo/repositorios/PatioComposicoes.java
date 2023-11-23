package pucrs.poo.repositorios;

import pucrs.poo.FerroviaControlador;
import pucrs.poo.entidades.*;

import java.io.*;
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
	public void preencheGaragem(FerroviaControlador ferroviaControlador) throws IOException {
		String nomeArquivo = "src/main/java/pucrs/poo/repositorios/Patio.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
			String linha;

			while ((linha = br.readLine()) != null) {
				String[] numeros = linha.split(",");
				System.out.println(numeros.toString());

				// Armazena os números da parte 1
				String id = numeros[0];
				String locomotivas = numeros[1];
				String vagoes = "";
				if(numeros.length > 2)
					vagoes = numeros[2];


				String[] loc = locomotivas.split(" ");
				System.out.println(loc.toString());

				int pesomax = Integer.parseInt(loc[0]);
				int qtdVagao = Integer.parseInt(loc[1]);
				int idloc = Integer.parseInt(loc[2]);

				Locomotiva locomotiva = new Locomotiva(pesomax,qtdVagao);
				locomotiva.setId(idloc);
				ferroviaControlador.criaLoc(locomotiva);

				Composicao comp = new Composicao(ferroviaControlador.getLocomotiva(Integer.parseInt(loc[2])));

				if (loc.length > 3)
				{
					for (int i = 3; i < loc.length; i = i + 3)
					{
						Locomotiva locomotivaLoop = new Locomotiva(Integer.parseInt(loc[i]), Integer.parseInt(loc[i + 1]));
						locomotivaLoop.setId(Integer.parseInt(loc[i + 2]));

						ferroviaControlador.criaLoc(locomotivaLoop);
						comp.engataLocomotiva(ferroviaControlador.getLocomotiva(Integer.parseInt(loc[i + 2])));
					}
				}
				String[] vag = new String[0];
				if(!vagoes.isEmpty())
					vag = vagoes.split(" ");
				if (vag.length > 0)
				{
					System.out.println("TESTE");



					for (int i = 0; i < vag.length; i = i + 2)
					{
						Vagao vagaoLoop = new Vagao(Integer.parseInt(vag[i]));
						vagaoLoop.setId(Integer.parseInt(vag[i+1]));

						ferroviaControlador.criaVagao(vagaoLoop);
						comp.engataVagao(ferroviaControlador.getVagao(Integer.parseInt(vag[i+1])));

					}
				}
				composicoes.add(comp);
				System.out.println(composicoes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LocomotivaEmOutraComposicaoException e) {
			System.out.println("erro");
		} catch (PesoMaximoExcedidoException e) {
			System.out.println("erro");
		} catch (MaximoDeVagoesExcedidoException e) {
			System.out.println("erro");
		} catch (VagaoEmOutraComposicaoException e) {
			System.out.println("erro");
		}
	}
}


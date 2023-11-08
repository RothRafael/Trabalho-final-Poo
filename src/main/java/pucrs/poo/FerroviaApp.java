package pucrs.poo;

import pucrs.poo.entidades.*;
import pucrs.poo.repositorios.IdentificadorNaoEncontradoExceptioin;

import java.util.Scanner;

/**
 * A classe FerroviaApp representa uma interface em linha de comando para o
 * sistema de composição de trens.
 *
 * @author marco.mangan@pucrs.br
 */
public class FerroviaApp {

    /**
     *
     * @param args não é utilizado
     */
    public static void main(java.lang.String[] args) {
        Scanner in = new Scanner(System.in);

        mostrarCabecalho();

        FerroviaControlador ferroviaControlador = new FerroviaControlador();
        ferroviaControlador.preencheGaragens();

        String line;
        int op;
        do {
            mostrarMenuPrincipal();
            line = in.nextLine();
            op = Integer.parseInt(line);
            switch (op) {
                case 1:
                    criarComposicao(in, ferroviaControlador);
                    break;
                case 2:
                    editarComposicao(in, ferroviaControlador);
                    break;
                case 3:
                    listarPatio(ferroviaControlador);
                    break;
                case 4:
                    desfazerComposicao(in,ferroviaControlador);
                    break;
            }
        } while (op != 9);
        in.close();
        System.out.println("-*FIM*-");
    }

    private static void mostrarCabecalho() {
        System.out.println(" Sistema de Composição de Trens (SCT)! ");
        System.out.println("=======================================");
        System.out.println();
    }

    /**
     *
     */
    private static void desfazerComposicao(Scanner in, FerroviaControlador ferroviaControlador) {
        System.out.println("==> DESFAZER COMPOSIÇÃO\n");
        try {
            Composicao composicao = selecionaComposicao(in, ferroviaControlador);
            ferroviaControlador.desfazComposicao(composicao);
        } catch (IdentificadorNaoEncontradoExceptioin e) {
            System.out.println("FALHA: composição não localizada!");
        } catch (VagaoAposLocomotivaException e) {
            System.out.println("FALHA: vagão após locomotiva!");
        } catch (NenhumVagaoNaComposicaoException e) {
            System.out.println("FALHA: nenhum vagão na composição!");
        } catch (PrimeiraLocomotivaNaoPodeSerRemovidaException e) {
            System.out.println("FALHA: primeira locomotiva não pode ser removida!");
        }
    }

    private static Composicao selecionaComposicao(Scanner in, FerroviaControlador ferroviaControlador) throws IdentificadorNaoEncontradoExceptioin {
        System.out.println("==> SELECIONAR COMPOSIÇÃO\n");
        listarPatio(ferroviaControlador);
        System.out.println("Escolha uma composição");
        String linha = in.nextLine();
        int identificador = Integer.parseInt(linha);
        Composicao composicao = ferroviaControlador.getComposicao(identificador);
        return composicao;
    }

    /**
     *
     * @param ferroviaControlador
     */
    private static void listarPatio(FerroviaControlador ferroviaControlador) {
        System.out.println("==> LISTAR PÁTIO\n");

        System.out.println(ferroviaControlador.listaComposicoes());
    }

    /**
     *
     */
    private static void editarComposicao(Scanner in, FerroviaControlador ferroviaControlador) {
        System.out.println("==> EDITAR  COMPOSIÇÃO\n");

        String line;
        int op;
        do {
            mostrarMenuEdicao();
            line = in.nextLine();
            op = Integer.parseInt(line);
            switch (op) {
                case 1:
                    inserirLocomotiva(in, ferroviaControlador);
                    break;
                case 2:
                    inserirVagao(in, ferroviaControlador);
                    break;
                case 3:
                    desengataUltimoElemento(in, ferroviaControlador);
                    break;
                case 4:
                    listarLocomotivasLivres(ferroviaControlador);
                    break;
                case 5:
                    listarVagoesLivres(ferroviaControlador);
                    break;
            }
        } while (op != 9);
    }

    private static void inserirVagao(Scanner in, FerroviaControlador ferroviaControlador) {
        System.out.println("==> INSERIR VAGÃO\n");

        Composicao composicao = null;
        try {
            composicao = selecionaComposicao(in, ferroviaControlador);
            listarVagoesLivres(ferroviaControlador);
            System.out.println("Escolha um vagão");
            String linha = in.nextLine();
            int identificador = Integer.parseInt(linha);
            Vagao vagao = ferroviaControlador.getVagao(identificador);
            ferroviaControlador.engataVagao(composicao, vagao);
        } catch (IdentificadorNaoEncontradoExceptioin e) {
            System.out.println("FALHA: identificador não encontrado!");
        } catch (PesoMaximoExcedidoException e) {
            System.out.println("FALHA: peso máximo excedido!");
        } catch (MaximoDeVagoesExcedidoException e) {
            System.out.println("FALHA: máximo de vagões excedido!");
        } catch (VagaoEmOutraComposicaoException e) {
            System.out.println("FALHA: vagão pertence a outra composição!");
        }
    }

    private static void inserirLocomotiva(Scanner in, FerroviaControlador ferroviaControlador) {
        System.out.println("==> INSERIR LOCOMOTIVA\n");
        try {
            Composicao composicao = selecionaComposicao(in, ferroviaControlador);
            Locomotiva locomotiva = selecionaLocomotiva(in, ferroviaControlador);
           ferroviaControlador.engataLocomotiva(composicao, locomotiva);
        } catch (IdentificadorNaoEncontradoExceptioin e) {
            System.out.println("FALHA: identificador não encontrado!");
        } catch (LocomotivaEmOutraComposicaoException e) {
            System.out.println("FALHA: locomotiva pertence a outra composição!");
        } catch (LocomotivaAposVagaoException e) {
            System.out.println("FALHA: locomotiva após vagão!");
        }
    }

    private static Locomotiva selecionaLocomotiva(Scanner in, FerroviaControlador ferroviaControlador) {
        listarLocomotivasLivres(ferroviaControlador);
        System.out.println("Escolha uma locomotiva");
        String linha = in.nextLine();
        int identificador = Integer.parseInt(linha);
        Locomotiva locomotiva = ferroviaControlador.getLocomotiva(identificador);
        return locomotiva;
    }

    private static void desengataUltimoElemento(Scanner in, FerroviaControlador ferroviaControlador) {
        Composicao composicao = null;
        try {
            composicao = selecionaComposicao(in, ferroviaControlador);
            composicao.desengataUltimoElemento();
        } catch (IdentificadorNaoEncontradoExceptioin e) {
            System.out.println("FALHA: composição não localizada!");
        } catch (VagaoAposLocomotivaException e) {
            System.out.println("FALHA: vagão após locomotiva!");
        } catch (PrimeiraLocomotivaNaoPodeSerRemovidaException e) {
            System.out.println("FALHA: a primeira locomotiva não pode ser removida!");
        } catch (NenhumVagaoNaComposicaoException e) {
            System.out.println("FALHA: nenhum vagão na composição!");
        }
    }

    private static void listarVagoesLivres(FerroviaControlador ferroviaControlador) {
        System.out.println(ferroviaControlador.listaVagoesLivres());
    }

    private static void listarLocomotivasLivres(FerroviaControlador ferroviaControlador) {
        System.out.println(ferroviaControlador.listaLocomotivasLivres());
    }

    /**
     *
     * @param ferroviaControlador
     */
    private static void criarComposicao(Scanner in, FerroviaControlador ferroviaControlador)  {
        System.out.println("==> CRIAR COMPOSIÇÃO\n");
        Locomotiva locomotiva = selecionaLocomotiva(in, ferroviaControlador);
        try {
            ferroviaControlador.criaComposicao(locomotiva);
        } catch (LocomotivaEmOutraComposicaoException e) {
            System.out.println("FALHA: locomotiva pertence a outra composição!");
        }
    }

    /**
     *
     */
    private static void mostrarMenuPrincipal() {
        System.out.println(
                """
                        1. Criar um trem
                        2. Editar um trem
                        3. Listar todos os trens
                        4. Desfazer um trem
                                        
                        9. Fim
                        """
        );
    }

    /**
     *
     */
    private static void mostrarMenuEdicao() {
        System.out.println(
                """
                        1. Inserir uma locomotiva
                        2. Inserir um vagão
                        3. Remover o último elemento do trem
                        4. Listar locomotivas livres
                        5. Listar vagões livres
                                        
                        9. Encerrar a edição do trem.
                        """
        );
    }
}

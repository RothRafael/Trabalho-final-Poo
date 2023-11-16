package pucrs.poo;

import pucrs.poo.JanelasErro.ControladorUpdatesEjanelas;
import pucrs.poo.JanelasErro.Popups;
import pucrs.poo.JanelasErro.Updates;
import pucrs.poo.entidades.*;
import pucrs.poo.repositorios.IdentificadorNaoEncontradoExceptioin;

import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Interface extends ControladorUpdatesEjanelas {

    public static FerroviaControlador JanelaEdit(FerroviaControlador ferroviaControlador, Composicao composicao)
    {
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(0,1));
        frame.setSize(670,600);

        JPanel panel1 = new JPanel(new BorderLayout());

        //PAINEL ESQUERDA
        JPanel leftPanel1 = new JPanel(new BorderLayout());
        //LABEL COMPOSICAO
        JLabel labelComp = new JLabel("Composição atual:");
        leftPanel1.add(labelComp, BorderLayout.NORTH);

        JTextArea txCOMP = new JTextArea(20, 30);

        // Adicionando JScrollPane ao redor da JTextArea
        JScrollPane scrollPane1 = new JScrollPane(txCOMP);
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        leftPanel1.add(scrollPane1);

        //PAINEL DIREITA
        JPanel rightPanel1 = new JPanel(new GridLayout(0,1));

        JPanel rightPanel1North = new JPanel(new FlowLayout());


        //BOTAO REMOVE
        JButton btRemVagao = new JButton("Remove vagão");
        rightPanel1North.add(btRemVagao, BorderLayout.CENTER);


        //ADD SUBPAINEL NORTEW
        rightPanel1.add(rightPanel1North);
        //

        JPanel rightPanel1South = new JPanel(new FlowLayout());
        //BOTAO REMOVE2
        JButton btRemLocomotiva = new JButton("Remove locomotiva");
        rightPanel1North.add(btRemLocomotiva, BorderLayout.CENTER);

        JButton btsairDaEdição = new JButton("Sair da edição: ");
        rightPanel1South.add(btsairDaEdição);

        rightPanel1.add(rightPanel1North);
        rightPanel1.add(rightPanel1South);



        panel1.add(leftPanel1, BorderLayout.WEST);
        panel1.add(rightPanel1, BorderLayout.EAST);
        frame.add(panel1);

        //LÓGICA
        Updates.updateCompAtual(txCOMP, ferroviaControlador, composicao);

        //////////////////////////////////////////////////////////////////GAMBIARRA
        JTextArea txVagoes = new JTextArea(20,30);
        JTextArea txLocomotivas = new JTextArea(20,30);
        //////////////////////////////////////////////////////////////////GAMBIRARRA
        btRemVagao.addActionListener(e -> {
            try {
                ferroviaControlador.getComposicao(composicao).desengataVagao();
            } catch (NenhumVagaoNaComposicaoException ex) {
               Popups.ComposicaoNaoPossuiVagoes();
            }
            Updates.updateVagao(txVagoes, ferroviaControlador);
            Updates.updateCompAtual(txCOMP, ferroviaControlador, composicao);
        });

        btRemLocomotiva.addActionListener(e -> {
            try {
                ferroviaControlador.getComposicao(composicao).desengataLocomotiva();
            } catch (PrimeiraLocomotivaNaoPodeSerRemovidaException ex) {
                Popups.UltimaLocomotiva();
            } catch (VagaoAposLocomotivaException ex) {
                Popups.ALocomotivaPossuiVagoes();
            }
            Updates.updateVagao(txLocomotivas, ferroviaControlador);
            Updates.updateCompAtual(txCOMP, ferroviaControlador, composicao);
        });
        btsairDaEdição.addActionListener(e -> {
            frame.dispose();
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel panel2 = new JPanel(new BorderLayout());

        panel2.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.NORTH);

        //PAINEL ESQUERDA
        JPanel leftPanel2 = new JPanel(new BorderLayout());
        //LABEL VAGOES
        JLabel labelVagoes = new JLabel("Vagoes:");
        leftPanel2.add(labelVagoes, BorderLayout.NORTH);



        // Adicionando JScrollPane ao redor da JTextArea
        JScrollPane scrollPane2 = new JScrollPane(txVagoes);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        leftPanel2.add(scrollPane2);

        //PAINEL DIREITA
        JPanel rightPanel2 = new JPanel(new FlowLayout());


        //BOTAO ADD
        JButton btADD2 = new JButton("Add");
        rightPanel2.add(btADD2, BorderLayout.WEST);

        //LABEL ID
        JLabel labelID2 = new JLabel("ID:");
        rightPanel2.add(labelID2);

        //TEXTO ID
        JTextField txIDvagao = new JTextField(20);
        txIDvagao.setPreferredSize(new Dimension(19, 122));
        rightPanel2.add(txIDvagao);


        panel2.add(leftPanel2, BorderLayout.WEST);
        panel2.add(rightPanel2, BorderLayout.EAST);
        frame.add(panel2);

        //LÓGICA
        Updates.updateVagao(txVagoes,ferroviaControlador);

        btADD2.addActionListener(e -> {
            try {
                ferroviaControlador.engataVagao(composicao, ferroviaControlador.getVagao(Integer.parseInt(txIDvagao.getText())));
            } catch (PesoMaximoExcedidoException ex) {
                throw new RuntimeException(ex);
            } catch (MaximoDeVagoesExcedidoException ex) {
                throw new RuntimeException(ex);
            } catch (VagaoEmOutraComposicaoException ex) {
                throw new RuntimeException(ex);
            }
            Updates.updateVagao(txVagoes, ferroviaControlador);
            Updates.updateCompAtual(txCOMP, ferroviaControlador, composicao);
            txIDvagao.setText("");
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel panel3 = new JPanel(new BorderLayout());

        panel3.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.NORTH);
        //PAINEL ESQUERDA
        JPanel leftPanel3 = new JPanel(new BorderLayout());
        //LABEL VAGOES
        JLabel labelLocomotivas = new JLabel("Locomotivas:");
        leftPanel3.add(labelLocomotivas, BorderLayout.NORTH);



        // Adicionando JScrollPane ao redor da JTextArea
        JScrollPane scrollPane3 = new JScrollPane(txLocomotivas);
        scrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        leftPanel3.add(scrollPane3);

        //PAINEL DIREITA
        JPanel rightPanel3 = new JPanel(new FlowLayout());

        // BOTAO ADD
        JButton btADD3 = new JButton("ADD");
        rightPanel3.add(btADD3, BorderLayout.WEST);

        // LABEL ID
        JLabel labelID3 = new JLabel("ID:");
        rightPanel3.add(labelID3);

        // TEXTO ID
        JTextField txIDLocomotiva = new JTextField(20);
        txIDLocomotiva.setPreferredSize(new Dimension(19, 122));
        rightPanel3.add(txIDLocomotiva);



        panel3.add(leftPanel3, BorderLayout.WEST);
        panel3.add(rightPanel3, BorderLayout.EAST);


        frame.add(panel3);

        //LÓGICA
        Updates.updateLocomotiva(txLocomotivas,ferroviaControlador);

        btADD3.addActionListener(e -> {
            try {
                ferroviaControlador.engataLocomotiva(composicao, ferroviaControlador.getLocomotiva(Integer.parseInt(txIDLocomotiva.getText())));
            } catch (LocomotivaEmOutraComposicaoException ex) {
                Popups.LocomotivaEmOutraComp();
            }

            clearTx(txIDLocomotiva);
            Updates.updateComp(txCOMP, ferroviaControlador);
            Updates.updateLocomotiva(txLocomotivas, ferroviaControlador);
        });


        frame.setVisible(true);
        return ferroviaControlador;
    }
    private static void criarInterface(FerroviaControlador ferroviaControlador) {
        JFrame frame = new JFrame("M.C.T.I.I");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 250);
        frame.setLayout(new BorderLayout());


        JLabel label = new JLabel("Montador de composições de trem\n implementados com interface");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        frame.add(label, BorderLayout.NORTH);

        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout());

        JButton botao1 = criarBotao("Criar trem");
        JButton botao2 = criarBotao("Editar trem");
        JButton botao3 = criarBotao("Ver todos trens");

        panelBotoes.add(botao1);
        panelBotoes.add(botao2);
        panelBotoes.add(botao3);

        frame.add(panelBotoes, BorderLayout.SOUTH);

        frame.setVisible(true);

        botao2.addActionListener(e -> {
            janelaPreEdit(ferroviaControlador);
        });
        botao1.addActionListener(e -> {
            JanelaCriaComp(ferroviaControlador);
        });
        botao3.addActionListener(e -> {
            listarComposicoes(ferroviaControlador);
        });
    }


    public static void janelaPreEdit(FerroviaControlador ferroviaControlador)
    {
        JFrame frame = new JFrame("Antes de Editar");
        frame.setSize(450, 200);
        frame.setLayout(new BorderLayout());


        JLabel label = new JLabel("Digite o id da composição que queira editar");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        frame.add(label, BorderLayout.NORTH);

        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout());

        JButton botao1 = criarBotao("Editar trem");

        JTextField txId = new JTextField(4);

        JLabel txErro = new JLabel("");
        txErro.setFont(new Font("Arial", Font.BOLD,20));
        frame.add(label);


        panelBotoes.add(txId);
        panelBotoes.add(botao1);



        frame.add(panelBotoes, BorderLayout.SOUTH);

        frame.setVisible(true);

        botao1.addActionListener(e -> {
            try {
                if (ferroviaControlador.getComposicao(Integer.parseInt(txId.getText()) ) != null)
                {
                    JanelaEdit(ferroviaControlador, ferroviaControlador.getComposicao(Integer.parseInt(txId.getText())));
                    frame.dispose();
                }
            } catch (IdentificadorNaoEncontradoExceptioin ex) {
                Popups.ComposicaoNãoExisteOULOC();
                frame.dispose();
            }
        });
    }
    public static void JanelaCriaComp(FerroviaControlador ferroviaControlador)
    {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(500,250);

        JPanel painel = new JPanel(new BorderLayout());


        JTextArea txLocomotivas = new JTextArea(5,26);
        txLocomotivas.setFont(new Font("Arial", Font.BOLD, 20));

        JScrollPane scrollPane = new JScrollPane(txLocomotivas);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        Updates.updateLocomotiva(txLocomotivas, ferroviaControlador);
        txLocomotivas.setText(txLocomotivas.getText().replace("],","]\n"));

        painel.add(scrollPane);

        JPanel painelBaixo = new JPanel();

        JButton btCriarComp = new JButton("Criar Comp (ID)");
        painelBaixo.add(btCriarComp);

        JLabel instrucao = new JLabel("Digite o ID da locomotiva para cirar uma Composicao");
        painelBaixo.add(instrucao, BorderLayout.NORTH);

        JTextField txID = new JTextField(4);
        painelBaixo.add(txID, BorderLayout.SOUTH);



        frame.add(painel);
        frame.add(painelBaixo, BorderLayout.SOUTH);
        txLocomotivas.setEditable(false);
        frame.setVisible(true);


        btCriarComp.addActionListener(e -> {
            try {
                ferroviaControlador.criaComposicao(ferroviaControlador.getLocomotiva(Integer.parseInt(txID.getText())));
                frame.dispose();
                janelaPreEdit(ferroviaControlador);

            } catch (LocomotivaEmOutraComposicaoException ex) {
                Popups.LocomotivaEmOutraComp();
            }
            Updates.updateLocomotiva(txLocomotivas, ferroviaControlador);
            txLocomotivas.setText(txLocomotivas.getText().replace("],","]\n"));

        });
    }
    private static JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.PLAIN, 14));
        return botao;
    }
    public static void listarComposicoes(FerroviaControlador ferroviaControlador) {
        JFrame frame = new JFrame("Lista de composições");
        frame.setLayout(new BorderLayout());
        frame.setSize(640, 250);

        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textComp = new JTextArea(10, 40);
        textComp.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textComp);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.CENTER);

        JPanel painel = new JPanel(new FlowLayout());

        JButton btRemoverComp = new JButton("Exlcuir Comp");
        painel.add(btRemoverComp);

        JLabel Id = new JLabel("Id");
        painel.add(Id);

        JTextField txId = new JTextField(4);
        painel.add(txId);

        frame.add(painel, BorderLayout.SOUTH);

        textComp.setText(ferroviaControlador.listaComposicoes().toString().replace("]]], ", "\n"));


        btRemoverComp.addActionListener(e -> {
            try {
                ferroviaControlador.desfazComposicao(ferroviaControlador.getComposicao(Integer.parseInt(txId.getText())));
            } catch (VagaoAposLocomotivaException ex) {
                throw new RuntimeException(ex);
            } catch (NenhumVagaoNaComposicaoException ex) {
                throw new RuntimeException(ex);
            } catch (PrimeiraLocomotivaNaoPodeSerRemovidaException ex) {
                throw new RuntimeException(ex);
            } catch (IdentificadorNaoEncontradoExceptioin ex) {
                throw new RuntimeException(ex);
            }
            txId.setText("");
            textComp.setText(ferroviaControlador.listaComposicoes().toString().replace("]]], ", "\n"));
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        FerroviaControlador ferroviaControlador = new FerroviaControlador();
        ferroviaControlador.preencheGaragens();
        SwingUtilities.invokeLater(() -> criarInterface(ferroviaControlador));

    }
}
package pucrs.poo;

import pucrs.poo.JanelasErro.ControladorUpdatesEjanelas;
import pucrs.poo.JanelasErro.LocomotivaEmOutraComposicao;
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
        frame.setSize(900,600);

        JPanel panel1 = new JPanel(new GridLayout(1, 2));

        //PAINEL ESQUERDA
        JPanel leftPanel1 = new JPanel(new GridLayout(0, 1));
        //LABEL COMPOSICAO
        JLabel labelComp = new JLabel("Id da composicao atual: " + ferroviaControlador);
        leftPanel1.add(labelComp);

        JTextArea txCOMP = new JTextArea(20, 30);

        // Adicionando JScrollPane ao redor da JTextArea
        JScrollPane scrollPane1 = new JScrollPane(txCOMP);
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        leftPanel1.add(scrollPane1);

        //PAINEL DIREITA
        JPanel rightPanel1 = new JPanel(new GridLayout(0, 1));

        //BOTAO REMOVE
        JButton btREM1 = new JButton("REMOVE");
        rightPanel1.add(btREM1);

        //LABEL ID
        JLabel labelID1 = new JLabel("ID DA COMPOSICAO ATUAL:");
        rightPanel1.add(labelID1);

        //TEXTO ID
        JTextField txIDcomp = new JTextField(20);
        rightPanel1.add(txIDcomp);

        //ID COMPOSICAO


        panel1.add(leftPanel1);
        panel1.add(rightPanel1);
        frame.add(panel1);

        //LÓGICA
        Updates.updateComp(txCOMP, ferroviaControlador);

        btREM1.addActionListener(e -> {
            Updates.updateComp(txCOMP, ferroviaControlador);
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel panel2 = new JPanel(new GridLayout(1, 2));

        //PAINEL ESQUERDA
        JPanel leftPanel2 = new JPanel(new GridLayout(0,1));
        //LABEL VAGOES
        JLabel labelVagoes = new JLabel("Vagoes:");
        leftPanel2.add(labelVagoes);

        JTextArea txVagoes = new JTextArea(20,30);

        // Adicionando JScrollPane ao redor da JTextArea
        JScrollPane scrollPane2 = new JScrollPane(txVagoes);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        leftPanel2.add(scrollPane2);

        //PAINEL DIREITA
        JPanel rightPanel2 = new JPanel(new GridLayout(0,2));


        //BOTAO ADD
        JButton btADD2 = new JButton("AawdDD");
        rightPanel2.add(btADD2);

        //LABEL ID
        JLabel labelID2 = new JLabel("ID:");
        rightPanel2.add(labelID2);

        //TEXTO ID
        JTextField txIDvagao = new JTextField(20);
        rightPanel2.add(txIDvagao);


        panel2.add(leftPanel2);
        panel2.add(rightPanel2);
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
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel panel3 = new JPanel(new GridLayout(1, 2));

        //PAINEL ESQUERDA
        JPanel leftPanel3 = new JPanel(new GridLayout(0,1));
        //LABEL VAGOES
        JLabel labelLocomotivas = new JLabel("Locomotivas:");
        leftPanel3.add(labelLocomotivas);

        JTextArea txLocomotivas = new JTextArea(20,30);

        // Adicionando JScrollPane ao redor da JTextArea
        JScrollPane scrollPane3 = new JScrollPane(txLocomotivas);
        scrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        leftPanel3.add(scrollPane3);

        //PAINEL DIREITA
        JPanel rightPanel3 = new JPanel(new BorderLayout());

        // BOTAO ADD
        JButton btADD3 = new JButton("ADD");
        rightPanel3.add(btADD3, BorderLayout.WEST);

        // LABEL ID
        JLabel labelID3 = new JLabel("ID:");
        rightPanel3.add(labelID3, BorderLayout.CENTER);

        // TEXTO ID
        JTextField txIDLocomotiva = new JTextField(20);
        // You can set the preferred size if needed
        txIDLocomotiva.setPreferredSize(new Dimension(19, 12));
        rightPanel3.add(txIDLocomotiva, BorderLayout.EAST);



        panel3.add(leftPanel3);
        panel3.add(rightPanel3);
        frame.add(panel3);

        //LÓGICA
        Updates.updateLocomotiva(txLocomotivas,ferroviaControlador);

        btADD3.addActionListener(e -> {
            try {
                ferroviaControlador.engataLocomotiva(composicao, ferroviaControlador.getLocomotiva(Integer.parseInt(txIDLocomotiva.getText())));
            } catch (LocomotivaEmOutraComposicaoException ex) {
                LocomotivaEmOutraComposicao.LocomotivaEmOutraComp();
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

        JButton botao1 = criarBotao("Criar trem");

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
                }
            } catch (IdentificadorNaoEncontradoExceptioin ex) {
                LocomotivaEmOutraComposicao.ComposicaoNãoExisteOULOC();
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
        frame.setVisible(true);


        btCriarComp.addActionListener(e -> {
            try {
                ferroviaControlador.criaComposicao(ferroviaControlador.getLocomotiva(Integer.parseInt(txID.getText())));
                frame.dispose();
                janelaPreEdit(ferroviaControlador);

            } catch (LocomotivaEmOutraComposicaoException ex) {
                LocomotivaEmOutraComposicao.LocomotivaEmOutraComp();
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
        frame.setSize(640, 250);

        JPanel panel = new JPanel();
        JTextArea textComp = new JTextArea(10, 40);
        textComp.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textComp);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scrollPane);
        frame.add(panel);


        textComp.setText(ferroviaControlador.listaComposicoes().toString().replace("]]], ", "\n"));



        frame.setVisible(true);
    }

    public static void main(String[] args) {
        FerroviaControlador ferroviaControlador = new FerroviaControlador();
        ferroviaControlador.preencheGaragens();
        SwingUtilities.invokeLater(() -> criarInterface(ferroviaControlador));

    }
}
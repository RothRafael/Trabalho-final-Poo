package pucrs.poo;

import pucrs.poo.JanelasErro.ControladorUpdatesEjanelas;
import pucrs.poo.JanelasErro.LocomotivaEmOutraComposicao;
import pucrs.poo.JanelasErro.Updates;
import pucrs.poo.entidades.LocomotivaEmOutraComposicaoException;
import pucrs.poo.entidades.MaximoDeVagoesExcedidoException;
import pucrs.poo.entidades.PesoMaximoExcedidoException;
import pucrs.poo.entidades.VagaoEmOutraComposicaoException;
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

    public static JFrame Paineis(JFrame frame, FerroviaControlador ferroviaControlador)
    {
        JPanel panel1 = new JPanel(new GridLayout(1, 2));

        //PAINEL ESQUERDA
        JPanel leftPanel1 = new JPanel(new GridLayout(0, 1));
        //LABEL COMPOSICAO
        JLabel labelComp = new JLabel("Composicao atual: " + ControladorUpdatesEjanelas.getComposicaoAtual());
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
                ferroviaControlador.engataVagao(ferroviaControlador.getComposicao(Integer.parseInt(txIDcomp.getText())), ferroviaControlador.getVagao(Integer.parseInt(txIDvagao.getText())));
            } catch (PesoMaximoExcedidoException ex) {
                throw new RuntimeException(ex);
            } catch (MaximoDeVagoesExcedidoException ex) {
                throw new RuntimeException(ex);
            } catch (VagaoEmOutraComposicaoException ex) {
                throw new RuntimeException(ex);
            } catch (IdentificadorNaoEncontradoExceptioin ex) {
                throw new RuntimeException(ex);
            }
            Updates.updateVagao(txVagoes, ferroviaControlador);
            Updates.updateComp(txCOMP, ferroviaControlador);
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
        JPanel rightPanel3 = new JPanel(new GridLayout(0,1));

        //BOTAO ADD
        JButton btADD3 = new JButton("ADD");
        rightPanel3.add(btADD3);

        //LABEL ID
        JLabel labelID3 = new JLabel("ID:");
        rightPanel3.add(labelID3);

        //TEXTO ID
        JTextField txIDLocomotiva = new JTextField(20);
        rightPanel3.add(txIDLocomotiva);


        panel3.add(leftPanel3);
        panel3.add(rightPanel3);
        frame.add(panel3);

        //LÓGICA
        Updates.updateLocomotiva(txLocomotivas,ferroviaControlador);

        btADD3.addActionListener(e -> {
            try {
                ferroviaControlador.criaComposicao(ferroviaControlador.getLocomotiva(Integer.parseInt(txIDLocomotiva.getText())));
            } catch (LocomotivaEmOutraComposicaoException ex) {
                LocomotivaEmOutraComposicao.LocomotivaEmOutraComp();
            }
            Updates.updateLocomotiva(txLocomotivas,ferroviaControlador);
            clearTx(txIDLocomotiva);
            Updates.updateComp(txCOMP, ferroviaControlador);
        });

        return frame;

    }

    public static void criarGUI()
    {
        FerroviaControlador ferroviaControlador = new FerroviaControlador();
        ferroviaControlador.preencheGaragens();

        JFrame Mainframe = new JFrame("TrainMaker");
        Mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Mainframe.setSize(900,250);
        Mainframe.setLayout(new GridLayout(0,1));


        Paineis(Mainframe, ferroviaControlador);



        Mainframe.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> criarGUI());

    }
}
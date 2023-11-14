package pucrs.poo;

import pucrs.poo.entidades.Locomotiva;
import pucrs.poo.entidades.LocomotivaEmOutraComposicaoException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Interface {
    public static void updateLocomotiva(JTextArea tx, FerroviaControlador ferroviaControlador)
    {
        tx.setText(ferroviaControlador.listaLocomotivasLivres().toString());
    }

    public static JFrame criarPainel1(JFrame frame, FerroviaControlador ferroviaControlador)
    {


        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(1,2));

        JPanel subPainel2 = new JPanel();

        JPanel subPainel1 = new JPanel();
        subPainel2.setLayout(new GridLayout(0,1));

        JButton bt1 = new JButton("Update");
        JButton bt2 = new JButton("Add");
        JButton bt3 = new JButton("3");

        JTextField txId = new JTextField();

        JTextArea tx = new JTextArea(20,30);
        tx.setLineWrap(true);
        tx.setWrapStyleWord(true);

        JTextArea txTEMPORARIO = new JTextArea(2,30);


        subPainel1.add(tx);
        subPainel2.add(txTEMPORARIO);

        subPainel2.add(bt1);
        subPainel2.add(bt2);
        subPainel2.add(new JLabel("Id:"));
        subPainel2.add(txId);

        painel.add(subPainel1);
        painel.add(subPainel2);

        bt1.addActionListener(e -> {
            updateLocomotiva(tx, ferroviaControlador);
            Gatinho();
        });
        bt2.addActionListener(e -> {
            try {
                ferroviaControlador.criaComposicao(ferroviaControlador.getLocomotiva(Integer.parseInt(txId.getText())));
            } catch (LocomotivaEmOutraComposicaoException ex) {
                throw new RuntimeException(ex);
            }
            txTEMPORARIO.setText(ferroviaControlador.listaComposicoes().toString());
        });

        frame.add(painel);
        return frame;

    }
    public static JFrame criarPainel2(JFrame frame, FerroviaControlador ferroviaControlador)
    {


        JPanel painel = new JPanel();
        JPanel subPainel = new JPanel();
        painel.setLayout(new GridLayout(1,2));
        subPainel.setLayout(new GridLayout(0,1));

        JTextArea txComposicao = new JTextArea();

        painel.add(txComposicao);


        frame.add(painel);
        return frame;
    }
    public static void Gatinho()
    {
        JFrame f = new JFrame();
        try {
            f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\Roth\\Pictures\\Perfil\\DogSad.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        f.pack();
        f.setVisible(true);


    }

    public static void criarGUI()
    {
        FerroviaControlador ferroviaControlador = new FerroviaControlador();
        ferroviaControlador.preencheGaragens();

        JFrame Mainframe = new JFrame("janela");
        Mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Mainframe.setSize(900,250);
        Mainframe.setLayout(new GridLayout(0,1));


        criarPainel1(Mainframe, ferroviaControlador);
        criarPainel2(Mainframe, ferroviaControlador);


        Mainframe.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> criarGUI());

    }
}
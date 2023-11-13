package pucrs.poo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Interface {

    public static JFrame criarPainel1(JFrame frame)
    {
        FerroviaControlador ferroviaControlador = new FerroviaControlador();
        ferroviaControlador.preencheGaragens();

        JPanel painel = new JPanel();
        JPanel subPainel = new JPanel();
        painel.setLayout(new GridLayout(1,2));
        subPainel.setLayout(new GridLayout(0,1));

        JButton bt = new JButton("1");
        JButton bt1 = new JButton("2");
        JButton bt2 = new JButton("3");

        JTextField tx = new JTextField(10);
        painel.add(tx);

        subPainel.add(bt);
        subPainel.add(bt1);
        subPainel.add(bt2);

        painel.add(subPainel);

        bt1.addActionListener(e -> {
            tx.setText(ferroviaControlador.listaVagoesLivres().toString());
        });


        frame.add(painel);
        return frame;
    }
    public static void CriarJanelaPatio()
    {
        JFrame frame = new JFrame("janela");
        frame.setSize(500,500);
        frame.setLayout(new GridLayout(0,1));

        criarPainel1(frame);

        frame.setVisible(true);
    }
    public static void criarGUI()
    {
        JFrame frame = new JFrame("janela");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(new GridLayout(0,1));


        criarPainel1(frame);
        criarPainel1(frame);
        criarPainel1(frame);


        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> criarGUI());

    }
}
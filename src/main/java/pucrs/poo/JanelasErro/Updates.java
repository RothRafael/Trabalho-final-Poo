package pucrs.poo.JanelasErro;

import pucrs.poo.FerroviaControlador;
import pucrs.poo.entidades.Composicao;

import javax.swing.*;

public class Updates
{
    public static void updateLocomotiva(JTextArea tx, FerroviaControlador ferroviaControlador)
    {
        tx.setText(ferroviaControlador.listaLocomotivasLivres().toString().replace("],", "]\n"));
    }
    public static void updateVagao(JTextArea tx, FerroviaControlador ferroviaControlador)
    {
        tx.setText(ferroviaControlador.listaVagoesLivres().toString().replace("],", "]\n"));
    }
    public static void updateComp(JTextArea tx, FerroviaControlador ferroviaControlador)
    {
        tx.setText(ferroviaControlador.listaComposicoes().toString().replace("],", "]\n"));
    }
    public static void updateCompAtual(JTextArea tx, FerroviaControlador ferroviaControlador, Composicao composicao)
    {
        String texto = ferroviaControlador.getComposicao(composicao).toString()

            .replace("],", "]\n")
            .replace(", L=", "]\n")
            .replace("V =", "");

        tx.setText(texto);

    }


}

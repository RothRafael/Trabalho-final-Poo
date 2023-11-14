package pucrs.poo.JanelasErro;

import pucrs.poo.FerroviaControlador;

import javax.swing.*;

public class Updates
{
    public static void updateLocomotiva(JTextArea tx, FerroviaControlador ferroviaControlador)
    {
        tx.setText(ferroviaControlador.listaLocomotivasLivres().toString());
    }
    public static void updateVagao(JTextArea tx, FerroviaControlador ferroviaControlador)
    {
        tx.setText(ferroviaControlador.listaVagoesLivres().toString());
    }
    public static void updateComp(JTextArea tx, FerroviaControlador ferroviaControlador)
    {
        tx.setText(ferroviaControlador.listaComposicoes().toString());
    }

}

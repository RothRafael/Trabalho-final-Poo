package pucrs.poo.JanelasErro;

import javax.swing.*;

public class ControladorUpdatesEjanelas {
    private static int compatual;

    Updates updates = new Updates();

    public static void clearTx(JTextField tx)
    {
        tx.setText("");
    }
    public static void setCompatual(int a)
    {
        compatual = a;
    }
    public static int getComposicaoAtual()
    {
        return compatual;
    }
}

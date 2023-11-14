package pucrs.poo.JanelasErro;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;

public class LocomotivaEmOutraComposicao {
    //JANELAS ERRO!
    public static void LocomotivaEmOutraComp()
    {
        JFrame Mainframe = new JFrame("ERRO!");
        Mainframe.setSize(400,100);
        Mainframe.setLayout(new GridLayout(0,1));

        Mainframe.add(new JLabel("LOCOMOTIVA EM OUTRA COMPOSICAO!"));
        Mainframe.setVisible(true);

    }
}

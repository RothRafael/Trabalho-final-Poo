package pucrs.poo.JanelasErro;

import javax.swing.*;
import java.awt.*;

public class Popups {
    //JANELAS ERRO!
    public static void LocomotivaEmOutraComp()
    {
        JFrame Mainframe = new JFrame("ERRO!");
        Mainframe.setSize(400,100);
        Mainframe.setLayout(new GridLayout(0,1));

        Mainframe.add(new JLabel("LOCOMOTIVA EM OUTRA COMPOSICAO!"));
        Mainframe.setVisible(true);

    }
    public static void ComposicaoNãoExisteOULOC()
    {
        JFrame Mainframe = new JFrame("ERRO!");
        Mainframe.setSize(400,100);
        Mainframe.setLayout(new GridLayout(0,1));

        Mainframe.add(new JLabel("LOCOMOTIVA EM OUTRA COMPOSICAO OU \n COMPOSICAO NAO EXISTE"));
        Mainframe.setVisible(true);

    }
    public static void ComposicaoNaoPossuiVagoes()
    {
        JFrame Mainframe = new JFrame("ERRO!");
        Mainframe.setSize(400,100);
        Mainframe.setLayout(new GridLayout(0,1));

        Mainframe.add(new JLabel("A COMPOSICÃO NÃO POSSUI MAIS VAGÕES"));
        Mainframe.setVisible(true);

    }
    public static void ALocomotivaPossuiVagoes()
    {
        JFrame Mainframe = new JFrame("ERRO!");
        Mainframe.setSize(450,100);
        Mainframe.setLayout(new GridLayout(0,1));

        Mainframe.add(new JLabel("A LOCOMOTIVA POSSUI VAGOES (REMOVA-OS PRIMEIRO)"));
        Mainframe.setVisible(true);

    }
    public static void UltimaLocomotiva()
    {
        JFrame Mainframe = new JFrame("ERRO!");
        Mainframe.setSize(450,100);
        Mainframe.setLayout(new GridLayout(0,1));

        Mainframe.add(new JLabel("NÃO É POSSIVEL REMOVER A ÚNICA LOCOMOTIVA"));
        Mainframe.setVisible(true);

    }
    public static void LocomotivaApósVagao()
    {
        JFrame Mainframe = new JFrame("ERRO!");
        Mainframe.setSize(450,100);
        Mainframe.setLayout(new GridLayout(0,1));

        Mainframe.add(new JLabel("NÃO É POSSIVEL ADICIONAR UMA LOCOMOTIVA APÓS UM VAGAO"));
        Mainframe.setVisible(true);

    }
}

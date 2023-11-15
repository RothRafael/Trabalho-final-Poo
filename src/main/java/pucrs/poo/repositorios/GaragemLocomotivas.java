package pucrs.poo.repositorios;

import pucrs.poo.entidades.Locomotiva;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

/**
 * @author marco.mangan@pucrs.br
 */
public class GaragemLocomotivas {

    /**
     *
     */
    private final ArrayList<Locomotiva> locomotivas;

    /**
     *
     */
    public GaragemLocomotivas() {
        locomotivas = new ArrayList<>();
    }

    /**
     * @param identificador
     * @return
     */
    public Locomotiva getLocomotiva(int identificador) {
        return locomotivas.get(identificador - 1);
    }

    /**
     * Obtém a lista de locomotivas livres.
     *
     * @return a lista de locomotivas livres
     */
    public ArrayList<Locomotiva> getLocomotivasLivres() {
        ArrayList<Locomotiva> livres = new ArrayList<>();
        for (Locomotiva locomotiva : locomotivas) {
            if (locomotiva.getComposicao() == null) {
                livres.add(locomotiva);
            }
        }
        return livres;
    }

    /**
     *
     */
    public void preencheGaragem() throws FileNotFoundException {
//        final int PESO_MAXIMO = 300;
//        final int QTDADE_MAX_VAGOES = 100;
//        locomotivas.add(new Locomotiva(PESO_MAXIMO, QTDADE_MAX_VAGOES));
//        locomotivas.add(new Locomotiva(PESO_MAXIMO, QTDADE_MAX_VAGOES));
//        locomotivas.add(new Locomotiva(PESO_MAXIMO, QTDADE_MAX_VAGOES));
//        locomotivas.add(new Locomotiva(PESO_MAXIMO, QTDADE_MAX_VAGOES));
//        locomotivas.add(new Locomotiva(PESO_MAXIMO, QTDADE_MAX_VAGOES));


        File arquivo = new File("src/main/java/pucrs/poo/repositorios/GaragemLocomotivas.csv");
        Scanner scanner = new Scanner (arquivo);

        while (scanner.hasNextLine())
        {
            String linha = scanner.nextLine();
            String[] aux = linha.split(";");

             int pesoMax = Integer.parseInt(aux[0]);
             int qtdadeMaxVagoes =  Integer.parseInt(aux[1]);

             Locomotiva locomotiva = new Locomotiva (pesoMax, qtdadeMaxVagoes);
             locomotivas.add(locomotiva);
        }
    }

}

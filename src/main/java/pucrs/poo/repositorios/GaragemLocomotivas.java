package pucrs.poo.repositorios;

import pucrs.poo.entidades.Locomotiva;

import java.io.FileWriter;
import java.io.IOException;
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
        for (Locomotiva locomotiva: locomotivas)
        {
            if (locomotiva.getIdentificador() == identificador)
            {
                return locomotiva;
            }
        }
        return null;
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

        File arquivo = new File("src/main/java/pucrs/poo/repositorios/GaragemLocomotivas.csv");
        Scanner scanner = new Scanner (arquivo);

        while (scanner.hasNextLine())
        {
            String linha = scanner.nextLine();
            String[] aux = linha.split(";");

             int pesoMax = Integer.parseInt(aux[0]);
             int qtdadeMaxVagoes =  Integer.parseInt(aux[1]);
             int id = Integer.parseInt(aux[2]);

             Locomotiva locomotiva = new Locomotiva (pesoMax, qtdadeMaxVagoes);
             locomotiva.setId(id);
             locomotivas.add(locomotiva);
        }
    }
    public void salvarGaragem() throws IOException {
        FileWriter arquivo = new FileWriter("src/main/java/pucrs/poo/repositorios/GaragemLocomotivas.csv");

        for (Locomotiva locomotiva: getLocomotivasLivres())
        {
            String pesoMax = locomotiva.getPesoMaximo() + "";
            String qtdadeMaxVagoes = locomotiva.getQtdadeMaxVagoes() + "";
            int id = locomotiva.getIdentificador();

            arquivo.write(pesoMax + ";");
            arquivo.write(qtdadeMaxVagoes + ";");
            arquivo.write(id + "");
            arquivo.append("\n");
        }
        arquivo.close();
    }

}

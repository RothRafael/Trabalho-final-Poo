package pucrs.poo.repositorios;

import pucrs.poo.entidades.Locomotiva;
import pucrs.poo.entidades.Vagao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;


/**
 * @author marco.mangan@pucrs.br
 */
public class GaragemVagoes {

    /**
     *
     */
    private final ArrayList<Vagao> vagoes;

    /**
     * @param identificador
     * @return
     */
    public Vagao getVagao(int identificador) {
        for (Vagao vagao: vagoes)
        {
            if (vagao.getIdentificador() == identificador)
            {
                return vagao;
            }
        }
        return null;
    }

    /**
     *
     */
    public GaragemVagoes() {
        vagoes = new ArrayList<>();
    }

    /**
     * @return
     */
    public java.util.ArrayList<Vagao> getVagoesLivres() {
        ArrayList<Vagao> livres = new ArrayList<>();
        for (Vagao vagao : vagoes) {
            if (vagao.getComposicao() == null) {
                livres.add(vagao);
            }
        }
        return livres;
    }

    /**
     *
     */
    public void preencheGaragem() throws FileNotFoundException {

        File arquivo = new File("src/main/java/pucrs/poo/repositorios/GaragemVagoes.csv");
        Scanner scanner = new Scanner (arquivo);

        while (scanner.hasNextLine())
        {
            String linha = scanner.nextLine();
            String[] aux = linha.split(";");

            int capacidadeCarga = Integer.parseInt(aux[0]);
            int id = Integer.parseInt(aux[1]);

            Vagao vagao = new Vagao (capacidadeCarga);
            vagao.setId(id);
            vagoes.add(vagao);
        }
    }
    public void salvarGaragem() throws IOException {
        FileWriter arquivo = new FileWriter("src/main/java/pucrs/poo/repositorios/GaragemVagoes.csv");

        for (Vagao vagao: getVagoesLivres())
        {
            String capacidadeCarga = vagao.getCapacidadeCarga() + "";
            int id = vagao.getIdentificador();

            arquivo.write(capacidadeCarga + ";");
            arquivo.write(id + "");

            arquivo.append("\n");
        }
        arquivo.close();
    }

}

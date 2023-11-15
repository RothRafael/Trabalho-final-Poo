package pucrs.poo.repositorios;

import pucrs.poo.entidades.Locomotiva;
import pucrs.poo.entidades.Vagao;

import java.io.File;
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
        return vagoes.get(identificador - 1);
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
//        final int CAPACIDADE_CARGA = 2;
//        vagoes.add(new Vagao(CAPACIDADE_CARGA));
//        vagoes.add(new Vagao(CAPACIDADE_CARGA));
//        vagoes.add(new Vagao(CAPACIDADE_CARGA));
//        vagoes.add(new Vagao(CAPACIDADE_CARGA));
//        vagoes.add(new Vagao(CAPACIDADE_CARGA));

        File arquivo = new File("src/main/java/pucrs/poo/repositorios/GaragemVagoes.csv");
        Scanner scanner = new Scanner (arquivo);

        while (scanner.hasNextLine())
        {
            String linha = scanner.nextLine();
            String[] aux = linha.split(";");

            int capacidadeCarga = Integer.parseInt(aux[0]);

            Vagao vagao = new Vagao (capacidadeCarga);
            vagoes.add(vagao);
        }
    }

}

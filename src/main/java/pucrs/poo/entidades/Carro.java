package pucrs.poo.entidades;

public abstract class Carro
{
    public Carro(){}

    public abstract int getIdentificador();

    public abstract Composicao getComposicao();

    public abstract void setComposicao(Composicao composicao);

    public abstract String toString();
}

package model;

public class Dado {
    public final int id;
    private int valor;
    public Dado(int id) {
        this.id = id;
        jogar(false);
    }

    public int jogar(Boolean showMessage) {
        valor = (int) (Math.random() * 6) + 1;
        if(showMessage) {
            System.out.printf("\nDado %d : %d", id, valor);
        }
        return getValor();
    }

    public int getValor() {
        if(valor > 6 || valor < 1) {
            return jogar(false);
        }
        return valor;
    }
}

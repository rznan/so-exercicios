package ex04.view;

import ex04.controller.ThreadNaoRecursiva;
import ex04.controller.ThreadRecursiva;

public class Main {
    public static void main(String[] args) {
        ThreadRecursiva tr = new ThreadRecursiva();
        ThreadNaoRecursiva tnr = new ThreadNaoRecursiva();
        tr.timeFatorial(12, false);
        tnr.timeFatorial(12);
    }
}

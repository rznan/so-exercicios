package ex04.controller;

public class ThreadNaoRecursiva {
    public long timeFatorial(int n) {
        long fatorial = 1, aux = n, start = System.nanoTime();
        while(aux > 1) {
            fatorial *= aux--;
        }
        long elapsedTime = System.nanoTime() - start;
        System.out.printf(
                "Fatorial de %d => %d\nTempo total: %d (ns)\n",
                n, fatorial, elapsedTime
        );
        return fatorial;
    }
}

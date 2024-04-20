package ex04.controller;

public class ThreadRecursiva {
    public long timeFatorial(int n, Boolean hideTiming) {
        if(!hideTiming) {
            long fatorial, start = System.nanoTime();
            if (n <= 1) {
                fatorial = 1;
            }
            else {
                fatorial = n * timeFatorial(n - 1, true);
            }

            long elapsedTime = System.nanoTime() - start;
            System.out.printf(
                    "Fatorial de %d => %d\nTempo total: %d (ns)\n",
                    n, fatorial, elapsedTime
            );
        }
        if (n <= 1) { return 1; }

        return n * timeFatorial(n-1, true);
    }
}

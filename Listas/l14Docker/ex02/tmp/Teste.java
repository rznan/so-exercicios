import java.util.Scanner;

public class Teste {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Escreva um número inteiro: ");
		int a = sc.nextInt();
		System.out.print("Escreva mais um número inteiro: ");
		int b = sc.nextInt();

		int res = a + b;

		System.out.println("SOMA: " + res);
	}
}

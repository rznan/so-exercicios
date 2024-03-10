package ex02.view;

import ex02.controller.KillController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        KillController kc = new KillController();
        Scanner sc = new Scanner(System.in);

        kc.listProcesses();
        System.out.print("Insira um pid ou nome do processo: ");
        String input = sc.nextLine().trim();
        try{
            int pid = Integer.parseInt(input);
            kc.killPid(pid);
        } catch (NumberFormatException e) {
            kc.killName(input);
        }
    }
}

package dev.pedroarthur.fleetmanager.app;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    public static void main(String[] args) {
        System.out.println("\n\n=== 🚗🚒🚙🛻 Bem vindo(a) ao Fleet Manager 🚗🚒🚙🛻 ===\n\n");
        System.out.println("Por favor, escolha a opção desejada\n");
        System.out.println("1 - Menu de cadastrar dados 📝s\n");
        System.out.println("2 - Menu de relatório de dados 🗒️\n");
        System.out.println("3 - Menu de atualizar dados 🔄️\n");
        System.out.println("4 - Menu para deletar dados ❌\n");
        //System.out.println("5 - Relatório 📓\n");
        System.out.println("0 - Sair do Fleet Manager 👋\n");
        System.out.println("-> ");
        String answer;
        Scanner sc = new Scanner(System.in);


        do {
            answer = sc.nextLine();
            switch (answer) {
                case "1":
                    System.out.println("Menu de cadastros 📝\n");
                    break;
                case "2":
                    System.out.println("Menu de relatório de dados existentes🗒️\n");
                    break;
                case "3":
                    System.out.println("Menu de atualizar dados existentes📝\n");
                    break;
                case "4":
                    System.out.println("Menu de deletar dados ️❌\n");
                    break;
                default:
                    try {
                        System.out.println("❌ Opção inválida! Tente novamente! ❌\n");
                        System.out.println("Você será redirecionado(a) ao menu em 5 segundos...");
                        Thread.sleep(5000);
                        System.out.flush();

                        System.out.println("\n\n=== 🚗🚒🚙🛻 Bem vindo(a) ao Fleet Manager 🚗🚒🚙🛻 ===\n\n");
                        System.out.println("Por favor, escolha a opção desejada\n");
                        System.out.println("1 - Menu de cadastrar dados 📝s\n");
                        System.out.println("2 - Menu de relatório de dados 🗒️\n");
                        System.out.println("3 - Menu de atualizar dados 🔄️\n");
                        System.out.println("4 - Menu para deletar dados ❌\n");
                        //System.out.println("5 - Relatório 📓\n");
                        System.out.println("0 - Sair do Fleet Manager 👋\n");
                        System.out.println("-> ");

                        answer = sc.nextLine();
                    }catch(InterruptedException e){
                        System.err.println("O temporizador falhou ou foi interrompido...");
                        break;
                    }

            }
        } while (!answer.equals("0")); //while answer differs from 0 the app won't close

        System.out.println("👋 Até logo! 👋");
        sc.close();
    }

}
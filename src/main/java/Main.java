import Biblioteca.Biblioteca;
import Usuario.Administrador;
import Usuario.Leitor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner dados = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        System.out.println("---INICIO DA BIBLIOTECA---");
        System.out.println("Seja bem vindo ao sistema de gerenciamento de biblioteca!");

        int opcao = -1;

        System.out.println("---Antes de acessar o menu adicione um Administrador---\n");
        biblioteca.cadastrarAdministrador();

        while(opcao!=0){
            System.out.println("\n---Menu da Biblioteca---");
            System.out.println("0-Encerrar programa");
            System.out.println("1-Cadastrar administrador");
            System.out.println("2-Fazer login como administrador");
            System.out.println("3-Cadastrar novo usuário");
            System.out.println("4-Fazer login como usuário");
            System.out.println("5-Mostrar acervo");
            System.out.print("Escolha uma das opções: ");
            opcao = dados.nextInt();
            dados.nextLine();

            switch (opcao){
                case 0:
                    System.out.println("---O programa está sendo encerrado---");
                    break;
                case 1:
                    biblioteca.cadastrarAdministrador();
                    break;
                case 2:
                    //fazer login adm
                    break;
                case 3:
                    biblioteca.cadastrarLeitor();
                    break;
                case 4:
                    System.out.println("--Login do leitor--");
                    int idLogin = dados.nextInt();
                    dados.nextLine();

                    //todo adicionar try/catch
                    Leitor leitor = biblioteca.buscarLeitor(idLogin);
                    int opcaoUsuario = -1;

                    while(opcaoUsuario!=0){
                        System.out.println("\n--Menu do Leitor--");
                        System.out.println("0-Encerrar sessão");
                        System.out.println("1-Mostrar dados do usuario");
                        System.out.println("2-Fazer reserva de livro");
                        System.out.println("3-Pegar reserva feita");
                        System.out.println("4-Fazer emprestimo de livro");
                        System.out.println("5-Devolver emprestimo");
                        System.out.print("Escolha uma das opções: ");
                        opcaoUsuario = dados.nextInt();
                        dados.nextLine();

                        switch(opcaoUsuario){
                            case 0:
                                System.out.println("--Desconectando usuario--");
                                break;
                            case 1:

                                break;
                        }
                    }

                    break;
                case 5:
                    biblioteca.mostrarAcervo();
                    break;
                default:
                    System.out.println("\n---OPÇÃO INVALIDA---\n");
            }
        }
    }
}

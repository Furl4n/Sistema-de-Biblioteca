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

        System.out.println("---Antes de acessar o menu adicione um Administrador---");
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
                    System.out.println("--Login do Administrador--");
                    System.out.print("Digite o Id do usuário: ");
                    String idAdm = dados.nextLine();

                    Administrador administrador = biblioteca.loginAdministrador(idAdm);
                    int opcaoAdm = -1;

                    while(opcaoAdm !=0) {
                        System.out.println("\n--Menu do Administrador--");
                        System.out.println("0-Encerrar sessão");
                        System.out.println("1-Mostrar dados do usuário");
                        System.out.println("2-Fazer cadastro de livro");
                        System.out.println("3-Remover livro");
                        System.out.println("4-Gerenciar usuário");
                        System.out.println("5-Gerar relatório");
                        System.out.print("Escolha uma das opções: ");
                        opcaoAdm = dados.nextInt();
                        dados.nextLine();

                        switch (opcaoAdm) {
                            case 0:
                                System.out.println("\n--Desconectando usuário--");
                                break;
                            case 1:
                                administrador.mostrarUsuario();
                                break;
                            case 2:
                                administrador.cadastrarLivro(biblioteca);
                                break;
                            case 3:
                                administrador.removerLivro(biblioteca);
                                break;
                            case 4:
                                administrador.gerenciarUsuario();
                                break;
                            case 5:
                                administrador.gerarRelatorio(biblioteca);
                                break;
                            default:
                                System.out.println("\n---OPÇÃO INVALIDA---\n");
                                break;
                        }
                    }
                    break;
                case 3:
                    biblioteca.cadastrarLeitor();
                    break;
                case 4:
                    System.out.println("--Login do leitor--");
                    System.out.print("Digite o Id do usuário: ");
                    String idLeitor = dados.nextLine();

                    //todo adicionar try/catch
                    Leitor leitor = biblioteca.loginLeitor(idLeitor);
                    int opcaoLeitor = -1;

                    while(opcaoLeitor!=0){
                        System.out.println("\n--Menu do Leitor--");
                        System.out.println("0-Encerrar sessão");
                        System.out.println("1-Mostrar dados do usuário");
                        System.out.println("2-Fazer reserva de livro");
                        System.out.println("3-Pegar reserva feita");
                        System.out.println("4-Fazer empréstimo de livro");
                        System.out.println("5-Devolver empréstimo");
                        System.out.print("Escolha uma das opções: ");
                        opcaoLeitor = dados.nextInt();
                        dados.nextLine();

                        switch(opcaoLeitor){
                            case 0:
                                System.out.println("--Desconectando usuário--");
                                break;
                            case 1:
                                leitor.mostrarUsuario();
                                break;
                            case 2:
                                leitor.fazerReserva(biblioteca);
                                break;
                            case 3:
                                leitor.pegarReserva(biblioteca);
                                break;
                            case 4:
                                leitor.realizarEmprestimo(biblioteca);
                                break;
                            case 5:
                                leitor.devolverEmprestimo(biblioteca);
                                break;
                            default:
                                System.out.println("\n---OPÇÃO INVALIDA---\n");
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

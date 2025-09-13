package Biblioteca;

import Livros.Livro;
import Livros.StatusLivro;
import Usuario.Administrador;
import Usuario.Leitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Biblioteca {  //Inicia as listas da biblioteca
    private List<Livro> acervo = new ArrayList<>();
    private List<Leitor> leitores = new ArrayList<>();
    private List<Administrador> administradores = new ArrayList<>();


    public void cadastrarLeitor() {
            //cadastra o leitor no sistema
            String nome, email;
            Scanner dados = new Scanner(System.in);
        System.out.println("\n--Cadastro de Usuário--\n");
            System.out.print("Digite seu nome: ");
            nome = dados.nextLine();
            System.out.print("Digite seu email: ");
            email = dados.nextLine();

            System.out.println("\nLeitor cadastrado com sucesso!");

            Leitor leitor = new Leitor(nome, email);

            leitor.mostrarUsuario();
            leitores.add(leitor);
        }

    public void cadastrarAdministrador(){
            //cadastra o leitor no sistema
            String nome, email,senha, conf_senha;
            Scanner dados = new Scanner(System.in);
        System.out.println("\n--Cadastro de Administrator--\n");
            System.out.print("Digite seu nome: ");
            nome = dados.nextLine();
            System.out.print("Digite seu email: ");
            email = dados.nextLine();
            do{//confirmação da senha
                System.out.print("Digite a senha: ");
                senha = dados.nextLine();

                System.out.print("Confirme a senha: ");
                conf_senha = dados.nextLine();

                if (!senha.equals(conf_senha)) {
                    System.out.println("\n*** Confirmação inválida: as senhas não correspondem. ***\n");
                }
            }while(!senha.equals(conf_senha));

            System.out.println("\nAdministrador cadastrado com sucesso!");

            Administrador adm = new Administrador(nome, email,senha);

            adm.mostrarUsuario();
            administradores.add(adm);
        }

    public void cadastrarLivro(){ //efetua a ação de criar o livro solicitada pelo adm
        String  titulo, autor, genero;
        int anoPublicacao;
        StatusLivro status;
        Scanner dados = new Scanner(System.in);

        System.out.println("\n--Cadastro de Livro--\n");

        System.out.print("Titulo: ");
        titulo = dados.nextLine();
        System.out.print("Autor: ");
        autor = dados.nextLine();
        System.out.print("Gênero: ");
        genero = dados.nextLine();
        System.out.print("Ano de Publicação: ");
        anoPublicacao = dados.nextInt();
        dados.nextLine();

        Livro novo = new Livro(titulo, autor, anoPublicacao, genero, StatusLivro.Disponivel); //cria o livro
        acervo.add(novo); //adiciona o livro no acervo

        System.out.println("\nLivro criado com sucesso!");
    }

    public void removerLivro(){ //remove o livro do acervo
        Scanner dados = new Scanner(System.in);

        System.out.println("\n--Apagar Livro--\n");

        System.out.print("Qual o Id do livro que deseja apagar? ");
        String IdparaApagar = dados.nextLine();
        //faz uma busca no acervo procurando um livro com esse Id, o Optional é para caso não encontre ele retorn vazio
        Optional<Livro> livroParaApagar = acervo.stream().filter(Livro -> Livro.getIdUnico().equals(IdparaApagar)).findFirst();

        if(livroParaApagar.isPresent()){ //Ve se achou um livro no acervo
            Livro livro= livroParaApagar.get();
            if(livro.getStatus() == StatusLivro.Disponivel){
                acervo.remove(livro); //apaga o livro
                System.out.println("O livro " +livro.getTitulo() + "foi apagado com sucesso!");
            } else{
                System.out.println("Não foi possível apagar o livro, ele está emprestado ou reservado.");
            }
        } else{ // Se ele retornar vazio para o Optional
            System.out.println("Nenhum livro com esse Id foi encontrado.");
        }
    }

    public Livro buscarLivro(String idLivro) throws RuntimeException {

        //faz uma busca no acervo procurando um livro com esse Id, o Optional é para caso não encontre ele retorn vazio
        Optional<Livro> livroParaBuscar = acervo.stream().filter(Livro -> Livro.getIdUnico().equals(idLivro)).findFirst();

        if(livroParaBuscar.isPresent()){ //Ve se achou um livro no acervo
            return livroParaBuscar.get();
        } else{ // Se ele retornar vazio para o Optional
                throw new RuntimeException("Livro não cadastrado");
        }
    }

    public Leitor buscarLeitor(int idLeitor) throws RuntimeException {

            //faz uma busca no acervo procurando o leitor com esse Id, o Optional é para caso não encontre ele retorn vazio
            Optional<Leitor> leitorParaBuscar = leitores.stream().filter(Leitor -> Leitor.getId() == idLeitor).findFirst();

            if(leitorParaBuscar.isPresent()){ //Ve se achou o leitor na lista
                return leitorParaBuscar.get();
            } else{ // Se ele retornar vazio para o Optional
                throw new RuntimeException("Leitor não cadastrado");
            }
        }

    public void mostrarAcervo(){  //estrutura base para mostrar o acervo
        if (acervo.isEmpty()) {
            System.out.println("O acervo está vazio.");
        } else{
            System.out.println("\n--Livros no acervo--");
            for(Livro livro : acervo){
                livro.mostrarLivro();
            }
            System.out.println("\n--Fim do acervo--");
        }
    }

    public List<Livro> getAcervo() {
            return acervo;
        }

    public List<Leitor> getLeitores() {
        return leitores;
    }

    public List<Administrador> getAdministradores() {
        return administradores;
    }
    }



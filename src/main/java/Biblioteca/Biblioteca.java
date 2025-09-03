package Biblioteca;

import Livros.Livro;
import Livros.StatusLivro;
import Usuario.Administrador;
import Usuario.Leitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

    //todo terminar método mostrarAcervo
public class Biblioteca {  //Inicia as listas da biblioteca
    List<Livro> acervo = new ArrayList<>();
    List<Leitor> leitores = new ArrayList<>();
    List<Administrador> administradores = new ArrayList<>();

        public void cadastrarLeitor() { //cadastra o leitor no sistema todo tornar cadastro interativo
            Leitor leitor = new Leitor("15b", "pedro", "pedrogfurlan1@gmail.com");
            leitores.add(leitor);
        }

    public void cadastrarLivro(){ //efetua a ação de criar o livro solicitada pelo adm
        String idUnico, titulo, autor, genero;
        int anoPublicacao;
        StatusLivro status;
        Scanner dados = new Scanner(System.in);

        System.out.print("Código: ");
        idUnico = dados.nextLine();
        System.out.print("Titulo: ");
        titulo = dados.nextLine();
        System.out.print("Autor: ");
        autor = dados.nextLine();
        System.out.print("Gênero: ");
        genero = dados.nextLine();
        System.out.print("Ano de Publicação: ");
        anoPublicacao = dados.nextInt();
        dados.nextLine();
        status = StatusLivro.Disponivel;

        Livro novo = new Livro(idUnico,titulo, autor, anoPublicacao, genero, status); //cria o livro

        acervo.add(novo); //adiciona o livro no acervo
    }

    public void removerLivro(){ //remove o livro do acervo
        Scanner dados = new Scanner(System.in);

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

    public void mostrarAcervo(){  //estrutura base para mostrar o acervo todo terminar código
        if (acervo.isEmpty()) {
            System.out.println("O acervo está vazio.");
        } else{
            for(Livro livro : acervo){
                System.out.println("Livro: ");
            }
        }
    }

    public void realizarEmprestimo(){ //metodo que vai iniciar o empreatimo
        Scanner dados = new Scanner(System.in);
        Leitor leitor;
        System.out.println("\n--Realizar emprestimo de livro--\n");
        System.out.print("Quem deseja pegar o livro emprestado?(id) ");
        String idLeitor = dados.nextLine(); //identifica o id do leitor que deseja fazer o emprestimo

        //procura o livro
        Optional<Leitor> idLeitorPegar = leitores.stream().filter(Leitor -> Leitor.getId().equals(idLeitor)).findFirst();

        if(idLeitorPegar.isPresent()){
            leitor = idLeitorPegar.get(); //salva o leitor se existir
        } else{
            System.out.println("Usuário inválido!"); //retorna erro se não existir
            return;
        }

        System.out.print("Qual livro deseja pegar emprestado(Id)? ");
        String IdLivro = dados.nextLine();

        //procura o livro
        Optional<Livro> livroParaEmprestar = acervo.stream().filter(Livro -> Livro.getIdUnico().equals(IdLivro)).findFirst();

        if(livroParaEmprestar.isPresent()){
            Livro livro = livroParaEmprestar.get(); //salva qual o livro se existir

            System.out.print("Por quantos dias deseja pegar emprestado? ");
            int prazoDevolucao = dados.nextInt();
            dados.nextLine();
            if(livro.emprestar(leitor, prazoDevolucao)){ //chama o metodo de Livro que vai criar o emprestimo
                System.out.println("emprestimo realizado com sucesso!");
                //informa que deu certo quando retorna 'true'
            } else{
                System.out.println("O livro " + livro.getTitulo() + " já está " + livro.getStatus());
                //informa que deu errado quando retorna 'false'
            }
        }
    }
}



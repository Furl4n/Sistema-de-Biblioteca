package Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import Acoes.*;
import Biblioteca.Biblioteca;
import Livros.Livro;
import Livros.StatusLivro;

public class Leitor extends Usuario {

    private List<Emprestimo> historicoEmprestimo = new ArrayList<>();
    private List<Reserva> livrosReservados = new ArrayList<>();

    public Leitor() {
    }

    public Leitor(String nome, String email) {
        super(nome, email);
    }

    @Override
    public void mostrarUsuario(){
        System.out.println("\n---------------------------");
        System.out.println("Nome: " + this.getNome());
        System.out.println("Email: " + this.getEmail());
        System.out.println("---------------------------\n");

    }

    public void realizarEmprestimo(Biblioteca biblioteca){ //metodo que vai iniciar o emprestimo
        Scanner dados = new Scanner(System.in);
        Livro livro;
        System.out.println("\n--Realizar emprestimo de livro--\n");


        System.out.print("Qual livro deseja pegar emprestado(Id)? ");
        String idLivro = dados.nextLine();

        try{
            livro = biblioteca.buscarLivro(idLivro);
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        System.out.print("Por quantos dias deseja pegar o livro? ");
        int prazoDevolucao = dados.nextInt();
        dados.nextLine();

        if(livro.getStatus() ==  StatusLivro.Disponivel){ //caso o livro esteja 'Disponivel'
            Emprestimo emprestimo = new Emprestimo(livro, prazoDevolucao); //Cria o emprestimo
            historicoEmprestimo.add(emprestimo); //adiciona o emprestimo no historico do leitor

            System.out.println("Emprestimo realizado com sucesso!");
        } else{ //caso o livro esteja 'emprestado' ou 'Reservado'
            System.out.println("O livro " + livro.getTitulo() + " já está " + livro.getStatus());
        }
    }

    public void devolverEmprestimo(Biblioteca biblioteca){

    }

    public void fazerReserva(Biblioteca biblioteca) {
        Scanner dados = new Scanner(System.in);
        Livro livro;

        System.out.println("\n--Realizar reserva de livro--\n");

        System.out.print("Qual livro deseja reservar? ");
        String idLivro = dados.nextLine();

        try{
            livro = biblioteca.buscarLivro(idLivro);
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }
        //verefica nos livros reservados por meio do Titulo, se esse livro ja foi reservado pelo leitor;
        //confere o status se o status de reserva está ativa;
        Optional<Reserva> livroReserva = livrosReservados.stream().filter(reserva -> reserva.getLivroReservado().getTitulo().equals(livro.getTitulo()) && reserva.getStatusReserva() == statusReserva.Ativa).findFirst();

        if(livroReserva.isPresent()){ //caso encontre o livro no FindFirst
            System.out.println("Voce ja reservou esse livro!");
            return;
        }

        if(livro.getStatus() !=  StatusLivro.Disponivel){
            System.out.println("O livro não está disponível para reserva");
        }else {

            System.out.print("Por quanto tempo deseja reservar? ");
            int tempoParaBuscar = dados.nextInt();
            dados.nextLine();

            Reserva reservado = new Reserva(livro, tempoParaBuscar);

            livrosReservados.add(reservado);

            System.out.println("Reserva realizada com sucesso!");
            //todo mostrar reserva;
        }
    }

    public void pegarReserva(Biblioteca biblioteca){
        Scanner dados = new Scanner(System.in);

        System.out.println("\n--Pegar livro reservado--\n");
        System.out.print("Digite o código da reserva: ");
        int idReserva= dados.nextInt();
        dados.nextLine();

        Optional<Reserva> buscaReserva = livrosReservados.stream().filter(Reserva -> Reserva.getIdReserva() == idReserva).findFirst();

        if(buscaReserva.isPresent()){
            Reserva reserva = buscaReserva.get();
            reserva.setStatusReserva(statusReserva.Confirmada);

            System.out.print("Por quantos dias deseja pegar o livro? ");
            int prazoDevolucao = dados.nextInt();
            dados.nextLine();

            Emprestimo emprestimo = new Emprestimo(reserva.getLivroReservado(), prazoDevolucao); //Cria o emprestimo
            historicoEmprestimo.add(emprestimo);

            System.out.println("Reserva concluida com sucesso!");
        } else{
            System.out.println("Reserva inexistente!");
        }
    }

    public List<Emprestimo> getHistoricoEmprestimo() {
        return historicoEmprestimo;
    }

    public List<Reserva> getLivrosReservados() {
        return livrosReservados;
    }
}
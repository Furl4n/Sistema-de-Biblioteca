package Usuario;

import java.time.LocalDate;
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

    public void realizarEmprestimo(Biblioteca biblioteca) {
        Scanner dados = new Scanner(System.in);
        Livro livro;

        System.out.println("\n--Realizar emprestimo de livro--\n");
        System.out.print("Qual livro deseja pegar emprestado(Id)? ");
        String idLivro = dados.nextLine();

        try {
            livro = biblioteca.buscarLivro(idLivro);
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        // se o livro estiver disponível -> empresta direto
        if (livro.getStatus() == StatusLivro.Disponivel) {
            System.out.print("Por quantos dias deseja pegar o livro? ");
            int prazoDevolucao = dados.nextInt();
            dados.nextLine();

            Emprestimo emprestimo = new Emprestimo(livro, prazoDevolucao);
            historicoEmprestimo.add(emprestimo);
            livro.setStatus(StatusLivro.Emprestado);

            System.out.println("Emprestimo realizado com sucesso!");
            return;
        }

        // se o livro está reservado, conferir se a reserva pertence a este leitor
        Optional<Reserva> minhaReserva = livrosReservados.stream()
                .filter(reserva -> reserva.getLivroReservado().getIdUnico().equals(livro.getIdUnico())
                        && reserva.getStatusReserva() == statusReserva.Ativa)
                .findFirst();

        if (minhaReserva.isPresent()) {
            System.out.print("Por quantos dias deseja pegar o livro? ");
            int prazoDevolucao = dados.nextInt();
            dados.nextLine();

            Emprestimo emprestimo = new Emprestimo(livro, prazoDevolucao);
            historicoEmprestimo.add(emprestimo);
            livro.setStatus(StatusLivro.Emprestado);

            // atualiza a reserva como confirmada
            minhaReserva.get().setStatusReserva(statusReserva.Confirmada);

            System.out.println("Você tinha uma reserva, agora ela virou empréstimo!");
        } else {
            System.out.println("O livro não está disponível para você no momento.");
        }
    }

    public void devolverEmprestimo(Biblioteca biblioteca){
        Scanner dados = new Scanner(System.in);
        LocalDate dataDevol = LocalDate.now(); //data de entrega;
        Livro livro;

        System.out.println("\n--Devolver emprestimo de livro--\n");

        System.out.println("Qual livro deseja devolver?(Id) ");
        String idLivro = dados.nextLine();
        //procura no historico do leitor o Id do livro;
        Optional<Emprestimo> emprestimoLeitor = historicoEmprestimo.stream().filter(Emprestimo -> Emprestimo.getLivroEmprestado().getIdUnico().equals(idLivro)).findFirst();
        //caso nao encontre;
        if(emprestimoLeitor.isEmpty()){
            System.out.println("Livro não encontrado!");
            return;
        }
        Emprestimo emprestimo = emprestimoLeitor.get();

        livro = emprestimoLeitor.get().getLivroEmprestado();
        //chama a função de multa
        if(emprestimo.getDataDevolucao().isAfter(dataDevol)){
            System.out.println( "Você tem um taxa aplicada sobre atraso.");
            float valor = emprestimoLeitor.get().calcularMulta(dataDevol);
            System.out.println("\nValor: \n" + valor);

        }

        // verifica se existe uma reserva ativa para este livro
        Optional<Reserva> reservaAtiva = livrosReservados.stream()
                .filter(reserva -> reserva.getLivroReservado().getIdUnico().equals(livro.getIdUnico())
                && reserva.getStatusReserva() == statusReserva.Ativa).findFirst();

        if (reservaAtiva.isPresent()) {
            // Se houver reserva ativa, o livro não fica disponível, vai direto para reservado
            Reserva reserva = reservaAtiva.get();
            livro.setStatus(StatusLivro.Reservado);
            reserva.setStatusReserva(statusReserva.Confirmada);

            System.out.println("O livro foi devolvido e já está reservado para outro leitor!");
            return;
        }

        System.out.println("Livro devolvido com sucesso!");

        livro.setStatus(StatusLivro.Disponivel);

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

    //todo cancelarResevc();

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
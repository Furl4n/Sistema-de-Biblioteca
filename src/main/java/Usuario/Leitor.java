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

    public void adicionarEmprestimo(Emprestimo emprestimo) { //salva o emprestimo no historico do Leitor
        historicoEmprestimo.add(emprestimo);
    }

    public void fazerReserva(Biblioteca biblioteca) {
        Scanner dados = new Scanner(System.in);

        System.out.println("--Realizar reserva de livro--");

        System.out.print("Qual livro deseja reservar? ");
        String idLivro = dados.nextLine();

        try{
            biblioteca.buscarLivro(idLivro);
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        //livro.setStatus(StatusLivro.Reservado);

        System.out.print("Por quanto tempo deseja reservar? ");
        int tempoParaBuscar = dados.nextInt();
        dados.nextLine();

        //Reserva reservado = new Reserva(livro, tempoParaBuscar);
        //livrosReservados.add(reservado);

        //todo mostrar reserva;
    }

    public void pegarReserva(){
        int idReserva =0;
        Optional<Reserva> buscaReserva = livrosReservados.stream().filter(Reserva -> Reserva.getIdReserva() == idReserva).findFirst();

        if(buscaReserva.isPresent()){
            Reserva reserva = buscaReserva.get();
            //Leitor.solicitarEmprestimo();
        }
    }

}
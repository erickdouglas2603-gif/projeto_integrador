import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class SistemaEscola {

    private ListaDeAlunos listaAlunos;
    private ArrayList<Turma> turmas;

    private Scanner scanner;

    public SistemaEscola(){

        listaAlunos = new ListaDeAlunos();
        turmas = new ArrayList<>();

        scanner = new Scanner(System.in);

    }

    public void iniciar(){

        int opcao;

        do{

            System.out.println("\n===== SISTEMA ESCOLA =====");

            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Cadastrar turma");
            System.out.println("3 - Listar alunos");
            System.out.println("4 - Matricular aluno em turma");
            System.out.println("5 - Listar turmas e alunos matriculados");
            System.out.println("0 - Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch(opcao){

                case 1:
                    cadastrarAluno();
                break;

                case 2:
                    cadastrarTurma();
                break;

                case 3:
                    listarAlunos();
                break;
                
                case 4:
                    matricularAluno();
                break;
                
                case 5:
                    listarAlunosDaTurma();
                break;



            }

        }while(opcao != 0);

    }

    private void cadastrarAluno(){

        System.out.println("Nome:");
        String nome = scanner.nextLine();

        System.out.println("CPF:");
        String cpf = scanner.nextLine();

        System.out.println("Endereco:");
        String endereco = scanner.nextLine();

        System.out.println("Ano nascimento:");
        int ano = scanner.nextInt();

        System.out.println("Mes:");
        int mes = scanner.nextInt();

        System.out.println("Dia:");
        int dia = scanner.nextInt();
        scanner.nextLine();

        LocalDate data = LocalDate.of(ano, mes, dia);

        Aluno aluno = new Aluno(nome, cpf, endereco, data);

        listaAlunos.incluirNoFim(aluno);

    }

    private void cadastrarTurma(){

        System.out.println("Codigo turma:");
        String codigo = scanner.nextLine();

        System.out.println("Etapa ensino:");
        String etapa = scanner.nextLine();

        System.out.println("Ano:");
        int ano = scanner.nextInt();

        System.out.println("Limite vagas:");
        int limite = scanner.nextInt();
        scanner.nextLine();

        Turma turma = new Turma(codigo, etapa, ano, limite);

        turmas.add(turma);

    }

    private void listarAlunos(){

        listaAlunos.ordenar();

        for(int i=0;i<listaAlunos.tamanho();i++){

            Aluno aluno = listaAlunos.get(i);

            System.out.println(aluno.getNome() + " - " + aluno.getIdade() + " anos");

        }

    }

    private Aluno buscarAlunoPorNome(String nome){

        for(int i=0;i<listaAlunos.tamanho();i++){

            Aluno aluno = listaAlunos.get(i);

            if(aluno.getNome().equalsIgnoreCase(nome)){
                return aluno;
            }

        }

        return null;

    }

    private Turma buscarTurmaPorCodigo(String codigo){

        for(Turma turma : turmas){

            if(turma.getCodigo().equalsIgnoreCase(codigo)){
                return turma;
            }

        }

        return null;

    }

    private void matricularAluno(){

        System.out.println("Nome do aluno:");
        String nomeAluno = scanner.nextLine();

        Aluno aluno = buscarAlunoPorNome(nomeAluno);

        if(aluno == null){
            System.out.println("Aluno nao encontrado!");
            return;
        }

        System.out.println("Codigo da turma:");
        String codigoTurma = scanner.nextLine();

        Turma turma = buscarTurmaPorCodigo(codigoTurma);

        if(turma == null){
            System.out.println("Turma nao encontrada!");
            return;
        }

        if(turma.adicionarAluno(aluno)){
            System.out.println("Aluno matriculado com sucesso!");
        }else{
            System.out.println("Turma cheia! Nao foi possivel matricular o aluno.");
        }

    }

    private void listarAlunosDaTurma(){
        System.out.println("Codigo da turma:");
        String codigoTurma = scanner.nextLine();

        Turma turma = buscarTurmaPorCodigo(codigoTurma);

        if(turma == null){
            System.out.println("Turma nao encontrada!");
            return;
        }

        System.out.println("Alunos matriculados na turma " + turma.getCodigo() + ":");

        for(Aluno aluno : turma.getAlunos()){
            System.out.println(aluno.getNome());
        }

    }

}
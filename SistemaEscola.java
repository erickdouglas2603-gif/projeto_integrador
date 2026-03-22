import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
            System.out.println("5 - Listar turmas");
            System.out.println("6 - Alunos fora da idade por etapa");
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
                    listarTurmas();
                break;
                
                case 6:
                    alunosForaDaIdade();
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

    System.out.println("Codigo da turma:");
    String codigo = scanner.nextLine();

    String etapa = escolherEtapa();

    System.out.println("Ano:");
    int ano = scanner.nextInt();

    System.out.println("Limite de vagas:");
    int limite = scanner.nextInt();
    scanner.nextLine();

    Turma turma = new Turma(codigo, etapa, ano, limite);

    turmas.add(turma);

    System.out.println("Turma cadastrada com sucesso!");

}

    private String escolherEtapa(){

    int opcao;

    while(true){

        System.out.println("Escolha a etapa de ensino:");
        System.out.println("1 - Infantil");
        System.out.println("2 - Fundamental Inicial");
        System.out.println("3 - Fundamental Final");
        System.out.println("4 - Medio");

        opcao = scanner.nextInt();
        scanner.nextLine();

        switch(opcao){
            case 1: return "infantil";
            case 2: return "fundamental_inicial";
            case 3: return "fundamental_final";
            case 4: return "medio";
            default:
                System.out.println("Opcao invalida! Tente novamente.\n");
        }

    }

}

    private void listarAlunos(){

    listaAlunos.ordenar();

    System.out.println("\n=== LISTA DE ALUNOS ===");

    for(int i = 0; i < listaAlunos.tamanho(); i++){

        Aluno aluno = listaAlunos.get(i);

        String turma = buscarTurmaDoAluno(aluno);

        System.out.println("---------------------------");
        System.out.println("Nome: " + aluno.getNome());
        System.out.println("CPF: " + aluno.getCpf());
        System.out.println("Nascimento: " + aluno.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Idade: " + aluno.getIdade() + " anos");
        System.out.println("Endereco: " + aluno.getEndereco());
        System.out.println("CPF: " + aluno.getCpf());

        if(turma != null){
            System.out.println("Turma: " + turma);
        } else {
            System.out.println("Turma: NÃO MATRICULADO");
        }

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

    private String buscarTurmaDoAluno(Aluno aluno){

    for(Turma t : turmas){

        if(t.getAlunos().contains(aluno)){
            return t.getCodigo();
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

    
    

    private void alunosForaDaIdade(){

    String etapa = escolherEtapa();

    int contador = 0;

    for(Turma t : turmas){

        if(t.getEtapaEnsino().equalsIgnoreCase(etapa)){

            for(Aluno a : t.getAlunos()){

                int idade = a.getIdade();

                if(!idadeValida(etapa, idade)){

                    contador++;

                    String etapaCorreta = etapaCorreta(idade);

                    System.out.println("\nAluno fora da idade:");
                    System.out.println("Nome: " + a.getNome());
                    System.out.println("Idade: " + idade);
                    System.out.println("Turma: " + t.getCodigo());
                    System.out.println("Etapa atual: " + etapa);
                    System.out.println("Etapa correta: " + etapaCorreta);

                }

            }

        }

    }

    System.out.println("\nTotal de alunos fora da idade: " + contador);

}

private boolean idadeValida(String etapa, int idade){

    switch(etapa.toLowerCase()){

        case "infantil":
            return idade < 6;

        case "fundamental_inicial":
            return idade >= 6 && idade <= 11;

        case "fundamental_final":
            return idade >= 11 && idade <= 15;

        case "medio":
            return idade >= 15 && idade <= 18;

        default:
            return false;
    }

}

private String etapaCorreta(int idade){

    if(idade < 6){
        return "infantil";
    } else if(idade <= 11){
        return "fundamental_inicial";
    } else if(idade <= 15){
        return "fundamental_final";
    } else {
        return "medio";
    }

}

private void listarTurmas(){

    if(turmas.isEmpty()){
        System.out.println("Nenhuma turma cadastrada.");
        return;
    }

    System.out.println("\n=== TURMAS CADASTRADAS ===");

    for(Turma t : turmas){

        System.out.println("---------------------------");
        System.out.println("Codigo: " + t.getCodigo());
        System.out.println("Etapa: " + t.getEtapaEnsino());
        System.out.println("Ano: " + t.getAno());
        System.out.println("Matriculados: " + t.getQuantidade() + "/" + t.getLimiteVagas());

    }

}
    

}
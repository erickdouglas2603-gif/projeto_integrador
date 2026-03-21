import java.util.ArrayList;

public class Turma {

    private String codigo;
    private String etapaEnsino;
    private int ano;
    private int limiteVagas;

    private ArrayList<Aluno> alunos;

    public Turma(String codigo, String etapaEnsino, int ano, int limiteVagas) {

        this.codigo = codigo;
        this.etapaEnsino = etapaEnsino;
        this.ano = ano;
        this.limiteVagas = limiteVagas;

        alunos = new ArrayList<>();

    }

    public boolean adicionarAluno(Aluno aluno) {

        if(alunos.size() < limiteVagas){

            alunos.add(aluno);
            return true;

        }

        return false;

    }

    public String getCodigo(){
        return codigo;
    }

    public ArrayList<Aluno> getAlunos(){
        return alunos;
    }

    public String getEtapaEnsino(){
    return etapaEnsino;
}

}
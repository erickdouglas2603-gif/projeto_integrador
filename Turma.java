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

    
    public int getAno(){
        return ano;
    }

    public int getLimiteVagas(){
        return limiteVagas;
    }
    
    public int getQuantidade(){
    return alunos.size();
    }

    @Override
    public String toString(){
    return "Codigo: " + codigo + 
           " | Etapa: " + etapaEnsino + 
           " | Ano: " + ano + 
           " | Matriculados: " + alunos.size() + "/" + limiteVagas;
}

    @Override
    public boolean equals(Object obj){

    if(this == obj) return true;

    if(obj == null || getClass() != obj.getClass()) return false;

    Turma outra = (Turma) obj;

    return this.codigo.equals(outra.codigo);

}

}
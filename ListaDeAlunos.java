public class ListaDeAlunos {

    private Aluno[] alunos;
    private int tamanho;

    public ListaDeAlunos(){

        alunos = new Aluno[100];
        tamanho = 0;

    }

    public void incluirNoInicio(Aluno aluno){

        for(int i = tamanho; i > 0; i--){

            alunos[i] = alunos[i-1];

        }

        alunos[0] = aluno;
        tamanho++;

    }

    public void incluirNoFim(Aluno aluno){

        alunos[tamanho] = aluno;
        tamanho++;

    }

    public void ordenar(){

        for(int i=0;i<tamanho-1;i++){

            for(int j=0;j<tamanho-1-i;j++){

                if(alunos[j].getNome().compareTo(alunos[j+1].getNome()) > 0){

                    Aluno temp = alunos[j];
                    alunos[j] = alunos[j+1];
                    alunos[j+1] = temp;

                }

            }

        }

    }

    public Aluno removerDoFim(){

        if(tamanho == 0)
            return null;

        Aluno aluno = alunos[tamanho-1];

        tamanho--;

        return aluno;

    }

    public int tamanho(){

        return tamanho;

    }

    public Aluno get(int posicao){

        if(posicao < 0 || posicao >= tamanho)
            return null;

        return alunos[posicao];

    }

}
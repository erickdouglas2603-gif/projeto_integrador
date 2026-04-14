public class ExcecaoDeAlunoJaExistente extends Exception {

    public ExcecaoDeAlunoJaExistente() {
        super("Aluno ja existente na lista.");
    }

    public ExcecaoDeAlunoJaExistente(String message) {
        super(message);
    }
}

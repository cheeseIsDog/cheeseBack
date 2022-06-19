package cheese.cheese.Advice.Exception;

public class QuestionException extends RuntimeException {
    public QuestionException(String msg, Throwable t){
        super(msg, t);
    }

    public QuestionException(String msg){
        super(msg);
    }

    public QuestionException(int code) {super();}

    public QuestionException(){
        super();
    }
}

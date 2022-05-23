package cheese.cheese.Advice.Exception;

public class UserException extends RuntimeException {
    public UserException(String msg, Throwable t){
        super(msg, t);
    }

    public UserException(String msg){
        super(msg);
    }

    public UserException(int code) {super();}

    public UserException(){
        super();
    }
}

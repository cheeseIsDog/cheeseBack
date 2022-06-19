package cheese.cheese.Advice.Exception;

public class GlobalException extends RuntimeException {
    public GlobalException(String msg, Throwable t){
        super(msg, t);
    }

    public GlobalException(String msg){
        super(msg);
    }

    public GlobalException(int code) {super();}

    public GlobalException(){
        super();
    }
}

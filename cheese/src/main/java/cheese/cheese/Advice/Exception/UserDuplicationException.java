package cheese.cheese.Advice.Exception;

import lombok.Getter;

@Getter
public class UserDuplicationException extends RuntimeException {
    public UserDuplicationException(String msg, Throwable t){
        super(msg, t);
    }

    public UserDuplicationException(String msg){
        super(msg);
    }

    public UserDuplicationException(){
        super();
    }
}

package cheese.cheese.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class IdGenerator {
    private Random random = new Random();

    public Long getNewId() {
        long id = random.nextLong();
        id = id < 0 ? id*(-1) : id;
        id %= 100000000;
        return id;
    }
}

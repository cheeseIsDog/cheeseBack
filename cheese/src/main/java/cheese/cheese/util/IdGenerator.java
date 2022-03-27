package cheese.cheese.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class IdGenerator {
    private Random random = new Random();

    public Long getNewId() {
        long id = random.nextLong();
        return id < 0 ? id*(-1) : id;
    }
}

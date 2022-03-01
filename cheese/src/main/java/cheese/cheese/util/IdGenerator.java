package cheese.cheese.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class IdGenerator {
    private Random random = new Random();

    public Long getNewId() {
        return random.nextLong();
    }
}

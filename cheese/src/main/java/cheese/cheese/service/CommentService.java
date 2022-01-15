package cheese.cheese.service;

import cheese.cheese.dto.CommentDto;
import cheese.cheese.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Boolean create(CommentDto.gen gen) {
        Boolean result = true;
        try {
            commentRepository.save(gen.toEntity());
        } catch (Exception e) {
            result = false;
        }
        return result;
    }


}

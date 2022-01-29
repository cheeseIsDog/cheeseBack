package cheese.cheese.dto.Projections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserQuestionInfoProjection {
    private Integer myTotalQuestions;
    private Integer mySolvedQuestions;
    private Integer myUnsolvedQuestions;
    private Integer totalAnswerForMyQuestions;
}

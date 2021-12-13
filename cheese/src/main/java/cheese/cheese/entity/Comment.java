package cheese.cheese.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long commentId;

    @Column(name="answer_id")
    private Long answerId;

    @Column(name="contents")
    private String contents;

    @Column(name="likes")
    private Integer likes;

    @Column(name="dislikes")
    private Integer dislikes;
}
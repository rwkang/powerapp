package shop.onekorea.powerapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Comment")
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int boardId;
//    private int userEmail;
    private int userUserid;
    private String commentProfile;
    private String commentNickname;
    private String content;
    private String updated;

}

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
@Entity(name = "Board")
@Table(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private String image;
    private String video;
    private String file;
    private String writer;
    private String writerProfile;
    private String writerNickname;
    private int clickCount;
    private int thumbsUpCount;
    private int commentCount;
    private String updated;
    private String created;

}

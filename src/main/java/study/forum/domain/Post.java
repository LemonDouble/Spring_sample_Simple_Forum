package study.forum.domain;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    private String title;
    private String content;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User author;

    public Post(User author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    protected Post(){}
}
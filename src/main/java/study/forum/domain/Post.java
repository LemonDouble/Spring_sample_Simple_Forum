package study.forum.domain;

import lombok.Data;

@Data
public class Post {
    private Long id;

    private Long userId;
    private String title;
    private String content;

    public Post(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
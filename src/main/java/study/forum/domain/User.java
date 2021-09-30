package study.forum.domain;

import lombok.Data;

@Data
public class User {
    private Long id;

    private String userId;
    private String userPassword;

    public User(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }
}

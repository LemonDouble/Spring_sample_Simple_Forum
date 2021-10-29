package study.forum.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class User {
    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    private String loginId;
    private String loginPassword;

    public User(String loginId, String loginPassword) {
        this.loginId = loginId;
        this.loginPassword = loginPassword;
    }

    protected User(){}
}

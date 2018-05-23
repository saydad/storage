package self.start.persistence.bean;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * create by liuyong4 2018/5/26 上午10:06
 **/
@Entity
@Table(name = "author_user", indexes = {@Index(name = "union_name_password", columnList = "user_name,pass_word")})
public class AuthUser {
    public static final String ADMIN = "admin";
    public static final String USER = "user";
    public static final String FAKE_USER = "fake_user";
    public static final String GUEST = "guest";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String username;
    @Column(name = "pass_word", nullable = false)
    private String password;

    @ElementCollection
    @CollectionTable(name = "author_user_role", joinColumns = {@JoinColumn(name = "author_user_id")})
    @Column(nullable = false)
    private List<String> role;

    @Column(nullable = false)
    private String salt;

    @Transient
    private String token;

    public AuthUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

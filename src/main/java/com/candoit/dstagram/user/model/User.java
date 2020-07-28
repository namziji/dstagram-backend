package com.candoit.dstagram.user.model;

import com.candoit.dstagram.security.model.AuthUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name ="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int userId;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    private String role;

    @Column
    private boolean enable;

    public AuthUser toAuthUser() {
        return new AuthUser(email, password);
    }

    public static class NullUser extends  User {
        public NullUser(){ super(0, "--", "--", "--", "ROLE_USER", false);}
    }

}

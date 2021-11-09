package com.EnergyForecasting.Users;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class User {
//        implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    private String username;
    private String password;
    private String email;
    private boolean enabled;
    private boolean locked;
    @Enumerated(EnumType.STRING)
    private UserRole role;


    public User(String username, String password, String email,  UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.enabled=true;
        this.locked=false;
    }
}
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        SimpleGrantedAuthority auth= new SimpleGrantedAuthority(role.name());
//        return Collections.singletonList(auth);
//    }
//    @Override
//    public String getPassword() {
//        return password;
//    }
//    @Override
//    public String getUsername() {
//        return username;
//    }
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//    @Override
//    public boolean isAccountNonLocked() {
//        return !locked;
//    }
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}

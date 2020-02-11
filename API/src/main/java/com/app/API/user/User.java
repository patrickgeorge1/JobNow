package com.app.API.user;

import com.app.API.role.Role;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 128, nullable = false, unique = true)
    private String username;

    @Column(name = "realName", length = 128, nullable = false, unique = false)
    private String realName;

    @Lob
    @Column(name = "stats", nullable = true, unique = false)
    private String stats;

    @Column(name = "trust")
    private Integer trust = 7;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registerDate")
    private Date registerDate;

    @OneToOne
    @JoinColumn(name = "role", nullable = false)
    private Role role;


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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public Integer getTrust() {
        return trust;
    }

    public void setTrust(Integer trust) {
        this.trust = trust;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

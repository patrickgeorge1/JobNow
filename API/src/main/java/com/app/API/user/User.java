package com.app.API.user;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 128, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 256, nullable = false)
    private String password;

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

    @Column(name = "permissions")
    private String permissions = "";

    public User(String username, String password, String realName, String stats, Integer trust, Date registerDate, String permissions) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.stats = stats;
        this.trust = trust;
        this.registerDate = registerDate;
        this.permissions = permissions;
    }

    protected User() {}

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

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }


    public List<String> getPermissionList() {
        if (this.permissions.length() > 0 ) {
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<String>();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", stats='" + stats + '\'' +
                ", trust=" + trust +
                ", registerDate=" + registerDate +
                ", permissions='" + permissions + '\'' +
                '}';
    }
}

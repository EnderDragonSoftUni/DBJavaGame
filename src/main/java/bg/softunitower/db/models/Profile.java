package bg.softunitower.db.models;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "profiles")
@Transactional
public class Profile implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String name;
    @Basic
    private String password;
    @Column(unique = true)
    private String username;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unlocks_id")
    private Unlocks unlocks;
    @Basic
    private Integer money;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile")
    private Set<Score> scores;

    public Profile() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unlocks getUnlocks() {
        return unlocks;
    }

    public void setUnlocks(Unlocks unlocks) {
        this.unlocks = unlocks;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }
}

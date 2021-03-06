package bg.softunitower.db.models;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "unlocks")
@Transactional
public class Unlocks implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "item_one_unlocked")
    private boolean isItemOneUnlocked;
    @Column(name = "item_two_unlocked")
    private boolean isItemTwoUnlocked;
    @Column(name = "item_three_unlocked")
    private boolean isItemThreeUnlocked;
    @Column(name = "item_one_selected")
    private boolean isItemOneSelected;
    @Column(name = "item_two_selected")
    private boolean IsItemTwoSelected;
    @Column(name = "item_three_selected")
    private boolean isItemThreeSelected;
    @OneToOne(mappedBy = "unlocks")
    private Profile profile;

    public Unlocks() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getItemOneUnlocked() {
        return isItemOneUnlocked;
    }

    public void setItemOneUnlocked(Boolean itemOneUnlocked) {
        isItemOneUnlocked = itemOneUnlocked;
    }

    public Boolean getItemTwoUnlocked() {
        return isItemTwoUnlocked;
    }

    public void setItemTwoUnlocked(Boolean itemTwoUnlocked) {
        isItemTwoUnlocked = itemTwoUnlocked;
    }

    public Boolean getItemThreeUnlocked() {
        return isItemThreeUnlocked;
    }

    public void setItemThreeUnlocked(Boolean itemThreeUnlocked) {
        isItemThreeUnlocked = itemThreeUnlocked;
    }

    public Boolean getItemOneSelected() {
        return isItemOneSelected;
    }

    public void setItemOneSelected(Boolean itemOneSelected) {
        isItemOneSelected = itemOneSelected;
    }

    public Boolean getItemTwoSelected() {
        return IsItemTwoSelected;
    }

    public void setItemTwoSelected(Boolean itemTwoSelected) {
        IsItemTwoSelected = itemTwoSelected;
    }

    public Boolean getItemThreeSelected() {
        return isItemThreeSelected;
    }

    public void setItemThreeSelected(Boolean itemThreeSelected) {
        isItemThreeSelected = itemThreeSelected;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}

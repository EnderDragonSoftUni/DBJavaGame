package bg.softunitower.db.dtos;

/**
 * Created by Stella on 12/12/2016.
 */
public class UnlockDto {

    private boolean itemOneUnlocked;

    private boolean itemOneSelected;

    private boolean itemTwoUnlocked;

    private boolean itemTwoSelected;

    private boolean itemThreeUnlocked;

    private boolean itemThreeSelected;

    public boolean isItemOneUnlocked() {
        return itemOneUnlocked;
    }

    public void setItemOneUnlocked(boolean itemOneUnlocked) {
        this.itemOneUnlocked = itemOneUnlocked;
    }

    public boolean isItemOneSelected() {
        return itemOneSelected;
    }

    public void setItemOneSelected(boolean itemOneSelected) {
        this.itemOneSelected = itemOneSelected;
    }

    public boolean isItemTwoUnlocked() {
        return itemTwoUnlocked;
    }

    public void setItemTwoUnlocked(boolean itemTwoUnlocked) {
        this.itemTwoUnlocked = itemTwoUnlocked;
    }

    public boolean isItemTwoSelected() {
        return itemTwoSelected;
    }

    public void setItemTwoSelected(boolean itemTwoSelected) {
        this.itemTwoSelected = itemTwoSelected;
    }

    public boolean isItemThreeUnlocked() {
        return itemThreeUnlocked;
    }

    public void setItemThreeUnlocked(boolean itemThreeUnlocked) {
        this.itemThreeUnlocked = itemThreeUnlocked;
    }

    public boolean isItemThreeSelected() {
        return itemThreeSelected;
    }

    public void setItemThreeSelected(boolean itemThreeSelected) {
        this.itemThreeSelected = itemThreeSelected;
    }
}

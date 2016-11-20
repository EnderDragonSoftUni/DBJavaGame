package bg.softunitower.fortunebonuslevel;


import javax.swing.*;

public class Fortune {
    private LabyrinthEngine labyrinthEngine = new LabyrinthEngine();

    public Fortune() {
    }

    public void start() {
        this.labyrinthEngine.start();
        JFrame f = new JFrame();
        f.setLocation(640, 477);
        f.requestFocus();
        this.labyrinthEngine.getLabyrinth().setBounds(0, 0, 680, 720);
        f.add(this.labyrinthEngine.getLabyrinth());
        f.setTitle("Bonus Level: Fortune");
        f.setBounds(300, 120, 680, 720);
        f.setVisible(true);
    }
}

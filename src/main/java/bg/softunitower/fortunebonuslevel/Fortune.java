package bg.softunitower.fortunebonuslevel;


import javax.swing.*;

public class Fortune {

    private LabyrinthEngine labyrinthEngine = new LabyrinthEngine();

    private static final int SET_LOCATION_WIDTH = 640;

    private static final int SET_LOCATION_HEIGHT = 477;

    private static final int LABYRINTH_WIDHT = 680;

    private static final int  LABYRINTH_HEIGHT = 720;

    private static final int SET_LABYRINTH_X = 300;

    private static final int SET_LABYRINTH_Y = 120;


    public Fortune() {
    }

    public void start() {



        this.labyrinthEngine.start();

        JFrame f = new JFrame();

        f.setLocation(SET_LOCATION_WIDTH, SET_LOCATION_HEIGHT);

        f.requestFocus();

        this.labyrinthEngine.getLabyrinth().setBounds(0, 0, LABYRINTH_WIDHT, LABYRINTH_HEIGHT);

        f.add(this.labyrinthEngine.getLabyrinth());

        f.setTitle("Bonus Level: Fortune");

        f.setBounds(SET_LABYRINTH_X, SET_LABYRINTH_Y, LABYRINTH_WIDHT, LABYRINTH_HEIGHT);

        f.setVisible(true);

    }

}

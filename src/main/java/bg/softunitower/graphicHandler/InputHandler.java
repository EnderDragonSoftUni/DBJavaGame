package bg.softunitower.graphicHandler;

import bg.softunitower.objects.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Niki on 6.6.2016 г..
 */
public class InputHandler implements KeyListener {
    public static boolean beginning = true;
    public static boolean jumped = false;
    private boolean[] keyDown = new boolean[3];

    public InputHandler(Canvas canvas) {
        canvas.addKeyListener(this);
        this.keyDown[0] = false;
        this.keyDown[1] = false;
        this.keyDown[2] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_RIGHT) {
            keyDown[0] = true;
            Player.isMovingRight = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            keyDown[1] = true;
            Player.isMovingLeft = true;
        }
        if (code == KeyEvent.VK_SPACE && Player.inJumpingBox){
            keyDown[2] = true;
            beginning = false;
            Player.gravity = -22;
            jumped = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_RIGHT) {
            keyDown[0] = false;
            Player.isMovingRight = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            keyDown[1] = false;
            Player.isMovingLeft = false;
        }
        if (!keyDown[0] && !keyDown[1]) {
            Player.isMovingLeft = false;
            Player.isMovingRight = false;
        }
    }
}

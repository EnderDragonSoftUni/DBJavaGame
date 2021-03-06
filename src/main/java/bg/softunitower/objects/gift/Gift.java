/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.softunitower.objects.gift;

import bg.softunitower.game.Game;
import bg.softunitower.graphicHandler.Assets;
import bg.softunitower.graphicHandler.GiftHandler;
import bg.softunitower.graphicHandler.InputHandler;
import bg.softunitower.graphicHandler.SpriteSheet;

import java.awt.*;

public abstract class Gift {

    private static final int RENDER_SIZE = 32;

    private int x;
    private int y;
    private int width;
    private int height;
    protected SpriteSheet img;
    private Game game;
    private float frame = 0;
   

    public Gift(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = Assets.goldCoin;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }


    public void tick() {
        int tester = (int)(frame + .4);
        if (tester < 8){
            frame += .4;
        }else {
            frame = 0;
        }
        if (InputHandler.beginning == false) {
            y += 6;
        }
        if (this.y > Game.HEIGHT) {
            GiftHandler.removeObject(this);
        }
        
        
    }

    public void render(Graphics g) {
        Color myColour = new Color(0, 0, 0, 0);
        g.setColor(myColour);
        g.fillRect(x,y,this.width,this.height);
        g.drawImage(  this.img.crop( RENDER_SIZE* (int)frame, 0, RENDER_SIZE, RENDER_SIZE), this.x , this.y ,  RENDER_SIZE, RENDER_SIZE  ,null);
    }
}

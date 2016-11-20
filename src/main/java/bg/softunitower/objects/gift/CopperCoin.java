/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.softunitower.objects.gift;

import bg.softunitower.graphicHandler.Assets;

/**
 *
 * @author chobi
 */
public class CopperCoin extends Gift{
    
    public CopperCoin(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.img =  Assets.copperCoin;
    }
    
}

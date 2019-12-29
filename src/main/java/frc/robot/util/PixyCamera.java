/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

import java.util.ArrayList;

import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2.LinkType;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

/**
 * Add your docs here.
 */
public class PixyCamera {

    double sigX;
    double sigY;

    private Pixy2 pixy;
    
    public PixyCamera() {
        pixy = Pixy2.createInstance(LinkType.SPI);
        pixy.init();
    }

    public Block getLargestBlock() {
        pixy.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG1, 25);
        ArrayList<Block> blocks = pixy.getCCC().getBlocks();
        Block largest = null;

        for (Block block : blocks) {
            if (largest == null) {
                largest = block;
            } else if (block.getWidth() > largest.getWidth()) {
                largest = block;
            }
        }
        return largest;
    }

    public double getX() {
        this.sigX = 0;
        Block block = getLargestBlock();

        if (block != null) {
            this.sigX = block.getX();
        }
        return this.sigX;
    }

    public double getY() {
        this.sigY = 0;
        Block block = getLargestBlock();

        if (block != null) {
            this.sigY = block.getY();
        }

        return this.sigY;
    }
}

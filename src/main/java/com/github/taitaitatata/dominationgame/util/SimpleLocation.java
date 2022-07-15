package com.github.taitaitatata.dominationgame.util;

import org.bukkit.Location;
import org.bukkit.World;

public class SimpleLocation {

    private double x;
    private double y;
    private double z;

    private World dimention;

    public SimpleLocation(World world, double x, double y, double z) {
        this.dimention = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setDimention(World dimention) {
        this.dimention = dimention;
    }

    public World getDimention() {
        return dimention;
    }
}

package com.example.haaaa;

public class Connectionpoint {

    private double x;
    private double y;
    private GATE gate;
    private int state;
    private Connectionpoint connected_to;


    public Connectionpoint(GATE gate) {
        this.gate = gate;
    }


    public void setX(double x) {this.x = x;}

    public void setY(double y) {this.y = y;}

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public GATE getGate() {return this.gate;}

    public void setGate(GATE gate) {this.gate = gate;}

    public int getState() {return this.state;}
    public void setState(int state) {this.state = state;}

    public Connectionpoint getConnected_to() {return this.connected_to;}
    public void setConnected_to(Connectionpoint connected_to) {this.connected_to = connected_to;}
}

package com.example.haaaa;

public class WIRE {


    private boolean smooth ;
    private double[] coords = new double[4];
    private GATE[] connections = new GATE[2];

    //false =0 true =1
    private boolean state ;

    //constructor
    public WIRE(boolean smooth, double[] coords) {
        this.smooth = smooth;
        this.coords = coords;
        this.state = false ;
    }

    //getters-------------------------
    public boolean isWire_race()
    {return this.smooth;}

    public double[] getCoords()
    {return this.coords;}

    public boolean getState()
    {return this.state;}

    public GATE[] getConnections()
    {return this.connections;}

    //setters-----------------
    public void setState(boolean state)
    {this.state = state;}

    public void setConnections(GATE[] connections)
    {this.connections = connections;}
}//end of class

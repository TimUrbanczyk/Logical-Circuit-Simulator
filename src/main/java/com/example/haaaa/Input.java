package com.example.haaaa;

public class Input extends Connectionpoint{

    private int state;

    public Input(GATE Gate){
        super(Gate);
    }

    @Override
    public void setX(double x)
    {super.setX(x);}

    @Override
    public void setY(double y)
    {super.setY(y);}

    @Override
    public double getX()
    {return super.getX();}

    @Override
    public double getY()
    {return super.getY();}

    @Override
    public GATE getGate()
    {return super.getGate();}

    @Override
    public void setGate(GATE Gate)
    {super.setGate(Gate);}

    @Override
    public int getState()
    {return super.getState();}

    @Override
    public void setState(int state)
    {super.setState(state);}

    @Override
    public Connectionpoint getConnected_to()
    {return super.getConnected_to();}

    @Override
    public void setConnected_to(Connectionpoint connected_to) {
    super.setConnected_to(connected_to);}


}//end of class

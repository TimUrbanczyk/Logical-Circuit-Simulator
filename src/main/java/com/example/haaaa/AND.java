package com.example.haaaa;

import java.util.ArrayList;

public class AND extends GATE {
    private double x;
    private double y;

    private Output outputpoint = new Output(this);

    private int input_points_number;
    private Input[] inputpoints;

    public AND(double x, double y, int input_points_number) {
        super(x, y, input_points_number);
        this.input_points_number = input_points_number;
        this.inputpoints = new Input[input_points_number];

        outputpoint.setX(x + 31);
        outputpoint.setY(y + 15);
    }

    @Override
    public double get_x() {
        return super.get_x();
    }

    @Override
    public double get_y() {
        return super.get_y();
    }

    @Override
    public Output getOutput_point() {
        return this.outputpoint;
    }

    @Override
    public Input[] getInputpoints() {
        return super.getInputpoints();
    }

    @Override
    public int getInput_points_number() {
        return super.getInput_points_number();
    }

    @Override
    public void createInputpoints(int x, GATE gate) {
        super.createInputpoints(x, this);
    }

    @Override
    public void add_pointer(GATE gate) {
        super.add_pointer(gate);
    }

    @Override
    public ArrayList<GATE> getPoints_to() {
        return super.getPoints_to();
    }

    public int logic() {
        for (int i = 0; i < inputpoints.length; i++) {
            if (getInputpoints()[i].getState() == 2) {
                getOutput_point().setState(2);
                return 2;
            }
        }
        getOutput_point().setState(1);
        return 1;
    }
} 
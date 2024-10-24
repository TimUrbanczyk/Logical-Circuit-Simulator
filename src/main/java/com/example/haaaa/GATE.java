package com.example.haaaa;

import java.util.ArrayList;
import java.util.Arrays;

public class GATE {

    private double x;
    private double y;
    private Output outputpoint = new Output(this);
    private int input_points_number;
    private Input[] inputpoints;

    private ArrayList<GATE> points_to = new ArrayList<>();

    public GATE(double x, double y, int input_points_number) {
        this.x = x;
        this.y = y;
        this.input_points_number = input_points_number;
        this.inputpoints = new Input[input_points_number];
        createInputpoints(input_points_number,this);
    }


    public double get_x() {
        return this.x;
    }

    public double get_y() {
        return this.y;
    }

    public Output getOutput_point() {
        return this.outputpoint;
    }

    public Input[] getInputpoints() {
        return this.inputpoints;
    }



    public int getInput_points_number() {
        return this.input_points_number;
    }

    // Creates input points for GATE class
    public void createInputpoints(int x,GATE gate) {
        for (int i = 0; i < x; i++) {
            inputpoints[i] = new Input(this);
            inputpoints[i].setX(this.x-7);
            inputpoints[i].setY(this.y +i*10);
            inputpoints[i].setState(0);
            HelloController.getInputpoints_list().add(inputpoints[i]);

        }

    }

    public void add_pointer(GATE gate)
    {points_to.add(gate);}

    public ArrayList<GATE> getPoints_to()
    {return this.points_to;}

    public int logic(){
        return 0;
    }//end of logic

}

package com.example.haaaa;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller<T> implements Initializable {

    //Generic Arraylist, witch stores (and only stores) gates
    private ArrayList<GATE> Gates_List = new ArrayList<>();

    //Generic Arraylist, witch stores (and only stores) wires
    private ArrayList<WIRE> Wires_List = new ArrayList<>();

    //Generic Arraylist, witch stores (and only stores) outputpoints for the wires to connect
    private ArrayList<Output> Outputpoints_list = new ArrayList<>();

    //Generic Arraylist, witch stores (and only stores) intputpoints for the wires to connect
    private static ArrayList<Input> Inputpoints_list = new ArrayList<>();

    //temporary solution
    private static ArrayList<Connection> Connection_list = new ArrayList<>();

    public static ArrayList<Input> getInputpoints_list()
    {return Inputpoints_list;}


    //string to identify wich gate/wire is currently selected
    private String selected_gate = "NONE";

    //smame here for the wires. Use a boolean because we provide just two different wire types.
    private String selected_wire = "NONE";

    //Array of doubles wich allows to story 2 points --> a helper to calculate a path from one gate to another
    private double Coordinates_Line[] = new double[4];

    //flag to choose what to draw
    private int choose_drawable_flag = 0;

    //flag to evaluate if drawing is allowed at the moment
    private boolean drawable_flag = true;

    private boolean line_flag = false;

    //false == draw true == edit
    private boolean mode_flag = true;

    //-----------------------FXML-OBJECTS-----------------------------------------------
    @FXML
    public  Canvas canvas;

    @FXML
    public Label gatelabel;

    @FXML
    public Label wirelabel;

    @FXML
    private TextField Inputfield;

    @FXML
    public Menu Elements;

    @FXML
    public Menu Se_Elements;

    @FXML
    public Menu Wires;

    @FXML
    public ToggleButton Mode_button;

    @FXML
    public AnchorPane pane;

    private GraphicsContext gc;

    private Output o;

    private int Input_textfield;






    //select and draw gates
    //-------------------------------------------------------------------------------------------------------
    @FXML
    protected void select_gate_And() {
        this.selected_gate = "AND";
        this.selected_wire = "NONE";
        this.line_flag = false;
        gatelabel_refresh();
        choose_drawable_flag=1;
        choose_drawable();
    }

    @FXML
    protected void select_or() {
        this.selected_gate = "OR";
        this.selected_wire = "NONE";
        this.line_flag = false;
        gatelabel_refresh();
        choose_drawable_flag=1;
        choose_drawable();
    }

    @FXML
    protected void select_not() {
        this.selected_gate = "NOT";
        this.selected_wire = "NONE";
        this.line_flag = false;
        gatelabel_refresh();
        choose_drawable_flag=1;
        choose_drawable();
    }

    @FXML
    protected void select_High() {
        this.selected_gate = "HIGH";
        this.selected_wire = "NONE";
        this.line_flag = false;
        gatelabel_refresh();
        choose_drawable_flag=1;
        choose_drawable();
    }

    @FXML
    protected void select_Low() {
        this.selected_gate = "LOW";
        this.selected_wire = "NONE";
        this.line_flag = false;
        gatelabel_refresh();
        choose_drawable_flag=1;
        choose_drawable();
    }

    @FXML
    protected void select_Powersource() {
        this.selected_gate = "POWERSOURCE";
        this.selected_wire = "NONE";
        this.line_flag = false;
        gatelabel_refresh();
        choose_drawable_flag=1;
        choose_drawable();
    }

    @FXML
    protected void select_Edit() {
        choose_drawable_flag=2;
        choose_drawable();
    }


    //logic to draw one of the selectable gates from the menubar above on the canvas down under
    @FXML
    protected void draw_selected_gate(int inputs_number){

        canvas.setOnMousePressed(e->{
            gc = canvas.getGraphicsContext2D();

            double x = e.getX();
            double y = e.getY();

            drawable_flag = true;

            //logic to prevent gates to be drawn above another
            for (int i = 0; i < Gates_List.size(); i++) {
                if (x >= Gates_List.get(i).get_x() -20 && x <= Gates_List.get(i).get_x() + 30 &&
                        y >= Gates_List.get(i).get_y() -20 && y <= Gates_List.get(i).get_y() + 30) {

                    drawable_flag = false;
                    break;
                }
            }
            gc.setStroke(Color.RED);
            //logic to choose and draw the selected gate
            switch(this.selected_gate){
                case "AND":

                    if(drawable_flag) {

                        //add the gate to the global arraylist
                        AND a = new AND(x,y,inputs_number);
                        Gates_List.add(a);
                        Outputpoints_list.add(a.getOutput_point());



                        //draw the gate with connectionpoints
                        gc.strokeRect(x, y, 30, 30);
                        gc.strokeText("And", x + 7, y + 20);
                        gc.strokeOval(a.getOutput_point().getX(),a.getOutput_point().getY(), 7, 7);
                        draw_input_points(a);

                    }
                    break;
                case "OR":

                    if(drawable_flag){

                        //add the gate to the global arraylist
                        OR b = new OR(x,y,inputs_number);
                        Gates_List.add(b);
                        Outputpoints_list.add(b.getOutput_point());

                        //draw the gate with connectionpoints
                        gc.strokeRect(x,y,30,30);
                        gc.strokeText("Or",x+7,y+20);
                        gc.strokeOval(b.getOutput_point().getX(),b.getOutput_point().getY(), 7, 7);
                        draw_input_points(b);

                    }
                    break;
                case "NOT":

                    if(drawable_flag) {

                        //add the gate to the global arraylist
                        NOT c = new NOT(x,y,1);
                        Gates_List.add(c);
                        Outputpoints_list.add(c.getOutput_point());

                        //draw the gate with connectionpoints
                        gc.strokeRect(x, y, 30, 30);
                        gc.strokeText("Not", x + 7, y + 20);
                        gc.strokeOval(c.getOutput_point().getX(), c.getOutput_point().getY(), 7, 7);
                        draw_input_points(c);
                    }
                    break;
                case "HIGH":
                    if(drawable_flag) {
                        //add the gate to the global arraylist
                        HIGH d = new HIGH(x, y);
                        Gates_List.add(d);
                        Outputpoints_list.add(d.getOutput_point());

                        //draw the gate with connectionpoints
                        gc.setStroke(Color.GREEN);
                        gc.strokeRect(x, y, 30, 30);
                        gc.strokeText("H", x + 7, y + 20);
                        gc.strokeOval(d.getOutput_point().getX(), d.getOutput_point().getY(), 7, 7);
                        gc.setStroke(Color.BLACK);
                        break;
                    }
                case "LOW":
                    if(drawable_flag) {
                        //add the gate to the global arraylist
                        LOW f = new LOW(x, y);
                        Gates_List.add(f);
                        Outputpoints_list.add(f.getOutput_point());

                        //draw the gate with connectionpoints
                        gc.setStroke(Color.RED);
                        gc.strokeRect(x, y, 30, 30);
                        gc.strokeText("L", x + 7, y + 20);
                        gc.strokeOval(f.getOutput_point().getX(), f.getOutput_point().getY(), 7, 7);
                        gc.setStroke(Color.BLACK);
                        break;
                    }

                case "POWERSOURCE":
                    if(drawable_flag) {
                        //add the gate to the global arraylist
                        Powersource g = new Powersource(x, y);
                        Gates_List.add(g);
                        Outputpoints_list.add(g.getOutput_point());

                        //draw the gate with connectionpoints
                        gc.setStroke(Color.RED);
                        gc.strokeRect(x, y, 30, 30);
                        gc.strokeText("L(P)", x + 7, y + 20);
                        gc.strokeOval(g.getOutput_point().getX(), g.getOutput_point().getY(), 7, 7);
                        gc.setStroke(Color.BLACK);
                        break;
                    }
                case "WIRE":
                    break;



            }//end of switch

        });//end of lambda
    }//end of method

    //subroutine to draw and create
    protected void draw_input_points(GATE x){

            int number_of_inputpoints = x.getInput_points_number();
            double coord_x = x.get_x();
            double coord_y = x.get_y();

            gc = canvas.getGraphicsContext2D();

            for(int i = 0; i < number_of_inputpoints; i++){
                gc.strokeOval(x.getInputpoints()[i].getX(),x.getInputpoints()[i].getY(),7,7);
            }

    }//end of draw_input_points

//----------------------------------------------------------------------------------

    //select and draw wires

    @FXML
    protected void select_wire_smooth(){
        this.selected_wire = "SMOOTH";
        this.selected_gate = "WIRE";
        this.line_flag = true;
        choose_drawable_flag = 0 ;
        this.wirelabel.setText("Current wire: "+this.selected_wire);
        choose_drawable();
    }

    @FXML
    protected void select_wire_hard(){
        this.selected_wire = "HARD";
        this.selected_gate = "WIRE";
        this.line_flag = true;
        choose_drawable_flag = 0;
        this.wirelabel.setText("Current wire: "+this.selected_wire);
        choose_drawable();
    }

    private double[] Help = new double[2];


    private boolean test = false;
    protected void draw_line() {



        double[] previousCoordinates = new double[4];

        canvas.setOnMousePressed(event -> {

            Coordinates_Line[0] = event.getX();
            Coordinates_Line[1] = event.getY();

            check_first_point(Coordinates_Line[0], Coordinates_Line[1]);



            canvas.setOnMouseDragged(dragEvent -> {


                if(!mode_flag){return;}
                Coordinates_Line[2] = dragEvent.getX();
                Coordinates_Line[3] = dragEvent.getY();

                gc = canvas.getGraphicsContext2D();
                redraw();

                Help = seek_connection(Coordinates_Line[2], Coordinates_Line[3]);

                gc.setStroke(Color.BLACK);
                if (this.selected_wire.equals("SMOOTH")) {
                    double controlX = (Coordinates_Line[0] + Coordinates_Line[2]) / 2;
                    double controlY = (Coordinates_Line[1] + Coordinates_Line[3]) / 2 - 50;

                    gc.beginPath();
                    gc.moveTo(Coordinates_Line[0], Coordinates_Line[1]);
                    gc.quadraticCurveTo(controlX, controlY, Coordinates_Line[2], Coordinates_Line[3]);
                    gc.stroke();
                } else if (this.selected_wire.equals("HARD")) {
                    gc.strokeLine(Coordinates_Line[0], Coordinates_Line[1], Coordinates_Line[2] - ((Coordinates_Line[0] + Coordinates_Line[2]) / 10), Coordinates_Line[1]);
                    gc.strokeLine(Coordinates_Line[2] - ((Coordinates_Line[0] + Coordinates_Line[2]) / 10), Coordinates_Line[1], Coordinates_Line[2] - ((Coordinates_Line[0] + Coordinates_Line[2]) / 10), Coordinates_Line[3]);
                    gc.strokeLine(Coordinates_Line[2] - ((Coordinates_Line[0] + Coordinates_Line[2]) / 10), Coordinates_Line[3], Coordinates_Line[2], Coordinates_Line[3]);
                }

                previousCoordinates[0] = Coordinates_Line[0];
                previousCoordinates[1] = Coordinates_Line[1];
                previousCoordinates[2] = Coordinates_Line[2];
                previousCoordinates[3] = Coordinates_Line[3];
            });

            // Set up mouse released event handler
            canvas.setOnMouseReleased(releaseEvent -> {
                redraw();

                Coordinates_Line[2] = Help[0];
                Coordinates_Line[3] = Help[1];

                gc.setStroke(Color.BLACK);
                if (this.selected_wire.equals("SMOOTH")) {
                    double controlX = (Coordinates_Line[0] + Coordinates_Line[2]) / 2;
                    double controlY = (Coordinates_Line[1] + Coordinates_Line[3]) / 2 - 50;

                    gc.beginPath();
                    gc.moveTo(Coordinates_Line[0], Coordinates_Line[1]);
                    gc.quadraticCurveTo(controlX, controlY, Coordinates_Line[2], Coordinates_Line[3]);
                    gc.stroke();
                } else if (this.selected_wire.equals("HARD")) {
                    gc.strokeLine(Coordinates_Line[0], Coordinates_Line[1], Coordinates_Line[2] - ((Coordinates_Line[0] + Coordinates_Line[2]) / 10), Coordinates_Line[1]);
                    gc.strokeLine(Coordinates_Line[2] - ((Coordinates_Line[0] + Coordinates_Line[2]) / 10), Coordinates_Line[1], Coordinates_Line[2] - ((Coordinates_Line[0] + Coordinates_Line[2]) / 10), Coordinates_Line[3]);
                    gc.strokeLine(Coordinates_Line[2] - ((Coordinates_Line[0] + Coordinates_Line[2]) / 10), Coordinates_Line[3], Coordinates_Line[2], Coordinates_Line[3]);
                }

                // Creating the WIRE object with the final coordinates
                if (line_flag) {

                    if(test){
                        GATE[] c = new GATE[2];
                        WIRE newWire = new WIRE(true, Coordinates_Line.clone());
                        if(mode_flag){Wires_List.add(newWire);}

                        for(int i = 0; i < Gates_List.size(); i++){
                            if(o == Gates_List.get(i).getOutput_point()) {

                                c[0] = Gates_List.get(i);
                            }
                        }

                       for(int i =0; i < Inputpoints_list.size(); i++){
                           if(liegtImKreis(newWire.getCoords()[2],newWire.getCoords()[3],
                           Inputpoints_list.get(i).getX(),Inputpoints_list.get(i).getY(),7)) {

                               c[1] = Inputpoints_list.get(i).getGate();
                               if(mode_flag){connect(c[0], c[1]);
                               }
                           }

                       }
                    }
                    redraw();
                    test = false;
                }
            });
        });




    }//end of draw_line


    //logic to evaluate if the clicked point is valid to create a wire
    private void check_first_point(double x, double y){

        for(int i =0;i < Outputpoints_list.size(); i++) {
            if (liegtImKreis(x, y, Outputpoints_list.get(i).getX(), Outputpoints_list.get(i).getY(), 10)){
                test = true;
                o = Outputpoints_list.get(i);
                return;
            }
        }

        for(int i =0;i < Inputpoints_list.size(); i++) {
            if (liegtImKreis(x, y, Inputpoints_list.get(i).getX(), Inputpoints_list.get(i).getY(), 7)){
                test = true;
                return;
            }
        }



        test = false;
    }//end of check_first_point


    //logic to look for the next connectionpoint on the canvas
    protected double[] seek_connection(double x, double y) {

        double[] Output = new double[2];

        Output[0] = x;
        Output[1] = y;


       for (int i = 0; i < Outputpoints_list.size(); i++) {
           if(liegtImKreis(x,y, Outputpoints_list.get(i).getX(),Outputpoints_list.get(i).getY(),7)) {
               Output[0] =  Outputpoints_list.get(i).getX();
               Output[1] = Outputpoints_list.get(i).getY();



           }
        }


        for (int i = 0; i < Inputpoints_list.size(); i++) {
            if(liegtImKreis(x,y, Inputpoints_list.get(i).getX(),Inputpoints_list.get(i).getY(),7)) {
                Output[0] =  Inputpoints_list.get(i).getX()+2;
                Output[1] = Inputpoints_list.get(i).getY()+2;



            }
        }

        return Output;
    }//end of method seek_connection


    private  boolean liegtImKreis(double px, double py, double mx, double my, double radius) {

        double distanz = Math.sqrt(Math.pow(px - mx, 2) + Math.pow(py - my, 2));




        return distanz <= radius;
    }

    private static boolean isPointInRect(double pointX, double pointY, double rectX, double rectY) {
        final double RECT_WIDTH = 30;
        final double RECT_HEIGHT = 30;

        if (pointX >= rectX && pointX <= rectX + RECT_WIDTH &&
                pointY >= rectY && pointY <= rectY + RECT_HEIGHT) {
            return true;
        } else {
            return false;
        }
    }


    @FXML
    protected void gatelabel_refresh()
    {gatelabel.setText("Current gate: "+selected_gate);}


    //logic to delete the last placed gate
    @FXML
    protected void delete_latest_gate(){
        int a = Gates_List.size()-1;

        double x = Gates_List.get(a).get_x();
        double y = Gates_List.get(a).get_y();

        //delete rect
        gc.setLineWidth(0);
        gc.setFill(Color.WHITE);
        gc.fillRect(x-1, y-1, 32, 32);


        //delete outputpoint
        for(int i =0; i < Outputpoints_list.size(); i++){
            if(Outputpoints_list.get(i) == Gates_List.get(a).getOutput_point()) {
                Outputpoints_list.remove(i);
            }
        }
        gc.fillOval(Gates_List.get(a).getOutput_point().getX()-1, Gates_List.get(a).getOutput_point().getY()-1, 9, 9);


        //delete inputpoints
        for(int i =0; i < Inputpoints_list.size(); i++){
            for(int j =0;j < Gates_List.get(a).getInput_points_number();j++){
                if(Gates_List.get(a).getInputpoints()[j] == Inputpoints_list.get(i)) {
                    gc.fillOval(Inputpoints_list.get(i).getX()-1, Inputpoints_list.get(i).getY()-1, 9, 9);
                    Inputpoints_list.remove(i);
                }
            }

        }

        Gates_List.get(a).getOutput_point().setState(0);
        Gates_List.remove(a);


    }


    @FXML
    protected void choose_drawable(){
        if(choose_drawable_flag == 0){
            draw_line();
        }else if(choose_drawable_flag == 1){
            try {
                draw_selected_gate(Input_textfield);
            }catch (NumberFormatException e) {
                Inputfield.setText("Only Numbers are valid!");
                }

        }
        else if(choose_drawable_flag == 2){

            edit();

        }
        else if(choose_drawable_flag == 3){

        }
    }

    //logic to redraw the canvas
    private void redraw(){
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());

        //redraw all the gates in Gates_list
        for(int i =0;i<Gates_List.size();i++){
            gc = canvas.getGraphicsContext2D();

            if(Gates_List.get(i) instanceof  AND){
                if(Gates_List.get(i).logic() == 1){
                    gc.setStroke(Color.GREEN);
                    gc.strokeRect(Gates_List.get(i).get_x(), Gates_List.get(i).get_y(), 30, 30);
                    gc.strokeText("And", Gates_List.get(i).get_x()+7, Gates_List.get(i).get_y() + 20);
                    gc.strokeOval(Gates_List.get(i).getOutput_point().getX(), Gates_List.get(i).getOutput_point().getY(), 7, 7);
                    draw_input_points(Gates_List.get(i));
                    gc.setStroke(Color.BLACK);

                }else {
                    gc.setStroke(Color.RED);
                    gc.strokeRect(Gates_List.get(i).get_x(), Gates_List.get(i).get_y(), 30, 30);
                    gc.strokeText("And", Gates_List.get(i).get_x() + 7, Gates_List.get(i).get_y() + 20);
                    gc.strokeOval(Gates_List.get(i).getOutput_point().getX(), Gates_List.get(i).getOutput_point().getY(), 7, 7);
                    draw_input_points(Gates_List.get(i));
                    gc.setStroke(Color.BLACK);
                }

            }
            else if(Gates_List.get(i) instanceof  OR){
                if(Gates_List.get(i).logic() == 1){
                    gc.setStroke(Color.GREEN);
                    gc.strokeRect(Gates_List.get(i).get_x(), Gates_List.get(i).get_y(), 30, 30);
                    gc.strokeText("Or", Gates_List.get(i).get_x()+7, Gates_List.get(i).get_y() + 20);
                    gc.strokeOval(Gates_List.get(i).getOutput_point().getX(), Gates_List.get(i).getOutput_point().getY(), 7, 7);
                    draw_input_points(Gates_List.get(i));
                    gc.setStroke(Color.BLACK);

                }else {
                    gc.setStroke(Color.RED);
                    gc.strokeRect(Gates_List.get(i).get_x(), Gates_List.get(i).get_y(), 30, 30);
                    gc.strokeText("Or", Gates_List.get(i).get_x() + 7, Gates_List.get(i).get_y() + 20);
                    gc.strokeOval(Gates_List.get(i).getOutput_point().getX(), Gates_List.get(i).getOutput_point().getY(), 7, 7);
                    draw_input_points(Gates_List.get(i));
                    gc.setStroke(Color.BLACK);
                }

            }
            else if(Gates_List.get(i) instanceof  NOT){
                if(Gates_List.get(i).logic() == 1){
                    gc.setStroke(Color.GREEN);
                    gc.strokeRect(Gates_List.get(i).get_x(), Gates_List.get(i).get_y(), 30, 30);
                    gc.strokeText("Not", Gates_List.get(i).get_x()+7, Gates_List.get(i).get_y() + 20);
                    gc.strokeOval(Gates_List.get(i).getOutput_point().getX(), Gates_List.get(i).getOutput_point().getY(), 7, 7);
                    draw_input_points(Gates_List.get(i));
                    gc.setStroke(Color.BLACK);

                }else {
                    gc.setStroke(Color.RED);
                    gc.strokeRect(Gates_List.get(i).get_x(), Gates_List.get(i).get_y(), 30, 30);
                    gc.strokeText("Not", Gates_List.get(i).get_x() + 7, Gates_List.get(i).get_y() + 20);
                    gc.strokeOval(Gates_List.get(i).getOutput_point().getX(), Gates_List.get(i).getOutput_point().getY(), 7, 7);
                    draw_input_points(Gates_List.get(i));
                    gc.setStroke(Color.BLACK);
                }
            }
            else if(Gates_List.get(i) instanceof HIGH) {
                gc.setStroke(Color.GREEN);
                gc.strokeRect(Gates_List.get(i).get_x(), Gates_List.get(i).get_y(), 30, 30);
                gc.strokeText("H", Gates_List.get(i).get_x() + 7, Gates_List.get(i).get_y() + 20);
                gc.strokeOval(Gates_List.get(i).getOutput_point().getX(), Gates_List.get(i).getOutput_point().getY(), 7, 7);
                gc.setStroke(Color.BLACK);
            }
            else if(Gates_List.get(i) instanceof LOW) {
                gc.setStroke(Color.RED);
                gc.strokeRect(Gates_List.get(i).get_x(), Gates_List.get(i).get_y(), 30, 30);
                gc.strokeText("L", Gates_List.get(i).get_x() + 7, Gates_List.get(i).get_y() + 20);
                gc.strokeOval(Gates_List.get(i).getOutput_point().getX(), Gates_List.get(i).getOutput_point().getY(), 7, 7);
                gc.setStroke(Color.BLACK);
            }
            else if(Gates_List.get(i) instanceof Powersource) {
                if(Gates_List.get(i).getOutput_point().getState() == 1){
                    gc.setStroke(Color.GREEN);
                    gc.strokeRect(Gates_List.get(i).get_x(), Gates_List.get(i).get_y(), 30, 30);
                    gc.strokeText("H(P)", Gates_List.get(i).get_x() + 7, Gates_List.get(i).get_y() + 20);
                    gc.strokeOval(Gates_List.get(i).getOutput_point().getX(), Gates_List.get(i).getOutput_point().getY(), 7, 7);
                    gc.setStroke(Color.BLACK);
                }
                else if (Gates_List.get(i).getOutput_point().getState() == 2){
                    gc.setStroke(Color.RED);
                    gc.strokeRect(Gates_List.get(i).get_x(), Gates_List.get(i).get_y(), 30, 30);
                    gc.strokeText("L(P)", Gates_List.get(i).get_x() + 7, Gates_List.get(i).get_y() + 20);
                    gc.strokeOval(Gates_List.get(i).getOutput_point().getX(), Gates_List.get(i).getOutput_point().getY(), 7, 7);
                    gc.setStroke(Color.BLACK);
                }

            }
        }


        //redraw all the Wires in Wires_list
        for(int j=0;j<Wires_List.size();j++){

            gc.strokeLine(Wires_List.get(j).getCoords()[0], Wires_List.get(j).getCoords()[1], Wires_List.get(j).getCoords()[2] - ((Wires_List.get(j).getCoords()[0] + Wires_List.get(j).getCoords()[2]) / 10),Wires_List.get(j).getCoords()[1]);
            gc.strokeLine(Wires_List.get(j).getCoords()[2] - ((Wires_List.get(j).getCoords()[0] + Wires_List.get(j).getCoords()[2]) / 10), Wires_List.get(j).getCoords()[1], Wires_List.get(j).getCoords()[2] - ((Wires_List.get(j).getCoords()[0] + Wires_List.get(j).getCoords()[2]) / 10), Wires_List.get(j).getCoords()[3]);
            gc.strokeLine(Wires_List.get(j).getCoords()[2] - ((Wires_List.get(j).getCoords()[0] + Wires_List.get(j).getCoords()[2]) / 10), Wires_List.get(j).getCoords()[3],Wires_List.get(j).getCoords()[2],Wires_List.get(j).getCoords()[3]);

        }//end of for
    }//end of redraw


    /*in this method I do some necessary things to provide a good GUI and/or to add some static things. There might be another solution for this.
    I will take a look at this later*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Inputfield.setText("2");
        change_n_inputs();
        Wires.setVisible(false);
        Elements.setVisible(false);
        Se_Elements.setVisible(false);


    }

    private void connect(GATE a, GATE b){
        for(int i =0; i < b.getInput_points_number(); i++){
            if(b.getInputpoints()[i].getState() == 0) {
                b.getInputpoints()[i].setState(a.getOutput_point().getState());
                a.getOutput_point().setConnected_to(b.getInputpoints()[i]);
                Connection_list.add(new Connection(a, b));
                redraw();
                return;
            }
        }
    }//end of connect

    private void connect2(GATE a, GATE b){
        for(int i =0; i < b.getInput_points_number(); i++){
            if(b.getInputpoints()[i] == a.getOutput_point().getConnected_to()) {
                b.getInputpoints()[i].setState(a.getOutput_point().getState());
            }
        }
        redraw();
    }//end of connection2


    @FXML
    protected void change_mode(){

        if(!mode_flag){
            Wires.setVisible(true);
            Elements.setVisible(true);
            Se_Elements.setVisible(true);
            Mode_button.setText("DRAWMODE");
        }else{
            Wires.setVisible(false);
            Elements.setVisible(false);
            Se_Elements.setVisible(false);
            Mode_button.setText("EDITMODE");
            edit();

        }
//sdd/
        mode_flag = !mode_flag;
    }//end of activate_draw

    protected void edit(){
        //setup eventhandler
        canvas.setOnMouseClicked(e->{
            //check for the right mode selected
            if(!mode_flag){
                for(int i =0; i < Gates_List.size(); i++){
                    if(isPointInRect(e.getX(),e.getY(),Gates_List.get(i).get_x(),Gates_List.get(i).get_y())){
                        //logic to change the state of the power source by simply clicking on it
                        if(Gates_List.get(i) instanceof Powersource){
                            if(Gates_List.get(i).getOutput_point().getState() == 1){Gates_List.get(i).getOutput_point().setState(2);}
                            else if(Gates_List.get(i).getOutput_point().getState() == 2){Gates_List.get(i).getOutput_point().setState(1);}
                            connect2(Gates_List.get(i),Gates_List.get(i).getOutput_point().getConnected_to().getGate());



                            for(int k =0; k < Connection_list.size(); k++){
                                connect2(Connection_list.get(k).get_connection()[0], Connection_list.get(k).get_connection()[1] );
                            }


                        }

                    }
                }
            }
        });

    }//end of edit

    @FXML
    protected void change_n_inputs(){


        try{
            if(Integer.parseInt(Inputfield.getText()) <= 3) {
                Input_textfield = Integer.parseInt(Inputfield.getText());
                if (this.selected_gate == "AND") {
                    select_gate_And();
                } else if (this.selected_gate == "OR") {
                    select_or();
                } else if (this.selected_gate == "NOT") {
                    select_not();
                } else if (this.selected_gate == "HIGH") {
                    select_High();
                } else if (this.selected_gate == "LOW") {
                    select_Low();
                } else if (this.selected_gate == "POWERSOURCE") {
                    select_Powersource();
                }
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Error: Invalid input, not an integer.");
        }
    }//end of change_n_inputs
}//end of class
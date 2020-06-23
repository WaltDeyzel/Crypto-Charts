public class Point{

    private double y = 0;
    private double x = 0;
    private double index = 0;

    public Point(double ycor, double xcor, double i){
        y = ycor;
        x = xcor;
        index = i;
    }

    public double   getYcor(){ return y  ;}
    public double   getXcor(){ return x  ;}
    public double   getIndex(){ return index  ;}

}
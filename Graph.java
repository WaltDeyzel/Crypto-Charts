import java.util.ArrayList;
import java.awt.Color;
public class Graph{
    private ArrayList<Point> graph =  new ArrayList<Point>();

    private double radius;
    private Color colour;
    private double plotscale;
    private double plotofset;
    private double scale = 0;
    private double ofset = 0;


    public Graph(ArrayList<Point> points, double pen, Color color, int frame){
        graph = points;   
        radius = pen;
        colour = color;
        if(frame == 1){
            plotscale = Constants.getFrameScale();
            plotofset = Constants.getFrameOfset();
        }
        else if(frame == 2){
            plotscale = Constants.getFrame2Scale();
            plotofset = Constants.getFrame2Ofset();
        }
    }

    public ArrayList<Point> getGraph(){ return graph;}
    public double getRadius()   {return radius    ;}
    public Color  getColor()    { return colour   ;}
    public double getPlotscale(){return plotscale ;}
    public double getPlotofset(){ return plotofset;}
    public double getScale()    { return scale    ;}
    public double getOfset()    { return ofset    ;}

    public void plot(){
        plot(graph, colour);
    }

    public void plot(ArrayList<Point> set, Color color){
        StdDraw.setPenColor(color);
        StdDraw.setPenRadius(radius);
        double x = 0;                       //Start of screen
        double dx = 2.0/set.size();        //Increment of x
        for(int i = 1; i< set.size(); i++){
            double y1 = (set.get(i-1).getYcor()-ofset)/scale;
            double y2 = (set.get(i).getYcor()-ofset)/scale;
            double y1s = y1*plotscale+plotofset;
            double y2s = y2*plotscale+plotofset;
            StdDraw.line(x, y1s, x+dx, y2s);
            x+=dx;
        }

    }

    public void scaleTo(ArrayList<Point> set){
        double max = Indicator.getMax(set)[1];
        double min = Indicator.getMin(set)[1];
        scale = max-min;
        ofset = min;
    }

    public void showMax(){
        double[] maxCor = Indicator.getMax(graph);
        double y = (maxCor[1]-ofset)/scale;
        double x = graph.get((int)maxCor[0]).getXcor();
        StdDraw.setPenColor(colour);
        StdDraw.filledCircle(x, y*plotscale+plotofset, 0.008);
    }

    public void showMin(){

        double[] minCor = Indicator.getMin(graph);
        double y = (minCor[1]-ofset)/scale;
        double x = graph.get((int)minCor[0]).getXcor();
        StdDraw.setPenColor(colour);
        StdDraw.filledCircle(x, y*plotscale+plotofset, 0.008);
    }

    public void col(int interval){
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setPenRadius(0.000005);
        for(int i = 0; i < graph.size(); i++){
            if(i % interval == 0){
            StdDraw.line(graph.get(i).getXcor(), 0, graph.get(i).getXcor(), Constants.height);
            StdDraw.text(Constants.Xscale - graph.get(i).getXcor(), 0.01, "" + (int)graph.get(i).getIndex());
            }
        }
    }

    public void grid(int increments){
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setPenRadius(0.000005);

        int increment = increments;

        double min = Indicator.getMin(graph)[1];
        double max = Indicator.getMax(graph)[1];
        int i = 0;
        do{
            double value = (min + increment*i);
            double y1  = (value -ofset)/scale;
            double y = y1*plotscale+plotofset;
            i++;
            StdDraw.line(0, y, Constants.width, y);
            StdDraw.text(1.97, y + 0.008, "" + (int)(value));
        }while((min+increment*i)<=max);
        
    }



}
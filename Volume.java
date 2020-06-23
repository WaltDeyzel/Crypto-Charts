import java.util.ArrayList;
import java.awt.Color;
public class Volume extends Graph{
    
    public Volume(ArrayList<Point> points, double pen, Color color, int frame){
        super(points, pen, color, frame);
    }

    public void plot(){
        plotVol(super.getGraph(), super.getColor());
    }

    public void plotVol(ArrayList<Point> set, Color color){

        StdDraw.setPenColor(color);
        StdDraw.setPenRadius(super.getRadius());

        double dx = Constants.Xscale/set.size();
        double pre = 0;

        for(int i = 1; i<set.size(); i++){

            double y1 = 0.5*(set.get(i).getYcor()-super.getOfset())/super.getScale(); //scale to fit between zero and one.
            double y = y1*Constants.getFrameOfset(); //scale to fit within frame... heigh of 

            double x = set.get(i).getXcor();

            StdDraw.filledRectangle(x, ((y1-y)/2+Constants.getFrameOfset()), dx/2, (y1-y)/2);
        }
    }
}
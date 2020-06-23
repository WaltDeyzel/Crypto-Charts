import java.util.ArrayList;
import java.awt.Color;
public class BollingerBands extends Graph{
    
    public BollingerBands(ArrayList<Point> points, double pen, Color color, int frame){
        super(points, pen, color, frame);
    }

    public void plot(){
        plotBollingerBands(super.getGraph(), super.getColor());
    }

    private void plotBollingerBands(ArrayList<Point> set, Color color){
        StdDraw.setPenColor(color);
        StdDraw.setPenRadius(super.getRadius());

        ArrayList<Point> upper = new ArrayList<Point>();
        ArrayList<Point> downer = new ArrayList<Point>();

        double sd = Indicator.standardDiviation(set);

        for(int i = 0; i<set.size(); i++){
            double y1 = (set.get(i).getYcor()+sd);
            double y2 = (set.get(i).getYcor()-sd);
            Point up = new Point(y1, 0, i);
            Point down = new Point(y2, 0, i);

            upper.add(up);
            downer.add(down);
        }
        super.plot(upper, color);
        super.plot(downer, color);
    }
}
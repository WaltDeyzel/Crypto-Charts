import java.util.ArrayList;
import java.awt.Color;
public class MACD extends Graph{
    
    ArrayList<Point> line = new ArrayList<Point>();
    ArrayList<Point> macd = new ArrayList<Point>();
    
    public MACD(ArrayList<Point> points, double pen, Color color, int frame){
        super(points, pen, color,frame);
    }

    public void plot(){
        macd = plotMACD(super.getGraph(), super.getColor());
        line = plotMACD(Indicator.exponentialMovingAverageP(super.getGraph(), 9), Color.CYAN);
        plotBaseline();
        plotRec();
        macd = plotMACD(super.getGraph(), super.getColor());
        line = plotMACD(Indicator.exponentialMovingAverageP(super.getGraph(), 9), Color.CYAN);
    }

    public ArrayList<Point> plotMACD(ArrayList<Point> set, Color color){
        StdDraw.setPenColor(color);
        StdDraw.setPenRadius(super.getRadius());

        ArrayList<Point> export = new ArrayList<Point>();

        double x = 0;                       //Start of screen
        double dx = 2.0/set.size();        //Increment of x
        
        for(int i = 1; i< set.size(); i++){
            double y1 = (set.get(i-1).getYcor()-super.getOfset())/super.getScale();
            double y2 = (set.get(i).getYcor()-super.getOfset())/super.getScale();
            double y1s = y1*(0.95-Constants.getFrameScale());
            double y2s = y2*(0.95-Constants.getFrameScale());
            Point cor = new Point(y1, x, i);
            export.add(cor);
            StdDraw.line(x, y1s, x+dx, y2s);
            x+=dx;
        }
        return export;

    }

    private void plotBaseline(){
        StdDraw.setPenColor(Color.GRAY);
        double l = (0-super.getOfset())/super.getScale();
        double l2 = l*(0.95-Constants.getFrameScale());
        StdDraw.line(0, l2, Constants.width, l2);
    }

    private void plotRec(){
        ArrayList<Point> export = new ArrayList<Point>();
        double x = 0;
        double dx = 2.0/macd.size();
        for(int i = 0; i< macd.size(); i++){
            double dif = macd.get(i).getYcor()-line.get(i).getYcor();
            Point diff = new Point(dif, x, i);
            export.add(diff);
            x+=dx;
        }
        plotVol(export, Color.DARK_GRAY);
    }

    public void plotVol(ArrayList<Point> set, Color color){
        StdDraw.setPenRadius(super.getRadius());

        double dx = 2.0/set.size();
        double pre = 0;

        double l = (0-super.getOfset())/super.getScale(); //Base Line as ref
        double l2 = l*(0.95-Constants.getFrameScale());   //Base line scaled 

        for(int i = 1; i<set.size(); i++){

            double y =set.get(i).getYcor()*0.3; //scale to fit within frame... heigh of 
            double x = set.get(i).getXcor();
            if(y>0){
                StdDraw.setPenColor(Color.green);
            StdDraw.filledRectangle(x, l2+y/2, dx/2, y/2);
            }
            else{
                StdDraw.setPenColor(Color.red);
                StdDraw.filledRectangle(x, l2+y/2, dx/2, -y/2);
            }
        }
    }
}
import java.util.ArrayList;
public class Indicator{
    //Make this class static and give all the functions a ArrayList<Point> input.
    //You can then access the data directrly from Diagram.... Also rename diagram
    //Main plot will have all the data. The actual price plot.

    //Diagram returns data to a Grpah function that does the maths and then Graph returns data to Diagram

    public static double getAverage(ArrayList<Point> set){
        int m = 0;
        int sum = 0;

        for( int i = 0 ; i< set.size(); i++){

            double price = (set.get(i).getYcor());
            sum += price;
            m ++;
        }
        return (double)sum/m;
    }

    public static double[] getMax(ArrayList<Point> set){
        double max = 0;
        double index = 0;
        for(int i = 0; i < set.size(); i++){
            double yValue = set.get(i).getYcor();
            if(yValue > max){ 
                max = yValue;//Close price
                index = set.get(i).getIndex();
            }
        }
        double[] cor = {index, max};
        return cor;
    }

    public static double[] getMin(ArrayList<Point> set){
        double min = 50000000;
        double index = 0;
        for(int i = 0; i < set.size(); i++){
            double yValue = set.get(i).getYcor();
            if(yValue < min){ 
                min = yValue;
                index = set.get(i).getIndex();
            }
        }
       double[] cor = {index, min};
        return cor;
    }

    public static double standardDiviation(ArrayList<Point> set){
        double average = getAverage(set);
        ArrayList<Point> points = new ArrayList<Point>();

        for(int i = 0; i<set.size(); i++){
            double y = Math.pow(set.get(i).getYcor() - average, 2);
            Point diviation = new Point(y, set.get(i).getXcor(), i);
            points.add(diviation);
        }

        return Math.sqrt(getAverage(points));
    }

    public static ArrayList<Point> priceChart(ArrayList<Data> set){

        ArrayList<Point> export = new ArrayList<Point>();
        double x = 0;
        double dx = 2.0/set.size();
        for(int i = 0; i < set.size(); i++){
            double y = set.get(i).getClose();
            Point cor = new Point(y, x, i);
            export.add(cor);
            x+=dx;
        }

        return export;
    }

    public static ArrayList<Point> exponentialMovingAverage(ArrayList<Data> set, double period){
        double smoothing = 2/(period+1);

        ArrayList<Point> export =  new ArrayList<Point>();
        double pastEMA = set.get(0).getClose();
       
        for(int i = 0; i<set.size(); i++){
            
            double currentEMA = set.get(i).getClose()*smoothing + (1-smoothing)*pastEMA ;
            Point cor = new Point(currentEMA, 0, i);
            export.add(cor);
            pastEMA = currentEMA; 
        }
        return export;
    }

    public static ArrayList<Point> exponentialMovingAverageP(ArrayList<Point> set, double period){
        double smoothing = 2/(period+1);

        ArrayList<Point> export =  new ArrayList<Point>();
        double pastEMA = set.get(0).getYcor();
       
        for(int i = 0; i<set.size(); i++){
            
            double currentEMA = set.get(i).getYcor()*smoothing + (1-smoothing)*pastEMA ;
            Point cor = new Point(currentEMA, 0, i);
            export.add(cor);
            pastEMA = currentEMA; 
        }
        return export;
    }

    public static ArrayList<Point> simplemovingAverage(ArrayList<Data> set, double period){

        ArrayList<Point> export =  new ArrayList<Point>();
        double x =0, y=0;
        for(int i = 1; i < set.size(); i++){
            
            if(i>=period){
                y = pastAvg(set, period, (double)i);
                x = 0;
            }
            else{
                y = pastAvg(set, (period-(period-i)), (double)i);
                x = 0;
            }
            Point cor = new Point(y, x, i);
            export.add(cor);
        }
        
        return export;
    }

    public static double pastAvg(ArrayList<Data> set, double period, double where){
        double sum = 0;
        double start = where-period;
        for(int i = (int)start; i<where; i++){
            sum += set.get(i).getClose();
        }
        return sum/period;
    }

    public static ArrayList<Point> volume(ArrayList<Data> set){

        ArrayList<Point> export = new ArrayList<Point>();
        double x = 0;
        double dx = 2.0/set.size();
        for(int i = 0; i < set.size(); i++){
            double y = set.get(i).getVol();
            Point cor = new Point(y, x, i);
            export.add(cor);
            x+=dx;
        }

        return export;
    }

    public static ArrayList<Point> MACD(ArrayList<Data> set){
        ArrayList<Point> set1 = exponentialMovingAverage(set, 26);
        ArrayList<Point> set2 = exponentialMovingAverage(set, 12);
        ArrayList<Point> export = new ArrayList<Point>();
        for(int i = 0; i<set1.size(); i++){
            double ycor = set2.get(i).getYcor()-set1.get(i).getYcor();
            double xcor = set2.get(i).getXcor();
            Point cor = new Point(ycor, xcor, i);
            export.add(cor);
        }
        return export;
    }


    
}
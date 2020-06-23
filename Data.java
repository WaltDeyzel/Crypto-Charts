public class Data{
    private String date;
    private double close;
    private double open;
    private double high;
    private double low;
    private double vol;
    private double change;

    private double y;
    private double x;
    private int index;

    public Data(String d, double c, double o, double h, double l, double v, double cc){
        date = d;
        close = c;
        open = o;
        high = h;
        low = l;
        vol = v; 
        change = cc;
    }

    public String getDate()  { return date;}
    public double getClose() { return close;} //Closing Price
    public double getOpen()  { return open;}
    public double getHigh()  { return high;}
    public double getLow()   { return low;}
    public double getVol()   { return vol;}
    public double getChange(){ return change;}

    public double   getYcor(){ return y;}
    public double   getXcor(){ return x ;}
    public double   getIndex(){ return index  ;}

    public void setIndex(int i){index = i;}
    public void setXcor(double xcor){x = xcor;}
    public void setYcor(double ycor){y = ycor;}
}
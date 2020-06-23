import java.util.ArrayList;
import java.awt.Color;
public class Crypto{
    private String filename = "Empty";
    private ArrayList<Data> data = new ArrayList<Data>();
    private ArrayList<Data> data1 = new ArrayList<Data>();

    public Crypto(String name){
        filename = name;
        textfile();
    }

    public void textfile(){
        Readfile datafile = new Readfile(filename);
        datafile.OpenFile();
        datafile.ReadFile();
        datafile.CloseFile();
        importData(datafile.dataPackage());
    }

    public void importData(ArrayList<Data> datafile){
        int index = 0; // start
        double x = 0;
        for(int i = datafile.size()-1; i>=0; i--){
            Data current = datafile.get(i);
            Data cor = new Data(current.getDate(), current.getClose(), current.getOpen(), current.getHigh(), current.getLow(), current.getVol(), current.getChange());
            cor.setIndex(index);
            data1.add(cor);
            index++;
        }
    }

    public void setData(){
        data = periodSet(Constants.daysBackInTime);
    }

    public ArrayList<Data> periodSet(int period){
        ArrayList<Data> intervalSet = new ArrayList<Data>();
        double deltaX = 2.0/period;
        double x = 0;
        double m = 0;
        if( period == 0 || period == data1.size()){
            period = data1.size();
        }
        for(int i = (data1.size() - period); i< data1.size(); i++){
            Data cur = data1.get(i);
            Data cor = new Data(cur.getDate(), cur.getClose(), cur.getOpen(), cur.getHigh(), cur.getLow(), cur.getVol(), cur.getChange());
            intervalSet.add(cor);
            x+= deltaX;
            m++;
        }
        return intervalSet;
    }

    public ArrayList<Data> exportData(){
        return data;
    }

    public int dataSize(){ return data1.size();}

    public void plot(double penRadius, Color color, int frame){
        Graph price = new Graph(Indicator.priceChart(data), penRadius, color, frame);
        price.scaleTo(Indicator.priceChart(data));
        price.showMax();
        price.showMin();
        price.col(10);
        price.grid(1000);
        price.plot();
    }

    public void plotExponentialMovingAverage(double period, double penRadius, Color color, int frame){
        Graph ema =  new Graph(Indicator.exponentialMovingAverage(data, period), penRadius, color, frame);
        ema.scaleTo(Indicator.priceChart(data));
        ema.plot();
    }

    public void plotSimpleMovingAverage(double period, double penRadius, Color color, int frame){
        Graph sma =  new Graph(Indicator.simplemovingAverage(data, period), penRadius, color, frame);
        sma.scaleTo(Indicator.priceChart(data));
        sma.plot();
    }

    public void plotBollingerBands(double period, double penRadius, Color color, int frame){
        
        Graph band =  new BollingerBands(Indicator.simplemovingAverage(data, period), penRadius, color, frame);
        band.scaleTo(Indicator.priceChart(data));
        band.plot();
    }

    public void plotVol(double penRadius, Color color, int frame){
        Graph vol =  new Volume(Indicator.volume(data), penRadius, color, frame);
        vol.scaleTo(Indicator.volume(data));
        vol.plot();

    }

    public void plotMACD(double penRadius, Color color, int frame){
        Graph macd =  new MACD(Indicator.MACD(data), penRadius, color, frame);
        macd.scaleTo(Indicator.MACD(data));
        macd.grid(100);
        macd.plot();
    }
}
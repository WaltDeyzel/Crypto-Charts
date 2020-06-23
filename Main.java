import java.awt.Color;;
public class Main{
    
    public static void main(String[] args) {

        StdDraw.setCanvasSize(Constants.width, Constants.height);
        StdDraw.setYscale(0, Constants.Yscale);
        StdDraw.setXscale(0,Constants.Xscale);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);

        Crypto bitcoin = new Crypto("test.txt");

        StdDraw.show();
        
        while(true){
            
            StdDraw.clear(StdDraw.BLACK);
            updateGraphs(bitcoin);
            scaler(bitcoin);
            slider();
            curserX();
            curserY();

            StdDraw.show();
        }
    }

    public static void scaler(Crypto coin){
        if(StdDraw.isKeyPressed(37) && (coin.dataSize()-Constants.daysBackInTime >0)){ 
            Constants.incrementDays();
            
        }
        if(StdDraw.isKeyPressed(39) && (coin.dataSize()-Constants.daysBackInTime < coin.dataSize())){
            Constants.decrementDays();
        }
    }
    public static void slider(){
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.line(0, Constants.getFrameOfset(), Constants.width,  Constants.getFrameOfset());
        StdDraw.filledRectangle(0.05, Constants.getFrameOfset(), 0.05, 0.002);
        if(StdDraw.isKeyPressed(38)){ 
            Constants.decrementScale();
        }
        if(StdDraw.isKeyPressed(40)){ 
            Constants.incrementScale();
        }
    }

    public static void curserX(){
        double x = StdDraw.mouseX()-0.007;
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.line(x, 0, x,  Constants.height);
    }

    public static void curserY(){
        double y = StdDraw.mouseY()+0.007;
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.line(0, y, Constants.width,  y);
    }

    public static void updateGraphs(Crypto coin){
        coin.setData();
        
        if(true){coin.plotVol(0.001, Color.GRAY, 1);}
        if(true){coin.plot(0.003, Color.BLUE, 1);}
        if(false){coin.plotExponentialMovingAverage(9, 0.003, Color.YELLOW, 1);}
        if(true){coin.plotSimpleMovingAverage(20, 0.003, Color.RED, 1);}
        if(true){coin.plotBollingerBands(20, 0.003, Color.GREEN, 1);}
        if(true){coin.plotMACD(0.003, Color.ORANGE, 2);}
    }
}
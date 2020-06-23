public class Constants{
    public static int n = 1500;
    public static int width = n;
    public static int height = (int)(n/2);
    public static double Xscale = 2;
    public static double Yscale = 1;

    public static int daysBackInTime = 100; // interested in the past 100 days

    public static int colScale = 10; //number of days back from present day displayed in the collum scale

    public static void incrementDays(){ daysBackInTime++;}
    public static void decrementDays(){ daysBackInTime--;}

    private static double frame1Ofset = 0; // From the zero line that is the bottom of the screen
    private static double frame1Scale = 1;   // Scaled 

    public static double getFrameScale(){ return frame1Scale;}
    public static double getFrameOfset(){ return frame1Ofset;}
    
    public static void decrementScale(){ 
        frame1Ofset += 0.01; 
        frame1Scale = 1-frame1Ofset;
    }
    public static void incrementScale(){ 
        frame1Ofset -= 0.01; 
        frame1Scale = 1-frame1Ofset;
    }

    private static double frame2Scale = frame1Ofset;
    private static double frame2Ofset = 0;

    public static double getFrame2Scale(){ return frame1Ofset;}
    public static double getFrame2Ofset(){ return frame2Ofset;}
}
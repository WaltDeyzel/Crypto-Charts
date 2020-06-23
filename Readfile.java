import java.io.*;
import java.util.*;
import java.util.ArrayList;
public class Readfile{

    private Scanner file;
    private String filename;
    private ArrayList<Data> data = new ArrayList<Data>();

    public Readfile(String name){
        filename = name;
    }

    public void OpenFile(){
        
        try{
            file = new Scanner(new File(filename));
        }
        catch(Exception e){
            System.out.println("No file");
        }
    }

    public void ReadFile(){

        file.useDelimiter(";"); 
        while(file.hasNext()){

            String date = file.next();
            double close = file.nextDouble();
            double open = file.nextDouble();
            double high = file.nextDouble();
            double low = file.nextDouble();
            double vol = file.nextDouble();
            double change = file.nextDouble();

            Data day = new Data(date, close, open, high, low, vol, change);
            //System.out.println("Date " + date + " close " + close + " open " + open + " high " + high + " low " + low + " vol " + vol + " ch " + change);
            data.add(day);
        }
    }

    public void CloseFile(){
        file.close();
    }

    public ArrayList<Data> dataPackage(){
        return data;
    }
}
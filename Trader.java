import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
public class Trader{

    private double bank;
    private double coin = 0;

    private ArrayList<Data> data = new ArrayList<Data>();

    public Trader(double money){
        bank = money;
        textfile();
    }

    public void textfile(){
        Readfile datafile = new Readfile("test.txt");
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
            data.add(cor);
            index++;
        }
    }

    public void showData(){
        //for(int i = 0; i<data.size(); i++){
        //    System.out.println(data.get(i).getClose());
        //}
        //System.out.println("Bank  " + bank);
        //System.out.println("coin  " + coin);
        //System.out.println("Value " + coin*data.get(data.size()-1).getClose());
        
    }

    public void buy(double amount, double price){
        if(bank >= amount){
            coin += amount/price;
            bank -= amount;
        }
    }

    public void sell(double coins, double price){
        if(coins>= coin){
            coin -= coins;
            bank += coins*price;
        }
    }

    public void trade(){
        trade(1000, coin);
    }
    private void trade(double amountBuy, double amountSell ){
        double avgBuy = 0;
        int n = 0;
        Random rand = new Random();
        for(int i = 0; i<data.size(); i++){
            int choice = RandomBuy();
            double current = data.get(i).getClose();
            double c = rand.nextDouble();
            if(choice == 1){
                buy(amountBuy, current);
                n++;
                avgBuy+=current;
            }
            else if(choice == 0 && average(i)>current*1.1){
                sell(amountSell*c, current);
            }
                
        }
    }

    public int RandomBuy(){
        Random rand = new Random();
        int choice = rand.nextInt(2);
        return choice;
    }

    public double average(int n){
        double total = 0;
        for(int i = 0; i<n; i++){
            total += data.get(i).getClose();
        }
        return total/n;
    }

    public void simulate(){
        double dx = 2.0/10000;
        double x = 0;
       
        
        StdDraw.setPenColor(Color.RED);
        for(int i = 0; i<10000; i++){
           
            trade(i, coin);
            double value = bank + coin*data.get(data.size()-1).getClose();
            
            StdDraw.filledCircle(x, value/25000.0, 0.0008);
            x+= dx;
            bank = 10000;
            coin = 0;
        }
    }
}
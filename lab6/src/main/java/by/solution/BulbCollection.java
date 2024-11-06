package by.solution;



import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BulbCollection {
public static ArrayList<LightBulb> sortUp(ArrayList<LightBulb> col){
    return col.stream()
            .sorted(Comparator.comparingInt(LightBulb::findPrice))
            .collect(Collectors.toCollection(ArrayList::new));
}
    public static List<LightBulb> sortDownByPricePower(ArrayList<LightBulb> col){
        return col.stream()
                .sorted(Comparator.comparingDouble((LightBulb bulb) -> ((double) bulb.findPrice() /bulb.getPower()))
                        .reversed())
                .toList();
    }
    public static ArrayList<String> Cmanufacturer(ArrayList<LightBulb> col){

    ArrayList<String> lst=new ArrayList<>();
    for (LightBulb b:col){
        if(b.getManufacturer().charAt(0)=='C'){
            lst.add(b.getManufacturer());
        }
    }
    return lst;
    }
    public static Double findAveragePrice(ArrayList<LightBulb> col,String name){
    Double res=0.0;
    int count=0;
    for (LightBulb b:col){
        if(b.getManufacturer().equals(name)){
            res+=b.findPrice();
            count++;
        }
    }
    if(count==0) return 0.0;
    return res/count;
    }
    public static ArrayList<LightBulb> getFromFile(String filename){
            File file = new File(filename);
            ArrayList<LightBulb> t=new ArrayList<LightBulb>();
            try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8))
            {
                while(sc.hasNextLine()){
                    String[] ar=sc.nextLine().split("\\s+");
                    if(ar.length!=3){
                        throw new Exception("Wrong data format");
                    }
                    LightBulb temp=new LED(ar[0],Double.parseDouble(ar[1]),Integer.parseInt(ar[2]));
                    t.add(temp);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return t;
        }
    }

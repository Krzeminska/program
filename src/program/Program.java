
package program;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Katy
 */
public class Program {

    public static ArrayList<Item> DataIn = new ArrayList<>();
    //type of data: HumanWithParent_Id(1),OtherWithParent_Id(2),Other(3)
    public static int type=0;
    
    public static void main(String[] args) {
        String fileName = args[0];
        String IN = args[1];
        /*
        String fileName = "dane.csv";
        Scanner sc = new Scanner(System.in);
        String IN = sc.nextLine();
        String IN2 = sc.nextLine();
        String IN3 = sc.nextLine();
        */
        
        int number=0,n1=0,n2=0;
        //Walidacja argumentow
        try{
            number=Integer.valueOf(IN);
        }
        catch (Exception e){
            System.out.println("");
        }
        if(args.length==3)
            try{
                n1=Integer.valueOf(args[2]);
            }
            catch (Exception e){
                System.out.println("");
            }
        if(args.length==4)
            try{
                n1=Integer.valueOf(args[3]);
            }
            catch (Exception e){
                System.out.println("");
            }
            
        Read(fileName);
        
        switch (number) {
            case 1:
                //Print all items
                for(Item i : DataIn){
                    i.print();
                    System.out.println("");
                }
                break;
            case 2:
                //Print first 10 items
                for(int i=0;i<10;i++){
                    DataIn.get(i).print();
                    System.out.println("");
                }
                break;
            case 3:
                //quantity of items
                System.out.print(DataIn.size());
                break;
            case 4:
                //sorted items while ID>n1
                Collections.sort(DataIn, new IDComparator());
                for(int i=0;i<DataIn.size();i++){
                    if(DataIn.get(i).getID()>n1){
                        DataIn.get(i).print();
                        System.out.println("");
                    }
                }
                break;
            case 5:
                //items from n1 to n2
                if(n2<n1){
                    int temp=n1;
                    n1=n2;
                    n2=temp;
                }
                int sum=0;
                for(int i=n1;i<n2-1;i++){
                    sum+=1;
                }
                System.out.println(sum);
                break;   
            case 6:
                //counting names (sorted by n.counter)
                ArrayList<Names> n = NameCount();
                Collections.sort(n, new CounterComparator());
                for(int i=0;i<n.size();i++)
                    n.get(i).print();
                break;
            case 7:
                //n1. item (and number of his parents)
                int m=0,index=0;
                while(m<DataIn.size() && index==0){
                    if(DataIn.get(m).getID()==n1)
                        index=m;
                    m++;
                }
                if(index>0){
                    System.out.print(DataIn.get(index).getName());
                    if(DataIn.get(index).getParents()>=0){
                        if(DataIn.get(index).getParents()>0){
                            int parents=0;
                            for(int i=0;i<DataIn.size();i++){
                                if(DataIn.get(index).getParents()>DataIn.get(i).getParents())
                                    parents++;
                            }
                            System.out.print(" - "+parents);
                        }
                        else
                            System.out.print(" - "+DataIn.get(index).getParents());
                    }  
                }
                System.out.println("");
                break;
            case 8:
                //property of names:
                Pattern pattern = Pattern.compile("[P].+[r]");
                Pattern pattern2 = Pattern.compile(".+[ij].+");
                for(int i=0;i<DataIn.size();i++){
                    Matcher matcher = pattern.matcher(DataIn.get(i).getName());
                    Matcher matcher2 = pattern2.matcher(DataIn.get(i).getName());
                    if(matcher.matches() && !matcher2.matches())
                        DataIn.get(i).print();
                }
                break;
            case 9:
                //n1-random items
                Random r = new Random();
                while(n1>0){
                    int i = r.nextInt(DataIn.size());
                    DataIn.get(i).print();
                    System.out.println("");
                    n1--;
                }
                break;
            case 10:
                //Average of age all of the list
                int Sum=0;
                for(int i=n1;i<DataIn.size();i++){
                    Sum+=DataIn.get(i).getAge();
                }
                System.out.println(Sum/DataIn.size());
                break;
            //SORTOWANIE LISTY PO ELEMENTACH    
            case 11:
                ArrayList<Integer> IdList = new ArrayList<>();
                for(int i=0;i<DataIn.size();i++){
                    IdList.add(DataIn.get(i).getID());
                }
                Collections.sort(IdList,new IntegerComparator());
                for(int i=0;i<IdList.size();i++){
                    System.out.print(IdList.get(i)+" ");
                }
                System.out.println("");
                break;
            case 12:
                //counting the percentage of items in the age range r1
                int min=100,max=0;
                for(int i=0;i<DataIn.size();i++){
                    if(DataIn.get(i).getAge()>max)
                        max=DataIn.get(i).getAge();
                    if(DataIn.get(i).getAge()<min)
                        min=DataIn.get(i).getAge();
                }
                //range
                double r1;
                r1 = (max-min)*0.25;
                int iterator=0;
                for(int i=0;i<DataIn.size();i++){
                    if(DataIn.get(i).getAge()>=min && DataIn.get(i).getAge()<(min+r1))
                        iterator+=1;
                }
                System.out.println(min+"-"+(min+r1)+"\t: "+(iterator*(100/DataIn.size()))+"%");
                iterator=0;
                for(int i=0;i<DataIn.size();i++){
                    if(DataIn.get(i).getAge()>=(min+r1) && DataIn.get(i).getAge()<(min+2*r1))
                        iterator+=1;
                }
                System.out.println((min+r1)+"-"+(min+2*r1)+"\t: "+(iterator*(100/DataIn.size()))+"%");
                iterator=0;
                for(int i=0;i<DataIn.size();i++){
                    if(DataIn.get(i).getAge()>=(min+2*r1) && DataIn.get(i).getAge()<(min+3*r1))
                        iterator+=1;
                }
                System.out.println((min+2*r1)+"-"+(min+3*r1)+"\t: "+(iterator*(100/DataIn.size()))+"%");
                iterator=0;
                for(int i=0;i<DataIn.size();i++){
                    if(DataIn.get(i).getAge()>=(min+3*r1) && DataIn.get(i).getAge()<=max)
                        iterator+=1;
                }
                System.out.println((min+3*r1)+"-"+max+"\t: "+(iterator*(100/DataIn.size()))+"%");
                break;
            default:
                break;
        }
    }
    
    public static void Read(String fName) {
        ArrayList<String> In = new ArrayList<>();
        Path path = Paths.get(fName);
        try {
            In = (ArrayList) Files.readAllLines(path);
        } catch (IOException ex) {
            System.out.println("Error. There is no file "+fName+".\n Try again.");
        }
        
        int i=0;
        for (String linia : In) {
            List<String> Line = new ArrayList<>(Arrays.asList(linia.split(", ")));
            if(i==0){
                switch (Line.size()) {
                    case 6:
                        type=1;
                        break;
                    case 5:
                        type=2;
                        break;
                    case 4:
                        type=3;
                        break;
                    default:
                        break;
                }
            }
            else{
                switch (type) {
                    case 1: //HumanWithParent_Id(1)
                        Human h = new Human(
                                    Integer.valueOf(Line.get(0)),
                                    Line.get(1),
                                    Line.get(2),
                                    Integer.valueOf(Line.get(3)),
                                    Line.get(4),
                                    Integer.valueOf(Line.get(5)));
                        DataIn.add(h);
                        break;
                    case 2: //OtherWithParent_Id(2)
                        if(Pattern.matches("age", Line.get(2))){ //PetWithParent_Id
                            Other o = new Other(
                                    Integer.valueOf(Line.get(0)),
                                    Line.get(1),
                                    Integer.valueOf(Line.get(2)),
                                    Line.get(3),
                                    Integer.valueOf(Line.get(4)));
                            DataIn.add(o);
                        }
                        else {
                            Other o = new Other(
                                    Integer.valueOf(Line.get(0)),
                                    Line.get(1),
                                    Line.get(2),
                                    Integer.valueOf(Line.get(3)),
                                    Integer.valueOf(Line.get(4)));
                            DataIn.add(o);
                        }
                        break;
                    case 3: //Other(3)
                        Other o = new Other(
                                    Integer.valueOf(Line.get(0)),
                                    Line.get(1),
                                    Integer.valueOf(Line.get(2)),
                                    Line.get(3));
                        DataIn.add(o);
                        break;
                    default:
                        break;
                }
            }
            i++;
        }
    }
    
    public static ArrayList<Names> NameCount(){
        ArrayList<Names> Data = new ArrayList<>();
        for(int i=0;i<DataIn.size();i++){
            if(i==0){
                Data.add(new Names(DataIn.get(i).getName()));
            }
            else{
                boolean alreadyExist=false;
                for(int j=0;j<Data.size();j++)
                    if(DataIn.get(i).NameComparison(Data.get(j).name)){
                        Data.get(j).counter++;
                        alreadyExist=true;
                    }
                if(!alreadyExist)
                    Data.add(new Names(DataIn.get(i).getName()));
            }
        }
        return Data;
    }
    
    public static class IDComparator implements Comparator<Item>{

    @Override
    public int compare(Item i1, Item i2) {
        
        if (i1.getID() < i2.getID()){
            return -1;
        }
        else if  (i1.getID() == i2.getID()){    
            return 0;
        }
        else {
            return 1;
        }
    }
    }
    public static class CounterComparator implements Comparator<Names>{

    @Override
    public int compare(Names n1, Names n2) {
        
        if (n1.counter < n2.counter){
            return -1;
        }
        else if  (n1.counter == n2.counter){    
            return 0;
        }
        else {
            return 1;
        }
    }
    }
    public static class IntegerComparator implements Comparator<Integer>{

    @Override
    public int compare(Integer i1, Integer i2) {
        
        if ( i1 > i2){
            return -1;
        }
        else if  (i1 == i2){    
            return 0;
        }
        else {
            return 1;
        }
    }
    }
    
    
}
    


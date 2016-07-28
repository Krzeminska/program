
package program;

import java.util.regex.Pattern;

/**
 *
 * @author Katy
 */
public class Item{
    protected int ID;
    protected String Name;
    protected int Age;
    protected int Parent_ID;
    
    public Item(int i, String s, int a, int i2){
        ID = i;
        Name = s;
        Age = a;
        Parent_ID = i2;
    }
    public Item(int i, String s, int a){
        ID = i;
        Name = s;
        Age = a;
        Parent_ID = -1;
    }
    
    public int getID(){
        return ID;
    }
    public int getAge(){
        return Age;
    }
    public String getName(){
        return Name;
    }
    public boolean NameComparison(String i){
        return Pattern.matches(this.Name, i);
    }
    public int getParents(){
        return Parent_ID;
    }
    
    public void print(){
        if(Parent_ID>=0)
            if(Name.length()>=8)
                System.out.print(ID+"\t"+Name+"\t"+Age+"\t"+Parent_ID);
            else
                System.out.print(ID+"\t"+Name+"\t\t"+Age+"\t"+Parent_ID);
        else
            if(Name.length()>=8)
                System.out.print(ID+"\t"+Name+"\t"+Age);
            else
                System.out.print(ID+"\t"+Name+"\t\t"+Age);
    }
}


package program;

/**
 *
 * @author Katy
 */
public class Human extends Item {
    String Surname;
    String City;

    public Human(int i, String s, String s2, int a, String c, int i2) {
        super(i, s, a, i2);
        Surname = s2;
        City = c;
    }
    
    @Override
    public void print(){
        if(Name.length()>=8)
            if(Surname.length()>=8)
                System.out.print(ID+"\t"+Name+"\t"+Surname+"\t"+Age+"\t"+City+"\t\t"+Parent_ID);
                else
                    System.out.print(ID+"\t"+Name+"\t"+Surname+"\t\t"+Age+"\t"+City+"\t\t"+Parent_ID);
            else
                if(Surname.length()>=8)
                    System.out.print(ID+"\t"+Name+"\t\t"+Surname+"\t"+Age+"\t"+City+"\t\t"+Parent_ID);
                    else
                        System.out.print(ID+"\t"+Name+"\t\t"+Surname+"\t\t"+Age+"\t"+City+"\t\t"+Parent_ID);
    }
    
}

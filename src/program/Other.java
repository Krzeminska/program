
package program;

/**
 *
 * @author Katy
 */
public class Other extends Item {
    String arg;

    public Other(int i, String s, int a, int i2) {
        super(i, s, a, i2);
    }
    public Other(int i, String s, int a, String s2, int i2) {
        super(i, s, a, i2);
        arg = s2;
    }
    public Other(int i, String s, String s2, int a, int i2) {
        super(i, s, a, i2);
        arg = s2;
    }
    public Other(int i, String s, int a, String s2) {
        super(i, s, a);
        arg = s2;
    }
    
    @Override
    public void print(){
        if(Parent_ID>=0)
            if(Name.length()>=8)
                System.out.print(ID+"\t"+Name+"\t"+Age+"\t"+arg+"\t\t"+Parent_ID);
            else
                System.out.print(ID+"\t"+Name+"\t\t"+Age+"\t"+arg+"\t\t"+Parent_ID);
        else
            if(Name.length()>=8)
                System.out.print(ID+"\t"+Name+"\t"+Age+"\t"+arg);
            else
                System.out.print(ID+"\t"+Name+"\t\t"+Age+"\t"+arg);
    }
}

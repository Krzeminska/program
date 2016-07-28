
package program;

/**
 *
 * @author Katy
 */
public class Names{
        public String name;
        public int counter;
        
        Names(String s){
            name = s;
            counter = 1;
        }
        
        public void print(){
            System.out.println(name+"\t\t"+counter);
        }
        
}

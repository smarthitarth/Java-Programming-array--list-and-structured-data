
/**
 * Write a description of countLength here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class countLength {
    public void countLength(){
        String[] str = {"abc", "1234", "agfdsa-34f", "fasdfds"};
        for(int i=0; i<str.length; i++){
            System.out.println(str[i] + " : " +str[i].length());
        }
    }
}

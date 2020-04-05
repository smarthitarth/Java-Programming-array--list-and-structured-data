
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class CharactersInPlay {
    private ArrayList<String> persons;
    private ArrayList<Integer> count;
    
    public CharactersInPlay(){
        persons = new ArrayList<String>();
        count = new ArrayList<Integer>();
    }
    
    public void update(String person){
        int index = persons.indexOf(person.toLowerCase());
        if (index == -1){
            persons.add(person.toLowerCase());
            count.add(1);
        }else{
            int val = count.get(index);
            count.set(index, val+1);
           // System.out.println(persons.get(index) + " " + count.get(index));
        }
       
    }
    
    public void findAllCharacter(){
        persons.clear();
        count.clear();
        FileResource resource = new FileResource();
        for (String str : resource.lines()){
            //System.out.println(str);
            int index = str.indexOf(".");
            if(index != -1){
                //System.out.println(index);
                String name = str.substring(0, index);
                update(name);
                
            }
        }
        // String str = resource.lines();
    }
    
    public void tester(){
        findAllCharacter();
        int ind = findIndexOfMax();
        System.out.println("Max index is: " + count.get(ind) + " and word is: " + persons.get(ind) );
        for (int i = 0; i < count.size(); i++){
            int c = count.get(i);
            if (c>=10 && c<15){
                System.out.println(persons.get(i) + " : " + c);
            }
        }
    }
    public int findIndexOfMax(){
        int max = 0;
        for (int i = 0; i < count.size(); i++){
            if (count.get(i) > count.get(max)){
                max = i;
            }
        }
        return max;
    }
}

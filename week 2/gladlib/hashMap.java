
/**
 * Write a description of hashMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class hashMap {
    public void count(){
        FileResource fr = new FileResource();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int total = 0;
        for(String w : fr.words()){
            w = w.toLowerCase();
            total++;
            if (map.keySet().contains(w)){
                map.put(w, map.get(w) + 1);
            }else{
                map.put(w, 1);
            }
        }
        System.out.println("total : " + total);
        System.out.println(map);
        for (String s: map.keySet()){
            System.out.println(s +  ": " + map.get(s));
        }
    }
}

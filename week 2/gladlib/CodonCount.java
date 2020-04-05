
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 *///Java HashMap isn’t thread-safe, for the multithreaded environment, you must use ConcurrentHashMap category or get a synchronous map using Collections.synchronizedMap() method.
import java.util.*;
import edu.duke.*;
public class CodonCount {
    private static HashMap<String, Integer> map;
    
    public CodonCount(){
       HashMap<String, Integer> map = new HashMap<String, Integer>();
    }
    
    private void buildCodonMap(int start, String dna){
       // map.clear();
        HashMap<String, Integer> newMap = new HashMap<String, Integer>();
        for(int i = start; (i+3) <= dna.length(); i+=3){
            String w = dna.substring(i, i+3);
            //System.out.println(w+" "+dna.length());
            if ( w != null){
                if(newMap.keySet().contains(w)){
                    newMap.put(w, newMap.get(w) + 1);
                }else{
                    newMap.put(w, 1);
                }
            }   
        map = newMap;
        //System.out.println(newMap);
        //System.out.println(map);
        }  
    }
    
    private String getMostCommonCodon(){
        int max = 0;
        String ans = "";
        HashMap <String, Integer> map2 = map;
        for(String sm : map2.keySet()){
            int key = map2.get(sm);
            if(key > max){
                max = key;
                ans = sm;
            }
        }
        return ans;
    }
    
    private void printCodonCounts(int start, int end){
        HashMap<String, Integer> map3 = map;
        int i = 0;
        for(String sm : map3.keySet()){
            int key = map3.get(sm);
            if (key >= start && key <= end ){
                System.out.println("Codon: " + sm + " Count: " + key);
                i++;
            }
        }
        System.out.println("Unique: " + i);
    }
    
    public void tester(String s){
      // FileResource fr = new FileResource();
      // String sm =  fr.toString();
        CodonCount cc = new CodonCount();
        //HashMap<String, Integer> map = cc.map;
        for(int i = 0; i < 3; i++){
            //map.clear();
            buildCodonMap(i , s.toUpperCase());
            System.out.println(map);
            System.out.println("Most common codon: " + getMostCommonCodon());
            printCodonCounts(4, 4);
        }
    }
}

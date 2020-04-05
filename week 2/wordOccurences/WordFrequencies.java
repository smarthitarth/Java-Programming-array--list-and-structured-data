
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        FileResource resource = new FileResource();
        myWords.clear();
        myFreqs.clear();
        for (String str : resource.words()){
            str = str.toLowerCase();
            int index = myWords.indexOf(str);
            if(index == -1){
                myWords.add(str);
                myFreqs.add(1);
            }else{
                int val = myFreqs.get(index);
                myFreqs.set(index, val+1);
            }
        }
    }
    
    public void testFindUnique(){
        findUnique();
        System.out.println("Unique words: " + myFreqs.size());
        for (int k = 0; k < myFreqs.size(); k++){
            System.out.println(myWords.get(k) + " " + myFreqs.get(k)); 
        }
    }
    
    public int findIndexOfMax(){
        int max = 0;
        for (int i = 0; i < myFreqs.size(); i++){
            if (myFreqs.get(i) > myFreqs.get(max)){
                max = i;
            }
        }
        return max;
    }
    
    public void testMaxIndex(){
        int ind = findIndexOfMax();
        System.out.println("Max index is: " + myFreqs.get(ind) + " and word is: " + myWords.get(ind) );
    }
}

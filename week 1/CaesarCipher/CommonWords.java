
/**
 * Write a description of CommonWords here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;
public class CommonWords{
    public String[] getCommon(){
        FileResource resource = new FileResource("common.txt");
        String[] common = new String[20];
        int index = 0;
        for(String s : resource.words()){
            common[index] = s;
            index+= 1;
        }
        return common;
    }
    
    public int indexOf(String[] list, String word){        
        for(int i = 0; i<list.length; i++){
            if(list[i].equals(word)){
                return i;
            }
        }
        return -1;
    }
    
    public void countWords(FileResource fr, String[] common, int[] counts){
        for(String word: fr.words()){
            word = word.toLowerCase();
            int index = indexOf(common, word);
            if(index != -1){
                counts[index]+=1;                
            }
        }
    }
    
    public void countShakespeare(){
        String[] plays = {"rom.html"};
        String[] common = getCommon();
        int[] counts = new int[common.length];
        for(int k = 0; k < plays.length; k++){
            FileResource fr = new FileResource(plays[k]);
            countWords(fr, common, counts);
            System.out.println("Done with : " + plays[k]);
        }
        
        for(int k = 0; k < common.length; k++){
            System.out.println(common[k] + "\t" + counts[k]);
        }
    }
}
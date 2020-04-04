
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        for(String word : resource.words()){
            //int index = word.length();
            //StringBuilder sb = new StringBuilder(word);
            int index = CountLength(word);
            System.out.println(word + ": " + index);
            counts[index] += 1;
        }
    }
    
    public int CountLength(String word){
        int length = 0, end = word.length();
        if(Character.isLetter(word.charAt(0))){
            length++;
        }
         if(Character.isLetter(word.charAt(end-1))){
            length++;
        }
        for(int i = 1; i < end-1; i++){
            length++;
        }
        return length;
    }
    
    public int indexOfMax(int[] arr){
        int max = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > arr[max]){
                max = i;
            }
        }
        return max;
    }
    
    public void testCountWordLengths(){
        FileResource resource = new FileResource();
        int [] counts = new int[31];
        countWordLengths(resource, counts);
        for(int i = 0; i<counts.length;i++){
            System.out.println(i + " = " + counts[i]);
        }
        System.out.println("Index of max : " + indexOfMax(counts));
    }
}


/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.*;
import java.lang.*;
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    private static int i = 0;
    public static int count1 = 0;
    public WordsInFiles(){
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();        
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        HashMap<String, ArrayList<String>> map2 = new HashMap<String, ArrayList<String>>();
        
        for(String s : fr.words()){
            System.out.println(s);
            String fname = f.getName();
            count1++;
            //System.out.println("f name : " + f);
            //System.out.println("i is: " + i);
            if(i == 0){
                if(map2.keySet().contains(s)){
                    ArrayList<String> fileName = map2.get(s);
                    fileName.add(fname);
                    //System.out.println("FIle name: " + fileName);
                    map2.put(s, fileName);
                    //System.out.println(map2);
                }else{
                    ArrayList<String> fileName = new ArrayList<String>();
                    fileName.add(fname);
                    map2.put(s, fileName);
                    //System.out.println(map2);
                }
            }else{
                if(map.keySet().contains(s)){
                    ArrayList<String> fileName = map.get(s);
                    fileName.add(fname);
                   // System.out.println("FIle name: " + fileName);
                    map.put(s, fileName);
                   // System.out.println(map);
                }else{
                    ArrayList<String> fileName = new ArrayList<String>();
                    fileName.add(fname);
                    map.put(s, fileName);
                   // System.out.println(map);
                }
            }
        }
       // map2.forEach((k, v) -> map.merge(k, v, ArrayList::addAll));
       if(i==0){ 
           map = map2;
           i = 1;
       }
       // System.out.println("End map2: " + map2);
        //System.out.println("End map: " + map);
    }
    
    public void buildWordFileMap(){
        //map.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        HashMap<String, ArrayList<String>> map3 = map;
        int max = 0;
        ArrayList<String> fileName = new ArrayList<String>();
        for (String s : map3.keySet()){
            fileName = map3.get(s);
            int k = fileName.size();
            if(k > max){
                max = k;
            }
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        HashMap<String, ArrayList<String>> map4 = map;
        int max = 0;
        ArrayList<String> fileName = new ArrayList<String>();
        ArrayList<String> wordList = new ArrayList<String>();
        for (String s : map4.keySet()){
            fileName = map4.get(s);
            int k = fileName.size();
            if(k == number){
                wordList.add(s);
            }
        }
        System.out.println("wordList: " + wordList.size());
        return wordList;
    }
    
    public void printFilesIn(String word){
        HashMap<String, ArrayList<String>> map5 = map;
        int max = 0;
        ArrayList<String> fileName = new ArrayList<String>();
        for (String s : map5.keySet()){
            if(s.equals(word)){
                fileName = map5.get(s);
                for(int i = 0; i < fileName.size(); i++){
                    System.out.println(fileName.get(i));
                }
            }
        }
    }
    
    public void tester(){
        buildWordFileMap();
        System.out.println("count : " + count1);
        System.out.println("Maximum files: " + maxNumber());
        System.out.println("5 times: ");
        ArrayList<String> ar = wordsInNumFiles(5);
        System.out.println("4 times: ");
        ar = wordsInNumFiles(4);
        //System.out.println("5 times: " + wordsInNumFiles(5));
        //System.out.println("4 times: " + wordsInNumFiles(4));
        System.out.println("sad: ");
        printFilesIn("sad");
        System.out.println("red: ");
        printFilesIn("red");
    }
}


/**
 * Write a description of GladLibHashMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class GladLibHashMap {
    private HashMap<String, ArrayList<String>> myMap;
    private Random myRandom;
    private static int in2 = 0;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    public static ArrayList<String> labelsUsed = new ArrayList<String>();
    public GladLibHashMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
       // ArrayList<String> labelsUsed = new ArrayList<String> ();
        HashMap<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>();
    }
    
    public GladLibHashMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
       // ArrayList<String> labelsUsed = new ArrayList<String> ();
        HashMap<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>();
    }
    
    private void initializeFromSource(String source) {
        String [] labels = {"country", "noun", "animal", "adjective", "name", "color", "verb", "timeframe", "fruit"};
        HashMap<String, ArrayList<String>> myMap2 = new HashMap<String, ArrayList<String>>();
        for(String s : labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap2.put(s, list);
        }
        myMap = myMap2;
     }
     
    private void totalWordsInMap (){
        int count = 0;
        HashMap<String, ArrayList<String>> myMap3 = myMap;
        for (String s : myMap3.keySet()){
            ArrayList<String> al = myMap3.get(s);
            count += al.size();
        }
        System.out.println("Total words to pick from: " + count);
    }
    
    private void totalWordsConsidered(){
        int count = 0;
        HashMap<String, ArrayList<String>> myMap3 = myMap;
        for (int i = 0; i<labelsUsed.size(); i++){
            String s = labelsUsed.get(i);
            System.out.println("String s: " + s);
            ArrayList<String> sm = myMap3.get(s);
            System.out.println("list: " + sm);
            count += sm.size();
        }
        System.out.println("labels:   " + labelsUsed + "Count : " + count);
    }
    
    /*private void initializeFromSource(String source) {
        HashMap<String, ArrayList<String>> myMap2 = new HashMap<String, ArrayList<String>>();
        for(String s : myLabelSource.keySet()){
            ArrayList<String> list = readIt(myLabelsource.get(s));
            myMap2.put(s, list);
        }
    }*/
    
    private String randomFrom(ArrayList<String> source){        
        int index = myRandom.nextInt(source.size());        
        if (in2 != index){
            in2 = index;
            return source.get(index);
        }
        return "no";
    }
    
    private String getSubstitute(String label) {
       if(!labelsUsed.contains(label)){
           if(!label.equals("number")){
        labelsUsed.add(label); }
       } 
       if (label.equals("number")){
           return ""+myRandom.nextInt(50)+5;
       }
       return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 25);
        totalWordsInMap ();
        totalWordsConsidered();
    }
    




}

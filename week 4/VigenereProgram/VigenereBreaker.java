import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb = new StringBuilder(message);
        StringBuilder ans = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices){
            ans.append(sb.charAt(i));
        }
        return ans.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker();
        for (int i = 0; i < klength; i++){
            key[i] = cc.getKey(sliceString(encrypted, i, klength));
            //System.out.println(key[i]);
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String message = fr.asString();        
        FileResource dict = new FileResource();
        HashSet<String> dictionary = readDictionary(dict);
        //int[] keys = tryKeyLength(message, 4, 'e');
        //for(int i = 0; i<keys.length; i++){
        //    System.out.println("Keys : "+keys[i]);
        //}
        //VigenereCipher vc = new VigenereCipher(keys);
        String decrypted = breakForLanguage(message, dictionary);
        System.out.println(decrypted);
    }
    public void breakVigenere2 () {
        FileResource fr = new FileResource();
        String message = fr.asString();        
        FileResource dict = new FileResource();
        HashSet<String> dictionary = readDictionary(dict);
        int[] keys = tryKeyLength(message, 38, 'e');
        //for(int i = 0; i<keys.length; i++){
          //  System.out.println("Keys : "+keys[i]);
        //}
        VigenereCipher vc = new VigenereCipher(keys);
        System.out.println("Valid words: " + countWords(vc.decrypt(message), dictionary));
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        String sr = fr.asString();
        String[] str = sr.split("\\W+");
        HashSet<String> dict = new HashSet<String>();
        for (int i = 0; i < str.length; i++){
            dict.add(str[i].toLowerCase());
        }
       // System.out.println(dict);      
       return dict;
    }
    
    public int countWords(String message, HashSet<String> dictionary){        
        //String[] str = message.split("\\W+");
        int count = 0;
        for (String word : message.split("\\W+")){
            if (dictionary.contains(word.toLowerCase())){
                count++;
            }
        }
        //System.out.println("Total valid words: " + count);
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int maxCount = 0;
        StringBuilder decryptedMax = new StringBuilder();
        int[] realKeys = {};
        for(int key = 1; key <= 100; key++){
            int[] keys = tryKeyLength(encrypted, key, 'e');
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted, dictionary);
            if(count > maxCount){
                maxCount = count;
                realKeys = keys;
                System.out.println("Max count: " + maxCount + "\tMax key: " + key);
                decryptedMax = new StringBuilder(decrypted);
            }
        }
        //System.out.println("Real counts: " + maxCount);
        //for(int i = 0; i<realKeys.length;i++){
        //    System.out.println("Keys: " + realKeys[i]);
        //}
        return decryptedMax.toString();
    }
    
    public void testReadDictionary(){
        FileResource fr = new FileResource();
        HashSet<String> h = readDictionary(fr);
        System.out.println(h);
    }
    
}

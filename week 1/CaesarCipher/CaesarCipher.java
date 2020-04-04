
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;
import java.util.*;
public class CaesarCipher {
    public String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
       // String alphabet2 = "abcdefghijklmnopqrstuvwxyz";
        String shifted = alphabet.substring(key) +  alphabet.substring(0, key);
       // String shifted2 = alphabet2.substring(key) +  alphabet2.substring(0, key);
        for (int i = 0; i<encrypted.length(); i++){
            if(Character.isLetter(encrypted.charAt(i))){
                char c = encrypted.charAt(i);           
                int idx = alphabet.indexOf(Character.toUpperCase(c));
                if(Character.isUpperCase(c)){   
                    encrypted.setCharAt(i, shifted.charAt(idx));         
                }else{
                    encrypted.setCharAt(i, Character.toLowerCase(shifted.charAt(idx)));   
                }
            }
        }
        return encrypted.toString();
    }
    
    public void testEncrypted(){
        String s = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",15);
        System.out.println(s);//At noon be in the conference room with your hat on for a surprise party. YELL LOUD!
        int[] j = countLetters("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!");
        for(int i = 0; i<26; i++){
            System.out.println(j[i]);
        }
        System.out.println( maxDex(j));
    }
    
    public void testEncrypted2(){
        String s = encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",8,21);
        System.out.println(s);
        //System.out.println(encrypt(s,15));
    }
    
    public String encryptTwoKeys(String phrase, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(phrase);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
       // String alphabet2 = "abcdefghijklmnopqrstuvwxyz";
        String shifted1 = alphabet.substring(key1) +  alphabet.substring(0, key1);
        String shifted2 = alphabet.substring(key2) +  alphabet.substring(0, key2);
        for (int i = 0; i<encrypted.length(); i++){
            if(Character.isLetter(encrypted.charAt(i))){
                char c = encrypted.charAt(i);           
                if(i%2==0){
                    int idx = alphabet.indexOf(Character.toUpperCase(c));
                    if(Character.isUpperCase(c)){   
                        encrypted.setCharAt(i, shifted1.charAt(idx));         
                    }else{
                        encrypted.setCharAt(i, Character.toLowerCase(shifted1.charAt(idx)));   
                    }
                }else{
                        int idx = alphabet.indexOf(Character.toUpperCase(c));
                        if(Character.isUpperCase(c)){   
                            encrypted.setCharAt(i, shifted2.charAt(idx));         
                        }else{
                            encrypted.setCharAt(i, Character.toLowerCase(shifted2.charAt(idx)));   
                        }
                }   
            }
        }
        return encrypted.toString();
    }
    public void testTwoEncry(String str, int key1, int key2){
        System.out.println(encryptTwoKeys(str,  key1,  key2));
    }
    public int[] countLetters(String encrypted){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int [] count = new int[26];
        for (int i = 0; i < encrypted.length(); i++){
           if(Character.isLetter(encrypted.charAt(i))){
               char ch = Character.toLowerCase(encrypted.charAt(i));
               int index = alphabet.indexOf(ch);
               count[index]++;
           }
        }
        return count;
    }
    
    public int maxDex(int[] freqs){
        int max = 0;
        for (int i = 0; i < freqs.length; i++){
            if(freqs[i] > freqs[max]){
                max = i;
            }
        }
        return max;
    }
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int [] freqs = countLetters(encrypted);
        int maxDex = maxDex(freqs);
        int ekey = maxDex - 4;
        if(maxDex < 4){
            ekey = 26 - (4-maxDex);
        }
        System.out.println("Keys : " + ekey);
        return cc.encrypt(encrypted, 26-ekey);        
    }
    
    public void testTwoKeyDecr(){
        FileResource fr = new FileResource();
        String str = fr.asString();
        System.out.println(decryptTwoKeys(str));
    }
    
    public String decryptTwoKeys(String encrypted){
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        char[] sb3 = new char[encrypted.length()];
        for (int i = 0; i < encrypted.length(); i++){
            if (i % 2 == 0){
                sb1.append(encrypted.charAt(i));
            }else{
                sb2.append(encrypted.charAt(i));
            }
        }
       // System.out.println(sb1);
       // System.out.println(sb2);
        String decrypted1 = decrypt(sb1.toString());
        String decrypted2 = decrypt(sb2.toString());
        //System.out.println(derypted1);
       // System.out.println(derypted2);
       int j = 0;
        for (int i = 0; i < decrypted1.length(); i++){
            sb3[j] = decrypted1.charAt(i);
            j += 2;
        }
        j = 1;
        for (int i = 0; i < decrypted2.length(); i++){
            sb3[j] = decrypted2.charAt(i);
            j += 2;
        }
        String str = new String(sb3);
        
        return str;
    }
    
  
}

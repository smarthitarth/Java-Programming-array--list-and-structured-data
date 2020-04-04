
/**
 * Write a description of WorldPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;
import java.util.*;
public class WorldPlay {
    public boolean isVowel(char ch){
        String vowels = "aeiou";
       // char c = Character.toLowerCase(ch);
       // String m = toString(c);
       // System.out.println(c);
        for (int i = 0; i < vowels.length(); i++){
            char Char = vowels.charAt(i);
            if (Char == Character.toLowerCase(ch)){
                return true;
            }
        }
        return false;
    }
    public void testIsVowel(){
        System.out.println(isVowel('a'));
        System.out.println(isVowel('A'));
        System.out.println(isVowel('U'));
        System.out.println(isVowel('B'));// + isVowel('b') + isVowel('I') + isVowel('C') );
    }
    public String replaceVowels(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        for (int i= 0; i<phrase.length(); i++){
            char c = sb.charAt(i);
            if(isVowel(c)){
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }
    public void testReplaceVowels(){
        System.out.println(replaceVowels("Hitarth", 'a'));
        System.out.println(replaceVowels("AeIbBc", '*'));
    }
    public String emphasize(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        char c = Character.toLowerCase(ch);;
        for(int i = 0; i<sb.length();i++){
            if(Character.toLowerCase(sb.charAt(i)) == c){
                if(i%2==0){
                    sb.setCharAt(i, '*');
                }else{
                    sb.setCharAt(i, '+');
                }
            }
        }
        return sb.toString();
    }
}

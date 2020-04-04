
/**
 * Write a description of RandomDice here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.Random;
public class RandomDice {
    public void randomDice(int throwDice){
        int [] counter = new int [13];
        Random rand = new Random();
        
        for (int k = 0; k < throwDice; k++){
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            System.out.println(d1 + " + " + d2+" = " + (d1+d2));
            counter[d1+d2]++;
        }
        for (int k = 2; k < 13; k++){
            System.out.println(k + " : " + counter[k]);
        }
    }
}


/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        //ArrayList<LogEntry> record = new ArrayList<LogEntry>();
        for (String s : fr.lines()){
            records.add(WebLogParser.parseEntry(s));
        }
        //records = record;
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIps(){
         ArrayList<String> uniqueIPs = new ArrayList<String> ();
         for (LogEntry le : records){
             String ipadd = le.getIpAddress();
             if (!uniqueIPs.contains(ipadd)){
                 uniqueIPs.add(ipadd);
             }
         }
         return uniqueIPs.size();
     }
     
     public void printlAllHigherThanNum(int num){
         for (LogEntry le : records){
             int sCode = le.getStatusCode();
             if(sCode>num){
                 System.out.println("Status codes higher than " + num + "is: " + sCode );
                 
                }
            }         
     }    
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIp = new ArrayList<String>();
         for (LogEntry le : records){
             Date d = le.getAccessTime();
             String date = d.toString();
            //System.out.println(date.substring(4,10));
            // System.out.println(d);
             String ip = le.getIpAddress();
                // System.out.println(ip);
             if (someday.equals(date.substring(4,10))){
                // ip = le.getIpAddress();
                 //System.out.println("sdfg" + ip);
                 if (!uniqueIp.contains(ip)){
                     uniqueIp.add(ip);
                     
                    }
                }
             
            }
            return uniqueIp;
     }
     
     public int countUniqueIPsInRange(int low, int high){
         int c = 0;
         ArrayList<String> uniqueIp = new ArrayList<String>();
         for (LogEntry le : records){
             int sCode = le.getStatusCode();
             String ip = le.getIpAddress();
             if (!uniqueIp.contains(ip)){
                     uniqueIp.add(ip);
                     if(sCode>=low && sCode<=high){
                   c++;               
                }
                    }
             
            }      
            return c;
        }
        
     public HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String, Integer> count = new HashMap<String, Integer>(); 
         for(LogEntry le : records){
             String ip = le.getIpAddress();
             if(!count.keySet().contains(ip)){
                 count.put(ip, 1);
                }else{
                    count.put(ip, count.get(ip) + 1);
                }
            }
            return count;
        }
        
     public int mostNumberVisitsByIP(HashMap<String, Integer> IPcount){
         int max = 0;
         String [] ips;
         for(String s : IPcount.keySet()){
             int i = IPcount.get(s);
             if (i > max){
                 max = i;
                 
                }
            }
            return max;
        } 
        
        public HashMap<String, ArrayList<String>> iPsForDays(){
            HashMap<String, ArrayList<String>> dateIp = new HashMap<String, ArrayList<String>>();
            for (LogEntry le : records){                
                Date d = le.getAccessTime();
                String date = d.toString();           
                String ip = le.getIpAddress();     
                String date2 = date.substring(4, 10);   
                ArrayList<String> alStr =  new ArrayList<String>();
                if (!dateIp.keySet().contains(date2)){
                    alStr.add(ip);
                    dateIp.put(date2, alStr);
                }else{
                    alStr = dateIp.get(date2);
                    alStr.add(ip);
                    dateIp.put(date2, alStr);
                }
            }
            return dateIp;
        }
        
        public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dateIp, String date){
            ArrayList<String> ips = new ArrayList<String>();
            HashMap<String, Integer> ipCount = new HashMap<String, Integer>();
            //int count = 0;
            for (String s : dateIp.keySet()){
                if(s.equals(date)){
                    ipCount = countVisitsPerIP();
                    int max = mostNumberVisitsByIP(ipCount);
                    for(String sm : ipCount.keySet()){
                        if(max == ipCount.get(sm)){
                            ips.add(sm);
                        }
                    }
                }
            }
            return ips;
        }
        
}

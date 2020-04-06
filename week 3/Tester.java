
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP(){
        LogAnalyzer le = new LogAnalyzer();
        le.readFile("short-test_log");
        System.out.println("Unique IPs: " + le.countUniqueIps());
    }
    
    public void testPrintlAllHigherThanNum(){
        LogAnalyzer le = new LogAnalyzer();
        le.readFile("weblog1_log");
        le.printlAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        
        System.out.println("Unique IPs on day: " + la.uniqueIPVisitsOnDay("Mar 17"));
        //System.out.println("Unique IPs on day: " + la.uniqueIPVisitsOnDay("Sep 14"));
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        System.out.println("Unique IPs in range: " + la.countUniqueIPsInRange(300,399));
    }
    
    public void testCountVisitsPerIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String, Integer> ipCount = la.countVisitsPerIP();
        System.out.println("IP count: " + ipCount);
        int max = la.mostNumberVisitsByIP(ipCount);
        System.out.println("Maximum ip: " + max);
        for(String s: ipCount.keySet()){
            int i = ipCount.get(s);
            if (i == max){
                System.out.println(s);
            }
        }
    }
    
    public void testIPsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String, ArrayList<String>> dateIp = la.iPsForDays();
        System.out.println(dateIp);
        int max = 0;
        String day = "";
        ArrayList<String> ips = new ArrayList<String>();
        for (String s: dateIp.keySet()){
            ArrayList<String> arr = dateIp.get(s);
            if (max < arr.size()){
                max = arr.size();
                day = s;
                ips = arr;
            }
        }
        System.out.println("Highest visited day: " + day);
        System.out.println("Highest visited IPs: " + ips);
    }
    
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        System.out.println(la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Mar 17"));
    }
}

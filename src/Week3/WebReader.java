package Week3;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static Week3.WebLogParser.parseEntry;

public class WebReader {
    public static void main(String[] args) {

        //WebLogParser
        // creates a new Log Entry which can then use my getters
//        LogEntry LE = parseEntry("110.76.104.12 - - [30/Sep/2015:07:47:11 -0400] \"GET //favicon.ico HTTP/1.1\" 200 3426");
//        System.out.println(LE.getIpAddress());
//        System.out.println(LE.getAccessTime());
//        System.out.println(LE.getRequest());
//        System.out.println(LE.getStatusCode());
//        System.out.println(LE.getBytesReturned());
//        System.out.println(LE.getLogInfo());

        //Log Analyzer Code
        LogAnalyzer LA = new LogAnalyzer();
        //                TextHolder/ is the folder that holds all my files now
//        LA.readFile("TextHolder/short-test_log.txt");
        LA.readFile("TextHolder/weblog2_log");
//        LA.printAll();
//        LA.countUniqueIPs();
//        LA.printStatusRange(400);
//        LA.uniqueIPVisitsOnDay("Sep 24");
//        LA.countUniqueIPRange(200,299);

        //Counting Website Visits
//        LA.readFile("TextHolder/weblog2_log");

//        HashMap<String, Integer> counter = LA.countVisitsPerIP();
//        System.out.println(counter + "\nThe number of unique IPs is: " + counter.size());
//
//        HashMap<String, Integer> counter = LA.countVisitsPerIP();
//        int sum = LA.mostVisitsByIP(counter);
//        System.out.println("The highest number of visits by an IP address is " + sum);
//
//        HashMap<String, Integer> counter = LA.countVisitsPerIP();
//        ArrayList<String> IPList = LA.IPsofMostVisits(counter);
//        System.out.println("This is the list of IPs that are tied " +
//                "with " + LA.mostVisitsByIP(counter)+"(The Maximum)" + " visits : ");
//        for (String word : IPList) {
//            System.out.println(word);
//        }

//        HashMap<String,ArrayList<String>> MapOfIPs = LA.IPsForDays();
//        for (String day : MapOfIPs.keySet()){
//            System.out.println(day + "\t" + MapOfIPs.get(day).size());
//        }

//        HashMap<String,ArrayList<String>> MapOfIPs = LA.IPsForDays();
//        String maxDay = LA.dayWithMostIPVisits(MapOfIPs);
//        System.out.println("This is the day with the most IP visits: " + maxDay);

        HashMap<String,ArrayList<String>> MapOfIPs = LA.IPsForDays();
        ArrayList<String> container = LA.IPsWithMostVisitsOnDay(MapOfIPs, "Sep 29");
        for (String IP : container){
            System.out.println(IP);
        }


    }
}

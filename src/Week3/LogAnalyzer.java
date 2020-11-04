package Week3;

import rsc.FileGrabber;

import java.io.File;
import java.util.*;

import static Week3.WebLogParser.parseEntry;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();

    }

    public void readFile(String filename) {
        File file = new File(filename);
        String line;
        try (Scanner sc = new Scanner(new FileGrabber().readFile(file))) {

            while ((line = sc.nextLine()) != null) {
//                System.out.println(line); // works
                LogEntry LE = parseEntry(line);
//                System.out.println(LE.toString()); // this also works
                records.add(LE);
            }
        } catch (Exception ignored) {
        }
    }

    public void printAll() {
        for (LogEntry webLog : records) {
            System.out.println(webLog);
        }
    }

    public void countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddress = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddress)) {
                uniqueIPs.add(ipAddress);
            }
        }
        System.out.println("This is the number of unique IPs found: " + uniqueIPs.size());
        System.out.println("\nThese are all the IPs: ");
        for (String ip : uniqueIPs) {
            System.out.println(ip);
        }
    }

    public void printStatusRange(int num) {
        ArrayList<LogEntry> statusCodes = new ArrayList<LogEntry>();
        for (LogEntry LE : records) {
            int status = LE.getStatusCode();
            if (status >= num) {
                statusCodes.add(LE);
            }
        }
        System.out.println("The number of Log Entries that have a status code above '" + num + "' is \t" + statusCodes.size());
        System.out.println("\nThese are all the Log Entries: ");
        for (LogEntry LE : statusCodes) {
            System.out.println(LE.toString());
        }
    }

    public void uniqueIPVisitsOnDay(String day) {
        ArrayList<String> logs = new ArrayList<String>();
        for (LogEntry LE : records) {
            Date leDate = LE.getAccessTime();
            String str = leDate.toString();
            if (str.contains(day)) {
                String ipAddress = LE.getIpAddress();
                if (!logs.contains(ipAddress)) {
                    logs.add(ipAddress);
                }
            }
        }
        System.out.println("The number of unique IPs: " + logs.size() + " on day: " + day);
        System.out.println("\nThese are all the IPs: ");
        for (String ip : logs) {
            System.out.println(ip);
        }
    }

    public void countUniqueIPRange(int low, int high) {
        ArrayList<String> logs = new ArrayList<String>();
        for (LogEntry LE : records) {
            int leStatus = LE.getStatusCode();
            String ipAddress = LE.getIpAddress();
            if (leStatus >= low && leStatus <= high) {
                if (!logs.contains(ipAddress)) {
                    logs.add(ipAddress);
                }

            }
        }
        System.out.println("The number of logs between " + low + " & " + high + " is: " + logs.size());
        System.out.println("These are the unique IPs: ");
        for (String ip : logs) {
            System.out.println(ip);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry LE : records) {
            String ip = LE.getIpAddress();
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }

    public int mostVisitsByIP(HashMap<String, Integer> counts) {
        int ipCount = 0;
        int currCount = 0;
        for (String ip : counts.keySet()) {
            currCount = counts.get(ip);
            //Which IP get's which count
//            System.out.println(ip +" =\t"+ currCount);
            if (ipCount < currCount) {
                ipCount = currCount;
            }
        }
        return ipCount;
    }

    public ArrayList<String> IPsofMostVisits(HashMap<String, Integer> counts) {
        ArrayList<String> IPsList = new ArrayList<String>();
        int maxCount = mostVisitsByIP(counts);
        for (String ip : counts.keySet()) {
            int currCout = counts.get(ip);
            if (currCout == maxCount) {
                IPsList.add(ip);
            }
        }
        return IPsList;
    }

    public HashMap<String, ArrayList<String>> IPsForDays() {
        HashMap<String, ArrayList<String>> mapOfIPs = new HashMap<String, ArrayList<String>>();
        for (LogEntry LE : records) {
            Date LEDate = LE.getAccessTime();
            String date = LEDate.toString();
            String[] holder = date.split(" ");
            String newDay = holder[1] + " " + holder[2];
            if (date.contains(newDay)) {
                String currLog = LE.getIpAddress();
                if (!mapOfIPs.containsKey(newDay)) {
                    mapOfIPs.put(newDay, (new ArrayList<String>()));

                }
                mapOfIPs.get(newDay).add(currLog);
            }
        }
        return mapOfIPs;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
        String finDay = "";
        int maxCount = 0;
        for (String day : map.keySet()) {
            int currCount = map.get(day).size();
            if (currCount > maxCount) {
                maxCount = currCount;
                finDay = day;
            }
        }
        return finDay;
    }

    public ArrayList<String> IPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String givenDay) {
        ArrayList<String> IPList = new ArrayList<String>();
        int maxVisits = 0;
        var totalVisitsPerDay = countVisitsPerIP(map, givenDay);

        for (String IP : map.get(givenDay)) {

            if (IPList.contains(IP)) continue;

            var numVisitsForIP = totalVisitsPerDay.get(IP);
            if (maxVisits > numVisitsForIP) continue;
            if (maxVisits < numVisitsForIP) {
                IPList.clear();
                maxVisits = numVisitsForIP;
            }
            IPList.add(IP);
        }
        return IPList;
    }

    public HashMap<String, Integer> countVisitsPerIP(HashMap<String, ArrayList<String>> map, String givenDay) {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (String IP : map.get(givenDay)) {
            if (!counts.containsKey(IP)) {
                counts.put(IP, 1);
            } else {
                counts.put(IP, counts.get(IP) + 1);
            }
        }
        return counts;
    }


}

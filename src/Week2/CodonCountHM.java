package Week2;

import rsc.FileGrabber;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class CodonCountHM {
    private HashMap<String, Integer> codonMap;

    public CodonCountHM() {
        codonMap = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna) {
        dna = dna.toUpperCase();
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < dna.length(); i++) {
            char t = dna.charAt(i);
            sb.append(t);
            if (sb.length() % 3 == 0) {
//                System.out.println(sb);
                String codon = sb.toString();
                if (codonMap.containsKey(codon)) {
                    codonMap.put(codon, codonMap.get(codon) + 1);
                } else {
                    codonMap.put(codon, 1);
                }
                sb.delete(0, 3);
            }
        }
//        System.out.println(codonMap.toString());
    }

    public String getMostCommonCodon() {
        int highestCount = 0;
        int currCount = 0;
        String sCount = " ";
        for (String words : codonMap.keySet()) {
            currCount = codonMap.get(words);
            if (currCount > highestCount) {
                highestCount = currCount;
                sCount = words;
            }
        }
        return sCount + " with count of " + highestCount;
    }

    public void printCodonCounts(int start, int end) {
        for (String w : codonMap.keySet()) {
            int occur = codonMap.get(w);
            if (occur >= start && occur <= end) {
                System.out.println(w + "\t" + occur);
            }
        }
    }

}


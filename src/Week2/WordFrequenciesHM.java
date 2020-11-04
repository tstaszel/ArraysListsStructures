package Week2;

import rsc.FileGrabber;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class WordFrequenciesHM {
    private HashMap<String, Integer> myMap;

    public WordFrequenciesHM() {
        myMap = new HashMap<String, Integer>();
    }

    public void countWords(String source) {
        int total = 0;
        try {
            for (String words : FileGrabber.words(FileGrabber.readFile(source))) {
                words = words.toLowerCase();
                if (myMap.containsKey(words)) {
                    myMap.put(words, myMap.get(words) + 1);
                } else {
                    myMap.put(words, 1);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        for (String w : myMap.keySet()) {
            int occur = myMap.get(w);
            if (occur > 200) {
                System.out.println(occur + "\t" + w);
            }
        }
    }

    public void tester(){
        String fileName = "errors.txt";
        countWords(fileName);
    }
}

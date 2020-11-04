package Week2;

import rsc.FileGrabber;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique(File file) throws FileNotFoundException {
        for (String word : FileGrabber.words(FileGrabber.readFile(file))) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }
        }
    }

    private int findIndexOfMax() {
        int currMax = 0;
        int holder = 0;
        for (int i = 0; i < myFreqs.size(); i++) {
            if (currMax < myFreqs.get(i)) {
                currMax = myFreqs.get(i);
                holder = myFreqs.indexOf(currMax);
            }
        }
        return holder;
    }

    public void tester() throws FileNotFoundException {
        File file = new File("errors.txt");
        int maxFreq = 0;
        findUnique(file);
        for (int i = 0; i < myWords.size(); i++) {
            maxFreq = findIndexOfMax();
//            System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));

        }

        System.out.println("The largest word and it's frequency is: '"
                + myWords.get(maxFreq) + "' with " + myFreqs.get(maxFreq)
                + " returns at position "+ (maxFreq+1));

        System.out.println("Unique Words " + myWords.size());

    }
}

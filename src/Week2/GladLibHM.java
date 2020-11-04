package Week2;

import rsc.FileGrabber;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class GladLibHM {
    private HashMap<String, ArrayList<String>> myMap;
    private Random myRandom;
    private ArrayList<String> wordCheck;
    private ArrayList<String> numArrays;
    private int count;

    public GladLibHM() {
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource();
        myRandom = new Random();
        numArrays = new ArrayList<String>();
    }

    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private void initializeFromSource() {
        wordCheck = readIt("blank.txt");
        String[] labels = {"country", "noun", "animal", "adjective",
                "name", "color", "timeframe", "verb", "fruit"};
        for (String name : labels) {
            ArrayList<String> list = readIt(name + ".txt");
            myMap.put(name, list);
        }
    }

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<>();
        try {
            list.addAll(Arrays.asList(FileGrabber.words(FileGrabber.readFile(source))));
        } catch (FileNotFoundException e) {
            System.out.println("test[" + source + "]");
        }
        return list;
    }

    private String getSubstitute(String label) {
        if (label.equals("number")) {
            return "" + myRandom.nextInt(50) + 5;
        }
        return randomFrom(myMap.get(label));
    }

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String replacedWord = "<" + w.substring(first + 1, last) + ">";
        String sub = getSubstitute(w.substring(first + 1, last));
        count++;

        int index = numArrays.indexOf(replacedWord);
        if (index == -1) {
            numArrays.add(replacedWord);
        }

        if (!wordCheck.contains(sub)) {
            wordCheck.add(sub);
        } else {
//            System.out.println(sub);
            return processWord(replacedWord);
        }
        return prefix + sub + suffix;
    }

    private String fromTemplate(String source) {
        StringBuilder story = new StringBuilder();
        try {
            for (String words : FileGrabber.words(FileGrabber.readFile(source))) {
                story.append(processWord(words)).append(" ");
            }
        } catch (FileNotFoundException e) {

            System.out.println("test[" + source + "]");
        }
        return story.toString();
    }

    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }

    public void makeStory() {
        System.out.println("\n");
        File file = new File("madtemplate2.txt");
        String story = fromTemplate(String.valueOf(file));
        printOut(story, 60);
        System.out.println("\nThe number of words replaced is: " + count);
    }

    public void totalWordsInMap() {
        int count = 0;
        for (ArrayList<String> values : myMap.values()) {
            for (int i = 0; i < values.size(); i++) {
                count++;
                System.out.println(count + "\t" + values.get(i));
            }
        }
        System.out.println("This is the number of possible words " + count);
    }

    public void totalWordsConsidered() {
        System.out.println(numArrays.size());
    }

}
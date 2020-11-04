package Week2;

import rsc.FileGrabber;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GladLib {
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    private ArrayList<String> wordCheck;
    private Random myRandom;
    private int count;

    public GladLib() {
        initializeFromSource();
        myRandom = new Random();
    }

    private void initializeFromSource() {
        adjectiveList = readIt("adjective.txt");
        nounList = readIt("noun.txt");
        colorList = readIt("color.txt");
        countryList = readIt("country.txt");
        nameList = readIt("name.txt");
        animalList = readIt("animal.txt");
        timeList = readIt("timeframe.txt");
        verbList = readIt("verb.txt");
        fruitList = readIt("fruit.txt");
        wordCheck = readIt("blank.txt");
    }

    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("country")) {
            return randomFrom(countryList);
        }
        if (label.equals("color")) {
            return randomFrom(colorList);
        }
        if (label.equals("noun")) {
            return randomFrom(nounList);
        }
        if (label.equals("name")) {
            return randomFrom(nameList);
        }
        if (label.equals("adjective")) {
            return randomFrom(adjectiveList);
        }
        if (label.equals("animal")) {
            return randomFrom(animalList);
        }
        if (label.equals("timeframe")) {
            return randomFrom(timeList);
        }
        if (label.equals("verb")) {
            return randomFrom(verbList);
        }
        if (label.equals("fruit")) {
            return randomFrom(fruitList);
        }
        if (label.equals("number")) {
            return "" + myRandom.nextInt(50) + 5;
        }
        return "**UNKNOWN**";
    }

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String holder = "<" + w.substring(first + 1, last) + ">";
        String sub = getSubstitute(w.substring(first + 1, last));
        count++;
        if (!wordCheck.contains(sub)) {
            wordCheck.add(sub);
        } else {
            System.out.println(sub);
            return processWord(holder);
        }
        return prefix + sub + suffix;
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

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<>();
        try {
            list.addAll(Arrays.asList(FileGrabber.words(FileGrabber.readFile(source))));
        } catch (FileNotFoundException e) {
            System.out.println("test[" + source + "]");
        }
        return list;
    }

    public void makeStory() {
        System.out.println("\n");
        File file = new File("madtemplate3.txt");
        String story = fromTemplate(String.valueOf(file));
        printOut(story, 60);
        System.out.println("\nThe number of words replaced is: " + count);
    }


}

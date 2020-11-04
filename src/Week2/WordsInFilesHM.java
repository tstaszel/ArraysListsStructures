package Week2;

import rsc.FileGrabber;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class WordsInFilesHM {
    private HashMap<String, ArrayList<String>> fileMap;
    private ArrayList<String> fileNames;

    public WordsInFilesHM() {
        this.fileMap = new HashMap<String, ArrayList<String>>();
        fileNames = new ArrayList<String>();
    }

    private void addWordsFromFile(File file) {

        try {
            String nameOfFile = file.getName();
            for (String word : FileGrabber.words(FileGrabber.readFile(file))) {
                // if fileMap does NOT contain key, put the word and an empty array into it
                // then move onto next line which will add the file name to the empty array

                if (!fileMap.containsKey(word)) {
                    fileMap.put(word, (new ArrayList<String>()));
                }
                // checking to see if file is already assosicated with the word
                else if(fileMap.get(word).contains(nameOfFile)){
                    continue;
                }
                //because with if & else we had to add the word anyways so we moved it out
                fileMap.get(word).add(nameOfFile);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void buildWordFileMap() {
        FileGrabber fg = new FileGrabber();
        File[] multiFiles = fg.getFiles();
//            creates a FileGrabber called fg that calls the .getFiles()
//            .getFiles() creates an array of files
//            run a for each array item to pull out the files
        for (File eachFile : multiFiles) {
            addWordsFromFile(eachFile);
        }
//        System.out.println(fileMap.toString());
    }

    public int maxNumber() {
        int maxCount = 0;
        for (String key : fileMap.keySet()){
//            System.out.println(key + "\t" + fileMap.get(key).size());
            int currCount = fileMap.get(key).size();
            if (currCount > maxCount){
                maxCount = currCount;
            }

        }
        return maxCount;
    }

    public ArrayList<String> wordsinNumFiles(int value){
        int counter = 0;
        ArrayList<String> holder = new ArrayList<String>();
        for (String key : fileMap.keySet()){

            int currCount = fileMap.get(key).size();
            if (currCount == value){
                counter++;
                holder.add(key);
            }
        }
        System.out.println(holder.size());
        System.out.println(" holder or counter ");
        System.out.println(counter);
        return holder;
    }

    public int wordsinMultiFiles(int value){
        int result = 0;
        for (String key: fileMap.keySet()){
            if (fileMap.get(key).size() == value){
                result++;
            }
        }
        return result;
    }

    public void printFilesIn(String word){
        if (fileMap.containsKey(word))
        {
            System.out.println(fileMap.get(word).toString());
        }
    }

}

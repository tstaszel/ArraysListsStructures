package Week4;

import rsc.FileGrabber;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class VigenereBreaker {


    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        ArrayList<String> listOfStrings = new ArrayList<String>();
        int[] key = new int[klength];
        for (int i = 0; i < klength; i++) {
            String sliced = sliceString(encrypted, i, klength);
            CaesarCracker CC = new CaesarCracker(mostCommon);
            int foundKey = CC.getKey(sliced);
            key[i] = foundKey;
        }
        return key;
    }

    public void breakVigenere() throws FileNotFoundException {
        String VBFile = FileGrabber.readFile(new File("Week4TextFiles/secretmessage1.txt"));
        int[] key = tryKeyLength(VBFile, 4, 'e');
        VigenereCipher VC = new VigenereCipher(key);
        String decrypted = VC.decrypt(VBFile);
        System.out.println(decrypted);
    }

    public HashSet<String> readDictionary(File file) throws FileNotFoundException {
        HashSet<String> HS = new HashSet<String>();
        for (String word : FileGrabber.words(FileGrabber.readFile(file))) {
            HS.add(word.toLowerCase());
        }
        return HS;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        int wordCount = 0;
        String[] wordArray = message.toLowerCase().split("\\W+");
        for (String word : wordArray) {
            if (dictionary.contains(word)) {
                wordCount++;
            }
        }
        return wordCount;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        String decryptedMessage = " ";
        int maxCount = 0;
        int[] trueKey = new int[0];
        char commonChar = mostCommonCharIn(dictionary);
        for (int i = 1; i < 100; i++) {
            int[] testKey = tryKeyLength(encrypted, i, commonChar);
            VigenereCipher VC = new VigenereCipher(testKey);
            String testDecrypt = VC.decrypt(encrypted);
            int wordCount = countWords(testDecrypt, dictionary);
            if (wordCount > maxCount) {
                maxCount = wordCount;
                decryptedMessage = testDecrypt;
                trueKey = testKey;
            }
        }
//        System.out.println("The valid words counted is: " + maxCount);
//        System.out.println("The key length is: \t" + trueKey.length + "\n");
        return decryptedMessage;
    }

    public void breakVigenere2() throws FileNotFoundException {
        String VBFile = FileGrabber.readFile(new File("Week4TextFiles/secretmessage2.txt"));
        File readEnglishDictionary = new File("Week4TextFiles/English");
        HashSet<String> readEnglish = readDictionary(readEnglishDictionary);
        String decryptedMessage = breakForLanguage(VBFile, readEnglish);
        System.out.println(decryptedMessage);
    }

    public void falseBreaker() throws FileNotFoundException {
        String VBFile = FileGrabber.readFile(new File("Week4TextFiles/secretmessage2.txt"));
        File readEnglishDictionary = new File("Week4TextFiles/English");
        HashSet<String> readEnglish = readDictionary(readEnglishDictionary);

        int[] key = tryKeyLength(VBFile, 38, 'e');
        VigenereCipher VC = new VigenereCipher(key);
        String decrypted = VC.decrypt(VBFile);
        int wordCount = countWords(decrypted, readEnglish);
        System.out.println(wordCount);
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        char finChar = 'e';
        int trueMax = 0;
        HashMap<Character, Integer> letterMap = new HashMap<Character, Integer>();
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                char currChar = word.charAt(i);
                if (!letterMap.containsKey(currChar)) {
                    letterMap.put(currChar, 1);
                }
                letterMap.put(currChar, letterMap.get(currChar) + 1);
            }
        }
        for (char Char : letterMap.keySet()) {
            int currMax = letterMap.get(Char);
//            System.out.println(Char + "\t" +letterMap.get(Char));
            if (currMax > trueMax) {
                trueMax = currMax;
                finChar = Char;
            }
        }
        return finChar;
    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        String trueDecrypted = " ";
        String trueLang = " ";
        int trueWordCount = 0;
        for (String currLang : languages.keySet()) {
            HashSet<String> dictionary = languages.get(currLang);
            String currDecrypted = breakForLanguage(encrypted, dictionary);
            int currWordsCounted = countWords(currDecrypted, dictionary);
            if (currWordsCounted > trueWordCount){
                trueDecrypted = currDecrypted;
                trueLang = currLang;
                trueWordCount = currWordsCounted;
            }
        }
        System.out.println(trueDecrypted+"\n");
        System.out.println("The language is: " + trueLang + "\n");
        System.out.println("The word count is: " + trueWordCount + "\n");
    }

    public void breakVigenere3() throws FileNotFoundException {
        String VBFile = FileGrabber.readFile(new File("Week4TextFiles/secretmessage4.txt"));
        String begFile = "Week4TextFiles/";
        ArrayList<String> languages = new ArrayList<String>();
        languages.add("Danish");
        languages.add("Dutch");
        languages.add("English");
        languages.add("French");
        languages.add("German");
        languages.add("Italian");
        languages.add("Portuguese");
        languages.add("Spanish");
        HashMap<String,HashSet<String>> langMap = new HashMap<String, HashSet<String>>();
        for (String word : languages){
            File langFile = new File(begFile + word);
            HashSet<String> dictionary = readDictionary(langFile);
            langMap.put(word,dictionary);
        }
        breakForAllLangs(VBFile,langMap);


    }

}

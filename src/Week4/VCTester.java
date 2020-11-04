package Week4;

import rsc.FileGrabber;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;

public class VCTester {
    public static void main(String[] args) throws FileNotFoundException {
        String processedFile = FileGrabber.readFile(new File("Week4TextFiles/titus-small.txt"));


        //Test Caesar Cipher
//        CaesarCipher CaCi = new CaesarCipher(4);
//        System.out.println("Original Text \n"+processedFile);
//        String encrypt = CaCi.encrypt(processedFile);
//        System.out.println("Encrypted Text \n" + encrypt);

        //Test Caesar Cracker
//        String file5 = FileGrabber.readFile(new File("Week4TextFiles/titus-small_key5.txt"));
//        CaesarCracker CaCr = new CaesarCracker('e');
//        int key = CaCr.getKey(file5);
//        System.out.println("The integer for the key is: " + key);
//        String decrypt = CaCr.decrypt(file5);
//        System.out.println("\n" + decrypt);

        //Test Vigenere Cipher
                            //r  o  m  e
//        int[] key = new int[]{17,14,12,4};
//        VigenereCipher VC = new VigenereCipher(key);
//        String encrypt = "Coal-black is better than another hue";
//        String encrypted = VC.encrypt(encrypt);
//        System.out.println(encrypted+"\n");
//        String decrypted = VC.decrypt(encrypted);
//        System.out.println(decrypted);


        //Test Vigenere Breaker
        VigenereBreaker VB = new VigenereBreaker();
//        String sliceTest = VB.sliceString("abcdefghijklm", 0, 5);
//        System.out.println(sliceTest);

//        String VBFile = FileGrabber.readFile(new File("Week4TextFiles/secretmessage1.txt"));
//        int[] key = VB.tryKeyLength(VBFile,4,'e');
//        System.out.println(Arrays.toString(key));
//
//        VB.breakVigenere();

//        File readFile = new File("Week4TextFiles/testReadFunc.txt");
//        HashSet<String> readHashSet = VB.readDictionary(readFile);
//        for (String word: readHashSet) {
//            System.out.println(word);
//        }

//        File readEnglishDictionary = new File("Week4TextFiles/English");
//        HashSet<String> readEnglish = VB.readDictionary(readEnglishDictionary);
//        String VBFile2 = FileGrabber.readFile(new File("Week4TextFiles/testCountFunc.txt"));
//        int wordCount = VB.countWords(VBFile2,readEnglish);
//        System.out.println("The word count is: " + wordCount);

//        VB.breakVigenere2();

//        VB.falseBreaker();

//        File readDictionary = new File("Week4TextFiles/Portuguese");
//        HashSet<String> readLanguage = VB.readDictionary(readDictionary);
//        char mostChar = VB.mostCommonCharIn(readLanguage);
//        System.out.println("The most common char is: " + mostChar);

        VB.breakVigenere3();

    }
}

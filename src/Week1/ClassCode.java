package Week1;

import Week1.CaesarCipher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Character.*;

public class ClassCode {
    public static void main(String[] args) {
        {
//            testIsVowel();
//            testReplaceVowels();
//            testEmphasize();
//            countDiceRolls(5);
//            System.out.println(simpleScanner("BaseText.txt"));
//            testCountLetters();
//            countShakespeare();
//            testDecrypt();
            testCountWordsLengths();
//            testHalfString();
//            testGetKey();
//            decryptTwoKeys("Mil Bcm mglzz Ojlymnaza edbc ZMzmzmzmzmzmzmzmzmz");
//            testDecryptTwoKeys();
//            dirtyDecrypt();
//
//            try {
//            testEncryptTwoKeys();
//            testEncrypt();
//            } catch (Exception ignored) {
//            }
        }

        {
//            CaesarEncrypt CE = new CaesarEncrypt(23);
//            System.out.println(CE.encrypt("Hello THere"));
        }

    }

    public static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public static void testIsVowel() {
        var A = isVowel('u');
        System.out.println(A);
        var F = isVowel('f');
        System.out.println(F);
    }

    public static String replaceVowels(String st, char ch) {
        StringBuilder sb = new StringBuilder(st);
        for (int i = 0; i < sb.length(); i++) {
            if (isVowel(sb.charAt(i))) {
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }

    public static void testReplaceVowels() {
        var test = replaceVowels("Hello World", '*');
        System.out.println(test);
    }

    public static String emphasize(String st, char ch) {
        StringBuilder sb = new StringBuilder(st.replace(toUpperCase(ch), toLowerCase(ch)));
        for (int i = 0; i < sb.length(); i++) {
            int e = i + 1;
//          This is an inline if                       true 'else' false
            if (sb.charAt(i) == ch) sb.setCharAt(i, e % 2 == 0 ? '+' : '*');
        }
        return sb.toString();
    }

    public static void testEmphasize() {
        var test = emphasize("This tesT nEeds More DestsT", 't');
        System.out.println(test);
    }

    //Caesar Cypher Code

    public static String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = upperAlphabet.toLowerCase();
        String shiftUpperAlphabet = upperAlphabet.substring(key) + upperAlphabet.substring(0, key);
        String shiftLowerAlphabet = lowerAlphabet.substring(key) + lowerAlphabet.substring(0, key);
        for (int i = 0; i < encrypted.length(); i++) {
            char curChar = encrypted.charAt(i);
            if (isUpperCase(curChar)) {
                int idx = upperAlphabet.indexOf(curChar);
                if (idx != -1) {
                    char newChar = shiftUpperAlphabet.charAt(idx);

                    encrypted.setCharAt(i, newChar);
                }
            }
            if (isLowerCase(curChar)) {
                int idx = lowerAlphabet.indexOf(curChar);
                if (idx != -1) {
                    char newChar = shiftLowerAlphabet.charAt(idx);

                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }

    public static void testEncrypt() throws FileNotFoundException {
        int key = 15;
        String line;
//        var file = new Week1.FileGrabber().getFile();
//        System.out.println(file.getName());
        try (Scanner sc = new Scanner(new rsc.FileGrabber().getFile())) {
            while ((line = sc.nextLine()) != null) {
                String encrypted = encrypt(line, key);
                System.out.println(encrypted);
                String decrypted = encrypt(encrypted, (26 - key));
                System.out.println(decrypted);
            }

        }
    }

    public static String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = upperAlphabet.toLowerCase();
        String shift1UpperAlphabet = upperAlphabet.substring(key1) +
                upperAlphabet.substring(0, key1);
        String shift1LowerAlphabet = lowerAlphabet.substring(key1) +
                lowerAlphabet.substring(0, key1);
        String shift2UpperAlphabet = upperAlphabet.substring(key2) +
                upperAlphabet.substring(0, key2);
        String shift2LowerAlphabet = lowerAlphabet.substring(key2) +
                lowerAlphabet.substring(0, key2);

        for (int i = 0; i < encrypted.length(); i++) {
            int e = i + 1;
            boolean isEven = (e % 2 == 0);
            char curChar = encrypted.charAt(i);
            if (isUpperCase(curChar)) {
                int idx = upperAlphabet.indexOf(curChar);
                if (idx != -1) {
                    char newChar = (isEven ? shift2UpperAlphabet : shift1UpperAlphabet).charAt(idx);

                    encrypted.setCharAt(i, newChar);
                }
            }
            if (isLowerCase(curChar)) {
                int idx = lowerAlphabet.indexOf(curChar);
                if (idx != -1) {
                    char newChar = (isEven ? shift2LowerAlphabet : shift1LowerAlphabet).charAt(idx);

                    encrypted.setCharAt(i, newChar);
                }
            }

        }
        return encrypted.toString();

    }

    public static void testEncryptTwoKeys() throws FileNotFoundException {
        int key1 = 2;
        int key2 = 20;
        String line;
        try (Scanner sc = new Scanner(new rsc.FileGrabber().getFile())) {
            while ((line = sc.nextLine()) != null) {
                String encrypted = encryptTwoKeys(line, key1, key2);
                System.out.println(encrypted);
                String decrypt = encryptTwoKeys(encrypted, (26 - key1), (26 - key2));
                System.out.println(decrypt);
            }
        }
    }

    //intro to Arrays
    public static void countDiceRolls(int rolls) {
        Random rand = new Random();
        int[] counts = new int[13];

        for (int i = 0; i < rolls; i++) {
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            System.out.println("Roll is " + d1 + "+" + d2 + "=" + (d1 + d2));
            counts[d1 + d2] += 1;
        }
        for (int i = 2; i <= 12; i++) {
            System.out.println(i + "s=\t" + counts[i] + "\t" + 100.0 * counts[i] / rolls);
        }
    }

    // Word Length code
    public static String simpleScanner(String placeholder) {
        String line;
        try {
            return rsc.FileGrabber.readFile(placeholder);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static String[] getCommon() {
        String[] common = new String[20];
        try {
            var holder = rsc.FileGrabber.readFile("common.txt");
            int index = 0;
            for (String s : holder.replace("\n", " ").split(" ")) {
                common[index] = s;
                index += 1;
            }
        } catch (Exception ignored) {
        }
        return common;
    }

    public static void countWords(File file, String[] common, int[] counts) {
        try {
            for (String word : rsc.FileGrabber.words(rsc.FileGrabber.readFile(file))) {
                word = word.toLowerCase();
                int index = indexOf(common, word);
                if (index != -1) {
                    counts[index] += 1;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public static int indexOf(String[] list, String word) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(word)) {
                return i;
            }
        }
        return -1;
    }

    public static void countShakespeare() {

        String[] plays = {"BaseText.txt", "test2.txt", "caesar.txt", "hamlet.txt", "likeit.txt"};
        String[] common = getCommon();
        int[] counts = new int[common.length];
        for (int i = 0; i < plays.length; i++) {
            File holder = new File(plays[i]);
            countWords(holder, common, counts);
            System.out.println("done with " + plays[i]);

        }
        for (int i = 0; i < common.length; i++) {
            System.out.println(common[i] + "\t" + counts[i]);
        }
    }

    public static int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }

    public static void testCountLetters() {
        String m = "abcdefghifjklmnopqrstuvwxyzabcdefghifjklnopqrstuvwxyz";
        var holder = countLetters(m);
        System.out.println(Arrays.toString(holder));
    }

    public static int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int i = 0; i < vals.length; i++) {
            if (vals[i] > vals[maxDex]) {
                maxDex = i;
            }
        }
        return maxDex;
    }

    public static String decrypt(String encrypted) {
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return CaesarCipher.encrypt(encrypted, 26 - dkey);
    }

    public static void testDecrypt() {
        String encrypted = "Tcs Iwt tastg Vdssthhth lxiw TTttttttttttttttttt";
        String message = decrypt(encrypted);
        System.out.println(message);

    }

    public static void countWordLengths(File file, int[] counts) {
        try {
            for (String word : rsc.FileGrabber.words(rsc.FileGrabber.readFile(file))) {
                int charLength = 0;

                for (int i = 0; i < word.length(); i++) {
                    char ch = Character.toLowerCase(word.charAt(i));
                    if (Character.isLetter(ch)) charLength++;
                }
                counts[charLength]++;
            }
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] == 0) continue;
                System.out.println("There are " + counts[i] + " words of \t" + (i + 1));
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public static void testCountWordsLengths() {
        int[] counts = new int[31];
        File file = new File("manywords.txt");
        countWordLengths(file, counts);
    }

   public static String halfOfString(String message, int start){
        StringBuilder sb = new StringBuilder(message);
        StringBuilder holder = new StringBuilder();
       for (int i = start; i < sb.length(); i+=2) {
           char newChar = sb.charAt(i);
            if(Character.isLetter(newChar)){
                holder.append(sb.charAt(i));
            }

       }
       return holder.toString();
   }

   public static void testHalfString(){
                                        // H l o W r d
        String half = halfOfString("Hello World", 0);
       System.out.println(half);
   }

   public static int getKey(String s){
       int[] freqs = countLetters(s);
       int maxDex = maxIndex(freqs);
       int dkey = maxDex - 4;
       if (maxDex < 4) {
           dkey = 26 - (4 - maxDex);
       }
       return dkey;
   }

   public static void testGetKey(){
        int key = getKey("Tcs Iwt tastg Vdssthhth lxiw TTttttttttttttttttt");
       System.out.println("This is the key " + key);
   }

   public static String decryptTwoKeys(String encrypted){
        String half1 = halfOfString(encrypted,0);
        String half2 = halfOfString(encrypted,1);
//       System.out.println("String 1: " + half1 + "\t String 2: " + half2);
        int key1 = getKey(half1);
        int key2 = getKey(half2);
       System.out.println("The keys are : " + key1 + "\t" + key2);
       String decrypt = encryptTwoKeys(encrypted, (26 - key1), (26 - key2));
//       System.out.println(decrypt);
       return decrypt;
   }

   public static void testDecryptTwoKeys(){

        String decrypt = decryptTwoKeys("Xifqvximt tsdtlxzrx iijirvtl ek Uybi afvbw yehvv xyi");
       System.out.println(decrypt);
   }

}

package Week1;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

public class CaesarCipherTwo {
    private String upperAlphabet;
    private String lowerAlphabet;
    private String shiftedUpperAlphabet1;
    private String shiftedLowerAlphabet1;
    private String shiftedUpperAlphabet2;
    private String shiftedLowerAlphabet2;
    private int keyAlpha;
    private int keyOmega;



    public CaesarCipherTwo(int key1, int key2){
        upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        lowerAlphabet = upperAlphabet.toLowerCase();
        shiftedUpperAlphabet1 = upperAlphabet.substring(key1) +
                upperAlphabet.substring(0, key1);
        shiftedLowerAlphabet1 = lowerAlphabet.substring(key1) +
                lowerAlphabet.substring(0, key1);
        shiftedUpperAlphabet2 = upperAlphabet.substring(key2) +
                upperAlphabet.substring(0, key2);
        shiftedLowerAlphabet2 = lowerAlphabet.substring(key2) +
                lowerAlphabet.substring(0, key2);
        keyAlpha = key1;
        keyOmega = key2;
    }

    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            int e = i + 1;
            boolean isEven = (e % 2 == 0);
            char curChar = encrypted.charAt(i);
            if (isUpperCase(curChar)) {
                int idx = upperAlphabet.indexOf(curChar);
                if (idx != -1) {
                    char newChar = (isEven ? shiftedUpperAlphabet2 : shiftedUpperAlphabet1).charAt(idx);

                    encrypted.setCharAt(i, newChar);
                }
            }
            if (isLowerCase(curChar)) {
                int idx = lowerAlphabet.indexOf(curChar);
                if (idx != -1) {
                    char newChar = (isEven ? shiftedLowerAlphabet2 : shiftedLowerAlphabet1).charAt(idx);

                    encrypted.setCharAt(i, newChar);
                }
            }

        }
        return encrypted.toString();
    }
    public String cheatBreak(String input){
        CaesarCipherTwo CCT = new CaesarCipherTwo((26-keyAlpha),(26-keyOmega));
        return CCT.encrypt(input);
    }

    private   int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int i = 0; i < vals.length; i++) {
            if (vals[i] > vals[maxDex]) {
                maxDex = i;
            }
        }
        return maxDex;
    }
    private   String halfOfString(String message, int start){
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
    private   int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }
    private   int[] countLetters(String message) {
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
    public String breakCaesarCipherTwo(String encrypted){
        String half1 = halfOfString(encrypted,0);
        String half2 = halfOfString(encrypted,1);
        int key1 = getKey(half1);
        int key2 = getKey(half2);
        System.out.println("The keys are : " + key1 + "\t" + key2);
        CaesarCipherTwo CCT = new CaesarCipherTwo((26-key1),(26-key2));
        return CCT.encrypt(encrypted);
    }

}

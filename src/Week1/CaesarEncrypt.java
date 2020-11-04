package Week1;

import java.io.FileNotFoundException;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

public class CaesarEncrypt {
    private String upperAlphabet;
    private String lowerAlphabet;
    private String shiftedUpperAlphabet;
    private String shiftedLowerAlphabet;
    private int mainKey;
    private int[]counts;

    //This is a constructor it runs when new is called
    public CaesarEncrypt(int key){
        upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        lowerAlphabet = upperAlphabet.toLowerCase();
        shiftedUpperAlphabet = upperAlphabet.substring(key)
                + upperAlphabet.substring(0,key);
        shiftedLowerAlphabet = lowerAlphabet.substring(key)
                + lowerAlphabet.substring(0,key);
        mainKey = key;
    }

    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        for (int i=0;i<sb.length(); i++){
            char ch = sb.charAt(i);
            if (isUpperCase(ch)){
                int idx = upperAlphabet.indexOf(ch);
                if (idx != -1){
                    ch = shiftedUpperAlphabet.charAt(idx);
                    sb.setCharAt(i,ch);
                }
            }
            if (isLowerCase(ch)){
                int idx = lowerAlphabet.indexOf(ch);
                if (idx != -1){
                    ch = shiftedLowerAlphabet.charAt(idx);
                    sb.setCharAt(i,ch);
                }
            }
        }
        return sb.toString();
    }

    public String decrypt(String input){
        CaesarEncrypt ce = new CaesarEncrypt(26- mainKey);
        return ce.encrypt(input);
    }

    private int[] countLetters(String input){
        counts = new int[26];
        for (int i=0;i<input.length();i++){
            char ch = Character.toLowerCase(input.charAt(i));
            int dex = lowerAlphabet.indexOf(ch);
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    private   int maxIndex(int[] values) {
        int maxDex = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[maxDex]) {
                maxDex = i;
            }
        }
        return maxDex;
    }
    public String breakCaesarEncrypt(String encrypted) throws FileNotFoundException {
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        CaesarEncrypt CE = new CaesarEncrypt(26-dkey);
        return CE.encrypt(encrypted);
    }



}

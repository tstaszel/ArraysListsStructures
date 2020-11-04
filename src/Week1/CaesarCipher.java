package Week1;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

public class CaesarCipher {

    public static void main(String[] args) {
        testEncrypt();
    }

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

    public static void testEncrypt() {
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

        } catch (Exception ignored) {
        }
    }

}

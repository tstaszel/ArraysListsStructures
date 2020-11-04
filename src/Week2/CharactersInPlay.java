package Week2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CharactersInPlay {
    private ArrayList<String> charNames;
    private ArrayList<Integer> numChars;

    public CharactersInPlay() {
        charNames = new ArrayList<String>();
        numChars = new ArrayList<Integer>();
    }

    private void update(String person) {
        int index = charNames.indexOf(person);
        if (index == -1) {
            charNames.add(person);
            numChars.add(1);
        } else {
            int value = numChars.get(index);
            numChars.set(index, value + 1);
        }
    }
    private void findAllCharacter(File file) {
        String line;
        try (Scanner sc = new Scanner(new File(String.valueOf(file)))) {
            while ((line = sc.nextLine()) != null) {
//               System.out.println(line);
                int dex = line.indexOf(".");
                if (dex != -1) {
                    line = line.substring(0, dex);
                    update(line);
                }
            }
        } catch (Exception ignored) { }
    }

    private void charsWithNumParts(int num1, int num2){
        for (int i = 0; i < charNames.size(); i++) {
        if ((numChars.get(i) >= num1) && (numChars.get(i)<= num2)){
                System.out.println(charNames.get(i) + "\t" + numChars.get(i));
            }
        }
    }
    public void tester() throws FileNotFoundException {
        File file = new File("errors.txt");
        int maxFreq = 0;
        findAllCharacter(file);
        for (int i = 0; i < charNames.size(); i++) {
            System.out.println(charNames.get(i) + "\t" + numChars.get(i));
        }
        System.out.println("");
        // set okay min, but ridiculous upper for the first run through
        charsWithNumParts(10,15);
    }


}

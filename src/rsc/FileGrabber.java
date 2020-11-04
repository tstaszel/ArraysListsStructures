package rsc;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
// THIS IS NOT MY CODE, MY FRIEND CREATED THIS FOR ME

public class FileGrabber extends JPanel {
    public String startDir = "./";

    public FileGrabber() {
    }

    public FileGrabber(String startDir) {
        this.startDir = startDir;
    }

    public FileGrabber(File startDir) {
        this.startDir = startDir.getPath();
    }

    public File getFile(){
        return getFiles()[0];
    }

    public File[] getFiles(){
        var fc = new JFileChooser(startDir);
        fc.setMultiSelectionEnabled(true);
        fc.showOpenDialog(this);
        return fc.getSelectedFiles();
    }

    public File getDir() {
        return getDirs()[0];
    }

    public File[] getDirs() {
        var fc = new JFileChooser(startDir);
        fc.setMultiSelectionEnabled(true);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.showOpenDialog(this);
        return fc.getSelectedFiles();
    }

    public static void main(String[] args) {
        var tst = new FileGrabber();
        System.out.println("file: " + tst.getFile().getName());
    }

    public static String readFile(String directory) throws FileNotFoundException {
        var dir = new File(directory);
        if (dir.exists()) return readFile(dir);
        else throw new FileNotFoundException();
    }

    public static String readFile(File directory) throws FileNotFoundException {
        if (!directory.exists()) throw new FileNotFoundException();
        var scanner = new Scanner(directory);
        var sb = new StringBuilder();
        while (scanner.hasNext()) sb.append(scanner.nextLine()).append("\n");
        var index = sb.lastIndexOf("\n");
        sb.replace(index, index + 1, "");
        return sb.toString();
    }
    public static String[] words(String string) {
        var sArr = new ArrayList<>(Arrays.asList(string.replace("\n", " ").split(" ")));
        sArr.removeAll(Collections.singletonList(""));
        return sArr.toArray(new String[]{});
    }

}

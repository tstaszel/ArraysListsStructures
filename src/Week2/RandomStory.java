package Week2;

import rsc.FileGrabber;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class RandomStory {
    private ArrayList<String> myWords;

    public static void main(String[] args) throws FileNotFoundException {
//        WordFrequencies wf = new WordFrequencies();
//        wf.tester();

//        CharactersInPlay cip = new CharactersInPlay();
//        cip.tester();
//
//        GladLib gl = new GladLib();
//        gl.makeStory();
//
//        WordFrequenciesHM wfHM = new WordFrequenciesHM();
//        wfHM.tester();
//
//        GladLibHM glHM = new GladLibHM();
//        glHM.makeStory();
//        System.out.println("\n");
//        glHM.totalWordsConsidered();
//
//        CodonCountHM ccHM = new CodonCountHM();
//        String dna = "CAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCTAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCCAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATC";
//        ccHM.buildCodonMap(0, dna);
//        System.out.println("This is the most common codon: " +
//        ccHM.getMostCommonCodon());
//        ccHM.printCodonCounts(5,7);

        WordsInFilesHM wifHM = new WordsInFilesHM();
        wifHM.buildWordFileMap();
//        int count = wifHM.maxNumber();
//        System.out.println("The highest count is " + count);
////        ArrayList<String> words =  wifHM.wordsinNumFiles(4);
////        System.out.println("The words that match the given value are " + words);
        wifHM.printFilesIn("tree");
//        int numWords = wifHM.wordsinMultiFiles(4);
//        System.out.println(numWords);

    }
}

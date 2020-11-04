package Week1;

import java.io.FileNotFoundException;

public class Tests {
    public static void main(String[] args) throws FileNotFoundException {
        {//Testing Base Caesar Encryption
//            int key = 15;
//            var baseText = FileGrabber.readFile("BaseText.txt");
//            var encryptedMessage = FileGrabber.readFile("SingleKeyEncrypt.txt");
//
//            CaesarEncrypt CE = new CaesarEncrypt(key);
//            var testEncryptCe = CE.encrypt(baseText);
//            System.out.println(testEncryptCe);
////            var testDecryptCe = CE.decrypt(testEncryptCe);
////            System.out.println(testDecryptCe);
//            var testBreakCaesarEncrypt = CE.breakCaesarEncrypt(testEncryptCe);
//            System.out.println(testBreakCaesarEncrypt);

        }
        {//Testing Two Key Encryption
            int key1 = 14;
            int key2= 24;
            var baseText = rsc.FileGrabber.readFile("BaseText.txt");
            var twoKeyEncrypted = rsc.FileGrabber.readFile("TwoKeyEncryption.txt");
            CaesarCipherTwo CCT = new CaesarCipherTwo(key1,key2);
//            var testCCT = CCT.encrypt(baseText);
//            System.out.println(testCCT);
//            var cheatBreakCCT = CCT.cheatBreak(twoKeyEncrypted);
//            System.out.println(cheatBreakCCT);
            var testCCTbreaker = CCT.breakCaesarCipherTwo(twoKeyEncrypted);
            System.out.println(testCCTbreaker);


        }
    }
}

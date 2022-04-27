/**
 * long description for the file
 *
 * @summary short description for the file
 * @author Minh Phung
 *
 * Created at     : 2022-04-06 19:08:07 
 * Last modified  : 2022-04-06 19:09:39
 */

package EncryptionSample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class testencrypt {
    /**
     * write to a file using the BufferedWriter
     * 
     * @param s the string to add to the file
     * @throws IOException
     */
    private static void BufferedWriterToFile(String s, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(s);
        writer.close();
    }

    /**
     * Append text to a file uisng the bufferedWriter
     * 
     * @param s the string of text to append
     * @throws IOException
     */
    private static void BufferedWriterAppend(String s, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.append(s);
        writer.close();
    }

    /**
     * Read from a file using the BufferedReader
     * 
     * @return All the text from the file as a single string
     * @throws IOException
     */
    private static String BufferedReaderFromFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuffer sb = new StringBuffer();
        String rtn = br.readLine();
        while (null != rtn) {
            sb.append(rtn);
            rtn = br.readLine();
        }
        br.close();
        return sb.toString();
    }

    /**
     * Write to a file using the fileoutputstream
     * 
     * @param s the string to write to the stream
     * @throws IOException
     */
    private static void FileOutputStreamToFile(byte[] s, String fileName) throws IOException {
        FileOutputStream fso = new FileOutputStream(fileName);
        fso.write(s);
        fso.close();
    }

    /**
     * Read a stream of text from a fileinputstream
     * 
     * @throws IOException
     */
    private static byte[] FileInputStreamFromFile(String fileName) throws IOException {
        StringBuffer sb = new StringBuffer();
        FileInputStream fsi = new FileInputStream(fileName);
        byte[] strBytes = fsi.readAllBytes();
        sb.append(strBytes);
        fsi.close();
        return strBytes;
    }

    /**
     * Write to a file using the File utility methods.
     * 
     * @param s the string to write to the file.
     * @throws IOException
     */
    private static void FileToFile(String s, String fileName) throws IOException {
        Path path = Paths.get(fileName);
        byte[] strToBytes = s.getBytes();
        Files.write(path, strToBytes);
    }

    /**
     * Read from a file using the file utility methods
     * 
     * @return the contents of the file as a string
     * @throws IOException
     */
    private static String FileFromFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        List<String> rtnStrings = Files.readAllLines(path);
        StringBuffer rtnString = new StringBuffer();
        for (String string : rtnStrings) {
            rtnString.append(string);
        }
        return rtnString.toString();
    }

    public static void EncryptThis() {
        String fileName = "testingEnc.txt";
        TextEncrypt t = new TextEncrypt();
        String testing = "abcdefghijklmnopqrstuvwxyz";
        byte[] encoded = t.encrypt(testing);
        try {
            // write the string to a file
            FileOutputStreamToFile(encoded, fileName);

            System.out.println(new String(FileInputStreamFromFile(fileName)));

            // now read the data from the file and decrypt it
            String decoded = t.decrypt(FileInputStreamFromFile(fileName));
            System.out.println(decoded);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * the main entry point for the test.
     * 
     * @param args
     */
    public static void main(String[] args) {
        String fileName = "testFile.txt";
        String textToTest = "abcdefghijklmnopqrstuvwxyz";
        /*
         * try {
         * 
         * FileOutputStreamToFile(textToTest.getBytes(), "FIS-"+fileName);
         * System.out.println (new String (FileInputStreamFromFile("FIS-"+fileName)));
         * 
         * BufferedWriterToFile(textToTest, "BW-"+fileName);
         * BufferedWriterAppend("more stuff", "BW-"+fileName);
         * System.out.println (BufferedReaderFromFile("BW-"+fileName));
         * 
         * FileToFile(textToTest, "F2F-"+fileName);
         * System.out.println (FileFromFile("F2F-"+fileName));
         */
        EncryptThis();

        // } catch (IOException e) {
        // System.out.println (e.getMessage());
        // }
    }
}
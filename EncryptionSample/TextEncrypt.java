/**
 * long description for the file
 *
 * @summary short description for the file
 * @author Minh Phung
 *
 * Created at     : 2022-04-06 19:01:53 
 * Last modified  : 2022-04-06 19:09:38
 */

package EncryptionSample;

import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * This class shows a simple encrytion decryption implementation
 */
public class TextEncrypt {
    /**
     * Write to a file using the fileoutputstream
     * 
     * @param s the string to write to the stream
     * @throws IOException
     */

    // change the method to static
    public static void FileOutputStreamToFile(byte[] s, File fileName) throws IOException {
        FileOutputStream fso = new FileOutputStream(fileName);
        fso.write(s);
        fso.close();
    }

    /**
     * Read a stream of text from a fileinputstream
     * 
     * @throws IOException
     */
    public byte[] FileInputStreamFromFile(File fileName) throws IOException {
        StringBuffer sb = new StringBuffer();
        FileInputStream fsi = new FileInputStream(fileName);
        byte[] strBytes = fsi.readAllBytes();
        sb.append(strBytes);
        fsi.close();
        return strBytes;
    }

    /**
     * this is where to devise the "key" to encrypt/decrypt with
     * 
     * @return the "key"
     */
    private int cypherKey() {
        return 7;
    }
    // Change the value of the cypherKey to a number within the range of 0 and 26

    // @Override --> Remove the override
    public byte[] encrypt(final String inBuffer) {
        final byte[] inChars = inBuffer.getBytes();
        final StringBuffer sb = new StringBuffer();

        for (int i = 0; i < inChars.length; i++) {
            int ascii = (int) inChars[i];
            ascii = ascii + cypherKey();
            final String formatted = String.format("%03d", ascii);
            sb.append(formatted);
        }
        /*
         * //the key needs to be 16 bytes
         * return (encryptBy("This class is IT", "AES", "AES", inBuffer));
         */return sb.toString().getBytes();
    }

    // @Override --> Remove the override
    public String decrypt(byte[] inBuffer) {
        final StringBuffer sb = new StringBuffer();
        final char inChars[] = new String(inBuffer).toCharArray();
        for (int i = 0; i < inChars.length; i = i + 3) {
            final char hundreds = inChars[i];
            final char tens = inChars[i + 1];
            final char ones = inChars[i + 2];
            final StringBuffer s = new StringBuffer();
            s.append(hundreds);
            s.append(tens);
            s.append(ones);
            final char x = (char) (Integer.parseInt(s.toString()) - cypherKey());
            sb.append(x);
        }

        // sb.append(decryptBy("This class is IT", "AES", "AES", inBuffer));
        return sb.toString();
    }

    public String encrypt64(String inBuffer) {
        byte[] strBytes = Base64.getEncoder().encode(inBuffer.getBytes());
        return new String(strBytes);
    }

    public String decrypt64(String inBuffer) {
        byte[] strBytes = Base64.getDecoder().decode(inBuffer);
        return new String(strBytes);
    }

    /**
     * this will encrypt with the algorithm that you send in.
     * reference:
     * https://docs.oracle.com/javase/7/docs/technotes/guides/security/crypto/CryptoSpec.html#AppA
     * 
     * @param key
     * @param algorithm
     * @param transformation
     * @param inBuffer
     * @return
     */
    public byte[] encryptBy(String key, String algorithm, String transformation, String inBuffer) {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), algorithm);
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] rtnBytes = cipher.doFinal(inBuffer.getBytes());
            return rtnBytes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String decryptBy(String key, String algorithm, String transformation, byte[] inBuffer) {
        try {
            int buffer = inBuffer.length % 16;
            int bufferSize = inBuffer.length;
            Key secretKey = new SecretKeySpec(key.getBytes(), algorithm);
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            // the buffer needs to be rounded to a 16byte boundary for AES.
            byte[] inBytes = new byte[bufferSize + (buffer > 0 ? 16 - buffer : 0)];
            System.arraycopy(inBuffer, 0, inBytes, 0, inBuffer.length);
            byte[] rtnBytes = cipher.doFinal(inBytes);
            return new String(rtnBytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

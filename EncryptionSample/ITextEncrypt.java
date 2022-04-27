package EncryptionSample;

public interface ITextEncrypt {
    /**
     * the call to encrypt the string
     * 
     * @param inBuffer
     * @return the encrypted string
     */
    public byte[] encrypt(String inBuffer);

    /**
     * the call to decrypt the string
     * 
     * @param inBuffer
     * @return the plain text string
     */
    public String decrypt(byte[] inBuffer);
}

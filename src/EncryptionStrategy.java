/**
 * Defines a common contract for encryption and decryption strategies.
 */
public interface EncryptionStrategy {

    /**
     * Encrypts the provided byte data.
     */
    EncryptedPayload encrypt(byte[] data) throws Exception;

    /**
     * Decrypts an encrypted payload back into its original byte data.
     */
    byte[] decrypt(EncryptedPayload payload) throws Exception;
}
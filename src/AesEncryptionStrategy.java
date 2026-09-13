import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;

/**
 * Implements encryption and decryption using AES-GCM.
 */
public class AesEncryptionStrategy implements EncryptionStrategy {

    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int KEY_SIZE = 256;
    private static final int IV_LENGTH = 12;
    private static final int TAG_LENGTH = 128;

    // The same key is used for both encryption and decryption.
    private final SecretKey key;

    // Generates secure random values for initialization vectors.
    private final SecureRandom secureRandom;

    /**
     * Creates a new AES strategy with a generated secret key.
     */
    public AesEncryptionStrategy() throws Exception {
        this.key = generateKey();
        this.secureRandom = new SecureRandom();
    }

    /**
     * Encrypts raw byte data using AES-GCM.
     */
    @Override
    public EncryptedPayload encrypt(byte[] data) throws Exception {
        byte[] iv = generateIv();

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        GCMParameterSpec specification =
                new GCMParameterSpec(TAG_LENGTH, iv);

        cipher.init(
                Cipher.ENCRYPT_MODE,
                key,
                specification
        );

        byte[] ciphertext = cipher.doFinal(data);

        return new EncryptedPayload(
                ciphertext,
                iv
        );
    }

    /**
     * Decrypts an AES-GCM payload back into its original byte data.
     */
    @Override
    public byte[] decrypt(EncryptedPayload payload) throws Exception {

        /*
         * TODO:
         *
         * - Use the SAME AES key stored in this object.
         * - Use payload.iv() to recreate the GCM parameters.
         * - Initialize the Cipher in DECRYPT_MODE.
         * - Decrypt payload.ciphertext().
         * - Return the original plaintext bytes.
         *
         * Do NOT generate a new key or IV here.
         */

        throw new UnsupportedOperationException(
                "Decryption will be implemented separately"
        );
    }

    /**
     * Generates a new AES secret key.
     */
    private SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator =
                KeyGenerator.getInstance("AES");

        keyGenerator.init(KEY_SIZE);

        return keyGenerator.generateKey();
    }

    /**
     * Generates a new initialization vector for encryption.
     */
    private byte[] generateIv() {
        byte[] iv = new byte[IV_LENGTH];

        secureRandom.nextBytes(iv);

        return iv;
    }
}
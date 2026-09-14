import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Runs the encryption and decryption demonstration.
 */
public class Main {

    /**
     * Starts the program.
     */
    public static void main(String[] args) throws Exception {

        /*
         * TODO: Partner demo / output
         *
         * 1. Create one AesEncryptionStrategy object.
         * 2. Create a sample message.
         * 3. Convert the message to byte[].
         * 4. Encrypt the data.
         * 5. Decrypt it using the SAME strategy object.
         * 6. Print the original, encrypted, decrypted, and PASS/FAIL result.
         */
        try {
            AesEncryptionStrategy strategy = new AesEncryptionStrategy();

            String message = "Hello CSC 5100";
            byte[] data = message.getBytes(StandardCharsets.UTF_8);

            EncryptedPayload encrypted = strategy.encrypt(data);
            byte[] decrypted = strategy.decrypt(encrypted);

            String encryptedText =
                    Base64.getEncoder().encodeToString(encrypted.ciphertext());
            String decryptedText =
                    new String(decrypted, StandardCharsets.UTF_8);

            printResult(message, encryptedText, decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Result: FAIL");
        }
    }
    private static void printResult(
            String original,
            String encrypted,
            String decrypted
    ) {
        System.out.println("Original: " + original);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        System.out.println(
                "Result: " + (original.equals(decrypted) ? "PASS" : "FAIL")
        );
    }
}
/**
 * Stores encrypted data and the initialization vector needed for decryption.
 */
public record EncryptedPayload(
        byte[] ciphertext,
        byte[] iv
) {
}
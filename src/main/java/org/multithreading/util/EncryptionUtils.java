package org.multithreading.util;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import lombok.extern.log4j.Log4j2;
import org.multithreading.config.KeystoreConfig;
import org.multithreading.constants.EncryptionConstants;
import org.multithreading.constants.PathConstants;

/**
 * The type Encryption utils.
 */
@Log4j2
public class EncryptionUtils {

  /**
   * The constant PASSWORD.
   */
  private static final String PASSWORD = KeystoreConfig.getInstance().getPassword();

  /**
   * The constant key.
   */
  private static SecretKey key = loadKey();

  /**
   * Generate key secret key.
   *
   * @param encryptionType the encryption type
   * @return the secret key
   */
  private static SecretKey generateKey(final String encryptionType) {
    try {
      final KeyGenerator keyGenerator = KeyGenerator.getInstance(encryptionType);
      return keyGenerator.generateKey();
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * Write key to key store.
   *
   * @throws Exception the exception
   */
  private static void writeKeyToKeyStore() {
    try {
      final File file = new File(PathConstants.KEYSTORE_PATH);
      final KeyStore javaKeyStore = KeyStore.getInstance(EncryptionConstants.KEYSTORE_TYPE);
      if (!file.exists()) {
        javaKeyStore.load(null, null);
      }
      javaKeyStore.setKeyEntry(EncryptionConstants.ALIAS, key, PASSWORD.toCharArray(), null);
      final OutputStream write = Files.newOutputStream(Paths.get(PathConstants.KEYSTORE_PATH));
      javaKeyStore.store(write, PASSWORD.toCharArray());
    } catch (Exception e) {
      log.error(e.getMessage());
    }

  }

  /**
   * Load key secret key.
   *
   * @return the secret key
   */
  private static SecretKey loadKey() {
    try {
      final KeyStore keyStore = KeyStore.getInstance(EncryptionConstants.KEYSTORE_TYPE);
      final InputStream inputStream = Files.newInputStream(Paths.get(PathConstants.KEYSTORE_PATH));
      keyStore.load(inputStream, PASSWORD.toCharArray());
      return (SecretKey) keyStore.getKey(EncryptionConstants.ALIAS, PASSWORD.toCharArray());
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * Encrypt string string.
   *
   * @param plainText the plain text
   * @return the string
   */
  public static String encryptString(final String plainText) {
    try {
      final byte[] byteText = plainText.getBytes(EncryptionConstants.FORMAT);
      final Cipher cipher = Cipher.getInstance(EncryptionConstants.ALGORITHM);
      cipher.init(Cipher.ENCRYPT_MODE, key);
      return new String(Base64.getEncoder().encode(cipher.doFinal(byteText)));
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * Decrypt string string.
   *
   * @param encryptedText the encrypted text
   * @return the string
   */
  public static String decryptString(final String encryptedText) {
    try {
      final byte[] byteText = Base64.getDecoder()
          .decode(encryptedText.getBytes(EncryptionConstants.FORMAT));
      final Cipher cipher = Cipher.getInstance(EncryptionConstants.ALGORITHM);
      cipher.init(Cipher.DECRYPT_MODE, key);
      return new String(cipher.doFinal(byteText));
    } catch (Exception e) {
      return null;
    }
  }
}

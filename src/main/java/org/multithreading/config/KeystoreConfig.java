package org.multithreading.config;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.multithreading.constants.PathConstants;
import org.yaml.snakeyaml.Yaml;

/**
 * The type Keystore config.
 */
@Getter
@Log4j2
@SuppressWarnings("unchecked")
public final class KeystoreConfig {
  /**
   * The Password.
   */
  private String password;
  /**
   * The constant INSTANCE.
   */
  private static final KeystoreConfig INSTANCE = new KeystoreConfig();

  /**
   * Instantiates a new Keystore config.
   */
  private KeystoreConfig() {
    try {
      final Object obj = new Yaml()
          .load(Files.newInputStream(Paths.get(PathConstants.APPLICATION_YML)));
      final Map<String, Map<String, String>> data = (Map) obj;
      password = data.get("keystore").get("password");
    } catch (Exception e) {
      log.error("Application YML reading failed");
    }
  }

  /**
   * Get instance keystore config.
   *
   * @return the keystore config
   */
  public static KeystoreConfig getInstance() {
    return INSTANCE;
  }
}

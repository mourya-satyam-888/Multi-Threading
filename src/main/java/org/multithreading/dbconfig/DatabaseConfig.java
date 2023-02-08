package org.multithreading.dbconfig;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.multithreading.constants.PathConstants;
import org.yaml.snakeyaml.Yaml;

/**
 * The type Database config.
 */
@Log4j2
@Getter
@SuppressWarnings("unchecked")
public class DatabaseConfig {
  /**
   * The constant url.
   */
  private String url;
  /**
   * The constant username.
   */
  private String username;
  /**
   * The constant password.
   */
  private String password;

  /**
   * Instantiates a new Database config.
   */
  public DatabaseConfig() {
    try {
      final Object obj = new Yaml()
          .load(Files.newInputStream(Paths.get(PathConstants.APPLICATION_YML)));
      final Map<String, String> data = (Map) obj;
      url = data.get("url");
      username = data.get("username");
      password = data.get("password");
    } catch (Exception e) {
      log.error("Application YML reading failed");
    }
  }
}

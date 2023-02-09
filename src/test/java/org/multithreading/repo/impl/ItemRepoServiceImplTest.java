package org.multithreading.repo.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.multithreading.config.DatabaseConfig;
import org.multithreading.util.EncryptionUtils;
import org.multithreading.handler.DatabaseHandler;
import org.multithreading.repo.ItemRepoService;

class ItemRepoServiceImplTest {
  @Disabled
  @Test
  void generateDB() {
    try {
      DatabaseConfig databaseConfig = new DatabaseConfig();
      String query = "insert into item values(?,?,?,?)";
      Connection con = DriverManager.getConnection(databaseConfig.getUrl(),
          databaseConfig.getUsername(), databaseConfig.getPassword());
      List<String> name = Arrays.asList("item");
      List<String> type = Arrays.asList("RAW");
      for (int i = 0; i < 92; i++) {
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, EncryptionUtils.encryptString(name.get(0) + (i + 8)));
        ps.setString(2, type.get(0));
        ps.setDouble(3, 100);
        ps.setInt(4, 2);
        ps.execute();
      }
      con.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Disabled
  @Test
  void dryRun() {
    ItemRepoService itemRepoService = new ItemRepoServiceImpl(
        new DatabaseHandler(new DatabaseConfig()));
    try {
      while (true) {
        System.out.println(itemRepoService.getNextItem());
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
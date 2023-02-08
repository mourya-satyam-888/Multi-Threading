package org.multithreading.handler.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.multithreading.dbconfig.DatabaseConfig;
import org.multithreading.encryptions.EncryptionUtils;

class DatabaseHandlerImplTest {
  @Disabled
  @Test
  void generateDB() {
    try {
      DatabaseConfig databaseConfig = new DatabaseConfig();
      String query = "insert into item values(?,?,?,?)";
      Connection con = DriverManager.getConnection(databaseConfig.getUrl(),
          databaseConfig.getUsername(), databaseConfig.getPassword());
      List<String> name = Arrays.asList("Mobile", "Computer", "Laptop", "Phone", "TV");
      List<String> type = Arrays.asList("RAW", "IMPORTED", "MANUFACTURED", "IMPORTED", "RAW");
      for (int i = 0; i < name.size(); i++) {
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, EncryptionUtils.encryptString(name.get(i)));
        ps.setString(2, type.get(i));
        ps.setDouble(3, 100);
        ps.setInt(4, 2);
        ps.execute();
      }
      con.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

}
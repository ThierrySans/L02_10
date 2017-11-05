package zodiac.dao.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import zodiac.definition.security.User;
import zodiac.util.PostgreSqlJdbc;

public class UserDao {

  /**
   * Gets the salted and hashed password of the given user.
   *
   * @param utorId the UTOR_Id of the user
   * @return salted and hashed password
   */
  public String getHash(String utorId) {
    Connection c;
    PreparedStatement stmt;

    String sql = "SELECT password FROM Users WHERE utor_id = ?";

    String hash = null;

    try {
      c = new PostgreSqlJdbc().getConnection();
      stmt = c.prepareStatement(sql);
      stmt.setString(1, utorId);

      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        hash = rs.getString("password");
      }
    } catch (Exception e) {
      // TODO Error Handling
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
    return hash;
  }

  /**
   * Modify the user to set their password. Hash should be the result of jBCrypt's hash function
   *
   * @param utorId the UTOR_Id of the user
   * @param hash the salted and hashed password
   * @return "Failed" if the update failed or "Success" if it succeeded
   */
  public String insertHash(String utorId, String hash) {
    Connection c;
    PreparedStatement stmt;

    String sql = "UPDATE Users SET password = ?, registered = TRUE WHERE utor_id = ?";

    String message = "Failed";

    try {
      c = new PostgreSqlJdbc().getConnection();
      stmt = c.prepareStatement(sql);
      stmt.setString(1, hash);
      stmt.setString(2, utorId);

      if (stmt.executeUpdate() > 0) {
        message = "Success";
      }

    } catch (Exception e) {
      // TODO Error Handling
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }

    return message;
  }

  /**
   * Get the status of a user's registration.
   *
   * @param utorId the UTOR_Id of the user
   * @return True if the user is registered, false if they are not
   * @throws SQLException If anything goes wrong, do not assume the user is or is not registered
   */
  public boolean getRegistered(String utorId) throws SQLException {
    Connection c;
    PreparedStatement stmt;

    String sql = "SELECT registered FROM Users WHERE utor_id = ?";

    c = new PostgreSqlJdbc().getConnection();
    stmt = c.prepareStatement(sql);
    stmt.setString(1, utorId);

    ResultSet rs = stmt.executeQuery();
    rs.next();
    return rs.getBoolean("registered");
  }

  /**
   * Get the user with the given UTOR Id.
   *
   * @param utorId the UTOR_Id of the user
   * @return the user, null if something went wrong
   */
  public User getUser(String utorId) {
    Connection c;
    PreparedStatement stmt;

    String sql = "SELECT last_name, first_name, role FROM users WHERE utor_id = ?";

    User user = null;

    try {
      c = new PostgreSqlJdbc().getConnection();
      stmt = c.prepareStatement(sql);
      stmt.setString(1, utorId);

      ResultSet rs = stmt.executeQuery();
      rs.next();
      String lastName = rs.getString("last_name");
      String firstName = rs.getString("first_name");
      String role = rs.getString("role");
      user = new User(utorId, role, lastName, firstName);

    } catch (Exception e) {
      // TODO Error Handling
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
    return user;
  }

}

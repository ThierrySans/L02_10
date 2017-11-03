package zodiac.action.security;

import java.sql.SQLException;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import zodiac.dao.security.UserDao;

public class UserAction {

  /**
   * Attempt to register the given user with a entered password.
   *
   * @param utorId the UTOR_Id of the user
   * @param password the password the user wants to use
   * @return message to display on the UI
   */
  public String register(String utorId, String password) {
    boolean isRegistered;
    try {
      isRegistered = new UserDao().getRegistered(utorId);
    } catch (SQLException e) {
      return "Unable to register, Contact your system admin";
    }
    if (isRegistered) {
      // Don't tell potential hackers why they can't register
      return "Unable to register, Contact your system admin";
    } else {
      password = StringUtils.trimToEmpty(password);
      if (password.length() < 6) {
        return "Password length must be at least 6";
      } else {
        // Recommend work factor is at least 12 log rounds
        String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        if (new UserDao().insertHash(utorId, hash).equals("Success")) {
          return "Registered";
        } else {
          return "Unable to register, Contact your system admin";
        }
      }
    }
  }
}

package zodiac.util;

import org.apache.commons.lang3.StringUtils;
import zodiac.definition.security.SecurityConstants;
import zodiac.definition.security.User;

public enum ActiveUser {
  INSTANCE;

  private User user;

  private ActiveUser() {
    user = null;
  }

  public void logOff() {
    user = null;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Check if user has read permission on a given course.
   *
   * @param course the course code
   * @return true if user has read or write permissions
   */
  public boolean canRead(String course) {
    if (user == null) {
      return false;
    } else {
      // Trim to empty also converts null to empty string
      String permission = StringUtils.trimToEmpty(user.getPermissions().get(course));
      return (permission.equals(SecurityConstants.READ_PERMISSION)
          || permission.equals(SecurityConstants.WRITE_PERMISSION));
    }
  }

  /**
   * Check if user has write permission of a given course.
   *
   * @param course the course code
   * @return true if user has write permissions
   */
  public boolean canWrite(String course) {
    if (user == null) {
      return false;
    } else {
      return StringUtils.trimToEmpty(user.getPermissions().get(course)).equals(
          SecurityConstants.WRITE_PERMISSION);
    }
  }

}

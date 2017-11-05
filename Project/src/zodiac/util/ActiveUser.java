package zodiac.util;

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

}

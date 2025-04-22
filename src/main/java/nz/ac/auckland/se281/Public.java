package nz.ac.auckland.se281;

public class Public extends Review {
  boolean anonymous;
  boolean endorsed;

  public Public(String name, String rating, String text, String id, String anonymous) {
    super(name, rating, text, id);
    if (anonymous.equalsIgnoreCase("y")) {
      this.anonymous = true;
    } else {
      this.anonymous = false;
    }
    this.endorsed = false;
  }

  public boolean getAnonymous() {
    return this.anonymous;
  }

  public boolean getEndorsed() {
    return this.endorsed;
  }

  public void setEndorsed(boolean i) {
    this.endorsed = i;
  }
}

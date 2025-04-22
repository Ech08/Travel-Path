package nz.ac.auckland.se281;

public class Public extends Review {
  boolean anonymous;
  boolean endorsed;

  public Public(
      String name, int rating, String text, String id, boolean anonymous, boolean endorsed) {
    super(name, rating, text, id);
    this.anonymous = anonymous;
    this.endorsed = endorsed;
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

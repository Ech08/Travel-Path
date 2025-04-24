package nz.ac.auckland.se281;

public class Public extends Review {
  private boolean anonymous;
  private boolean endorsed;
  private String type = "Public";

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

  @Override
  public String getType() {
    return this.type;
  }
}

package nz.ac.auckland.se281;

public class Public extends Review {
  private boolean anonymous;
  private boolean endorsed;
  private String type = "Public";

  public Public(String name, String rating, String text, String id) {
    super(name, rating, text, id);
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

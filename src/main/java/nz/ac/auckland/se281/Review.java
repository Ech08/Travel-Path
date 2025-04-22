package nz.ac.auckland.se281;

public abstract class Review {
  protected String name;
  protected String rating;
  protected String text;
  protected String actId;
  protected String id;

  public Review(String name, String rating, String text, String id) {
    this.name = name;
    this.rating = rating;
    this.text = text;
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public String getRating() {
    return this.rating;
  }

  public String getText() {
    return this.text;
  }

  public String id() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }
}

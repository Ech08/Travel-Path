package nz.ac.auckland.se281;

public class Expert extends Review {
  private boolean recommend;
  private String image;

  public Expert(
      String name, String rating, String text, String id, String email, boolean recommend) {
    super(name, rating, text, id);
    this.recommend = recommend;
  }

  public boolean getRecomend() {
    return this.recommend;
  }

  public void setImage(String i) {
    this.image = i;
  }
}

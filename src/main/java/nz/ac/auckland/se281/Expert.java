package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Expert extends Review {
  private boolean recommend;
  private ArrayList<String> images;
  private String type = "Expert";

  public Expert(String name, String rating, String text, String id, boolean recommend) {
    super(name, rating, text, id);
    this.recommend = recommend;
    this.images = new ArrayList<String>();
  }

  public boolean getRecomend() {
    return this.recommend;
  }

  public void addImages(String i) {
    this.images.add(i);
  }

  public boolean hasImagess() {
    if (images.size() > 0) {
      return true;
    }
    return false;
  }

  public ArrayList<String> getImages() {
    return this.images;
  }

  @Override
  public String getType() {
    return this.type;
  }
}

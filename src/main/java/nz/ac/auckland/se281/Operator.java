package nz.ac.auckland.se281;

public class Operator {
  private String id;
  private String idNum;
  private String fullName;
  private String fullLoc;

  public Operator(String name, String id, String loc) {
    this.id = id;
    this.fullName = name;
    this.fullLoc = loc;
  }
}

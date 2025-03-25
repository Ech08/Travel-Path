package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Operator {
  private String id;
  private String idNum;
  private String fullName;
  private String fullLoc;

  // initialises operator
  public Operator(String name, String id, String idNum, String loc) {
    this.id = id;
    this.idNum = idNum;
    this.fullName = name;
    this.fullLoc = loc;
  }

  // gets list of details
  public ArrayList<String> getDetails() {
    ArrayList<String> details = new ArrayList<>();
    details.add(this.fullName);
    details.add(this.id);
    details.add(this.fullLoc);
    return details;
  }
}

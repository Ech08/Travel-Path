package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.Location;

public class Operator {
  private String id;
  private String idNum;
  private String fullName;
  private String fullLoc;
  private Location loc;

  // initialises operator
  public Operator(String name, String id, String idNum, String loc) {
    this.id = id;
    this.idNum = idNum;
    this.fullName = name;
    this.fullLoc = loc;
    this.loc = Types.Location.fromString(loc);
  }

  // gets list of details
  public ArrayList<String> getDetails() {
    ArrayList<String> details = new ArrayList<>();
    details.add(this.fullName);
    details.add(this.id);
    details.add(this.fullLoc);
    return details;
  }

  public Location getLoc() {
    return this.loc;
  }
}

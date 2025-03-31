package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.ActivityType;

public class Activity {
  private String name;
  private ActivityType type;
  private String loc;
  private String id;

  // constructor
  public Activity(String name, ActivityType type, String loc, String id) {
    this.name = name;
    this.type = type;
    this.loc = loc;
    this.id = id;
  }

  // gets list of details
  public ArrayList<String> getDetails() {
    ArrayList<String> details = new ArrayList<>();
    details.add(this.name);
    details.add(this.type.toString());
    details.add(this.id);
    details.add(this.loc);
    return details;
  }
}

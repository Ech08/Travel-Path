package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.ActivityType;
import nz.ac.auckland.se281.Types.Location;

public class Activity {
  private String name;
  private ActivityType type;
  private Location loc;
  private String locName;
  private String id;

  // constructor
  public Activity(String name, ActivityType type, String loc, String id) {
    this.name = name;
    this.type = type;
    this.locName = loc;
    this.id = id;
    this.loc = Types.Location.fromString(loc);
  }

  // gets list of details
  public ArrayList<String> getDetails() {
    ArrayList<String> details = new ArrayList<>();
    details.add(this.name);
    details.add(this.type.toString());
    details.add(this.id);
    details.add(this.locName);
    return details;
  }
}

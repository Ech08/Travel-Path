package nz.ac.auckland.se281;


import nz.ac.auckland.se281.Types.ActivityType;
import nz.ac.auckland.se281.Types.Location;

public class Activity {
  private String name;
  private ActivityType type;
  private Location loc;
  private String fullLoc;
  private String id;
  private Operator operator;

  // constructor
  public Activity(String name, ActivityType type, Location loc, Operator op, String id) {
    this.name = name;
    this.type = type;
    this.operator = op;
    this.loc = loc;
    this.fullLoc = loc.getFullName();
    this.id = id;
  }

  @Override
  public String toString() {
    return this.name;
  }

  public Location getLoc() {
    return this.loc;
  }

  public String getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getLocFull() {
    return this.fullLoc;
  }

  public ActivityType getType() {
    return this.type;
  }

  public Operator getOp() {
    return this.operator;
  }
}

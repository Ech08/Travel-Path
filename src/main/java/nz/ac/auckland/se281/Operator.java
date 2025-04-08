package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

public class Operator {
  private String id;
  private String idNum;
  private String fullName;
  private String abbrevName;
  private String fullLoc;
  private Location loc;
  private String currentNum;

  // initialises operator
  public Operator(String name, String id, String idNum, String loc) {
    this.id = id;
    this.idNum = idNum;
    this.fullName = name;
    this.fullLoc = Types.Location.fromString(loc).getFullName();
    this.loc = Types.Location.fromString(loc);
    this.currentNum = "000";
  }

  public Location getLoc() {
    return this.loc;
  }

  public String getId() {
    return this.id;
  }

  public String getIdNum() {
    return this.idNum;
  }

  public String getNameAbbrev() {
    return this.abbrevName;
  }

  public String getNameFull() {
    return this.fullName;
  }

  public String getLocFull() {
    return this.fullLoc;
  }

  public void setNameAbbrev(String name) {
    this.abbrevName = name;
  }

  public String getNum() {
    return this.currentNum;
  }

  public void setNum(String num) {
    this.currentNum = num;
  }
}

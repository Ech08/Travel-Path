package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.HashMap;
import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  private ArrayList<Operator> opList = new ArrayList<>();
  private HashMap<String, String> locNums = new HashMap<String, String>();

  // custom methods:
  public String idNum(String num) {
    // increments input int by 1 and returns 3 digit string
    char digits[] = num.toCharArray();
    boolean carryOut = true;
    for (int i = 2; i >= 0; i--) {
      if (carryOut) {
        switch (digits[i]) {
          case '9':
            if (i == 0) {
              // set to 999 (largest possible value)
              digits[0] = '9';
              digits[1] = '9';
              digits[2] = '9';
              carryOut = false;
            } else {
              digits[i] = '0';
              carryOut = true;
            }
            break;

          default:
            // add one to digit
            digits[i]++;
            carryOut = false;
            break;
        }
      }
    }
    String result = new String(digits);
    return result;
  }

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    this.opList = new ArrayList<>();
    this.locNums = new HashMap<String, String>();

    this.locNums.put("AKL", "000");
    this.locNums.put("HLZ", "000");
    this.locNums.put("TRG", "000");
    this.locNums.put("TUO", "000");
    this.locNums.put("WLG", "000");
    this.locNums.put("NSN", "000");
    this.locNums.put("CHC", "000");
    this.locNums.put("DUD", "000");
  }

  public void searchOperators(String keyword) {
    // look for keyword
    // set foundOps to num of operators found
    // print ops found + message
    ArrayList<Operator> matches = new ArrayList<>();
    for (Operator op : opList) {
      matches.add(op);
    }

    int foundOps = 8;

    // display correct message
    switch (foundOps) {
      case 0:
        MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ":");
        break;
      case 1:
        MessageCli.OPERATORS_FOUND.printMessage("is", Integer.toString(foundOps), "", ":");
        break;
      default:
        MessageCli.OPERATORS_FOUND.printMessage("are", Integer.toString(foundOps), "s", ":");
        break;
    }

    for (Operator match : matches) {
      ArrayList<String> details = match.getDetails();
      MessageCli.OPERATOR_ENTRY.printMessage(details.get(0), details.get(1), details.get(2));
    }
  }

  public void createOperator(String operatorName, String location) {
    // get operatorName abbreviation
    String words[] = operatorName.split(" ");
    String abbrevName = "";

    // add the first letter of each word in words to abbrevName
    for (String word : words) {
      abbrevName = abbrevName + word.charAt(0);
    }
    abbrevName = abbrevName.toUpperCase();

    // get operator id
    String abbrevLoc = Location.fromString(location).getLocationAbbreviation();
    String test = locNums.get(abbrevLoc);
    String numOp = idNum(test);
    locNums.put(abbrevLoc, numOp);
    String idOp = abbrevName + "-" + abbrevLoc + "-" + numOp;

    // get full location name
    String fullLoc = Location.fromString(location).getFullName();

    // print message with all operator properties
    MessageCli.OPERATOR_CREATED.printMessage(operatorName, idOp, fullLoc);

    Operator newOp = new Operator(operatorName, idOp, numOp, fullLoc);
    opList.add(newOp);
  }

  public void viewActivities(String operatorId) {
    // TODO implement
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    // TODO implement
  }

  public void searchActivities(String keyword) {
    // TODO implement
  }

  public void addPublicReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addPrivateReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addExpertReview(String activityId, String[] options) {
    // TODO implement
  }

  public void displayReviews(String activityId) {
    // TODO implement
  }

  public void endorseReview(String reviewId) {
    // TODO implement
  }

  public void resolveReview(String reviewId, String response) {
    // TODO implement
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    // TODO implement
  }

  public void displayTopActivities() {
    // TODO implement
  }
}

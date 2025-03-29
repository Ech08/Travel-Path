package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Arrays;
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

  public Operator opFromString(String text) {
    for (Operator op : opList) {
      ArrayList<String> det = op.getDetails();
      if (det.get(0).equalsIgnoreCase(text)) {
        return op;
      }
    }
    return null;
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
    int foundOps = 0;
    ArrayList<Operator> matches = new ArrayList<>();

    // add all operators to matches list if searching '*'
    if (keyword.equalsIgnoreCase("*")) {
      for (Operator op : opList) {
        matches.add(op);
        foundOps++;
      }
    } else { // else search for keyword in operator details
      keyword = keyword.trim();
      String keywords[] = keyword.split(" ");

      for (Operator op : opList) {
        boolean match = false;
        ArrayList<String> detList = op.getDetails();
        ArrayList<String> parts = new ArrayList<>();

        String[] part1 = detList.get(0).split(" "); // name
        parts.addAll(Arrays.asList(part1));
        String[] part2 = detList.get(1).split("-"); // id
        parts.addAll(Arrays.asList(part2));
        String[] part3 = detList.get(2).split(" "); // location
        parts.addAll(Arrays.asList(part3));

        // check if keyword matches
        for (String word : keywords) {
          if (!word.isBlank()) {
            for (int i = 0; i < parts.size(); i++) {
              if (parts.get(i).toLowerCase().contains(word.toLowerCase())) {
                match = true;
              }
            }
            System.out.println(word);
          }
        }
        if (match) {
          foundOps++;
          matches.add(op);
        }
      }
    }
    // display correct message
    switch (foundOps) {
      case 0:
        MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
        break;
      case 1:
        MessageCli.OPERATORS_FOUND.printMessage("is", Integer.toString(foundOps), "", ":");
        break;
      default:
        MessageCli.OPERATORS_FOUND.printMessage("are", Integer.toString(foundOps), "s", ":");
        break;
    }

    // print operators found
    for (Operator match : matches) {
      ArrayList<String> details = match.getDetails();
      MessageCli.OPERATOR_ENTRY.printMessage(details.get(0), details.get(1), details.get(2));
    }
  }

  public void createOperator(String operatorName, String location) {
    // checks if operator name is valid before continuing
    if (operatorName.length() < 3) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.printMessage(operatorName);
      return;
    }

    // get operatorName abbreviation
    String words[] = operatorName.split(" ");
    String abbrevName = "";

    // add the first letter of each word in words to abbrevName
    for (String word : words) {
      abbrevName = abbrevName + word.charAt(0);
    }
    abbrevName = abbrevName.toUpperCase();

    // check if location exists before continuing
    if (Location.fromString(location) == null) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_LOCATION.printMessage(location);
      return;
    }
    // get full and abbreviated location name, and current loc operator count
    String fullLoc = Location.fromString(location).getFullName();

    String abbrevLoc = Location.fromString(location).getLocationAbbreviation();
    String currentNum = locNums.get(abbrevLoc);

    // check if operator already exists
    if ((!currentNum.equals("000")) && (opList.contains(opFromString(operatorName)))) {
      MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(
          operatorName, fullLoc);
      return;
    }

    // get operator id
    String numOp = idNum(currentNum);
    locNums.put(abbrevLoc, numOp);
    String idOp = abbrevName + "-" + abbrevLoc + "-" + numOp;

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

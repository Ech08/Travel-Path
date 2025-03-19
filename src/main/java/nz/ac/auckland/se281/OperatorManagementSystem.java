package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  // custom methods:
  public String idNum(int num) {
    // increments input int by 1 and returns 3 digit string
    int digits[] = {0, 0, 0};
    boolean carryOut = true;
    for (int i = 2; i < digits.length; i--) {
      if (carryOut) {
        switch (digits[i]) {
          case 9:
            if (i == 0) {
              // set to 999 (largest possible value)
              digits[0] = 9;
              digits[1] = 9;
              digits[2] = 9;
              carryOut = false;
            } else {
              digits[i] = 0;
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
    return digits.toString();
  }

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void searchOperators(String keyword) {
    MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
  }

  public void createOperator(String operatorName, String location) {
    // get operatorName abbreviation
    String words[] = operatorName.split(" ");
    String abbrevName = "";

    // add the first letter of each word in words to abbrevName
    for (String word : words) {
      abbrevName = abbrevName + word.charAt(0);
    }

    // get full location name
    String fullLoc = Location.fromString(location).getFullName();

    // print message with all operator properties
    MessageCli.OPERATOR_CREATED.printMessage(operatorName, abbrevName, fullLoc);
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

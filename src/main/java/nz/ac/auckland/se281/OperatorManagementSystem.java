package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void searchOperators(String keyword) {
    MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
  }

  public void createOperator(String operatorName, String location) {
    // setting up variables
    String words[] = operatorName.split(" ");
    String abbrevName = "";
    boolean start = true;

    // add the first letter of each word in words to abbrevName
    for (int i = 0; i < words.length; i++) {
      // if whitespace detected, get ready to add letter
      if (words[i] == " ") {
        start = true;
      }
      // if ready and whitespace not detected, add letter
      else if (start = true && words[i] != " ") {
        abbrevName = abbrevName + words[i].charAt(0);
        start = false;
      }
    }

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

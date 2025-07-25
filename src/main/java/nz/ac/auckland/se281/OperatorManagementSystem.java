package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import nz.ac.auckland.se281.Types.ActivityType;
import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  // start lists of operators, activities, and keep track of how many for each loc, op respectively
  private ArrayList<Operator> opList = new ArrayList<>();
  private HashMap<String, String> locNums = new HashMap<String, String>();
  private ArrayList<Activity> actList = new ArrayList<>();
  private ArrayList<Review> revList = new ArrayList<>();
  private ArrayList<Location> locList = new ArrayList<>();

  // custom methods:
  public String nameFromOpId(String id) {
    for (Operator op : opList) {
      if (id.equals(op.getId())) {
        return op.getNameFull();
      }
    }
    return null;
  }

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

  public String idNumRemoveZero(String id) {
    return Integer.toString(Integer.parseInt(id));
  }

  public Operator opFromString(String text) {
    for (Operator op : opList) {
      if (op.getNameFull().equalsIgnoreCase(text)) {
        return op;
      }
    }
    return null;
  }

  public Operator opFromId(String id) {
    Operator operator = null;
    for (Operator op : opList) {
      if (id.equals(op.getId())) {
        operator = op;
      }
    }
    return operator;
  }

  public Activity actFromId(String id) {
    Activity activity = null;
    for (Activity act : actList) {
      if (id.equals(act.getId())) {
        activity = act;
      }
    }
    return activity;
  }

  public Review revFromId(String id) {
    Review rev = null;
    for (Review r : revList) {
      if (id.equals(r.getId())) {
        rev = r;
      }
    }
    return rev;
  }

  public String getAbbrev(String name) {
    // get name abbreviation
    String words[] = name.split(" ");
    String abbrevName = "";

    // add the first letter of each word in words to abbrevName
    for (String word : words) {
      abbrevName = abbrevName + word.charAt(0);
    }
    abbrevName = abbrevName.toUpperCase();
    return abbrevName;
  }

  public boolean containsOp(Operator op) {
    for (Operator testOp : opList) {
      if (testOp.equals(op)) {
        return true;
      }
    }
    return false;
  }

  public boolean containsAct(Activity act) {
    for (Activity testAct : actList) {
      if (testAct.equals(act)) {
        return true;
      }
    }
    return false;
  }

  public boolean containsRev(Review rev) {
    for (Review testRev : revList) {
      if (testRev.equals(rev)) {
        return true;
      }
    }
    return false;
  }

  public boolean opExists(String id) {
    Operator op = opFromId(id);
    if (!containsOp(op)) {
      return false;
    }
    return true;
  }

  public boolean actExists(String id) {
    Activity act = actFromId(id);
    if (!containsAct(act)) {
      return false;
    }
    return true;
  }

  public boolean revExists(String id) {
    Review rev = revFromId(id);
    if (containsRev(rev)) {
      return true;
    }
    return false;
  }

  // -----------------------------------------------------------------

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    this.opList = new ArrayList<>();
    this.locNums = new HashMap<String, String>();

    for (Location loc : Location.values()) {
      this.locList.add(loc);
      this.locNums.put(loc.getLocationAbbreviation(), "000");
    }
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
    } else if (keyword.equals("|")) { // if searching for | nothing should be found
      foundOps = 0;
    } else { // else search for keyword in operator details
      keyword = keyword.trim();
      String keywords[] = keyword.split(" ");

      for (Operator op : opList) {
        boolean match = false;
        ArrayList<String> parts = new ArrayList<>();

        String[] part1 = op.getNameFull().split(" "); // name
        parts.addAll(Arrays.asList(part1));
        String[] part2 = op.getLocFull().split(" "); // location (full)
        parts.addAll(Arrays.asList(part2));
        String abbrevLoc = (op.getLoc()).getLocationAbbreviation(); // location (abbrev)
        parts.add(abbrevLoc);

        // check if keyword matches
        for (String word : keywords) {
          if (!word.isBlank()) {
            for (int i = 0; i < parts.size(); i++) {
              if (parts.get(i).toLowerCase().contains(word.toLowerCase())) {
                match = true;
              }
            }
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
      MessageCli.OPERATOR_ENTRY.printMessage(
          match.getNameFull(), match.getId(), match.getLocFull());
    }
  }

  public void createOperator(String operatorName, String location) {
    // checks if operator name is valid before continuing
    if (operatorName.length() < 3) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.printMessage(operatorName);
      return;
    }

    // get name initials
    String abbrevName = getAbbrev(operatorName);

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

    Operator newOp = new Operator(operatorName, idOp, numOp, abbrevLoc);
    opList.add(newOp);
    newOp.setNameAbbrev(abbrevName);
  }

  // activities

  public void viewActivities(String operatorId) {
    // check if operator exists
    boolean exists = opExists(operatorId);
    if (!exists) {
      MessageCli.OPERATOR_NOT_FOUND.printMessage(operatorId);
      return;
    }
    Operator op = opFromId(operatorId);

    // check if op has activities
    if (op.getNum() == "000") {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
      return;
    }

    String num = idNumRemoveZero(idNum(op.getIdNum()));

    // print found message
    if (op.getNum() == "001") {
      MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
    } else {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", num, "ies", ":");
    }

    // if op has activities, add them to a printed list
    for (Activity act : actList) {
      MessageCli.ACTIVITY_ENTRY.printMessage(
          act.getName(), act.getId(), act.getType().toString(), op.getNameFull());
    }
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    activityName = activityName.trim();

    // checks if operator name is valid before continuing
    if (activityName.length() < 3) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_ACTIVITY_NAME.printMessage(activityName);
      return;
    }

    // checks if operator ID exists
    if (opExists(operatorId) == false) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_OPERATOR_ID.printMessage(operatorId);
      return;
    }
    Operator op = opFromId(operatorId);

    // set type
    ActivityType type = ActivityType.fromString(activityType);
    String actType = type.toString();

    // set id
    String opName = nameFromOpId(operatorId);
    String nextNum = idNum(op.getNum());
    String actId = operatorId + "-" + nextNum;
    op.setNum(nextNum);

    // print success message
    MessageCli.ACTIVITY_CREATED.printMessage(activityName, actId, actType, opName);

    // create activity
    Location loc = op.getLoc();
    Activity newAct = new Activity(activityName, type, loc, op, actId);
    actList.add(newAct);
  }

  public void searchActivities(String keyword) {
    int foundActs = 0;
    ArrayList<Activity> matches = new ArrayList<>();
    keyword = keyword.toLowerCase().trim();

    // add all activities to matches list if searching '*'
    if (keyword.equalsIgnoreCase("*")) {
      for (Activity act : actList) {
        matches.add(act);
        foundActs++;
      }
    } else {
      // if keyword is found in activity name or type or location
      for (Activity act : actList) {
        boolean match = false;
        ArrayList<String> parts = new ArrayList<>();
        Operator op = act.getOp();

        String part1 = act.getName().toLowerCase(); // name
        parts.add(part1);
        String part2 = act.getType().toString().toLowerCase(); // type
        parts.add(part2);
        String[] part3 = op.getLocFull().split(" "); // location (full)
        parts.addAll(Arrays.asList(part3));
        String abbrevLoc =
            (op.getLoc()).getLocationAbbreviation().toLowerCase(); // location (abbrev)
        parts.add(abbrevLoc);

        if (!keyword.isBlank()) {
          for (int i = 0; i < parts.size(); i++) {
            System.out.println(parts.get(i));
            if ((parts.get(i).toLowerCase()).contains(keyword)) {
              match = true;
            }
          }
        }

        if (match) {
          matches.add(act);
          foundActs += 1;
        }
      }
    }

    // print message
    switch (foundActs) {
      case 0:
        MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
        break;
      case 1:
        MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
        break;
      default:
        MessageCli.ACTIVITIES_FOUND.printMessage("are", Integer.toString(foundActs), "ies", ":");
        break;
    }

    // print activities found
    for (Activity act : matches) {
      MessageCli.ACTIVITY_ENTRY.printMessage(
          act.getName(), act.getId(), act.getType().toString(), act.getOp().getNameFull());
    }
  }

  // reviews

  public void addPublicReview(String activityId, String[] options) {

    // check id activity exists
    if (actExists(activityId) == false) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }
    Activity act = actFromId(activityId);

    // check rating is appropriate, if not set as closest number
    String rating = options[2];
    int ratingInt = Integer.parseInt(rating);
    if (ratingInt > 5) {
      rating = "5";
    } else if (ratingInt < 1) {
      rating = "1";
    } else {
      rating = Integer.toString(ratingInt);
    }

    // make id
    String nextNum = idNum(act.getNum());
    String revId = activityId + "-R" + idNumRemoveZero(nextNum);
    act.setNum(nextNum);

    String name = options[0];
    if (options[1].equalsIgnoreCase("y")) {
      name = "Anonymous";
    }

    // amke review
    Review newReview = new Public(name, rating, options[3], revId);
    revList.add(newReview);
    newReview.setActId(act.getId());

    // print success message
    MessageCli.REVIEW_ADDED.printMessage("Public", revId, act.getName());
  }

  public void addPrivateReview(String activityId, String[] options) {
    boolean followup = false;
    boolean resolved = true;
    if (options[4].equals("y")) {
      followup = true;
      resolved = false;
    } else {
      followup = false;
      resolved = true;
    }

    // check id activity exists
    if (actExists(activityId) == false) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }
    Activity act = actFromId(activityId);

    // check rating is appropriate, if not set as closest number
    String rating = options[2];
    int ratingInt = Integer.parseInt(rating);
    if (ratingInt > 5) {
      rating = "5";
    } else if (ratingInt < 1) {
      rating = "1";
    } else {
      rating = Integer.toString(ratingInt);
    }

    // make id
    String nextNum = idNum(act.getNum());
    String revId = activityId + "-R" + idNumRemoveZero(nextNum);
    act.setNum(nextNum);

    // followup handling
    if (followup) {
      resolved = false;
    }

    // amke review
    Review newReview =
        new Private(options[0], rating, options[3], revId, options[1], followup, resolved);
    revList.add(newReview);
    newReview.setActId(act.getId());

    // print success message
    MessageCli.REVIEW_ADDED.printMessage("Private", revId, act.getName());
  }

  public void addExpertReview(String activityId, String[] options) {
    boolean recommend = false;
    if (options[3].equals("y")) {
      recommend = true;
    } else {
      recommend = false;
    }

    // check id activity exists
    if (actExists(activityId) == false) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }
    Activity act = actFromId(activityId);

    // check rating is appropriate, if not set as closest number
    String rating = options[1];
    int ratingInt = Integer.parseInt(rating);
    if (ratingInt > 5) {
      rating = "5";
    } else if (ratingInt < 1) {
      rating = "1";
    } else {
      rating = Integer.toString(ratingInt);
    }

    // make id
    String nextNum = idNum(act.getNum());
    String revId = activityId + "-R" + idNumRemoveZero(nextNum);
    act.setNum(nextNum);

    // amke review
    Review newReview = new Expert(options[0], rating, options[2], revId, recommend);
    revList.add(newReview);
    newReview.setActId(act.getId());

    // print success message
    MessageCli.REVIEW_ADDED.printMessage("Expert", revId, act.getName());
  }

  public void displayReviews(String activityId) {

    // check id activity exists
    if (actExists(activityId) == false) {
      MessageCli.ACTIVITY_NOT_FOUND.printMessage(activityId);
      return;
    }
    Activity act = actFromId(activityId);
    ArrayList<Review> matches = new ArrayList<>();
    int match = 0;

    // if there are no reviews ever created, skip
    if (revList.size() != 0) {
      // else check every review to see if it falls under activity
      for (Review r : revList) {
        // if match add to list
        if (r.getActId().equals(activityId)) {
          matches.add(r);
          match++;
        }
      }
    }
    // print message
    switch (match) {
      case 0:
        MessageCli.REVIEWS_FOUND.printMessage("are", "no", "s", act.getName());
        break;
      case 1:
        MessageCli.REVIEWS_FOUND.printMessage("is", "1", "", act.getName());
        break;
      default:
        MessageCli.REVIEWS_FOUND.printMessage("are", Integer.toString(match), "s", act.getName());
        break;
    }

    // print reviews
    for (Review rev : matches) {
      // print review details
      MessageCli.REVIEW_ENTRY_HEADER.printMessage(
          rev.getRating(), "5", rev.getType(), rev.getId(), rev.getName());
      MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(rev.getText());
      if (rev.getType().equals("Public")) {
        // check if review is endorsed
        if (((Public) rev).getEndorsed() == true) {
          MessageCli.REVIEW_ENTRY_ENDORSED.printMessage();
        }
      }
      if (rev.getType().equals("Private")) {
        // check if review is resolved or needs follow up
        if (((Private) rev).isResolved()) {
          MessageCli.REVIEW_ENTRY_RESOLVED.printMessage(((Private) rev).getResolveText());
        } else {
          MessageCli.REVIEW_ENTRY_FOLLOW_UP.printMessage(((Private) rev).getEmail());
        }
      }
      if (rev.getType().equals("Expert")) {
        if (((Expert) rev).getRecomend()) {
          MessageCli.REVIEW_ENTRY_RECOMMENDED.printMessage();
        }
        // check if review has images
        if (((Expert) rev).hasImagess()) {
          // format images into one string before printing
          String imageText = ((Expert) rev).getImages().get(0);
          for (String i : ((Expert) rev).getImages()) {
            if (i == ((Expert) rev).getImages().get(0)) {
              continue;
            }
            imageText = imageText + "," + i;
          }
          MessageCli.REVIEW_ENTRY_IMAGES.printMessage(imageText);
        }
      }
    }
  }

  public void endorseReview(String reviewId) {
    // check id review exists
    if (revExists(reviewId) == false) {
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
      return;
    }
    Review rev = revFromId(reviewId);

    // check if public
    if (!rev.getType().equals("Public")) {
      MessageCli.REVIEW_NOT_ENDORSED.printMessage(reviewId);
      return;
    }

    // change review to endorsed
    ((Public) rev).setEndorsed(true);

    // print success message
    MessageCli.REVIEW_ENDORSED.printMessage(reviewId);
  }

  public void resolveReview(String reviewId, String response) {
    // check id review exists
    if (revExists(reviewId) == false) {
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
      return;
    }
    Review rev = revFromId(reviewId);

    // check if private
    if (!rev.getType().equals("Private")) {
      MessageCli.REVIEW_NOT_RESOLVED.printMessage(reviewId);
      return;
    }

    // change to resolved
    ((Private) rev).setResolveText(response);
    ((Private) rev).setResolved(true);

    // print success message
    MessageCli.REVIEW_RESOLVED.printMessage(reviewId);
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    // check id review exists
    if (revExists(reviewId) == false) {
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
      return;
    }
    Review rev = revFromId(reviewId);

    // check if expert
    if (!rev.getType().equals("Expert")) {
      MessageCli.REVIEW_IMAGE_NOT_ADDED_NOT_EXPERT.printMessage(reviewId);
      return;
    }

    // add image
    ((Expert) rev).addImages(imageName);

    // print success message
    MessageCli.REVIEW_IMAGE_ADDED.printMessage(imageName, reviewId);
  }

  public void displayTopActivities() {
    // initialise arrays to store top activities and ratings
    ArrayList<Activity> topActivities = new ArrayList<>();
    ArrayList<Integer> averageRatings = new ArrayList<>();

    // find top activities and store in arrays
    for (Location loc : locList) {
      int currentRating = 0;
      Activity topActivity = null;

      // find top activity for location loc
      for (Activity act : actList) {

        if (act.getLoc().equals(loc)) {
          int actRating = 0;
          int reviewCount = 0;
          // find average rating
          for (Review rev : revList) {
            if (rev instanceof Private) {
              continue;
            }
            if (rev.getActId().equals(act.getId())) {
              actRating += Integer.parseInt(rev.getRating());
              reviewCount++;
            }
          }
          // if activity has no reviews, skip it
          if (reviewCount == 0) {
            continue;
          }
          actRating = actRating / reviewCount;

          // if rating is higher than previous activities set as top activity
          if (actRating > currentRating) {
            currentRating = actRating;
            topActivity = act;
          }
        }
      }
      // add top activity to list
      topActivities.add(topActivity);
      averageRatings.add(currentRating);
    }

    // print top activities
    for (int i = 0; i < topActivities.size(); i++) {
      String location = locList.get(i).toString();
      Activity act = topActivities.get(i);

      if (topActivities.get(i) == null) {
        MessageCli.NO_REVIEWED_ACTIVITIES.printMessage(location);
      } else {
        MessageCli.TOP_ACTIVITY.printMessage(
            location, act.getName(), averageRatings.get(i).toString());
      }
      // }
    }
  }
}

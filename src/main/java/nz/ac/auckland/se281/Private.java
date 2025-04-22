package nz.ac.auckland.se281;

public class Private extends Review {
  String email;
  boolean followUp;
  boolean resolved;
  String resolveText = "-";

  public Private(
      String name,
      int rating,
      String text,
      String id,
      String email,
      boolean followUp,
      boolean resolved) {
    super(name, rating, text, id);
    this.email = email;
    this.followUp = followUp;
    this.resolved = resolved;
  }

  public boolean getFollowUp() {
    return this.followUp;
  }

  public String getEmail() {
    return this.email;
  }

  public void setResolveText(String text) {
    this.resolveText = text;
  }

  public void setFollowUp(boolean i) {
    this.followUp = i;
  }

  public void setResolved(boolean i) {
    this.resolved = i;
  }
}

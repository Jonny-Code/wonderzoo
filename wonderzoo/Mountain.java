// Jon Sledge
// COP 2552
// Final Project

package wonderzoo;

public class Mountain extends Terrain {
  private int mntHighestPoint;

  public Mountain() {
    super();
  }

  public Mountain(int height) {
    super();
    setMntHighestPoint(height);
  }

  public int getMntHighestPoint() {
    return mntHighestPoint;
  }

  public void setMntHighestPoint(int highestPoint) {
    this.mntHighestPoint = highestPoint;
  }

  public void createTerrain(String n, String c, String t) {
    setName(n);
    setClimate(c);
    setTopography(t);
  }

  public String toString() {
    return "Name is: " + getName() + "\nClimate is: " + getClimate() + "\nTopography is: " + getTopography()
            + "\nHighest point is: " + mntHighestPoint + " feet";
  }
}

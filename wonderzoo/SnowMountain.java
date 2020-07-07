package wonderzoo;

public class SnowMountain extends Mountain {
  private int mntColdestTemp;

  public SnowMountain(int temp) {
    super();
    setMntColdestTemp(temp);
  }

  public int getMntColdestTemp() {
    return mntColdestTemp;
  }

  public void setMntColdestTemp(int mntColdestTemp) {
    this.mntColdestTemp = mntColdestTemp;
  }

  public void addColdestTemp(int t) {
    setMntColdestTemp(t);
  }

  public void createTerrain(String n, String c, String t) {
    setName(n);
    setClimate(c);
    setTopography(t);
  }

  public String toString() {
    return "Name is: " + getName() + "\nClimate is: " + getClimate() + "\nTopography is: " + getTopography()
            + "\nColdest temp is: " + mntColdestTemp + " degrees Farenheight";
  }
}
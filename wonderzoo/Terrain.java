// Jon Sledge
// COP 2552
// Final Project

package wonderzoo;

public abstract class Terrain implements Comparable<Terrain> {

  private String name;
  private String climate;
  private String topography;

  public Terrain() {
    setName("default");
    setClimate("default");
    setTopography("default");
  }

  public abstract void createTerrain(String n, String c, String t);

  public String getTopography() {
    return topography;
  }

  public void setTopography(String topography) {
    this.topography = topography;
  }

  public String getClimate() {
    return climate;
  }

  public void setClimate(String climate) {
    this.climate = climate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String toString() {
    return "Name is: " + getName() + "\nClimate is: " + getClimate() + "\nTopography is: " + getTopography();
  }

  public int compareTo(Terrain t) {
    if (name.equals(t.name))
      return 0;
    else
      return -1;

  }
}
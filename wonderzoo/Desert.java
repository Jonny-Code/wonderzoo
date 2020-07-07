// Jon Sledge
// COP 2552
// Final Project

package wonderzoo;

public class Desert extends Terrain {
  private int desHottestTemp;

  public Desert(int temp) {
    super();
    setDesHottestTemp(temp);
  }

  public int getDesHottestTemp() {
    return desHottestTemp;
  }

  public void setDesHottestTemp(int desHottestTemp) {
    this.desHottestTemp = desHottestTemp;
  }

  public void createTerrain(String n, String c, String t) {
    setName(n);
    setClimate(c);
    setTopography(t);
  }

  public String toString() {
    return "Name is: " + getName() + "\nClimate is: " + getClimate() + "\nTopography is: " + getTopography()
            + "\nHottest temp is: " + desHottestTemp + " degrees Farenheight";
  }
}

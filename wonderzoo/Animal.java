// Jon Sledge
// COP 2552
// Final Project

package wonderzoo;

import java.util.ArrayList;

public class Animal {
    private String name;
    private String food;
    private ArrayList<String> foodList;
    private String terrain;
    private Terrain ter;
    private Food foo;

    public Animal(String n, String t) {
        this.name = n;
        this.terrain = t;
    }

    public Animal(String n, String t, String f) {
        this.name = n;
        this.terrain = t;
        this.food = f;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String f) {
        food = f;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String t) {
        terrain = t;
    }

    public Terrain getTer() { return ter; }

    public void setTer(Terrain t) { ter = t; }

    public Food getFoo() { return foo; }

    public void setFoo(Food f) { foo = f; }

    public ArrayList<String> getFoodList() {
        return foodList;
    }

    public void setFoodList(ArrayList<String> foodList) {
        this.foodList = foodList;
    }
}

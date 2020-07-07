// Jon Sledge
// COP 2552
// Final Project


package wonderzoo;

import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import org.json.*;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    Pane homePane, addPane;
    @FXML
    Button homeBtn, addBtn, submitBtn, deleteBtn;
    @FXML
    ComboBox terrainBox;
    @FXML
    TextField animalNameInput;
    @FXML
    TableView<Animal> animalTable;
    @FXML
    TableView<Food> foodTable;
    @FXML
    CheckBox genderCheckBox;
    @FXML
    TableColumn animalCol, terrainCol, foodCol1, foodCol2, foodCol3, foodCol4, foodCol5;
    private ObservableList<Animal> list = FXCollections.observableArrayList();
    private ObservableList<Food> ob = FXCollections.observableArrayList();


    @FXML
    private void handleNavBtnClick(ActionEvent e) {
        if (e.getSource().equals(homeBtn)) {
            navigateHome();
        } else if (e.getSource().equals(addBtn)) {
            navigateAdd();
        }

    }

    @FXML
    private void handleEditCommit(TableColumn.CellEditEvent<Object, String> objectStringCellEditEvent) throws Exception {
//      read json file contents and update it
        ArrayList<JSONObject> arr = new ArrayList<>();
        readFileContents(arr);
        for (int i = 0; i < arr.size(); i++) {
            if (objectStringCellEditEvent.getOldValue().equals(arr.get(i).get("name"))) {
                System.out.println("found it, replacing value");
                arr.get(i).put("name", objectStringCellEditEvent.getNewValue());
            } else if (objectStringCellEditEvent.getOldValue().equals(arr.get(i).get("terrain"))) {
              System.out.println("found it, replacing value");
              arr.get(i).put("terrain", objectStringCellEditEvent.getNewValue());
            }
        }
        JSONObject o = new JSONObject();
        o.put("animals", arr);
        writeFileContents(o);
        System.out.println("new array is " + arr.toString());
    }

    @FXML
    private void handleFoodEditCommit(TableColumn.CellEditEvent<Object, String> objectStringCellEditEvent) throws Exception {
        ArrayList<JSONObject> arr = new ArrayList<>();
        readFileContents(arr);

        for (int i = 0; i < arr.size(); i++) {
            arr.get(i).getJSONArray("foods").put(objectStringCellEditEvent.getNewValue());

        }

        JSONObject o = new JSONObject();
        o.put("animals", arr);
        writeFileContents(o);

    }

    @FXML
    private void handleDeleteBtnClick(ActionEvent e) throws Exception {
//      read json file contents and remove selected item
        System.out.println(list.get(animalTable.getSelectionModel().getSelectedIndex()).getName());
        Object item = animalTable.getSelectionModel().getSelectedItem();

        ArrayList<JSONObject> arr = new ArrayList<>();
        readFileContents(arr);

        for (int i = 0; i < arr.size(); i++) {
          if (arr.get(i).get("name").equals(list.get(animalTable.getSelectionModel().getSelectedIndex()).getName())) {
            System.out.println("found it, removing...");
            arr.remove(i);
          }
        }
        JSONObject o = new JSONObject();
        o.put("animals", arr);
        writeFileContents(o);
        animalTable.getItems().remove(item);
    }

    @FXML
    private void handleSubmitBtnClick(ActionEvent e) throws Exception {
    	JSONObject animalJson = new JSONObject();
        JSONObject animalJson2 = new JSONObject();
        ArrayList<JSONObject> arr = new ArrayList<>();
        String name = animalNameInput.getText();
        if (!name.matches("[A-Za-z\\s]+")) {
            System.out.println("incorrect input");
            return;
        } else {
        	System.out.println("adding animal...");
            String terrain = terrainBox.getSelectionModel().getSelectedItem().toString();
        	list.add(new Animal(name, terrain));
            animalJson.put("name", list.get(list.size() - 1).getName());

            animalJson.put("terrain", list.get(list.size() - 1).getTerrain());


            ob.addAll(new Food(""), new Food(""), new Food(""), new Food(""), new Food(""));

            animalJson.put("foods", new ArrayList());

            foodTable.setItems(ob);
            animalTable.setItems(list);
            readFileContents(arr);
            arr.add(animalJson);
            animalJson2.put("animals", arr);
            writeFileContents(animalJson2);
            navigateHome();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initBtnStyles();
        initPaneStyles();
        initColStyles();
        animalCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("name"));
        terrainCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("terrain"));
        foodCol1.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));
        foodCol2.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));

        terrainBox.getItems().addAll("Mountain", "Desert", "SnowMountain");
        initFileContents();
        revealBtns();
        animalTable.setEditable(true);
        foodTable.setEditable(true);
        animalCol.setCellFactory(TextFieldTableCell.forTableColumn());
        foodCol1.setCellFactory(TextFieldTableCell.forTableColumn());
        foodCol2.setCellFactory(TextFieldTableCell.forTableColumn());
        foodCol3.setCellFactory(TextFieldTableCell.forTableColumn());
        foodCol4.setCellFactory(TextFieldTableCell.forTableColumn());
        foodCol5.setCellFactory(TextFieldTableCell.forTableColumn());


    }
    public void initFileContents() {
        File temp = new File("Animals.json");
        if (temp.exists()) {
            try {
                String contents = new String((Files.readAllBytes(Paths.get("Animals.json"))));
                JSONObject o = new JSONObject(contents);
                for (int i = 0; i < o.getJSONArray("animals").length(); i++) {
                    String name = o.getJSONArray("animals").getJSONObject(i).get("name").toString();
                    String terrain = o.getJSONArray("animals").getJSONObject(i).get("terrain").toString();
                    list.add(new Animal(name, terrain));
                    animalTable.setEditable(true);
                    animalTable.setItems(list);
                }

                for (int i = 0; i < o.getJSONArray("animals").length(); i++) {
                    if (o.getJSONArray("animals").getJSONObject(i).getJSONArray("foods").length() != 0) {
                        String food = o.getJSONArray("animals").getJSONObject(i).getJSONArray("foods").get(i).toString();
                        ob.add(new Food(food));
                        System.out.println(ob);
                        foodTable.setEditable(true);
                        foodTable.setItems(ob);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void readFileContents(ArrayList<JSONObject> arr) throws Exception {
        File temp = new File("Animals.json");
        if (temp.exists()) {
            String contents = new String((Files.readAllBytes(Paths.get("Animals.json"))));
            JSONObject o = new JSONObject(contents);
            for (int i = 0; i < o.getJSONArray("animals").length(); i++) {
                arr.add(o.getJSONArray("animals").getJSONObject(i));
            }
        }
    }
    public void writeFileContents(JSONObject obj) {
        try (FileWriter file = new FileWriter("Animals.json")) {
            file.write(obj.toString());
            file.flush();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    public void navigateHome() {
        homePane.setStyle("-fx-opacity: 1");
        homePane.toFront();
        addPane.toBack();
        addPane.setStyle("-fx-opacity: 0");
        revealBtns();
    }
    public void navigateAdd() {
        homePane.setStyle("-fx-opacity: 0");
        homePane.toBack();
        addPane.setStyle("-fx-opacity: 1");
        addPane.toFront();
    }
    public void initBtnStyles() {
        homeBtn.styleProperty().bind(Bindings.when(homeBtn.hoverProperty())
                .then("-fx-scale-x: 1.1;-fx-scale-y: 1.1;-fx-scale-z: 1.1;")
                .otherwise(""));
        addBtn.styleProperty().bind(Bindings.when(addBtn.hoverProperty())
                .then("-fx-scale-x: 1.1;-fx-scale-y: 1.1;-fx-scale-z: 1.1;")
                .otherwise(""));
        submitBtn.styleProperty().bind(Bindings.when(submitBtn.hoverProperty())
                .then("-fx-scale-x: 1.1;-fx-scale-y: 1.1;-fx-scale-z: 1.1;")
                .otherwise(""));
        deleteBtn.styleProperty().bind(Bindings.when(deleteBtn.hoverProperty())
                .then("-fx-scale-x: 1.1;-fx-scale-y: 1.1;-fx-scale-z: 1.1;-fx-opacity: 0;")
                .otherwise("-fx-opacity: 0;"));

    }
    public void initPaneStyles() {
        homePane.setStyle("-fx-opacity: 0");
        addPane.setStyle("-fx-opacity: 0");
    }
    public void initColStyles() {
        animalCol.setStyle("-fx-alignment: CENTER;");
        terrainCol.setStyle("-fx-alignment: CENTER;");
        foodCol1.setStyle("-fx-alignment: CENTER;");
        foodCol2.setStyle("-fx-alignment: CENTER;");
        foodCol3.setStyle("-fx-alignment: CENTER;");
        foodCol4.setStyle("-fx-alignment: CENTER;");
        foodCol5.setStyle("-fx-alignment: CENTER;");
    }
    public void revealBtns() {
        File temp = new File("Animals.json");
        if (temp.exists()) {
            deleteBtn.styleProperty().bind(Bindings.when(deleteBtn.hoverProperty())
                    .then("-fx-scale-x: 1.1;-fx-scale-y: 1.1;-fx-scale-z: 1.1;-fx-opacity: 1;")
                    .otherwise("-fx-opacity: 1;"));
        }
    }
}
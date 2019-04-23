package travel.tool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import travel.tool.model.Landmark;
import util.IServer;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ipop
 */
@Controller
public class LandmarkController extends AbstractFxController<Landmark> {
    @Autowired
    private IServer server;

    @FXML
    public Label id;
    @FXML
    public TextField name;
    @FXML
    public TextField location;
    @FXML
    public TextField description;
    @FXML
    public Button submit;
    @FXML
    public Button clear;
    @FXML
    public TableView<Landmark> landmarkTable;
    @FXML
    public TableColumn<Landmark, Long> lId;
    @FXML
    public TableColumn<Landmark, String> lName;
    @FXML
    public TableColumn<Landmark, String> lLocation;
    @FXML
    public TableColumn<Landmark, String> lDescription;
    @FXML
    public TableColumn<Landmark, Boolean> lEdit;
    @FXML
    public TableColumn<Landmark, Boolean> lDelete;

    @Override
    protected void loadDetails() {
        entityList.clear();
        entityList.addAll(server.getAllLandmark());

        landmarkTable.setItems(entityList);
    }

    @Override
    protected void setColumnProperties() {
        lId.setCellValueFactory(new PropertyValueFactory<>("id"));
        lName.setCellValueFactory(new PropertyValueFactory<>("name"));
        lLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        lDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        lEdit.setCellFactory(editCellFactory);
        lDelete.setCellFactory(deleteCellFactory);
    }

    @Override
    protected void update(Landmark entity) {
        id.setText(Long.toString(entity.getId()));
        name.setText(entity.getName());
        location.setText(entity.getLocation());
        description.setText(entity.getLocation());
    }

    @Override
    protected void delete(Landmark entity) {
        if (deleteAlert()) {
            server.deleteLandmark(entity);
        }

        loadDetails();
    }

    @Override
    protected void clearFields() {
        id.setText(null);
        name.clear();
        location.clear();
        description.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        landmarkTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setColumnProperties();
        loadDetails();
    }

    public void submit(ActionEvent actionEvent) {
        Landmark landmark;
        if (id.getText() == null || id.getText().isEmpty()) {
            landmark = new Landmark(getName(), getLocation(), getDescription());
            saveAlert();
        } else {
            landmark = new Landmark(getId(), getName(), getLocation(), getDescription());
            updateAlert(landmark);
        }
        server.updateLandmark(landmark);

        clearFields();
        loadDetails();
    }

    private Long getId() {
        return Long.parseLong(id.getText());
    }

    private String getName() {
        return name.getText();
    }

    private String getLocation() {
        return location.getText();
    }

    private String getDescription() {
        return description.getText();
    }
}

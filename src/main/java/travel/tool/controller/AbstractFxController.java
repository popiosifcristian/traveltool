package travel.tool.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import travel.tool.entity.AbstractEntity;
import travel.tool.util.FxmlView;
import travel.tool.util.StageManager;

import java.util.Optional;

/**
 * @author ipop
 */
public abstract class AbstractFxController<T extends AbstractEntity> implements Initializable {
    @Lazy
    @Autowired
    private StageManager stageManager;

    protected ObservableList<T> entityList = FXCollections.observableArrayList();


    protected Callback<TableColumn<T, Boolean>, TableCell<T, Boolean>> editCellFactory =
            new Callback<TableColumn<T, Boolean>, TableCell<T, Boolean>>() {
                @Override
                public TableCell<T, Boolean> call(TableColumn<T, Boolean> param) {
                    return new TableCell<T, Boolean>() {
                        Image editIcon = new Image(getClass().getResourceAsStream("/icons/edit.png"));
                        Button editButton = new Button();

                        public void updateItem(Boolean check, boolean empty) {
                            super.updateItem(check, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                editButton.setOnAction(event -> {
                                    T entity = getTableView().getItems().get(getIndex());
                                    update(entity);
                                });
                                editButton.setStyle("-fx-background-color: transparent;");
                                ImageView imageView = new ImageView(editIcon);
                                imageView.setPreserveRatio(true);
                                imageView.setSmooth(true);
                                imageView.setCache(true);
                                editButton.setGraphic(imageView);

                                setGraphic(editButton);
                                setAlignment(Pos.CENTER);
                                setText(null);
                            }
                        }
                    };
                }
            };
    protected Callback<TableColumn<T, Boolean>, TableCell<T, Boolean>> deleteCellFactory =
            new Callback<TableColumn<T, Boolean>, TableCell<T, Boolean>>() {
                @Override
                public TableCell<T, Boolean> call(TableColumn<T, Boolean> param) {
                    return new TableCell<T, Boolean>() {
                        Image deleteIcon = new Image(getClass().getResourceAsStream("/icons/delete.png"));
                        Button deleteButton = new Button();

                        public void updateItem(Boolean check, boolean empty) {
                            super.updateItem(check, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                deleteButton.setOnAction(event -> {
                                    T entity = getTableView().getItems().get(getIndex());
                                    delete(entity);
                                });
                                deleteButton.setStyle("-fx-background-color: transparent;");
                                ImageView imageView = new ImageView(deleteIcon);
                                imageView.setPreserveRatio(true);
                                imageView.setSmooth(true);
                                imageView.setCache(true);
                                deleteButton.setGraphic(imageView);

                                setGraphic(deleteButton);
                                setAlignment(Pos.CENTER);
                                setText(null);
                            }
                        }
                    };
                }
            };

    protected abstract void loadDetails();

    protected abstract void setColumnProperties();

    protected abstract void update(T entity);

    protected abstract void delete(T entity);

    protected abstract void clearFields();

    @FXML
    public void clearSelections(ActionEvent actionEvent) {
        clearFields();
    }

    protected void saveAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Entity saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("A new entity has been created.");
        alert.showAndWait();
    }

    protected void updateAlert(T entity) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Entity updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The entity with the id " + entity.getId() + " has been updated.");
        alert.showAndWait();
    }

    protected boolean deleteAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete selected?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean notEmptyValidation(String field, boolean empty) {
        if (!empty) {
            return true;
        } else {
            validationAlert(field, true);
            return false;
        }
    }

    protected void validationAlert(String field, boolean empty) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if (field.equals("Available Places")) {
            alert.setContentText("Please select a number of tickets lower or equal to the actual available places for the trip.");
        } else {
            if (empty) {
                alert.setContentText("Please Enter " + field);
            } else {
                alert.setContentText("Please Enter Valid " + field);
            }
        }
        alert.showAndWait();
    }

    @FXML
    protected void exit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    protected void switchCompanyCrud(ActionEvent event) {
        stageManager.switchScene(FxmlView.COMPANY);
    }

    @FXML
    protected void switchCustomerCrud(ActionEvent event) {
        stageManager.switchScene(FxmlView.CUSTOMER);
    }

    @FXML
    protected void switchEmployeeCrud(ActionEvent event) {
        stageManager.switchScene(FxmlView.EMPLOYEE);
    }

    @FXML
    protected void switchLandmarkCrud(ActionEvent event) {
        stageManager.switchScene(FxmlView.LANDMARK);
    }

    @FXML
    protected void switchTripCrud(ActionEvent event) {
        stageManager.switchScene(FxmlView.TRIP);
    }

    @FXML
    protected void switchBookingCrud(ActionEvent event) {
        stageManager.switchScene(FxmlView.BOOKING);
    }

    @FXML
    protected void logout(ActionEvent event) {
        stageManager.switchScene(FxmlView.LOGIN);
    }
}

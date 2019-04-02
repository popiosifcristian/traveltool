package travel.tool.controller;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import travel.tool.entity.AbstractEntity;

/**
 * @author ipop
 */
public abstract class AbstractFxController<T extends AbstractEntity> {
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

    protected void saveAlert(T entity) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Entity saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The entity with the id " + entity.getId() + " has been created.");
        alert.showAndWait();
    }

    private void updateAlert(T entity) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Entity updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The entity with the id " + entity.getId() + " has been updated.");
        alert.showAndWait();
    }
}

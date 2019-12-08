package zosia.tasks.example.ent.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import lombok.Setter;
import zosia.tasks.example.ent.Navigation;
import zosia.tasks.example.ent.dao.CopseDao;
import zosia.tasks.example.ent.model.Copse;

import java.io.IOException;
import java.net.URL;

import java.util.Optional;
import java.util.ResourceBundle;

public class CopseList implements Initializable {

    @FXML
    private TableView<Copse> table;

    private ObservableList<Copse> copse = FXCollections.observableArrayList();

    private ObjectProperty<Copse> selectedCopse = new SimpleObjectProperty<>();

    @Setter
    private CopseDao dao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setItems(copse);
    }

    public void load(ActionEvent event) {
        copse.clear();
        copse.addAll(dao.findAll());
    }

    public void delete(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() != null) {
            dao.delete(table.getSelectionModel().getSelectedItem());
            load(event);
        }
    }

    public void showEnts(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() != null) {
            try {
                Navigation.getInstance().showEntList(table.getSelectionModel().getSelectedItem());
            } catch (IOException e) {
                //Show popup
            }
        }
    }

    public void addCopse(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add new Copse");
        dialog.setHeaderText("Enter copse name:");
        dialog.setContentText("Name:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            String newName = result.get();

            Copse copse = new Copse(newName);
            this.copse.add(copse);
            dao.add(copse);
        }
    }

    public void showEmpty(ActionEvent actionEvent) {
        copse.clear();
        copse.addAll(dao.findEmpty());


    }

}

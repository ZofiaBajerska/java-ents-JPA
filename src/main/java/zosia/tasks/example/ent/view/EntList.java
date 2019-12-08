package zosia.tasks.example.ent.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import lombok.Setter;
import zosia.tasks.example.ent.Navigation;
import zosia.tasks.example.ent.dao.EntDao;
import zosia.tasks.example.ent.model.Copse;
import zosia.tasks.example.ent.model.Ent;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EntList implements Initializable {

    @FXML
    private TableView<Ent> table;

    private ObservableList<Ent> ents = FXCollections.observableArrayList();

    @Setter
    private EntDao dao;

    @Setter
    private Copse copse;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setItems(ents);
    }

    @FXML
    public void load(ActionEvent event) {
        ents.clear();
        ents.addAll(dao.getEntsFromCopse(copse));
    }

    public void delete(ActionEvent actionEvent) {
        if (table.getSelectionModel().getSelectedItem() != null) {
            dao.delete(table.getSelectionModel().getSelectedItem());
            load(actionEvent);
        }
    }

    public void backToCopse(ActionEvent event) {
        try {
                Navigation.getInstance().showCopseList();
            } catch (IOException e) {
                //Show popup
            }
        }

    public void add(ActionEvent event) {
        try {
            Navigation.getInstance().showAddEnt(copse);
        } catch (IOException e) {

        }
    }

    public void edit(ActionEvent event) {
        try {
            if (table.getSelectionModel().getSelectedItem() != null) {
                Navigation.getInstance().showEditEnt(table.getSelectionModel().getSelectedItem());
            }
        } catch (IOException e) {

        }
    }

    public void trim(ActionEvent actionEvent) {
        try {
            Navigation.getInstance().showTrimEnt(copse);
        } catch (IOException e) {

        }
    }
}

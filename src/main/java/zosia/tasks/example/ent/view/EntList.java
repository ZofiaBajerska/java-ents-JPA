package zosia.tasks.example.ent.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import lombok.Setter;
import zosia.tasks.example.ent.dao.EntDao;
import zosia.tasks.example.ent.model.Copse;
import zosia.tasks.example.ent.model.Ent;


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
    public void load(ActionEvent actionEvent) {
        ents.clear();
        ents.addAll(dao.getEntsFromCopse(copse));
    }

    public void delete(ActionEvent actionEvent) {
    }


}

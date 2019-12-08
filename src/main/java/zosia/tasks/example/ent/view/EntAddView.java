package zosia.tasks.example.ent.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.Setter;
import zosia.tasks.example.ent.Navigation;
import zosia.tasks.example.ent.dao.EntDao;
import zosia.tasks.example.ent.model.Copse;
import zosia.tasks.example.ent.model.Ent;
import zosia.tasks.example.ent.model.EntType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class EntAddView implements Initializable {

    @FXML
    private TextField Name;

    @FXML
    private TextField Height;

    @FXML
    private ComboBox<EntType> cbSpecies;

    @Setter
    private EntDao dao;

    @Setter
    private Copse copse;

    private Ent ent;

    public void setEnt(Ent ent){
        this.ent = ent;
        if (ent != null){
            copse = ent.getCopse();

            cbSpecies.getSelectionModel().select(ent.getType());
            Name.setText(ent.getName());
            Height.setText(String.valueOf(ent.getHeight()));

            Name.setEditable(false);
        }
    }

    public void add(ActionEvent actionEvent) {
        int heightInt = 0;
        try {
            heightInt = Integer.parseInt(Height.getText());
        } catch (NumberFormatException ex) {
        }
        dao.add(new Ent(Name.getText(), EntType.valueOf(cbSpecies.getSelectionModel().getSelectedItem().toString()), heightInt, copse));
        try {
            Navigation.getInstance().showEntList(copse);
        } catch (IOException e) {
            //Show popup
        }
    }

    public void save(ActionEvent actionEvent) {
        int heightInt = 0;
        try {
            heightInt = Integer.parseInt(Height.getText());
            ent.setHeight(heightInt);
        } catch (NumberFormatException ex) {
        }
        ent.setType(EntType.valueOf(cbSpecies.getSelectionModel().getSelectedItem().toString()));
        dao.add(ent);
        try {
            Navigation.getInstance().showEntList(copse);
        } catch (IOException e) {
            //Show popup
        }
    }

    public void cancel(ActionEvent actionEvent) {
        try {
            Navigation.getInstance().showEntList(copse);
        } catch (IOException e) {
            //Show popup
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbSpecies.getItems().addAll(EntType.values());
        if (cbSpecies.getItems().size() > 0) {
            cbSpecies.getSelectionModel().select(0);
        }

    }

    public void apply(ActionEvent actionEvent) {
        if (ent != null){
            save(actionEvent);
        }
        else {
           add(actionEvent);
        }
    }
}

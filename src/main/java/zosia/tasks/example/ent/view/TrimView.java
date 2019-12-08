package zosia.tasks.example.ent.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class TrimView implements Initializable {

    @FXML
    private TextField trimFrom;

    @FXML
    private TextField trimTo;

    @Setter
    private EntDao dao;

    @Setter
    private Copse copse;

    public void trimEnts(ActionEvent actionEvent) {


        try {
            final int toInt = Integer.parseInt(trimTo.getText());
            final int fromInt = Integer.parseInt(trimFrom.getText());

            if((fromInt > toInt) && (toInt >0)) {
                dao.trim(copse, fromInt, toInt);
                        //getEntsFromCopse(copse).stream().filter(e -> e.getHeight() > fromInt).forEach(e -> e.setHeight(toInt));
            }
        } catch (NumberFormatException ex) {
        }
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

    }
}

package zosia.tasks.example.ent.view;

import javafx.event.ActionEvent;
import lombok.Setter;
import zosia.tasks.example.ent.Navigation;
import zosia.tasks.example.ent.model.Copse;

import java.io.IOException;

public class TrimView {

    @Setter
    private Copse copse;


    public void trimEnt(ActionEvent actionEvent) {
    }

    public void cancel(ActionEvent actionEvent) {
        try {
            Navigation.getInstance().showEntList(copse);
        } catch (IOException e) {
            //Show popup
        }
    }
}

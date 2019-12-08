package zosia.tasks.example.ent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;
import zosia.tasks.example.ent.model.Copse;
import zosia.tasks.example.ent.model.Ent;
import zosia.tasks.example.ent.view.CopseList;
import zosia.tasks.example.ent.view.EntAddView;
import zosia.tasks.example.ent.view.EntList;
import zosia.tasks.example.ent.view.TrimView;

import java.io.IOException;


public class Navigation {

    private final static Navigation instance = new Navigation();

    @Setter
    private Stage stage;

    public static Navigation getInstance() {
        return instance;
    }


    public void showCopseList() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Navigation.class.getResource("/zosia/tasks/example/ent/view/copse_list.fxml"));

        Parent parent = loader.load();
        CopseList controller = loader.getController();
        controller.setDao(DaoFactory.getInstance().getCopseDao());

        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }

    public void showEntList(Copse copse) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Navigation.class.getResource("/zosia/tasks/example/ent/view/ent_list.fxml"));

        Parent parent = loader.load();
        EntList controller = loader.getController();
        controller.setDao(DaoFactory.getInstance().getEntDao());
        controller.setCopse(copse);

        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }

    public void showAddEnt(Copse copse) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Navigation.class.getResource("/zosia/tasks/example/ent/view/ent_add_view.fxml"));

        Parent parent = loader.load();
        EntAddView controller = loader.getController();
        controller.setDao(DaoFactory.getInstance().getEntDao());
        controller.setCopse(copse);

        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }

    public void showEditEnt(Ent ent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Navigation.class.getResource("/zosia/tasks/example/ent/view/ent_add_view.fxml"));

        Parent parent = loader.load();
        EntAddView controller = loader.getController();
        controller.setDao(DaoFactory.getInstance().getEntDao());
        controller.setEnt(ent);

        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }

    public void showTrimEnt(Copse copse) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Navigation.class.getResource("/zosia/tasks/example/ent/view/trim_view.fxml"));

        Parent parent = loader.load();
        TrimView controller = loader.getController();
        controller.setDao(DaoFactory.getInstance().getEntDao());
        controller.setCopse(copse);

        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }

}


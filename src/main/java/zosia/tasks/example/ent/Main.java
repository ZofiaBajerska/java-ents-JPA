package zosia.tasks.example.ent;


import javafx.application.Application;
import javafx.stage.Stage;
import zosia.tasks.example.ent.model.Copse;
import zosia.tasks.example.ent.model.Ent;
import zosia.tasks.example.ent.model.EntType;

import javax.persistence.EntityManager;
import java.io.Closeable;
import java.io.IOException;

public class Main extends Application implements Closeable {


    public void initData() {
        EntityManager em = DaoFactory.getInstance().createEntityManager();
        em.getTransaction().begin();

        Copse first = new Copse();
        first.setName("First");
        Copse second = new Copse();
        second.setName("Second");

        Ent ziutek = new Ent("Ziutek", EntType.WILLOW, 203, first);
        Ent zbychu = new Ent("Zbychu", EntType.WILLOW, 221, second);
        Ent zyta = new Ent("Zyta", EntType.BIRCH, 221, first );
        Ent zuza = new Ent("Zuza", EntType.LARCH, 263, second );

        em.persist(first);
        em.persist(second);

        em.persist(ziutek);
        em.persist(zbychu);
        em.persist(zyta);
        em.persist(zuza);

        em.getTransaction().commit();
        em.close();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initData();
        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().showCopseList();
        primaryStage.show();
    }

    @Override
    public void close() throws IOException {
        DaoFactory.getInstance().close();
    }

}

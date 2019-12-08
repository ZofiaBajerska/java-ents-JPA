package zosia.tasks.example.ent;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zosia.tasks.example.ent.dao.CopseDao;
import zosia.tasks.example.ent.dao.EntDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Closeable;
import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DaoFactory implements Closeable {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookPu");

    @Getter
    private CopseDao copseDao = new CopseDao(emf);

    @Getter
    private EntDao entDao = new EntDao(emf);

    private static final DaoFactory instance = new DaoFactory();

    public static DaoFactory getInstance() {
        return instance;
    }

    public EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void close() throws IOException {
        emf.close();
    }
}

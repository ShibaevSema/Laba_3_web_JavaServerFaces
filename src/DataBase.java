import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataBase {

    public static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory( "ResultUnit" );
    public static EntityManager em =
            emf.createEntityManager();

}

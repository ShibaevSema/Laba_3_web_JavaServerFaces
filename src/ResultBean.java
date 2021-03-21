import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "Results", eager = true)
@SessionScoped
public class ResultBean {

    private EntityManager em = DataBase.em;

    public List<Result> getResults() {
        DataBase.em.getTransaction().begin();
        List<Result> list =
                DataBase.em.createQuery("SELECT c FROM Result c", Result.class).getResultList();
        DataBase.em.getTransaction().commit();
        return list;
    }

    private List<Result> results;

    public ResultBean() {
        results = new ArrayList<Result>();
    }

}

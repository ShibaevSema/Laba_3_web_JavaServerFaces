import javax.persistence.*;

import static java.lang.Math.pow;

@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double x;
    private Double y;
    private Double r;
    private Boolean isInArea;

    public Result(Double x, Double y, Double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isInArea = false ;
    }

    public Result() {
        this.x = 0.;
        this.y = 0.;
        this.r = 0.;
        isInArea = true;
    }

    public String getX() {
        return String.format("%.2f", x);
    }

    public void setX(Double x) {
        this.x = x;
    }

    public String getY() {
        return String.format("%.2f", y);
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getR() {
        return String.format("%.2f", r);
    }

    public void setR(Double r) {
        this.r = r;
    }

    public String getIsInArea(){
        return isInArea ? "Yes , point hits " : "No , point is out";
    }

    void setIsInArea(Boolean inArea) {
        this.isInArea = inArea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Result(Double x, Double y, Integer r) {
        this.x = 0.;
        this.y = 0.;
        this.r = 0.;
        isInArea = true;
    }

    void save() {
        DataBase.em.getTransaction().begin();
        DataBase.em.persist(this);
        DataBase.em.getTransaction().commit();
    }

    boolean checkArea(Result result) {
        Double X = result.x;
        Double Y = result.y;
        Double R = result.r;
        if (X >= 0 && Y >= 0 && Y+0.5*X<= 0.5*R) {
            return true;
        }

        if (X <= 0 && Y <= 0 && pow(X, 2) + pow(Y, 2) <= pow(R , 2)) {
            return true;
        }

        if (X >= 0 && Y <= 0 && X <=R && Y >= -0.5*R){
            return true;
        }
        return false;
    }
}

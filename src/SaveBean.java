import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Arrays;

@ManagedBean(name = "saveBean", eager = true)
@SessionScoped
public class SaveBean implements Serializable {

    private Double x;
    private Double y;
    private Double r;

    private Boolean inArea;


    public SaveBean() {
        this.x = 0.;
        this.y = 0.;
        this.r = 1.;
        this.inArea = false;
    }

    public void onSubmit(){
        Result result = new Result(x,y,r);
        result.setIsInArea(result.checkArea(result));
        result.save();

    }

    public void onRChecked(Double value){
        if (Arrays.asList(1d, 2d, 3d, 4d, 5d).contains(value))
            this.r = value;
    }


    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public String ready() {
        return "success";
    }

    public String back() {
        return "success";
    }
}

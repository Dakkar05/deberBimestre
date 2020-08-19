
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class MegaMenuView {
  
    private String orientation = "horizontal";
 
    public String getOrientation() {
        return orientation;
    }
 
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
}
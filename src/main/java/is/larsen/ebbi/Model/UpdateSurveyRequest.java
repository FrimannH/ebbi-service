package is.larsen.ebbi.Model;

import java.io.Serializable;
import lombok.Data;

@Data
public class UpdateSurveyRequest implements Serializable {
    private Integer customerId;
    private String input;
}

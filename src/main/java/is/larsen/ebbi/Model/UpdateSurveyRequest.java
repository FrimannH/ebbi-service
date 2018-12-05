package is.larsen.ebbi.Model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateSurveyRequest implements Serializable {
    private Integer customerId;
    private List<Answer> answers;
}

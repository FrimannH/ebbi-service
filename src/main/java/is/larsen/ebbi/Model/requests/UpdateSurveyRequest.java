package is.larsen.ebbi.Model.requests;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateSurveyRequest implements Serializable {
    private Integer customerId;
    private String surveyStartDate;
    private String surveyEndDate;
    private String surveyAgeFrom;
    private String surveyAgeTo;
    private List<AnswerRequest> surveyResponses;
}

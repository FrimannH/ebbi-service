package is.larsen.ebbi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportAnswers {
    private Integer questionId;
    private String questionName;
    private String questionOverviewName;
    private String questionShortDescription;
    private String questionText;
    private List<AnswerResponse> answers;
}

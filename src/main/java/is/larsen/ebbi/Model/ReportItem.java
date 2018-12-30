package is.larsen.ebbi.Model;

import is.larsen.ebbi.Model.ReportScore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportItem {
    private Integer questionId;
    private String questionName;
    private String questionShortDescription;
    private String questionText;
    private Integer count;
    private String ebbiScore;
    private ReportScore reportScore;
    private ReportDetails reportDetails;

    public ReportItem(Integer questionId, String questionName, String questionShortDescription, String questionText, Integer count) {
        this.questionId = questionId;
        this.questionName = questionName;
        this.questionShortDescription = questionShortDescription;
        this.questionText = questionText;
        this.count = count;
    }
}

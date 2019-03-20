package is.larsen.ebbi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportItem {
    private Integer questionId;
    private String questionName;
    private String questionOverviewName;
    private String questionShortDescription;
    private String questionText;
    private Integer count;
    private ReportScores reportScores;
    private ReportDetails reportDetails;

    public ReportItem(Integer questionId, String questionName, String questionOverviewName, String questionShortDescription, String questionText, Integer count) {
        this.questionId = questionId;
        this.questionName = questionName;
        this.questionOverviewName = questionOverviewName;
        this.questionShortDescription = questionShortDescription;
        this.questionText = questionText;
        this.count = count;
    }

    public ReportItem(Integer questionId, String questionName) {
        this.questionId = questionId;
        this.questionName = questionName;
    }
}

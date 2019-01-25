package is.larsen.ebbi.Model.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerImportanceSummary {
    private Integer questionId;
    private String questionName;
    private String questionScore;
    private String questionScorePercentage;
    private Integer questionCount;
}

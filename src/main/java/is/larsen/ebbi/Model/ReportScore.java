package is.larsen.ebbi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportScore {
    private Integer veryWell;
    private Integer well;
    private Integer moderate;
    private Integer badly;
    private Integer veryBadly;
    private Integer validAnswers;
    private Integer totalAnswers;
    private String mean;
    private String median;
    private String standardDeviation;
    private String yourMean;
    private String yourMedian;
    private String ebbiScore;
    private String ebbiIndex;

}

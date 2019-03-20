package is.larsen.ebbi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportScores {
    private Score veryWell;
    private Score well;
    private Score moderate;
    private Score badly;
    private Score veryBadly;
    private Score validAnswers;
    private Score totalAnswers;
    private Score didNotAnswer;
    private String meanPercentage;
    private String meanPercentageOneBased;
    private String medianPercentage;
    private String standardDeviation;
    private String yourMean;
    private String yourMedian;
    private String ebbiScore;
    private String ebbiIndex;
    private String ebbiPercentage;
    private String ebbiPercentageOneBased;
}

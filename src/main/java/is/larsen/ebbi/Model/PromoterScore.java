package is.larsen.ebbi.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoterScore {
    private Score detractors;
    private Score passives;
    private Score promoters;
    private Integer score1Count;
    private Integer score2Count;
    private Integer score3Count;
    private Integer score4Count;
    private Integer score5Count;
    private Integer score6Count;
    private Integer score7Count;
    private Integer score8Count;
    private Integer score9Count;
    private Integer score10Count;
}

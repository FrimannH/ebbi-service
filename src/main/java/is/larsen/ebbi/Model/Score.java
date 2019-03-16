package is.larsen.ebbi.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Score {
    private Integer value;
    private String percentage;
    private String percentageAdjusted;
    private String strokeDashStart;
    private String strokeDashEnd;
    private String strokeOffset;
    private String strokeDashArray;
}

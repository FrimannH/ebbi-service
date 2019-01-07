package is.larsen.ebbi.Model.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDetailsData {
    private String well;
    private String moderate;
    private String badly;
    private String score;
}

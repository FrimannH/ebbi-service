package is.larsen.ebbi.Model.responses;

import is.larsen.ebbi.Model.ReportDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerImportanceDetails {
    private String customerImportanceQuestion;
    private ReportDetails customerImportanceDetails;
}

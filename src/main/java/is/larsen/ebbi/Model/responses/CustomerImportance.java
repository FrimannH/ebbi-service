package is.larsen.ebbi.Model.responses;


import is.larsen.ebbi.Model.ReportDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerImportance extends BaseResponse {
    private List<CustomerImportanceSummary> customerImportanceSummary;
    private String customerImportanceQuestion;
    private ReportDetails customerImportanceDetails;
}

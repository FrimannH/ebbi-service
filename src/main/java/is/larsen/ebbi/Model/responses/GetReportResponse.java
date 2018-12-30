package is.larsen.ebbi.Model.responses;

import is.larsen.ebbi.Model.ReportItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetReportResponse extends BaseResponse {
    private String customerName;
    private Integer customerId;
    private List<ReportItem> reportItems;
}

package is.larsen.ebbi.Model.responses;

import is.larsen.ebbi.Model.PromoterScore;
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
    private PromoterScore promoterScore;
    private List<ReportItem> reportItems;
    private CustomerImportance customerImportance;
    private List<ReportItem> customerImportanceItems;
    private String mean;
    private String differentiation;
    private String segmentation;
    private String imageAndPerception;
    private String differentiationPercentage;
    private String segmentationPercentage;
    private String imageAndPerceptionPercentage;
    private String reportStartDate;
    private String reportEndDate;
    private String respondentCount;
    private String ageRangeFrom;
    private String ageRangeTo;
}

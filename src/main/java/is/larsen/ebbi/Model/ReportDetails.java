package is.larsen.ebbi.Model;


import is.larsen.ebbi.Model.responses.ReportDetailsData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDetails {
    private ReportDetailsData male;
    private ReportDetailsData female;
    private ReportDetailsData ageLessThan45;
    private ReportDetailsData age46to55;
    private ReportDetailsData age56to65;
    private ReportDetailsData ageOver66;
    private ReportDetailsData income1;
    private ReportDetailsData income2;
    private ReportDetailsData education1;
    private ReportDetailsData education2;
    private ReportDetailsData detractors;
    private ReportDetailsData passives;
    private ReportDetailsData promoters;
    private ReportDetailsData switchSupplierYes;
    private ReportDetailsData switchSupplierNo;
}

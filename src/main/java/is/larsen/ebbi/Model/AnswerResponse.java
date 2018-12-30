package is.larsen.ebbi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponse {

    private Integer questionId;
    private Integer questionType;
    private Integer answerValue;
    private Integer gender;
    private Integer age;
    private Integer income;
    private Integer education;
    private Integer switchSuppliers;
    private String countryCode;
    private String region;
    private String timeStamp;

}

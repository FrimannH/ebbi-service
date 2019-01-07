package is.larsen.ebbi.Model.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSurveyResponse extends BaseResponse {
    private Integer rowsAffected;

    public UpdateSurveyResponse(Integer status, String message, Integer rowsAffected) {
        super(status, message);
        this.rowsAffected = rowsAffected;
    }
}

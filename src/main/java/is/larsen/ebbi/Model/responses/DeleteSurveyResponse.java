package is.larsen.ebbi.Model.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteSurveyResponse extends BaseResponse {
    private Integer rowsAffected;

    public DeleteSurveyResponse(Integer status, String message, Integer rowsAffected) {
        super(status, message);
        this.rowsAffected = rowsAffected;
    }
}

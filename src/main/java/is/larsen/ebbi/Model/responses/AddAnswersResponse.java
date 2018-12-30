package is.larsen.ebbi.Model.responses;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddAnswersResponse extends BaseResponse {

    public AddAnswersResponse(Integer status, String message) {
        super(status, message);
    }
}

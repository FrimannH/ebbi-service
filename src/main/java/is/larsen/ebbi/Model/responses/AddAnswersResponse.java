package is.larsen.ebbi.Model.responses;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddAnswersResponse extends BaseResponse {
    private Integer count;

    public AddAnswersResponse(Integer status, String message, Integer count) {
        super(status, message);
        this.count = count;
    }
}

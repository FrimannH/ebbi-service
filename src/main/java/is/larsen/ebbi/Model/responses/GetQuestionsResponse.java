package is.larsen.ebbi.Model.responses;


import is.larsen.ebbi.Model.Question;
import is.larsen.ebbi.Model.responses.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetQuestionsResponse extends BaseResponse {
    private List<Question> questions;
}

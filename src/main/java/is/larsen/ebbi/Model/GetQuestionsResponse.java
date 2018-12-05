package is.larsen.ebbi.Model;


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

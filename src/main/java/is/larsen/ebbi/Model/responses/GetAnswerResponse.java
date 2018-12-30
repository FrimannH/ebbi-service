package is.larsen.ebbi.Model.responses;


import is.larsen.ebbi.Model.AnswerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAnswerResponse {
    private Integer customerId;
    private Integer count;
    private List<AnswerResponse> answerResponses;

    public GetAnswerResponse(Integer customerId, List<AnswerResponse> answerResponses) {
        this.customerId = customerId;
        this.answerResponses = answerResponses;
        this.count = answerResponses.size();
    }
}

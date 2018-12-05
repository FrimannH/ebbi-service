package is.larsen.ebbi.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private Integer questionId;
    private String questionName;
    private String questionShortDescription;
    private String questionLongDescription;
}
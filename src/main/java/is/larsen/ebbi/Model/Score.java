package is.larsen.ebbi.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Score {
    private Integer value;
    private String percentage;
}

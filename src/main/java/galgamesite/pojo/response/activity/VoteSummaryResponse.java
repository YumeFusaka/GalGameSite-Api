package galgamesite.pojo.response.activity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VoteSummaryResponse {
    private Integer edition;
    private Long usedVotes;
}

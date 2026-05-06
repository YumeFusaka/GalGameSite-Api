package galgamesite.pojo.response.activity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VoteQuotaResponse {
    private Integer edition;
    private Integer totalVotes;
}

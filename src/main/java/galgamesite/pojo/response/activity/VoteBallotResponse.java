package galgamesite.pojo.response.activity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class VoteBallotResponse extends VotingResultResponse {
    private Long votesCastCount;
    private Integer edition;
}

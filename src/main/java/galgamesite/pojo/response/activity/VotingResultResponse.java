package galgamesite.pojo.response.activity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import galgamesite.pojo.response.game.GameResponse;

@EqualsAndHashCode(callSuper = true)
@Data
public class VotingResultResponse extends GameResponse {
    private Long totalVotes;
    private Long totalRank;
}

package yumefusaka.galgamesite.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GalGameTwelveVotingResultVO extends GalGameVO {

    Long totalVotes;

    Long totalRank;
}

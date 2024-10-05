package yumefusaka.galgamesite.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GalGameVoteResultVO extends GalGameVO {

    long myVote;

    long myRank;
}

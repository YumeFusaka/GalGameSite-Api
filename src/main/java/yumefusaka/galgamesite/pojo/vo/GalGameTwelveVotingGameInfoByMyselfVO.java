package yumefusaka.galgamesite.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GalGameTwelveVotingGameInfoByMyselfVO extends GalGameTwelveVotingResultVO {

    Long votesCastCount;
}


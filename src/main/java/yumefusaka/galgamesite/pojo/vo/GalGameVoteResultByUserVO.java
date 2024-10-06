package yumefusaka.galgamesite.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GalGameVoteResultByUserVO extends GalGameVoteResultVO {

    Long id;

    Integer voteByUser;
}

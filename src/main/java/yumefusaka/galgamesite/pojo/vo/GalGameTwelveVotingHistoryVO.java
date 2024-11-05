package yumefusaka.galgamesite.pojo.vo;

import lombok.Data;

@Data
public class GalGameTwelveVotingHistoryVO {

    Long id;

    Long subjectId;

    String translatedName;

    String imgUrl;

    Long votesCastCount;
}

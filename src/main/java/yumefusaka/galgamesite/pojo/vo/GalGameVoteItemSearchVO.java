package yumefusaka.galgamesite.pojo.vo;

import lombok.Data;

@Data
public class GalGameVoteItemSearchVO {
    long subjectId;

    String name;

    long totalVote;

    String url;
}

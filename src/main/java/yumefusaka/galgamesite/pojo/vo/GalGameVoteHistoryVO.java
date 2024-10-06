package yumefusaka.galgamesite.pojo.vo;

import lombok.Data;

@Data
public class GalGameVoteHistoryVO {

    long id;

    long subjectId;

    String name;

    String url;

    int voteByMe;
}

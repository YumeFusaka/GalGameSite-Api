package galgamesite.pojo.response.activity;

import lombok.Data;

@Data
public class VotingGameListItemResponse {
    private Long subjectId;
    private String translatedName;
    private Long totalVotes;
    private String imgUrl;
}

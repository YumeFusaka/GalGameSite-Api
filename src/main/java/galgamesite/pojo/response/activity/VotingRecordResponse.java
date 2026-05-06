package galgamesite.pojo.response.activity;

import lombok.Data;

@Data
public class VotingRecordResponse {
    private Long id;
    private Long subjectId;
    private String translatedName;
    private String imgUrl;
    private Long votesCastCount;
}

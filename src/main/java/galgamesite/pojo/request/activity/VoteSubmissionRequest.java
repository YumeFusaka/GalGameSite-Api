package galgamesite.pojo.request.activity;

import lombok.Data;

@Data
public class VoteSubmissionRequest {
    private Long subjectId;
    private Long votesCastCount;
}

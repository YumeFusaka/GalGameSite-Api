package galgamesite.pojo.request.activity;

import lombok.Data;

@Data
public class VoteQuotaUpdateRequest {
    private Integer edition;
    private Integer totalVotes;
}

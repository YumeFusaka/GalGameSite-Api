package galgamesite.pojo.request.activity;

import lombok.Data;

import java.util.List;

@Data
public class TierMakerRecordSaveRequest {
    private List<String> rankNames;
    private List<List<TierMakerSubjectRequest>> tiers;
}

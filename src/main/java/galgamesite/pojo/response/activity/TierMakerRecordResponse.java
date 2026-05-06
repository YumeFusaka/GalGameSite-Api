package galgamesite.pojo.response.activity;

import lombok.Data;

import java.util.List;

@Data
public class TierMakerRecordResponse {
    private List<String> rankNames;
    private List<List<TierMakerSubjectResponse>> tiers;
}

package galgamesite.controller.activity;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import galgamesite.common.context.BaseContext;
import galgamesite.common.result.Result;
import galgamesite.pojo.entity.GalGameTierMakerRank;
import galgamesite.pojo.entity.GalGameTierMakerSubject;
import galgamesite.pojo.request.activity.TierMakerRecordSaveRequest;
import galgamesite.pojo.request.activity.TierMakerSubjectRequest;
import galgamesite.pojo.response.activity.TierMakerRecordResponse;
import galgamesite.pojo.response.activity.TierMakerSubjectResponse;
import galgamesite.service.TierMakerRankService;
import galgamesite.service.TierMakerSubjectService;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/activities/tier-maker")
@Tag(name = "GalGameTierMaker")
public class TierMakerController {

    @Autowired
    private TierMakerSubjectService tierMakerSubjectService;

    @Autowired
    private TierMakerRankService tierMakerRankService;

    @Operation(summary = "获取tier记录")
    @GetMapping("/records/me")
    public Result<TierMakerRecordResponse> getCurrentUserRecord() {
        String uin = BaseContext.getCurrentId();

        List<GalGameTierMakerRank> galGameTierMakerRanks = tierMakerRankService.list(
                new QueryWrapper<GalGameTierMakerRank>().eq("user_uin",uin).orderByAsc("rank_level")
        );

        List<String> rankNameList = new ArrayList<>();

        for(GalGameTierMakerRank galGameTierMakerRank : galGameTierMakerRanks){
            rankNameList.add(galGameTierMakerRank.getRankName());
        }

        int rankLength = rankNameList.size();

        List<GalGameTierMakerSubject> galGameTierMakerSubjects = tierMakerSubjectService.list(
                new QueryWrapper<GalGameTierMakerSubject>().eq("user_uin",uin)
                        .orderByAsc("rank_level")
                        .orderByAsc("rank_order")
        );
        List<List<TierMakerSubjectResponse>> tiers = new ArrayList<>();
        for(int i = 0; i < rankLength; i++){
            tiers.add(new ArrayList<>());
        }

        for(GalGameTierMakerSubject galGameTierMakerSubject : galGameTierMakerSubjects){
            TierMakerSubjectResponse subjectResponse = new TierMakerSubjectResponse();
            subjectResponse.setSubjectId(galGameTierMakerSubject.getGalgameSubjectId());
            subjectResponse.setImgUrl(galGameTierMakerSubject.getGalgameImgUrl());
            tiers.get(Math.toIntExact(galGameTierMakerSubject.getRankLevel())).add(subjectResponse);
        }
        TierMakerRecordResponse response = new TierMakerRecordResponse();
        response.setRankNames(rankNameList);
        response.setTiers(tiers);

        BaseContext.removeCurrentId();
        return Result.success(response);
    }

    @Operation(summary = "保存tier记录")
    @PutMapping("/records/me")
    public Result<String> saveCurrentUserRecord(@RequestBody TierMakerRecordSaveRequest request) {
        String uin = BaseContext.getCurrentId();

        tierMakerRankService.remove(new QueryWrapper<GalGameTierMakerRank>().eq("user_uin",uin));
        tierMakerSubjectService.remove(new QueryWrapper<GalGameTierMakerSubject>().eq("user_uin",uin));

        List<String> rankNameList = request.getRankNames();

        for(int i = 0; i < rankNameList.size(); i++){
            GalGameTierMakerRank galGameTierMakerRank = new GalGameTierMakerRank();
            galGameTierMakerRank.setRankName(rankNameList.get(i));
            galGameTierMakerRank.setUserUin(uin);
            galGameTierMakerRank.setRankLevel((long) i);
            tierMakerRankService.save(galGameTierMakerRank);
        }

        List<List<TierMakerSubjectRequest>> tiers = request.getTiers();
        for(int i = 0; i < tiers.size(); i++){
            for(int j = 0; j < tiers.get(i).size(); j++){
                Long rankSubject = tiers.get(i).get(j).getSubjectId();
                String rankImgUrl = tiers.get(i).get(j).getImgUrl();
                GalGameTierMakerSubject galGameTierMakerSubject = new GalGameTierMakerSubject();
                galGameTierMakerSubject.setRankLevel((long) i);
                galGameTierMakerSubject.setUserUin(uin);
                galGameTierMakerSubject.setGalgameSubjectId(rankSubject);
                galGameTierMakerSubject.setRankOrder((long) j);
                galGameTierMakerSubject.setGalgameImgUrl(rankImgUrl);
                tierMakerSubjectService.save(galGameTierMakerSubject);
            }
        }

        BaseContext.removeCurrentId();
        return Result.success("保存成功");
    }

}

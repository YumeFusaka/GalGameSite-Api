package yumefusaka.galgamesite.controller.activity;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yumefusaka.galgamesite.common.context.BaseContext;
import yumefusaka.galgamesite.common.result.Result;
import yumefusaka.galgamesite.pojo.dto.GalGameTierMakerRecordDTO;
import yumefusaka.galgamesite.pojo.dto.GalGameTierMakerSubjectDTO;
import yumefusaka.galgamesite.pojo.entity.GalGameTierMakerRank;
import yumefusaka.galgamesite.pojo.entity.GalGameTierMakerSubject;
import yumefusaka.galgamesite.pojo.vo.GalGameTierMakerRecordVO;
import yumefusaka.galgamesite.pojo.vo.GalGameTierMakerSubjectVO;
import yumefusaka.galgamesite.service.IGalGameService;
import yumefusaka.galgamesite.service.IGalGameTierMakerRankService;
import yumefusaka.galgamesite.service.IGalGameTierMakerSubjectService;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/activity/galgame-tier-maker")
@Tag(name = "GalGameTierMaker")
public class GalGameTierMakerController {

    @Autowired
    private IGalGameTierMakerSubjectService galGameTierMakerSubjectService;

    @Autowired
    private IGalGameTierMakerRankService galGameTierMakerRankService;

    @Operation(summary = "获取tier记录")
    @GetMapping("/record")
    public Result<GalGameTierMakerRecordVO> getGalGameTierMakerRecord() {
        String uin = BaseContext.getCurrentId();
        BaseContext.removeCurrentId();

        List<GalGameTierMakerRank> galGameTierMakerRanks = galGameTierMakerRankService.list(
                new QueryWrapper<GalGameTierMakerRank>().eq("user_uin",uin).orderByAsc("rank_level")
        );

        List<String> rankNameList = new ArrayList<>();

        for(GalGameTierMakerRank galGameTierMakerRank : galGameTierMakerRanks){
            rankNameList.add(galGameTierMakerRank.getRankName());
        }

        int rankLength = rankNameList.size();

        List<GalGameTierMakerSubject> galGameTierMakerSubjects = galGameTierMakerSubjectService.list(
                new QueryWrapper<GalGameTierMakerSubject>().eq("user_uin",uin)
                        .orderByAsc("rank_level")
                        .orderByAsc("rank_order")
        );
        List<List<GalGameTierMakerSubjectVO>> rankSubjectList = new ArrayList<>();
        for(int i = 0; i < rankLength; i++){
            rankSubjectList.add(new ArrayList<>());
        }

        for(GalGameTierMakerSubject galGameTierMakerSubject : galGameTierMakerSubjects){
            GalGameTierMakerSubjectVO galGameTierMakerSubjectVO = new GalGameTierMakerSubjectVO();
            galGameTierMakerSubjectVO.setSubjectId(galGameTierMakerSubject.getGalgameSubjectId());
            galGameTierMakerSubjectVO.setImgUrl(galGameTierMakerSubject.getGalgameImgUrl());
            rankSubjectList.get(Math.toIntExact(galGameTierMakerSubject.getRankLevel())).add(galGameTierMakerSubjectVO);
        }
        GalGameTierMakerRecordVO galGameTierMakerRecordVO = new GalGameTierMakerRecordVO();
        galGameTierMakerRecordVO.setRankNameList(rankNameList);
        galGameTierMakerRecordVO.setRankSubjectList(rankSubjectList);
        return Result.success(galGameTierMakerRecordVO);
    }

    @Operation(summary = "保存tier记录")
    @PostMapping("/record")
    public Result<String> postGalGameTierMakerRecord(@RequestBody GalGameTierMakerRecordDTO galGameTierMakerRecordDTO) {
        String uin = BaseContext.getCurrentId();
        BaseContext.removeCurrentId();

        galGameTierMakerRankService.remove(new QueryWrapper<GalGameTierMakerRank>().eq("user_uin",uin));
        galGameTierMakerSubjectService.remove(new QueryWrapper<GalGameTierMakerSubject>().eq("user_uin",uin));

        List<String> rankNameList = galGameTierMakerRecordDTO.getRankNameList();

        for(int i = 0; i < rankNameList.size(); i++){
            GalGameTierMakerRank galGameTierMakerRank = new GalGameTierMakerRank();
            galGameTierMakerRank.setRankName(rankNameList.get(i));
            galGameTierMakerRank.setUserUin(uin);
            galGameTierMakerRank.setRankLevel((long) i);
            galGameTierMakerRankService.save(galGameTierMakerRank);
        }

        List<List<GalGameTierMakerSubjectDTO>> rankSubjectList = galGameTierMakerRecordDTO.getRankSubjectList();
        for(int i = 0; i < rankSubjectList.size(); i++){
            for(int j = 0; j < rankSubjectList.get(i).size(); j++){
                Long rankSubject = rankSubjectList.get(i).get(j).getSubjectId();
                String rankImgUrl = rankSubjectList.get(i).get(j).getImgUrl();
                GalGameTierMakerSubject galGameTierMakerSubject = new GalGameTierMakerSubject();
                galGameTierMakerSubject.setRankLevel((long) i);
                galGameTierMakerSubject.setUserUin(uin);
                galGameTierMakerSubject.setGalgameSubjectId(rankSubject);
                galGameTierMakerSubject.setRankOrder((long) j);
                galGameTierMakerSubject.setGalgameImgUrl(rankImgUrl);
                galGameTierMakerSubjectService.save(galGameTierMakerSubject);
            }
        }
        return Result.success("保存成功");
    }

}

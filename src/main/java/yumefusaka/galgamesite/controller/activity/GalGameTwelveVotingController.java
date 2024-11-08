package yumefusaka.galgamesite.controller.activity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yumefusaka.galgamesite.common.result.Result;
import yumefusaka.galgamesite.mapper.GalGameMapper;
import yumefusaka.galgamesite.pojo.dto.GalGameSearchBySubjectIdDTO;
import yumefusaka.galgamesite.pojo.dto.GalGameSearchByTranslatedNameDTO;

import yumefusaka.galgamesite.pojo.dto.GalGameTwelveVotingInitiateVoteDTO;
import yumefusaka.galgamesite.pojo.vo.GalGameTwelveVotingGameInfoVO;

import yumefusaka.galgamesite.pojo.vo.GalGameTwelveVotingHistoryVO;
import yumefusaka.galgamesite.pojo.vo.GalGameTwelveVotingResultVO;
import yumefusaka.galgamesite.pojo.vo.GalGameTwelveVotingGameInfoByMyselfVO;
import yumefusaka.galgamesite.service.IGalGameTwelveVotingService;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/activity/galgame-twelve-voting")
@Tag(name = "GalGame十二神器投票")
public class GalGameTwelveVotingController {

    @Autowired
    private GalGameMapper galGameMapper;

    @Autowired
    private IGalGameTwelveVotingService galGameTwelveVotingService;

    @Operation(summary = "通过译名获取GalGame投票列表")
    @PostMapping("/game-info/list")
    public Result<List<GalGameTwelveVotingGameInfoVO>> getGalGameTwelveVotingGameInfoList (@RequestBody GalGameSearchByTranslatedNameDTO galGameSearchByTranslatedNameDTO) {
        List<GalGameTwelveVotingGameInfoVO> galGameTwelveVotingGameInfoVOS = galGameTwelveVotingService.getGalGameTwelveVotingGameInfoList(galGameSearchByTranslatedNameDTO);
        return Result.success(galGameTwelveVotingGameInfoVOS);
    }

    @Operation(summary = "通过译名获取GalGame投票结果")
    @GetMapping("/result/list")
    public Result<List<GalGameTwelveVotingResultVO>> getGalGameTwelveVotingResultList () {
        List<GalGameTwelveVotingResultVO> galGameTwelveVotingResultVOS = galGameTwelveVotingService.getGalGameTwelveVotingResultList();
        return Result.success(galGameTwelveVotingResultVOS);
    }

    @Operation(summary = "获取本人GalGame投票历史")
    @GetMapping("/history/list")
    public Result<List<GalGameTwelveVotingHistoryVO>> getGalGameTwelveVotingHistoryList () {
        List<GalGameTwelveVotingHistoryVO> galGameTwelveVotingHistoryVOS = galGameTwelveVotingService.getGalGameTwelveVotingHistoryList();
        return Result.success(galGameTwelveVotingHistoryVOS);
    }

    @Operation(summary = "获取本人已投的总票数")
    @GetMapping("/votes-cast-count/total")
    public Result<Long> getGalGameTwelveVotingVotesCastCountTotal () {
        return Result.success(galGameTwelveVotingService.getGalGameTwelveVotingVotesCastCountTotal());
    }

    @Operation(summary = "获取作品信息与投票数")
    @PostMapping("/game-info/by-myself")
    public Result<GalGameTwelveVotingGameInfoByMyselfVO> getGalGameTwelveVotingGameInfoByMyself(@RequestBody GalGameSearchBySubjectIdDTO galGameSearchBySubjectIdDTO){
        return Result.success(galGameTwelveVotingService.getGalGameTwelveVotingGameInfoByMyself(galGameSearchBySubjectIdDTO));
    }

    @Operation(summary = "发起投票")
    @PostMapping("/initiate-vote")
    public Result<String> postGalGameTwelveVotingInitiateVote(@RequestBody GalGameTwelveVotingInitiateVoteDTO galGameTwelveVotingInitiateVoteDTO) throws Exception {
         throw new Exception("投票已经结束了喵~");
//         galGameTwelveVotingService.postGalGameTwelveVotingInitiateVote(galGameTwelveVotingInitiateVoteDTO);
//         return Result.success("投票成功");
    }
}

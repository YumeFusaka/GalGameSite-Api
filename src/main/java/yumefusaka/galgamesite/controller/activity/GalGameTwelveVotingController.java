package yumefusaka.galgamesite.controller.activity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yumefusaka.galgamesite.common.result.Result;
import yumefusaka.galgamesite.mapper.GalGameMapper;
import yumefusaka.galgamesite.pojo.dto.GalGameSearchByTranslatedNameDTO;

import yumefusaka.galgamesite.pojo.vo.GalGameTwelveVotingGameInfoVO;

import yumefusaka.galgamesite.pojo.vo.GalGameTwelveVotingResultVO;
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

    @Operation(summary = "获取GalGame投票列表")
    @PostMapping("/game-info/list")
    public Result<List<GalGameTwelveVotingGameInfoVO>> galGameTwelveVotingGameInfoList (@RequestBody GalGameSearchByTranslatedNameDTO galGameSearchByTranslatedNameDTO) {
        List<GalGameTwelveVotingGameInfoVO> galGameTwelveVotingGameInfoVOS = galGameTwelveVotingService.galGameTwelveVotingGameInfoList(galGameSearchByTranslatedNameDTO);
        return Result.success(galGameTwelveVotingGameInfoVOS);
    }

    @Operation(summary = "获取GalGame投票结果")
    @GetMapping("/result/list")
    public Result<List<GalGameTwelveVotingResultVO>> galGameTwelveVotingResultList () {
        List<GalGameTwelveVotingResultVO> galGameTwelveVotingResultVOS = galGameTwelveVotingService.galGameTwelveVotingResultList();
        return Result.success(galGameTwelveVotingResultVOS);
    }
//
//
//
//
//    @Operation(summary = "获取本人GalGame投票历史")
//    @GetMapping("/activity/galGameVoteHistory/list")
//    public Result<List<GalGameVoteHistoryVO>> galGameVoteHistory () {
//        List<GalGameVoteHistoryVO> galGameVoteHistoryVOS = galGameVoteService.getGalGameVoteHistory();
//        return Result.success(galGameVoteHistoryVOS);
//    }
//
//    @Operation(summary = "获取本人已投的总票数")
//    @GetMapping("/activity/galGameVoteByUseCount")
//    public Result<Integer> galGameVoteByUseCount () {
//        return Result.success(galGameVoteService.galGameVoteByUseSum());
//    }
//
//    @Operation(summary = "获取作品信息与投票数")
//    @PostMapping("/activity/galGameVoteResultByUser")
//    public Result<GalGameVoteResultByUserVO> galGameVoteResultByUser(@RequestBody GalGameVoteResultByUserDTO galGameVoteResultByUserDTO){
//        return Result.success(galGameVoteService.galGameVoteResultByUser(galGameVoteResultByUserDTO));
//    }
//
//    @Operation(summary = "发起投票")
//    @PostMapping("/activity/galGameVoteSubmit")
//    public Result<String> galGameVoteSubmit(@RequestBody GalGameVoteSubmitDTO galGameVoteResultByUserDTO) throws Exception {
//        throw new Exception("投票已经结束了喵~");
//        // galGameVoteService.galGameVoteSubmit(galGameVoteResultByUserDTO);
//        // return Result.success("投票成功");
//    }
}
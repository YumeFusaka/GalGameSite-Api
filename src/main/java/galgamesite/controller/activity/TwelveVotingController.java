package galgamesite.controller.activity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import galgamesite.common.page.PageResponse;
import galgamesite.common.context.BaseContext;
import galgamesite.common.result.Result;
import galgamesite.pojo.request.activity.VoteSubmissionRequest;
import galgamesite.pojo.request.game.GameQueryRequest;
import galgamesite.pojo.response.activity.VoteBallotResponse;
import galgamesite.pojo.response.activity.VoteSummaryResponse;
import galgamesite.pojo.response.activity.VotingGameListItemResponse;
import galgamesite.pojo.response.activity.VotingRecordResponse;
import galgamesite.pojo.response.activity.VotingResultResponse;
import galgamesite.service.TwelveVotingService;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/activities/twelve-voting")
@Tag(name = "GalGame十二神器投票")
public class TwelveVotingController {

    @Autowired
    private TwelveVotingService twelveVotingService;

    @Operation(summary = "分页获取投票作品列表")
    @GetMapping("/games")
    public Result<PageResponse<VotingGameListItemResponse>> listVotingGames(
            @RequestParam Integer edition,
            @RequestParam Long pageNo,
            @RequestParam Long pageSize,
            @RequestParam(required = false) String translatedName) {
        GameQueryRequest request = new GameQueryRequest();
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        request.setTranslatedName(translatedName);
        PageResponse<VotingGameListItemResponse> response = twelveVotingService.listVotingGames(request, edition);
        BaseContext.removeCurrentId();
        return Result.success(response);
    }

    @Operation(summary = "获取投票结果")
    @GetMapping("/results")
    public Result<List<VotingResultResponse>> listVotingResults(@RequestParam Integer edition) {
        List<VotingResultResponse> list = twelveVotingService.listVotingResults(edition);
        BaseContext.removeCurrentId();
        return Result.success(list);
    }

    @Operation(summary = "获取当前用户投票记录")
    @GetMapping("/records/me")
    public Result<List<VotingRecordResponse>> listCurrentUserVotingRecords(@RequestParam Integer edition) {
        List<VotingRecordResponse> list = twelveVotingService.listCurrentUserVotingRecords(edition);
        BaseContext.removeCurrentId();
        return Result.success(list);
    }

    @Operation(summary = "获取当前用户已投票汇总")
    @GetMapping("/summary/me")
    public Result<VoteSummaryResponse> getCurrentUserVoteSummary(@RequestParam Integer edition) {
        Long usedVotes = twelveVotingService.getCurrentUserUsedVotes(edition);
        BaseContext.removeCurrentId();
        return Result.success(new VoteSummaryResponse(edition, usedVotes));
    }

    @Operation(summary = "获取当前用户对作品的投票面板信息")
    @GetMapping("/games/{subjectId}/ballot")
    public Result<VoteBallotResponse> getCurrentUserBallot(
            @PathVariable Long subjectId,
            @RequestParam Integer edition) {
        VoteBallotResponse response = twelveVotingService.getCurrentUserBallot(subjectId, edition);
        BaseContext.removeCurrentId();
        return Result.success(response);
    }

    @Operation(summary = "发起投票")
    @PutMapping("/games/{subjectId}/ballot")
    public Result<String> submitVote(
            @PathVariable Long subjectId,
            @RequestParam Integer edition,
            @RequestBody VoteSubmissionRequest request) throws Exception {
        if(edition != 2){
            throw new Exception("投票已经结束了喵~");
        }else{
            twelveVotingService.submitVote(subjectId, edition, request);
            BaseContext.removeCurrentId();
            return Result.success("投票成功");
        }
    }
}

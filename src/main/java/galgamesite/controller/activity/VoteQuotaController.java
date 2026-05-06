package galgamesite.controller.activity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import galgamesite.common.context.BaseContext;
import galgamesite.common.result.Result;
import galgamesite.pojo.request.activity.VoteQuotaUpdateRequest;
import galgamesite.pojo.response.activity.VoteQuotaResponse;
import galgamesite.service.VoteQuotaService;

@Slf4j
@RestController
@RequestMapping("/activities/twelve-voting/quota")
@Tag(name = "GalGame十二神器票数管理")
public class VoteQuotaController {

    @Autowired
    private VoteQuotaService voteQuotaService;

    @Operation(summary = "获取当前用户的票数")
    @GetMapping("/me")
    public Result<VoteQuotaResponse> getCurrentUserQuota(@RequestParam Integer edition) {
        Integer number = voteQuotaService.getCurrentUserVoteQuota(edition);
        BaseContext.removeCurrentId();
        return Result.success(new VoteQuotaResponse(edition, number));
    }

    @Operation(summary = "设置当前用户的票数")
    @PutMapping("/me")
    public Result<String> updateCurrentUserQuota(@RequestBody VoteQuotaUpdateRequest request) {
        voteQuotaService.updateCurrentUserVoteQuota(request.getEdition(), request.getTotalVotes());
        BaseContext.removeCurrentId();
        return Result.success("设置成功");
    }

}

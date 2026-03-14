package yumefusaka.galgamesite.controller.activity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yumefusaka.galgamesite.common.context.BaseContext;
import yumefusaka.galgamesite.common.result.Result;
import yumefusaka.galgamesite.pojo.entity.GalGameTwelveVoteNumber;
import yumefusaka.galgamesite.service.IGalGameTwelveVoteNumberService;

@Slf4j
@RestController
@RequestMapping("/activity/galgame-twelve-vote-number")
@Tag(name = "GalGame十二神器票数管理")
public class GalGameTwelveVoteNumberController {

    @Autowired
    private IGalGameTwelveVoteNumberService galGameTwelveVoteNumberService;

    @Operation(summary = "获取当前用户的票数")
    @GetMapping("/my-vote-number")
    public Result<Integer> getMyVoteNumber(@RequestParam Integer edition) {
        Integer number = galGameTwelveVoteNumberService.getUserVoteNumber(edition);
        BaseContext.removeCurrentId();
        return Result.success(number);
    }

    @Operation(summary = "设置当前用户的票数")
    @PostMapping("/set-vote-number")
    public Result<String> setMyVoteNumber(
            @RequestParam Integer edition,
            @RequestParam Integer number) {
        galGameTwelveVoteNumberService.setUserVoteNumber(edition, number);
        BaseContext.removeCurrentId();
        return Result.success("设置成功");
    }

}

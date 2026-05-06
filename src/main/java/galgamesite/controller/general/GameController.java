package galgamesite.controller.general;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import galgamesite.common.page.PageResponse;
import galgamesite.common.result.Result;
import galgamesite.pojo.request.game.GameQueryRequest;
import galgamesite.pojo.response.game.GameResponse;
import galgamesite.service.GameService;

@RestController
@Slf4j
@RequestMapping("/games")
@Tag(name = "GalGame")
public class GameController {

    @Autowired
    private GameService gameService;

    @Operation(summary = "分页查询 GalGame")
    @GetMapping
    public Result<PageResponse<GameResponse>> searchGames(
            @RequestParam Long pageNo,
            @RequestParam Long pageSize,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String translatedName) {
        GameQueryRequest request = new GameQueryRequest();
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        request.setKeyword(keyword);
        request.setTranslatedName(translatedName);
        return Result.success(gameService.searchGames(request));
    }
}

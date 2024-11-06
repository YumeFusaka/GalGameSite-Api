package yumefusaka.galgamesite.controller.general;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yumefusaka.galgamesite.common.result.Result;
import yumefusaka.galgamesite.pojo.dto.GalGameSearchByTranslatedNameDTO;
import yumefusaka.galgamesite.pojo.entity.GalGame;
import yumefusaka.galgamesite.service.IGalGameService;


@RestController
@Slf4j
@RequestMapping("/galgame")
@Tag(name = "GalGame")
public class GalGameController {

    @Autowired
    private IGalGameService galGameService;

    @Operation(summary = "获取GalGame总数")
    @GetMapping("/total")
    public Result<Long> getGalGameTotal () {
        Long total = galGameService.count();
        return Result.success(total);
    }

    @Operation(summary = "获取查询的GalGame总数")
    @PostMapping("/total")
    public Result<Long> getGalGameSearchByTranslatedNameTotal (@RequestBody GalGameSearchByTranslatedNameDTO galGameSearchByTranslatedNameDTO) {
        Long total = galGameService.count(
                new QueryWrapper<GalGame>().like(true,"translated_name",galGameSearchByTranslatedNameDTO.getTranslatedName()));
        return Result.success(total);
    }
}

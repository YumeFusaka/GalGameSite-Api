package yumefusaka.galgamesite.controller.general;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yumefusaka.galgamesite.common.result.Result;
import yumefusaka.galgamesite.pojo.dto.GalGameSearchByNameDTO;
import yumefusaka.galgamesite.pojo.dto.GalGameSearchByTranslatedNameDTO;
import yumefusaka.galgamesite.pojo.entity.GalGame;
import yumefusaka.galgamesite.pojo.vo.GalGameVO;
import yumefusaka.galgamesite.service.IGalGameService;

import java.util.List;


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

    @Operation(summary = "通过译名获取查询的GalGame总数")
    @PostMapping("/searchByTranslatedName/total")
    public Result<Long> getGalGameSearchByTranslatedNameTotal (@RequestBody GalGameSearchByTranslatedNameDTO galGameSearchByTranslatedNameDTO) {
        Long total = galGameService.count(
                new QueryWrapper<GalGame>().like(true,"translated_name",galGameSearchByTranslatedNameDTO.getTranslatedName()));
        return Result.success(total);
    }

    @Operation(summary = "通过译名获取查询的GalGame列表")
    @PostMapping("/searchByTranslatedName/list")
    public Result<List<GalGameVO>> getGalGameSearchByTranslatedNameList (@RequestBody GalGameSearchByTranslatedNameDTO galGameSearchByTranslatedNameDTO) {
        List<GalGameVO> galGameVOS = galGameService.getGalGameSearchByTranslatedNameList(galGameSearchByTranslatedNameDTO);
        return Result.success(galGameVOS);
    }

    @Operation(summary = "通过搜索名获取查询的GalGame总数")
    @PostMapping("/searchByName/total")
    public Result<Long> getGalGameSearchByNameTotal (@RequestBody GalGameSearchByNameDTO galGameSearchByNameDTO) {
        QueryWrapper<GalGame> queryWrapper = new QueryWrapper<GalGame>();
        queryWrapper
                .like("LOWER(translated_name)",galGameSearchByNameDTO.getName().toLowerCase())
                .or()
                .like("LOWER(original_name)",galGameSearchByNameDTO.getName().toLowerCase());
        Long total = galGameService.count(queryWrapper);
        return Result.success(total);
    }

    @Operation(summary = "通过搜索名获取查询的GalGame列表")
    @PostMapping("/searchByName/list")
    public Result<List<GalGameVO>> getGalGameSearchByNameList (@RequestBody GalGameSearchByNameDTO galGameSearchByNameDTO) {
        List<GalGameVO> galGameVOS = galGameService.getGalGameSearchByNameList(galGameSearchByNameDTO);
        return Result.success(galGameVOS);
    }
}

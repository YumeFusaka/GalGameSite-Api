package yumefusaka.galgamesite.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yumefusaka.galgamesite.common.result.Result;

import yumefusaka.galgamesite.pojo.dto.GalGameVoteItemSearchDTO;
import yumefusaka.galgamesite.pojo.vo.GalGameVO;
import yumefusaka.galgamesite.pojo.vo.GalGameVoteItemSearchVO;

import yumefusaka.galgamesite.service.IGalGameService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
@Tag(name = "用户")
public class UserController {

    @Autowired
    private IGalGameService galGameService;

    @Operation(summary="获取GalGame投票列表")
    @PostMapping("/activity/galgameVoteItemSearch")
    public Result<List<GalGameVoteItemSearchVO>> galGameVoteList (@RequestBody GalGameVoteItemSearchDTO galGameVoteItemSearchDTO) {
        List<GalGameVoteItemSearchVO> galGameVoteItemSearchVOS = galGameService.getGalGameVoteItemSearchList(galGameVoteItemSearchDTO);
        return Result.success(galGameVoteItemSearchVOS);
    }
}

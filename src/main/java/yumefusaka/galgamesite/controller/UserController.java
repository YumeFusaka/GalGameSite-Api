package yumefusaka.galgamesite.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yumefusaka.galgamesite.common.result.Result;
import yumefusaka.galgamesite.pojo.vo.GalGameVO;
import yumefusaka.galgamesite.service.IGalGameService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
@Tag(name = "用户")
public class UserController {

    @Autowired
    private IGalGameService galGameService;

    @PostMapping("/galgame/list")
    public Result<List<GalGameVO>> galGameList () {
        List<GalGameVO> galGameList = galGameService.getGalGameList();
        return Result.success(galGameList);
    }
}

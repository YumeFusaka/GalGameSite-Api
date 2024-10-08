package yumefusaka.galgamesite.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yumefusaka.galgamesite.common.properties.JwtProperties;
import yumefusaka.galgamesite.common.result.Result;
import yumefusaka.galgamesite.pojo.dto.*;
import yumefusaka.galgamesite.pojo.entity.GalGame;
import yumefusaka.galgamesite.pojo.entity.User;
import yumefusaka.galgamesite.pojo.vo.*;
import yumefusaka.galgamesite.service.IGalGameService;
import yumefusaka.galgamesite.service.IGalGameVoteService;
import yumefusaka.galgamesite.service.IUserService;
import yumefusaka.galgamesite.utils.JwtUtils;

import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
@Tag(name = "用户")
public class UserController {

    @Autowired
    private IGalGameService galGameService;

    @Autowired
    private IGalGameVoteService galGameVoteService;

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<LoginVO> login (@RequestBody UserLoginDTO userLoginDTO) throws Exception {
        log.info("登录用户：{}", userLoginDTO);
        LoginVO loginVO = new LoginVO();
        User user = userService.login(userLoginDTO);
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("qq", user.getQq());
        String token = JwtUtils.createToken(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claims);
        loginVO.setToken(token);
        loginVO.setNickName(user.getNickName());
        return Result.success(loginVO);
    }

    @Operation(summary = "获取GalGame投票列表")
    @PostMapping("/activity/galGameVoteItemSearch/list")
    public Result<List<GalGameVoteItemSearchVO>> galGameVoteItemSearch (@RequestBody GalGameVoteItemSearchDTO galGameVoteItemSearchDTO) {
        List<GalGameVoteItemSearchVO> galGameVoteItemSearchVOS = galGameService.getGalGameVoteItemSearchList(galGameVoteItemSearchDTO);
        return Result.success(galGameVoteItemSearchVOS);
    }

    @Operation(summary = "获取GalGame投票结果")
    @GetMapping("/activity/galGameVoteResult/list")
    public Result<List<GalGameVoteResultVO>> galGameVoteResult () {
        List<GalGameVoteResultVO> galGameVoteResultVOS = galGameVoteService.getGalGameVoteResult();
        return Result.success(galGameVoteResultVOS);
    }

    @Operation(summary = "获取GalGame总数")
    @GetMapping("/galGame/total")
    public Result<Long> galGameTotal () {
        Long total = galGameService.count();
        return Result.success(total);
    }

    @Operation(summary = "获取查询的GalGame总数")
    @PostMapping("/galGame/total")
    public Result<Long> galGameSearchTotal (@RequestBody GalGameSearchDTO galGameSearchDTO) {
        Long total = galGameService.count(new QueryWrapper<GalGame>().like(true,"name",galGameSearchDTO.getName()));
        return Result.success(total);
    }

    @Operation(summary = "获取本人GalGame投票历史")
    @GetMapping("/activity/galGameVoteHistory/list")
    public Result<List<GalGameVoteHistoryVO>> galGameVoteHistory () {
        List<GalGameVoteHistoryVO> galGameVoteHistoryVOS = galGameVoteService.getGalGameVoteHistory();
        return Result.success(galGameVoteHistoryVOS);
    }

    @Operation(summary = "获取本人已投的总票数")
    @GetMapping("/activity/galGameVoteByUseCount")
    public Result<Integer> galGameVoteByUseCount () {
        return Result.success(galGameVoteService.galGameVoteByUseSum());
    }

    @Operation(summary = "获取作品信息与投票数")
    @PostMapping("/activity/galGameVoteResultByUser")
    public Result<GalGameVoteResultByUserVO> galGameVoteResultByUser(@RequestBody GalGameVoteResultByUserDTO galGameVoteResultByUserDTO){
        return Result.success(galGameVoteService.galGameVoteResultByUser(galGameVoteResultByUserDTO));
    }

    @Operation(summary = "发起投票")
    @PostMapping("/activity/galGameVoteSubmit")
    public Result<String> galGameVoteSubmit(@RequestBody GalGameVoteSubmitDTO galGameVoteResultByUserDTO) throws Exception {
        galGameVoteService.galGameVoteSubmit(galGameVoteResultByUserDTO);
        return Result.success("投票成功");
    }

}

package yumefusaka.galgamesite.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yumefusaka.galgamesite.common.context.BaseContext;
import yumefusaka.galgamesite.common.properties.JwtProperties;
import yumefusaka.galgamesite.common.result.Result;
import yumefusaka.galgamesite.pojo.dto.*;
import yumefusaka.galgamesite.pojo.entity.User;
import yumefusaka.galgamesite.pojo.vo.*;
import yumefusaka.galgamesite.service.IUserService;
import yumefusaka.galgamesite.utils.JwtUtils;

import java.util.HashMap;

@RestController
@Slf4j
@RequestMapping("/user")
@Tag(name = "用户")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<UserLoginVO> login (@RequestBody UserLoginDTO userLoginDTO) throws Exception {
        log.info("登录用户：{}", userLoginDTO);
        UserLoginVO loginVO = new UserLoginVO();
        User user = userService.login(userLoginDTO);
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("uin", user.getUin());
        String token = JwtUtils.createToken(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claims);
        loginVO.setToken(token);
        loginVO.setNick(user.getNick());
        return Result.success(loginVO);
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/info")
    public Result<UserInfoVO> getUserInfo() {
        String uin = BaseContext.getCurrentId();
        BaseContext.removeCurrentId();
        User user= userService.getOne(new QueryWrapper<User>().eq("uin",uin));
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(user, userInfoVO);
        return Result.success(userInfoVO);
    }
}

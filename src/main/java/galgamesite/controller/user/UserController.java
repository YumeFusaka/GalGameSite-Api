package galgamesite.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import galgamesite.common.context.BaseContext;
import galgamesite.common.properties.JwtProperties;
import galgamesite.common.result.Result;
import galgamesite.pojo.entity.User;
import galgamesite.pojo.request.user.UserLoginRequest;
import galgamesite.pojo.response.user.UserLoginResponse;
import galgamesite.pojo.response.user.UserProfileResponse;
import galgamesite.service.UserService;
import galgamesite.utils.JwtUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")
@Tag(name = "用户")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @Operation(summary = "用户登录")
    @PostMapping("/sessions")
    public Result<UserLoginResponse> createSession(@RequestBody UserLoginRequest loginRequest) throws Exception {
        log.info("登录用户：{}", loginRequest);
        UserLoginResponse response = new UserLoginResponse();
        User user = userService.authenticate(loginRequest);
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("uin", user.getUin());
        String token = JwtUtils.createToken(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claims);
        response.setToken(token);
        response.setNick(user.getNick());
        return Result.success(response);
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/me")
    public Result<UserProfileResponse> getCurrentUserProfile() {
        String uin = BaseContext.getCurrentId();
        BaseContext.removeCurrentId();
        User user= userService.getOne(new QueryWrapper<User>().eq("uin",uin));
        UserProfileResponse response = new UserProfileResponse();
        BeanUtils.copyProperties(user, response);
        return Result.success(response);
    }

    @Operation(summary = "获取群成员列表")
    @GetMapping
    public Result<List<UserProfileResponse>> listUsers() {
        List<User> userList = userService.list();
        List<UserProfileResponse> responseList = new ArrayList<>();
        for(User user:userList){
            UserProfileResponse response = new UserProfileResponse();
            BeanUtils.copyProperties(user, response);
            responseList.add(response);
        }
        return Result.success(responseList);
    }
}

package galgamesite.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import galgamesite.mapper.UserMapper;
import galgamesite.pojo.entity.User;
import galgamesite.pojo.request.user.UserLoginRequest;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    @Autowired
    private UserMapper userMapper;

    public User authenticate(UserLoginRequest loginRequest) throws Exception {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("uin", loginRequest.getUin()));
        if(user == null){
           throw new Exception("嘻嘻,你的账号并不在GalGameSite唷");
        }
        return user;
    }
}

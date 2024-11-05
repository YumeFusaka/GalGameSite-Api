package yumefusaka.galgamesite.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yumefusaka.galgamesite.mapper.UserMapper;
import yumefusaka.galgamesite.pojo.dto.UserLoginDTO;
import yumefusaka.galgamesite.pojo.entity.User;
import yumefusaka.galgamesite.service.IUserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(UserLoginDTO userLoginDTO) throws Exception {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("uin", userLoginDTO.getUin()));
        if(user == null){
           throw new Exception("嘻嘻,你的账号并不在Game&Love唷");
        }
        return user;
    }
}

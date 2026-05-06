package galgamesite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import galgamesite.pojo.dto.UserLoginDTO;
import galgamesite.pojo.entity.User;

public interface IUserService extends IService<User> {
    User login(UserLoginDTO userLoginDTO) throws Exception;
}

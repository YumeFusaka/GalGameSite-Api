package yumefusaka.galgamesite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import yumefusaka.galgamesite.pojo.dto.UserLoginDTO;
import yumefusaka.galgamesite.pojo.entity.User;

public interface IUserService extends IService<User> {
    User login(UserLoginDTO userLoginDTO) throws Exception;
}

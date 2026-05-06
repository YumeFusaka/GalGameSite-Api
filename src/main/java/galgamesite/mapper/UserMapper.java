package galgamesite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import galgamesite.pojo.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

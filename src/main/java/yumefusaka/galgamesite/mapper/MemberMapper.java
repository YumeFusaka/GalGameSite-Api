package yumefusaka.galgamesite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import yumefusaka.galgamesite.pojo.entity.Member;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {
}

package yumefusaka.galgamesite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yumefusaka.galgamesite.pojo.entity.GalGameTwelveVoteNumber;

@Mapper
public interface GalGameTwelveVoteNumberMapper extends BaseMapper<GalGameTwelveVoteNumber> {

    /**
     * 根据 QQ 号和届次查询票数
     */
    GalGameTwelveVoteNumber selectByUinAndEdition(
            @Param("uin") String uin,
            @Param("edition") Integer edition
    );

    /**
     * 更新票数
     */
    int updateNumberByUinAndEdition(
            @Param("uin") String uin,
            @Param("edition") Integer edition,
            @Param("number") Integer number
    );
}

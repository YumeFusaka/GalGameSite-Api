package yumefusaka.galgamesite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import yumefusaka.galgamesite.pojo.entity.GalGame;
import yumefusaka.galgamesite.pojo.vo.GalGameTwelveVotingGameInfoVO;

import java.util.List;

@Mapper
public interface GalGameMapper extends BaseMapper<GalGame> {

    List<GalGameTwelveVotingGameInfoVO> getGalGameTwelveVotingGameInfoList(Page<GalGameTwelveVotingGameInfoVO> page, String translatedName);

}
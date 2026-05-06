package galgamesite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import galgamesite.pojo.entity.GalGame;
import galgamesite.pojo.response.activity.VotingGameListItemResponse;

import java.util.List;

@Mapper
public interface GameMapper extends BaseMapper<GalGame> {

    List<VotingGameListItemResponse> selectVotingGamePage(Page<VotingGameListItemResponse> page, String translatedName, Integer edition);

}

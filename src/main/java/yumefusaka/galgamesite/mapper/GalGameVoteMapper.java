package yumefusaka.galgamesite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import yumefusaka.galgamesite.pojo.entity.GalGameVote;
import yumefusaka.galgamesite.pojo.vo.GalGameVoteHistoryVO;
import yumefusaka.galgamesite.pojo.vo.GalGameVoteResultByUserVO;
import yumefusaka.galgamesite.pojo.vo.GalGameVoteResultVO;

import java.util.List;

@Mapper
public interface GalGameVoteMapper extends BaseMapper<GalGameVote> {
    List<GalGameVoteResultVO> selectGalGameVoteResult();

    List<GalGameVoteHistoryVO> selectGalGameVoteHistory(String qq);

    Integer galGameVoteByUseCount(String qq);

    GalGameVoteResultByUserVO galGameVoteResultByUser(String qq, Long subjectId);

    Long galGameVoteResultRank(Long subjectId);
}

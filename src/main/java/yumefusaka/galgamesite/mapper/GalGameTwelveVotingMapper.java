package yumefusaka.galgamesite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import yumefusaka.galgamesite.pojo.entity.GalGameTwelveVoting;
import yumefusaka.galgamesite.pojo.vo.GalGameTwelveVotingGameInfoByMyselfVO;
import yumefusaka.galgamesite.pojo.vo.GalGameTwelveVotingHistoryVO;
import yumefusaka.galgamesite.pojo.vo.GalGameTwelveVotingResultVO;


import java.util.List;

@Mapper
public interface GalGameTwelveVotingMapper extends BaseMapper<GalGameTwelveVoting> {


    List<GalGameTwelveVotingResultVO> getGalGameTwelveVotingResultList();

    List<GalGameTwelveVotingHistoryVO> getGalGameTwelveVotingHistoryList(String uin);

    Long getGalGameTwelveVotingVotesCastCountTotal(String uin);

    GalGameTwelveVotingGameInfoByMyselfVO getGalGameTwelveVotingGameInfoByMyself(String uin, Long subjectId);

    Long getGalGameTwelveVotingResultTotalRank(Long subjectId);

    Long getGalGameTwelveVotingResultTotalVotes(Long subjectId);
}

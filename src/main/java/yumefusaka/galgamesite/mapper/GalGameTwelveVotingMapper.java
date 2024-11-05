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


    List<GalGameTwelveVotingResultVO> galGameTwelveVotingResultList();

    List<GalGameTwelveVotingHistoryVO> galGameTwelveVotingHistoryList(String uin);

    Long galGameTwelveVotingVotesCastCountTotal(String uin);

    GalGameTwelveVotingGameInfoByMyselfVO galGameTwelveVotingGameInfoByMyself(String uin, Long subjectId);

    Long galGameTwelveVotingResultTotalRank(Long subjectId);

    Long galGameTwelveVotingResultTotalVotes(Long subjectId);
}

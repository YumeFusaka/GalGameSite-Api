package yumefusaka.galgamesite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import yumefusaka.galgamesite.pojo.entity.GalGameTwelveVoting;
import yumefusaka.galgamesite.pojo.vo.GalGameTwelveVotingResultVO;


import java.util.List;

@Mapper
public interface GalGameTwelveVotingMapper extends BaseMapper<GalGameTwelveVoting> {


    List<GalGameTwelveVotingResultVO> galGameTwelveVotingResultList();

//    List<GalGameVoteHistoryVO> selectGalGameVoteHistory(String qq);
//
//    Integer galGameVoteByUseCount(String qq);
//
//    GalGameVoteResultByUserVO galGameVoteResultByUser(String qq, Long subjectId);
//
//    Long galGameVoteResultRank(Long subjectId);
//
//    Long galGameVoteResultVote(Long subjectId);
}

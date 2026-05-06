package galgamesite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import galgamesite.pojo.entity.GalGameTwelveVoting;
import galgamesite.pojo.response.activity.VoteBallotResponse;
import galgamesite.pojo.response.activity.VotingRecordResponse;
import galgamesite.pojo.response.activity.VotingResultResponse;

import java.util.List;

@Mapper
public interface TwelveVotingMapper extends BaseMapper<GalGameTwelveVoting> {

    List<VotingResultResponse> selectResultsByEdition(@Param("edition") Integer edition);

    List<VotingRecordResponse> selectRecordsByEdition(
            @Param("uin") String uin,
            @Param("edition") Integer edition
    );

    Long sumUsedVotesByEdition(
            @Param("uin") String uin,
            @Param("edition") Integer edition
    );

    VoteBallotResponse selectBallotBySubjectAndEdition(
            @Param("uin") String uin,
            @Param("subjectId") Long subjectId,
            @Param("edition") Integer edition
    );

    Long selectRankBySubjectAndEdition(
            @Param("subjectId") Long subjectId,
            @Param("edition") Integer edition
    );

    Long sumVotesBySubjectAndEdition(
            @Param("subjectId") Long subjectId,
            @Param("edition") Integer edition
    );
}

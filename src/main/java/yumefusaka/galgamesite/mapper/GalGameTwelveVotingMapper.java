package yumefusaka.galgamesite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yumefusaka.galgamesite.pojo.entity.GalGameTwelveVoting;
import yumefusaka.galgamesite.pojo.vo.GalGameTwelveVotingGameInfoByMyselfVO;
import yumefusaka.galgamesite.pojo.vo.GalGameTwelveVotingHistoryVO;
import yumefusaka.galgamesite.pojo.vo.GalGameTwelveVotingResultVO;


import java.util.List;

@Mapper
public interface GalGameTwelveVotingMapper extends BaseMapper<GalGameTwelveVoting> {

    // 投票结果列表，增加 edition 参数
    List<GalGameTwelveVotingResultVO> getGalGameTwelveVotingResultList(@Param("edition") Integer edition);

    List<GalGameTwelveVotingHistoryVO> getGalGameTwelveVotingHistoryListByEdition(
            @Param("uin") String uin,
            @Param("edition") Integer edition
    );

    Long getGalGameTwelveVotingVotesCastCountTotalByEdition(
            @Param("uin") String uin,
            @Param("edition") Integer edition
    );
    // 获取自己对某作品投票信息，增加 edition
    GalGameTwelveVotingGameInfoByMyselfVO getGalGameTwelveVotingGameInfoByMyself(
            @Param("uin") String uin,
            @Param("subjectId") Long subjectId,
            @Param("edition") Integer edition
    );

    // 获取总排名，增加 edition
    Long getGalGameTwelveVotingResultTotalRank(
            @Param("subjectId") Long subjectId,
            @Param("edition") Integer edition
    );

    // 获取总票数，增加 edition
    Long getGalGameTwelveVotingResultTotalVotes(
            @Param("subjectId") Long subjectId,
            @Param("edition") Integer edition
    );
}

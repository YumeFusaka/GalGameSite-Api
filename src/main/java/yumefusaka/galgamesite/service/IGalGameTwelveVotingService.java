package yumefusaka.galgamesite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import yumefusaka.galgamesite.pojo.dto.GalGameSearchBySubjectIdDTO;
import yumefusaka.galgamesite.pojo.dto.GalGameSearchByTranslatedNameDTO;

import yumefusaka.galgamesite.pojo.dto.GalGameTwelveVotingInitiateVoteDTO;
import yumefusaka.galgamesite.pojo.entity.GalGameTwelveVoting;
import yumefusaka.galgamesite.pojo.vo.*;

import java.util.List;

public interface IGalGameTwelveVotingService extends IService<GalGameTwelveVoting> {


    // 获取投票游戏列表（增加 edition 参数）
    List<GalGameTwelveVotingGameInfoVO> getGalGameTwelveVotingGameInfoList(GalGameSearchByTranslatedNameDTO dto, Integer edition);

    // 获取投票结果列表（增加 edition 参数）
    List<GalGameTwelveVotingResultVO> getGalGameTwelveVotingResultList(Integer edition);

    // 获取投票历史记录
    List<GalGameTwelveVotingHistoryVO> getGalGameTwelveVotingHistoryList(Integer edition);

    // 获取用户已投票总数
    Long getGalGameTwelveVotingVotesCastCountTotal(Integer edition);

    // 获取自己对某作品投票信息（增加 edition 参数）
    GalGameTwelveVotingGameInfoByMyselfVO getGalGameTwelveVotingGameInfoByMyself(GalGameSearchBySubjectIdDTO dto, Integer edition);

    // 获取某作品总排名（增加 edition 参数）
    Long getGalGameTwelveVotingResultTotalRank(Long subjectId, Integer edition);

    // 提交投票（增加 edition 参数）
    void postGalGameTwelveVotingInitiateVote(GalGameTwelveVotingInitiateVoteDTO dto, Integer edition) throws Exception;
}

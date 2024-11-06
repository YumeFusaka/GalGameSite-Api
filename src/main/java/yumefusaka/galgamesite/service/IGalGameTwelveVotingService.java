package yumefusaka.galgamesite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import yumefusaka.galgamesite.pojo.dto.GalGameSearchBySubjectIdDTO;
import yumefusaka.galgamesite.pojo.dto.GalGameSearchByTranslatedNameDTO;

import yumefusaka.galgamesite.pojo.dto.GalGameTwelveVotingInitiateVoteDTO;
import yumefusaka.galgamesite.pojo.entity.GalGameTwelveVoting;
import yumefusaka.galgamesite.pojo.vo.*;

import java.util.List;

public interface IGalGameTwelveVotingService extends IService<GalGameTwelveVoting> {

    List<GalGameTwelveVotingGameInfoVO> getGalGameTwelveVotingGameInfoList(GalGameSearchByTranslatedNameDTO gameSearchByTranslatedNameDTO);


    List<GalGameTwelveVotingResultVO> getGalGameTwelveVotingResultList();

    List<GalGameTwelveVotingHistoryVO> getGalGameTwelveVotingHistoryList();

    Long getGalGameTwelveVotingVotesCastCountTotal();

    GalGameTwelveVotingGameInfoByMyselfVO getGalGameTwelveVotingGameInfoByMyself(GalGameSearchBySubjectIdDTO galGameSearchBySubjectIdDTO);

    Long getGalGameTwelveVotingResultTotalRank(Long subjectId);

    void postGalGameTwelveVotingInitiateVote(GalGameTwelveVotingInitiateVoteDTO galGameTwelveVotingInitiateVoteDTO) throws Exception;
}

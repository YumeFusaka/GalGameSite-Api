package yumefusaka.galgamesite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import yumefusaka.galgamesite.pojo.dto.GalGameSearchBySubjectIdDTO;
import yumefusaka.galgamesite.pojo.dto.GalGameSearchByTranslatedNameDTO;

import yumefusaka.galgamesite.pojo.entity.GalGameTwelveVoting;
import yumefusaka.galgamesite.pojo.vo.*;

import java.util.List;

public interface IGalGameTwelveVotingService extends IService<GalGameTwelveVoting> {

    List<GalGameTwelveVotingGameInfoVO> galGameTwelveVotingGameInfoList(GalGameSearchByTranslatedNameDTO gameSearchByTranslatedNameDTO);


    List<GalGameTwelveVotingResultVO> galGameTwelveVotingResultList();

    List<GalGameTwelveVotingHistoryVO> galGameTwelveVotingHistoryList();

    Long galGameTwelveVotingVotesCastCountTotal();

    GalGameTwelveVotingGameInfoByMyselfVO galGameTwelveVotingGameInfoByMyself(GalGameSearchBySubjectIdDTO galGameSearchBySubjectIdDTO);
//
//    Long galGameVoteResultRank(Long subjectId);
//
//    void galGameVoteSubmit(GalGameVoteSubmitDTO galGameVoteResultByUserDTO) throws Exception;
}

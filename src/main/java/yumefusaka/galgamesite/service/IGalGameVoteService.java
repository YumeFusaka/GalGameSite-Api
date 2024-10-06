package yumefusaka.galgamesite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import yumefusaka.galgamesite.pojo.dto.GalGameVoteResultByUserDTO;
import yumefusaka.galgamesite.pojo.entity.GalGameVote;
import yumefusaka.galgamesite.pojo.vo.GalGameVoteHistoryVO;
import yumefusaka.galgamesite.pojo.vo.GalGameVoteResultByUserVO;
import yumefusaka.galgamesite.pojo.vo.GalGameVoteResultVO;

import java.util.List;

public interface IGalGameVoteService extends IService<GalGameVote> {
    List<GalGameVoteResultVO> getGalGameVoteResult();

    List<GalGameVoteHistoryVO> getGalGameVoteHistory();

    Integer galGameVoteByUseSum();

    GalGameVoteResultByUserVO galGameVoteResultByUser(GalGameVoteResultByUserDTO galGameVoteResultByUserDTO);

    Long galGameVoteResultRank(Long subjectId);
}

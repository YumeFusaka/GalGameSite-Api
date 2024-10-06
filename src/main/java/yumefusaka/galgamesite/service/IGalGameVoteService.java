package yumefusaka.galgamesite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import yumefusaka.galgamesite.pojo.entity.GalGameVote;
import yumefusaka.galgamesite.pojo.vo.GalGameVoteHistoryVO;
import yumefusaka.galgamesite.pojo.vo.GalGameVoteResultVO;

import java.util.List;

public interface IGalGameVoteService extends IService<GalGameVote> {
    List<GalGameVoteResultVO> getGalGameVoteResult();

    List<GalGameVoteHistoryVO> getGalGameVoteHistory();
}

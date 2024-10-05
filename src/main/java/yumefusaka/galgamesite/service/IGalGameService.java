package yumefusaka.galgamesite.service;

import com.baomidou.mybatisplus.extension.service.IService;

import yumefusaka.galgamesite.pojo.dto.GalGameVoteItemSearchDTO;
import yumefusaka.galgamesite.pojo.entity.GalGame;
import yumefusaka.galgamesite.pojo.vo.GalGameVO;
import yumefusaka.galgamesite.pojo.vo.GalGameVoteItemSearchVO;


import java.util.List;

public interface IGalGameService  extends IService<GalGame> {

    List<GalGameVoteItemSearchVO> getGalGameVoteItemSearchList(GalGameVoteItemSearchDTO galGameVoteItemSearchDTO);
}

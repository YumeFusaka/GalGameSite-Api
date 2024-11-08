package yumefusaka.galgamesite.service;

import com.baomidou.mybatisplus.extension.service.IService;

import yumefusaka.galgamesite.pojo.dto.GalGameSearchByNameDTO;
import yumefusaka.galgamesite.pojo.dto.GalGameSearchByTranslatedNameDTO;
import yumefusaka.galgamesite.pojo.entity.GalGame;
import yumefusaka.galgamesite.pojo.vo.GalGameVO;

import java.util.List;

public interface IGalGameService extends IService<GalGame> {

    List<GalGameVO> getGalGameSearchByTranslatedNameList(GalGameSearchByTranslatedNameDTO galGameSearchByTranslatedNameDTO);

    List<GalGameVO> getGalGameSearchByNameList(GalGameSearchByNameDTO galGameSearchByNameDTO);
}

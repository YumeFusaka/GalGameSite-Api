package galgamesite.service;

import com.baomidou.mybatisplus.extension.service.IService;

import galgamesite.pojo.dto.GalGameSearchByNameDTO;
import galgamesite.pojo.dto.GalGameSearchByTranslatedNameDTO;
import galgamesite.pojo.entity.GalGame;
import galgamesite.pojo.vo.GalGameVO;

import java.util.List;

public interface IGalGameService extends IService<GalGame> {

    List<GalGameVO> getGalGameSearchByTranslatedNameList(GalGameSearchByTranslatedNameDTO galGameSearchByTranslatedNameDTO);

    List<GalGameVO> getGalGameSearchByNameList(GalGameSearchByNameDTO galGameSearchByNameDTO);
}

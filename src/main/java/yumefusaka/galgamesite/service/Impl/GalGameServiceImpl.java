package yumefusaka.galgamesite.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yumefusaka.galgamesite.mapper.GalGameMapper;

import yumefusaka.galgamesite.pojo.dto.GalGameSearchByNameDTO;
import yumefusaka.galgamesite.pojo.dto.GalGameSearchByTranslatedNameDTO;
import yumefusaka.galgamesite.pojo.entity.GalGame;

import yumefusaka.galgamesite.pojo.vo.GalGameTwelveVotingGameInfoVO;
import yumefusaka.galgamesite.pojo.vo.GalGameVO;
import yumefusaka.galgamesite.service.IGalGameService;

import java.util.ArrayList;
import java.util.List;

@Service
public class GalGameServiceImpl extends ServiceImpl<GalGameMapper, GalGame> implements IGalGameService {

    @Autowired
    private GalGameMapper galGameMapper;

    @Override
    public List<GalGameVO> getGalGameSearchByTranslatedNameList(GalGameSearchByTranslatedNameDTO galGameSearchByTranslatedNameDTO) {
        long pageNo = galGameSearchByTranslatedNameDTO.getPageNo(), pageSize = galGameSearchByTranslatedNameDTO.getPageSize();

        Page<GalGame> page = Page.of(pageNo, pageSize);

        page.addOrder(new OrderItem());

        Page<GalGame> galGamePage = galGameMapper.selectPage(page,
                new QueryWrapper<GalGame>().like("translated_name", galGameSearchByTranslatedNameDTO.getTranslatedName()));
        List<GalGame> galGames = galGamePage.getRecords();

        List<GalGameVO> galGameVOS = new ArrayList<>();
        for (GalGame galGame : galGames){
            GalGameVO galGameVO = new GalGameVO();
            BeanUtils.copyProperties(galGame,galGameVO);
            galGameVOS.add(galGameVO);
        }
        return galGameVOS;
    }

    @Override
    public List<GalGameVO> getGalGameSearchByNameList(GalGameSearchByNameDTO galGameSearchByNameDTO) {
        long pageNo = galGameSearchByNameDTO.getPageNo(), pageSize = galGameSearchByNameDTO.getPageSize();

        Page<GalGame> page = Page.of(pageNo, pageSize);

        page.addOrder(new OrderItem());

        QueryWrapper<GalGame> queryWrapper = new QueryWrapper<GalGame>();
        queryWrapper
                .like("LOWER(translated_name)",galGameSearchByNameDTO.getName().toLowerCase())
                .or()
                .like("LOWER(original_name)",galGameSearchByNameDTO.getName().toLowerCase());

        Page<GalGame> galGamePage = galGameMapper.selectPage(page, queryWrapper);

        List<GalGame> galGames = galGamePage.getRecords();

        List<GalGameVO> galGameVOS = new ArrayList<>();
        for (GalGame galGame : galGames){
            GalGameVO galGameVO = new GalGameVO();
            BeanUtils.copyProperties(galGame,galGameVO);
            galGameVOS.add(galGameVO);
        }
        return galGameVOS;
    }

}

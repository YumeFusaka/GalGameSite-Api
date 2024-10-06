package yumefusaka.galgamesite.service.Impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yumefusaka.galgamesite.mapper.GalGameMapper;

import yumefusaka.galgamesite.pojo.dto.GalGameVoteItemSearchDTO;
import yumefusaka.galgamesite.pojo.entity.GalGame;
import yumefusaka.galgamesite.pojo.vo.GalGameVO;
import yumefusaka.galgamesite.pojo.vo.GalGameVoteItemSearchVO;

import yumefusaka.galgamesite.service.IGalGameService;

import java.util.ArrayList;
import java.util.List;

@Service
public class GalGameServiceImpl extends ServiceImpl<GalGameMapper, GalGame> implements IGalGameService {

    @Autowired
    private GalGameMapper galGameMapper;


    @Override
    public List<GalGameVoteItemSearchVO> getGalGameVoteItemSearchList(GalGameVoteItemSearchDTO galGameVoteItemSearchDTO) {

        long pageNo = galGameVoteItemSearchDTO.getPageNo(), pageSize = galGameVoteItemSearchDTO.getPageSize();

        Page<GalGameVoteItemSearchVO> page = Page.of(pageNo, pageSize);

        page.addOrder(new OrderItem());

        return galGameMapper.galGameVoteItemSearch(page, galGameVoteItemSearchDTO.getSearchName());
    }
}

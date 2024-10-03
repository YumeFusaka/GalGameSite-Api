package yumefusaka.galgamesite.service.Impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yumefusaka.galgamesite.mapper.GalGameMapper;
import yumefusaka.galgamesite.pojo.entity.GalGame;
import yumefusaka.galgamesite.pojo.vo.GalGameVO;
import yumefusaka.galgamesite.service.IGalGameService;

import java.util.ArrayList;
import java.util.List;

@Service
public class GalGameServiceImpl extends ServiceImpl<GalGameMapper, GalGame> implements IGalGameService {

    @Autowired
    private GalGameMapper galGameMapper;

    @Override
    public List<GalGameVO> getGalGameList() {
        // 1.查询
        int pageNo = 1, pageSize = 20;
        // 1.1.分页参数
        Page<GalGame> page = Page.of(pageNo, pageSize);
        // 1.2.排序参数, 通过OrderItem来指定
        page.addOrder(new OrderItem());
        // 1.3.分页查询
        Page<GalGame> p = galGameMapper.selectPage(page, null);
        // 4.分页数据
        List<GalGame> records = p.getRecords();
        ArrayList<GalGameVO> galGameVOList = new ArrayList<>();
        for (GalGame galGame:records){
            GalGameVO galGameVO = new GalGameVO();
            BeanUtils.copyProperties(galGame,galGameVO);
            galGameVOList.add(galGameVO);
        }
        return galGameVOList;
    }
}

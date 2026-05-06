package galgamesite.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import galgamesite.mapper.GameMapper;
import galgamesite.common.page.PageResponse;
import galgamesite.pojo.entity.GalGame;
import galgamesite.pojo.request.game.GameQueryRequest;
import galgamesite.pojo.response.game.GameResponse;
import galgamesite.utils.IKAnalyzerUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService extends ServiceImpl<GameMapper, GalGame> {

    @Autowired
    private GameMapper gameMapper;

    public PageResponse<GameResponse> searchGames(GameQueryRequest queryRequest) {
        long pageNo = queryRequest.getPageNo();
        long pageSize = queryRequest.getPageSize();
        Page<GalGame> page = Page.of(pageNo, pageSize);
        page.addOrder(new OrderItem());
        QueryWrapper<GalGame> queryWrapper = new QueryWrapper<>();

        if (queryRequest.getTranslatedName() != null && !queryRequest.getTranslatedName().isBlank()) {
            queryWrapper.like("translated_name", queryRequest.getTranslatedName().trim());
        } else if (queryRequest.getKeyword() != null && !queryRequest.getKeyword().isBlank()) {
            List<String> keywords;
            try {
                keywords = IKAnalyzerUtils.iKSegmenterToList(queryRequest.getKeyword());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            for (String keyword : keywords) {
                queryWrapper
                        .like("LOWER(translated_name)", keyword.toLowerCase())
                        .or()
                        .like("LOWER(original_name)", keyword.toLowerCase());
            }
        }
        Page<GalGame> galGamePage = gameMapper.selectPage(page, queryWrapper);
        List<GalGame> galGames = galGamePage.getRecords();
        List<GameResponse> items = new ArrayList<>();
        for (GalGame galGame : galGames){
            GameResponse response = new GameResponse();
            BeanUtils.copyProperties(galGame, response);
            items.add(response);
        }
        return new PageResponse<>(items, galGamePage.getTotal(), galGamePage.getCurrent(), galGamePage.getSize());
    }
}

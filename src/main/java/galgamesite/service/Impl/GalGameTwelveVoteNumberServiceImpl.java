package galgamesite.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import galgamesite.common.context.BaseContext;
import galgamesite.mapper.GalGameTwelveVoteNumberMapper;
import galgamesite.pojo.entity.GalGameTwelveVoteNumber;
import galgamesite.service.IGalGameTwelveVoteNumberService;

@Service
public class GalGameTwelveVoteNumberServiceImpl extends ServiceImpl<GalGameTwelveVoteNumberMapper, GalGameTwelveVoteNumber>
        implements IGalGameTwelveVoteNumberService {

    @Override
    public Integer getUserVoteNumber(Integer edition) {
        String uin = BaseContext.getCurrentId();
        GalGameTwelveVoteNumber voteNumber = getBaseMapper().selectByUinAndEdition(uin, edition);
        return voteNumber != null ? voteNumber.getNumber() : 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setUserVoteNumber(Integer edition, Integer number) {
        String uin = BaseContext.getCurrentId();
        GalGameTwelveVoteNumber voteNumber = getBaseMapper().selectByUinAndEdition(uin, edition);
        
        if (voteNumber != null) {
            // 已存在记录，更新票数
            voteNumber.setNumber(number);
            getBaseMapper().updateById(voteNumber);
        } else {
            // 不存在记录，新建
            GalGameTwelveVoteNumber newVoteNumber = new GalGameTwelveVoteNumber();
            newVoteNumber.setUin(uin);
            newVoteNumber.setNumber(number);
            newVoteNumber.setEdition(edition);
            save(newVoteNumber);
        }
    }


}

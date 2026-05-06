package galgamesite.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import galgamesite.common.context.BaseContext;
import galgamesite.mapper.VoteQuotaMapper;
import galgamesite.pojo.entity.GalGameTwelveVoteNumber;

@Service
public class VoteQuotaService extends ServiceImpl<VoteQuotaMapper, GalGameTwelveVoteNumber> {

    public Integer getCurrentUserVoteQuota(Integer edition) {
        String uin = BaseContext.getCurrentId();
        GalGameTwelveVoteNumber voteNumber = getBaseMapper().selectByUinAndEdition(uin, edition);
        return voteNumber != null ? voteNumber.getNumber() : 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateCurrentUserVoteQuota(Integer edition, Integer totalVotes) {
        String uin = BaseContext.getCurrentId();
        GalGameTwelveVoteNumber voteNumber = getBaseMapper().selectByUinAndEdition(uin, edition);
        
        if (voteNumber != null) {
            voteNumber.setNumber(totalVotes);
            getBaseMapper().updateById(voteNumber);
        } else {
            GalGameTwelveVoteNumber newVoteNumber = new GalGameTwelveVoteNumber();
            newVoteNumber.setUin(uin);
            newVoteNumber.setNumber(totalVotes);
            newVoteNumber.setEdition(edition);
            save(newVoteNumber);
        }
    }


}

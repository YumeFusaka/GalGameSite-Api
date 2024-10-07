package yumefusaka.galgamesite.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yumefusaka.galgamesite.common.context.BaseContext;
import yumefusaka.galgamesite.mapper.GalGameMapper;
import yumefusaka.galgamesite.mapper.GalGameVoteMapper;
import yumefusaka.galgamesite.pojo.dto.GalGameVoteResultByUserDTO;
import yumefusaka.galgamesite.pojo.entity.GalGame;
import yumefusaka.galgamesite.pojo.entity.GalGameVote;
import yumefusaka.galgamesite.pojo.vo.GalGameVoteHistoryVO;
import yumefusaka.galgamesite.pojo.vo.GalGameVoteResultByUserVO;
import yumefusaka.galgamesite.pojo.vo.GalGameVoteResultVO;
import yumefusaka.galgamesite.service.IGalGameService;
import yumefusaka.galgamesite.service.IGalGameVoteService;

import java.util.List;

@Service
public class GalGameVoteServiceImpl extends ServiceImpl<GalGameVoteMapper, GalGameVote> implements IGalGameVoteService {

    @Autowired
    private GalGameVoteMapper galGameVoteMapper;

    @Autowired
    private GalGameMapper galGameMapper;

    @Override
    public List<GalGameVoteResultVO> getGalGameVoteResult() {
        return galGameVoteMapper.selectGalGameVoteResult();
    }

    @Override
    public List<GalGameVoteHistoryVO> getGalGameVoteHistory() {
        String qq = BaseContext.getCurrentId();
        BaseContext.removeCurrentId();
        return galGameVoteMapper.selectGalGameVoteHistory(qq);
    }

    @Override
    public Integer galGameVoteByUseSum() {
        String qq = BaseContext.getCurrentId();
        BaseContext.removeCurrentId();
        return galGameVoteMapper.galGameVoteByUseCount(qq);
    }



    @Override
    public GalGameVoteResultByUserVO galGameVoteResultByUser(GalGameVoteResultByUserDTO galGameVoteResultByUserDTO) {
        String qq = BaseContext.getCurrentId();
        BaseContext.removeCurrentId();

        GalGameVoteResultByUserVO galGameVoteResultByUserVO;
        List<GalGameVote> galGameVote1 = galGameVoteMapper.selectList(new QueryWrapper<GalGameVote>()
                .eq("qq", qq).eq("subject_id", galGameVoteResultByUserDTO.getSubjectId()));
        if(galGameVote1.isEmpty()){
            GalGame galGame = galGameMapper.selectOne(new QueryWrapper<GalGame>()
                    .eq("subject_id", galGameVoteResultByUserDTO.getSubjectId()));
            galGameVoteResultByUserVO = new GalGameVoteResultByUserVO();
            BeanUtils.copyProperties(galGame, galGameVoteResultByUserVO);
        }else{
            galGameVoteResultByUserVO =
                    galGameVoteMapper.galGameVoteResultByUser(qq, galGameVoteResultByUserDTO.getSubjectId());
        }
        List<GalGameVote> galGameVote2 = galGameVoteMapper.selectList(new QueryWrapper<GalGameVote>()
                .eq("subject_id", galGameVoteResultByUserDTO.getSubjectId()));
        if(!galGameVote2.isEmpty()){
            galGameVoteResultByUserVO.setMyRank(
                    galGameVoteMapper.galGameVoteResultRank(galGameVoteResultByUserDTO.getSubjectId()));
        }
        return galGameVoteResultByUserVO;
    }

    @Override
    public Long galGameVoteResultRank(Long subjectId) {
        return galGameVoteMapper.galGameVoteResultRank(subjectId);
    }
}

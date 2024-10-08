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
import yumefusaka.galgamesite.mapper.UserMapper;
import yumefusaka.galgamesite.pojo.dto.GalGameVoteResultByUserDTO;
import yumefusaka.galgamesite.pojo.dto.GalGameVoteSubmitDTO;
import yumefusaka.galgamesite.pojo.entity.GalGame;
import yumefusaka.galgamesite.pojo.entity.GalGameVote;
import yumefusaka.galgamesite.pojo.entity.User;
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

    @Autowired
    private UserMapper userMapper;

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
        Integer useCount = galGameVoteMapper.galGameVoteByUseCount(qq);
        if(useCount == null){
            return 0;
        }
        return useCount;
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
            Long myVote = galGameVoteMapper.galGameVoteResultVote(galGameVoteResultByUserDTO.getSubjectId());
            galGameVoteResultByUserVO.setMyVote(myVote);
        }
        return galGameVoteResultByUserVO;
    }

    @Override
    public Long galGameVoteResultRank(Long subjectId) {
        return galGameVoteMapper.galGameVoteResultRank(subjectId);
    }

    @Override
    public void galGameVoteSubmit(GalGameVoteSubmitDTO galGameVoteResultByUserDTO) throws Exception {
        String qq = BaseContext.getCurrentId();

        GalGameVote galGameVoteExist = galGameVoteMapper.selectOne(new QueryWrapper<GalGameVote>()
                .eq("qq", qq).eq("subject_id", galGameVoteResultByUserDTO.getSubjectId()));

        Integer leftover = 30 - galGameVoteByUseSum();

        if(galGameVoteExist == null){
            if(galGameVoteResultByUserDTO.getVote() != 0){
                // 1.如果没有记录
                if(galGameVoteResultByUserDTO.getVote() > leftover){
                    throw new Exception("您的剩余票数不够哦,杂鱼~");
                }
                User user = userMapper.selectOne(new QueryWrapper<User>().eq("qq", qq));
                GalGame galGame = galGameMapper.selectOne(new QueryWrapper<GalGame>()
                        .eq("subject_id", galGameVoteResultByUserDTO.getSubjectId()));
                GalGameVote galGameVote = new GalGameVote();
                galGameVote.setGameName(galGame.getName());
                galGameVote.setVoteNum(galGameVoteResultByUserDTO.getVote());
                galGameVote.setQq(qq);
                galGameVote.setSubjectId(galGameVoteResultByUserDTO.getSubjectId());
                galGameVote.setQqName(user.getNickName());
                galGameVoteMapper.insert(galGameVote);
            }else{
                // 2.如果没有记录但是投0票
                return;
            }
        }else{
            if(galGameVoteResultByUserDTO.getVote() != 0){
                // 3.如果有记录
                if(galGameVoteResultByUserDTO.getVote() - galGameVoteExist.getVoteNum() > leftover){
                    throw new Exception("您的剩余票数不够哦,杂鱼~");
                }
                galGameVoteExist.setVoteNum(galGameVoteResultByUserDTO.getVote());
                galGameVoteMapper.updateById(galGameVoteExist);
            }else{
                // 4.如果有记录但是投0票
                galGameVoteMapper.delete(new QueryWrapper<GalGameVote>()
                        .eq("qq", qq).eq("subject_id", galGameVoteResultByUserDTO.getSubjectId()));
            }
        }
    }
}

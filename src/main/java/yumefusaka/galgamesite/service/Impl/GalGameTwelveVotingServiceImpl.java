package yumefusaka.galgamesite.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yumefusaka.galgamesite.common.context.BaseContext;
import yumefusaka.galgamesite.mapper.GalGameMapper;
import yumefusaka.galgamesite.mapper.GalGameTwelveVotingMapper;
import yumefusaka.galgamesite.mapper.UserMapper;
import yumefusaka.galgamesite.pojo.dto.GalGameSearchBySubjectIdDTO;
import yumefusaka.galgamesite.pojo.dto.GalGameSearchByTranslatedNameDTO;

import yumefusaka.galgamesite.pojo.dto.GalGameTwelveVotingInitiateVoteDTO;
import yumefusaka.galgamesite.pojo.entity.GalGame;
import yumefusaka.galgamesite.pojo.entity.GalGameTwelveVoting;

import yumefusaka.galgamesite.pojo.entity.User;
import yumefusaka.galgamesite.pojo.vo.*;
import yumefusaka.galgamesite.service.IGalGameTwelveVotingService;

import java.util.List;

@Service
public class GalGameTwelveVotingServiceImpl extends ServiceImpl<GalGameTwelveVotingMapper, GalGameTwelveVoting> implements IGalGameTwelveVotingService {

    @Autowired
    private GalGameTwelveVotingMapper galGameTwelveVotingMapper;

    @Autowired
    private GalGameMapper galGameMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<GalGameTwelveVotingGameInfoVO> getGalGameTwelveVotingGameInfoList(GalGameSearchByTranslatedNameDTO gameSearchByTranslatedNameDTO) {

        long pageNo = gameSearchByTranslatedNameDTO.getPageNo(), pageSize = gameSearchByTranslatedNameDTO.getPageSize();

        Page<GalGameTwelveVotingGameInfoVO> page = Page.of(pageNo, pageSize);

        page.addOrder(new OrderItem());

        return galGameMapper.getGalGameTwelveVotingGameInfoList(page, gameSearchByTranslatedNameDTO.getTranslatedName());
    }



    @Override
    public List<GalGameTwelveVotingResultVO> getGalGameTwelveVotingResultList() {
        return galGameTwelveVotingMapper.getGalGameTwelveVotingResultList();
    }

    @Override
    public List<GalGameTwelveVotingHistoryVO> getGalGameTwelveVotingHistoryList() {
        String uin = BaseContext.getCurrentId();
        BaseContext.removeCurrentId();
        return galGameTwelveVotingMapper.getGalGameTwelveVotingHistoryList(uin);
    }

    @Override
    public Long getGalGameTwelveVotingVotesCastCountTotal() {
        String uin = BaseContext.getCurrentId();
        BaseContext.removeCurrentId();
        Long votingVotesCastCount = galGameTwelveVotingMapper.getGalGameTwelveVotingVotesCastCountTotal(uin);
        if(votingVotesCastCount == null){
            return 0L;
        }
        return votingVotesCastCount;
    }



    @Override
    public GalGameTwelveVotingGameInfoByMyselfVO getGalGameTwelveVotingGameInfoByMyself(GalGameSearchBySubjectIdDTO galGameSearchBySubjectIdDTO) {
        String uin = BaseContext.getCurrentId();
        BaseContext.removeCurrentId();

        GalGameTwelveVotingGameInfoByMyselfVO galGameTwelveVotingGameInfoByMyselfVO;
        List<GalGameTwelveVoting> galGameTwelveVotingList1 = galGameTwelveVotingMapper.selectList(new QueryWrapper<GalGameTwelveVoting>()
                .eq("user_uin", uin).eq("galgame_subject_id", galGameSearchBySubjectIdDTO.getSubjectId()));

        // 查询自己对该作品的投票记录
        if(galGameTwelveVotingList1.isEmpty()){
            // 如果自己没有对该作品投票，则获取该作品的其他信息
            GalGame galGame = galGameMapper.selectOne(new QueryWrapper<GalGame>()
                    .eq("subject_id", galGameSearchBySubjectIdDTO.getSubjectId()));

            galGameTwelveVotingGameInfoByMyselfVO = new GalGameTwelveVotingGameInfoByMyselfVO();
            BeanUtils.copyProperties(galGame, galGameTwelveVotingGameInfoByMyselfVO);
        }else{
            // 如果自己对该作品投过票，则获取该作品信息
            galGameTwelveVotingGameInfoByMyselfVO =
                    galGameTwelveVotingMapper.getGalGameTwelveVotingGameInfoByMyself(uin, galGameSearchBySubjectIdDTO.getSubjectId());
        }
        List<GalGameTwelveVoting> galGameTwelveVotingList2 = galGameTwelveVotingMapper.selectList(new QueryWrapper<GalGameTwelveVoting>()
                .eq("galgame_subject_id", galGameSearchBySubjectIdDTO.getSubjectId()));

        // 查询该作品是否有人投过票
        if(!galGameTwelveVotingList2.isEmpty()){
            // 如果有则顺带获取排名和总票数
            galGameTwelveVotingGameInfoByMyselfVO.setTotalRank(
                    galGameTwelveVotingMapper.getGalGameTwelveVotingResultTotalRank(galGameSearchBySubjectIdDTO.getSubjectId()));
            Long totalVotes = galGameTwelveVotingMapper.getGalGameTwelveVotingResultTotalVotes(galGameSearchBySubjectIdDTO.getSubjectId());
            galGameTwelveVotingGameInfoByMyselfVO.setTotalVotes(totalVotes);
        }
        return galGameTwelveVotingGameInfoByMyselfVO;
    }


    @Override
    public Long getGalGameTwelveVotingResultTotalRank(Long subjectId) {
        return galGameTwelveVotingMapper.getGalGameTwelveVotingResultTotalRank(subjectId);
    }

    @Override
    public void postGalGameTwelveVotingInitiateVote(GalGameTwelveVotingInitiateVoteDTO galGameTwelveVotingInitiateVoteDTO) throws Exception {
        String uin = BaseContext.getCurrentId();
        BaseContext.removeCurrentId();

        GalGameTwelveVoting galGameVoteExist = galGameTwelveVotingMapper.selectOne(new QueryWrapper<GalGameTwelveVoting>()
                .eq("user_uin", uin).eq("galgame_subject_id", galGameTwelveVotingInitiateVoteDTO.getSubjectId()));

        long leftover = 30 - getGalGameTwelveVotingVotesCastCountTotal();

        if(galGameVoteExist == null){
            if(galGameTwelveVotingInitiateVoteDTO.getVotesCastCount() != 0){
                // 1.如果没有记录
                if(galGameTwelveVotingInitiateVoteDTO.getVotesCastCount() > leftover){
                    throw new Exception("您的剩余票数不够哦,杂鱼~");
                }
                User user = userMapper.selectOne(new QueryWrapper<User>().eq("uin", uin));
                GalGame galGame = galGameMapper.selectOne(new QueryWrapper<GalGame>()
                        .eq("subject_id", galGameTwelveVotingInitiateVoteDTO.getSubjectId()));
                GalGameTwelveVoting galGameVote = new GalGameTwelveVoting();
                galGameVote.setGalgameTranslatedName(galGame.getTranslatedName());
                galGameVote.setVotesCastCount(galGameTwelveVotingInitiateVoteDTO.getVotesCastCount());
                galGameVote.setUserUin(uin);
                galGameVote.setGalgameSubjectId(galGameTwelveVotingInitiateVoteDTO.getSubjectId());
                galGameVote.setUserNick(user.getNick());
                galGameTwelveVotingMapper.insert(galGameVote);
            }else{
                // 2.如果没有记录但是投0票
                return;
            }
        }else{
            if(galGameTwelveVotingInitiateVoteDTO.getVotesCastCount() != 0){
                // 3.如果有记录
                if(galGameTwelveVotingInitiateVoteDTO.getVotesCastCount() - galGameVoteExist.getVotesCastCount() > leftover){
                    throw new Exception("您的剩余票数不够哦,杂鱼~");
                }
                galGameVoteExist.setVotesCastCount(galGameTwelveVotingInitiateVoteDTO.getVotesCastCount());
                galGameTwelveVotingMapper.updateById(galGameVoteExist);
            }else{
                // 4.如果有记录但是投0票
                galGameTwelveVotingMapper.delete(new QueryWrapper<GalGameTwelveVoting>()
                        .eq("user_uin", uin).eq("galgame_subject_id", galGameTwelveVotingInitiateVoteDTO.getSubjectId()));
            }
        }
    }
}

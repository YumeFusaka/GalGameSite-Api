package yumefusaka.galgamesite.service.Impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yumefusaka.galgamesite.common.context.BaseContext;
import yumefusaka.galgamesite.mapper.GalGameMapper;
import yumefusaka.galgamesite.mapper.GalGameTwelveVotingMapper;
import yumefusaka.galgamesite.mapper.UserMapper;
import yumefusaka.galgamesite.pojo.dto.GalGameSearchByTranslatedNameDTO;

import yumefusaka.galgamesite.pojo.entity.GalGameTwelveVoting;

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
    public List<GalGameTwelveVotingGameInfoVO> galGameTwelveVotingGameInfoList(GalGameSearchByTranslatedNameDTO gameSearchByTranslatedNameDTO) {

        long pageNo = gameSearchByTranslatedNameDTO.getPageNo(), pageSize = gameSearchByTranslatedNameDTO.getPageSize();

        Page<GalGameTwelveVotingGameInfoVO> page = Page.of(pageNo, pageSize);

        page.addOrder(new OrderItem());

        return galGameMapper.galGameTwelveVotingGameInfoList(page, gameSearchByTranslatedNameDTO.getTranslatedName());
    }



    @Override
    public List<GalGameTwelveVotingResultVO> galGameTwelveVotingResultList() {
        return galGameTwelveVotingMapper.galGameTwelveVotingResultList();
    }

    @Override
    public List<GalGameTwelveVotingHistoryVO> galGameTwelveVotingHistoryList() {
        String uin = BaseContext.getCurrentId();
        BaseContext.removeCurrentId();
        return galGameTwelveVotingMapper.galGameTwelveVotingHistoryList(uin);
    }

    @Override
    public Long galGameTwelveVotingVotesCastCount() {
        String uin = BaseContext.getCurrentId();
        BaseContext.removeCurrentId();
        Long votingVotesCastCount = galGameTwelveVotingMapper.galGameTwelveVotingVotesCastCount(uin);
        if(votingVotesCastCount == null){
            return 0L;
        }
        return votingVotesCastCount;
    }
//
//
//
//    @Override
//    public GalGameVoteResultByUserVO galGameVoteResultByUser(GalGameVoteResultByUserDTO galGameVoteResultByUserDTO) {
//        String qq = BaseContext.getCurrentId();
//        BaseContext.removeCurrentId();
//
//        GalGameVoteResultByUserVO galGameVoteResultByUserVO;
//        List<GalGameTwelveVoting> galGameVote1 = galGameVoteMapper.selectList(new QueryWrapper<GalGameTwelveVoting>()
//                .eq("qq", qq).eq("subject_id", galGameVoteResultByUserDTO.getSubjectId()));
//        if(galGameVote1.isEmpty()){
//            GalGame galGame = galGameMapper.selectOne(new QueryWrapper<GalGame>()
//                    .eq("subject_id", galGameVoteResultByUserDTO.getSubjectId()));
//            galGameVoteResultByUserVO = new GalGameVoteResultByUserVO();
//            BeanUtils.copyProperties(galGame, galGameVoteResultByUserVO);
//        }else{
//            galGameVoteResultByUserVO =
//                    galGameVoteMapper.galGameVoteResultByUser(qq, galGameVoteResultByUserDTO.getSubjectId());
//        }
//        List<GalGameTwelveVoting> galGameVote2 = galGameVoteMapper.selectList(new QueryWrapper<GalGameTwelveVoting>()
//                .eq("subject_id", galGameVoteResultByUserDTO.getSubjectId()));
//        if(!galGameVote2.isEmpty()){
//            galGameVoteResultByUserVO.setMyRank(
//                    galGameVoteMapper.galGameVoteResultRank(galGameVoteResultByUserDTO.getSubjectId()));
//            Long myVote = galGameVoteMapper.galGameVoteResultVote(galGameVoteResultByUserDTO.getSubjectId());
//            galGameVoteResultByUserVO.setMyVote(myVote);
//        }
//        return galGameVoteResultByUserVO;
//    }
//
//    @Override
//    public Long galGameVoteResultRank(Long subjectId) {
//        return galGameVoteMapper.galGameVoteResultRank(subjectId);
//    }
//
//    @Override
//    public void galGameVoteSubmit(GalGameVoteSubmitDTO galGameVoteResultByUserDTO) throws Exception {
//        String qq = BaseContext.getCurrentId();
//
//        GalGameTwelveVoting galGameVoteExist = galGameVoteMapper.selectOne(new QueryWrapper<GalGameTwelveVoting>()
//                .eq("qq", qq).eq("subject_id", galGameVoteResultByUserDTO.getSubjectId()));
//
//        Integer leftover = 30 - galGameVoteByUseSum();
//
//        if(galGameVoteExist == null){
//            if(galGameVoteResultByUserDTO.getVote() != 0){
//                // 1.如果没有记录
//                if(galGameVoteResultByUserDTO.getVote() > leftover){
//                    throw new Exception("您的剩余票数不够哦,杂鱼~");
//                }
//                User user = userMapper.selectOne(new QueryWrapper<User>().eq("qq", qq));
//                GalGame galGame = galGameMapper.selectOne(new QueryWrapper<GalGame>()
//                        .eq("subject_id", galGameVoteResultByUserDTO.getSubjectId()));
//                GalGameTwelveVoting galGameVote = new GalGameTwelveVoting();
//                galGameVote.setGameName(galGame.getName());
//                galGameVote.setVoteNum(galGameVoteResultByUserDTO.getVote());
//                galGameVote.setQq(qq);
//                galGameVote.setSubjectId(galGameVoteResultByUserDTO.getSubjectId());
//                galGameVote.setQqName(user.getNickName());
//                galGameVoteMapper.insert(galGameVote);
//            }else{
//                // 2.如果没有记录但是投0票
//                return;
//            }
//        }else{
//            if(galGameVoteResultByUserDTO.getVote() != 0){
//                // 3.如果有记录
//                if(galGameVoteResultByUserDTO.getVote() - galGameVoteExist.getVoteNum() > leftover){
//                    throw new Exception("您的剩余票数不够哦,杂鱼~");
//                }
//                galGameVoteExist.setVoteNum(galGameVoteResultByUserDTO.getVote());
//                galGameVoteMapper.updateById(galGameVoteExist);
//            }else{
//                // 4.如果有记录但是投0票
//                galGameVoteMapper.delete(new QueryWrapper<GalGameTwelveVoting>()
//                        .eq("qq", qq).eq("subject_id", galGameVoteResultByUserDTO.getSubjectId()));
//            }
//        }
//    }
}

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
    public List<GalGameTwelveVotingGameInfoVO> getGalGameTwelveVotingGameInfoList(GalGameSearchByTranslatedNameDTO gameSearchByTranslatedNameDTO, Integer edition) {
        long pageNo = gameSearchByTranslatedNameDTO.getPageNo(), pageSize = gameSearchByTranslatedNameDTO.getPageSize();
        Page<GalGameTwelveVotingGameInfoVO> page = Page.of(pageNo, pageSize);
        page.addOrder(new OrderItem());
        return galGameMapper.getGalGameTwelveVotingGameInfoList(
                page,
                gameSearchByTranslatedNameDTO.getTranslatedName(),
                edition // 传入 edition
        );
    }

    @Override
    public List<GalGameTwelveVotingResultVO> getGalGameTwelveVotingResultList(Integer edition) {
        return galGameTwelveVotingMapper.getGalGameTwelveVotingResultList(edition);
    }

    @Override
    public List<GalGameTwelveVotingHistoryVO> getGalGameTwelveVotingHistoryList(Integer edition) {
        String uin = BaseContext.getCurrentId();
        BaseContext.removeCurrentId();
        return galGameTwelveVotingMapper.getGalGameTwelveVotingHistoryListByEdition(uin, edition);
    }

    @Override
    public Long getGalGameTwelveVotingVotesCastCountTotal(Integer edition) {
        String uin = BaseContext.getCurrentId();
        BaseContext.removeCurrentId();
        Long count = galGameTwelveVotingMapper.getGalGameTwelveVotingVotesCastCountTotalByEdition(uin, edition);
        return count == null ? 0L : count;
    }
    @Override
    public GalGameTwelveVotingGameInfoByMyselfVO getGalGameTwelveVotingGameInfoByMyself(GalGameSearchBySubjectIdDTO galGameSearchBySubjectIdDTO, Integer edition) {
        String uin = BaseContext.getCurrentId();
        BaseContext.removeCurrentId();

        GalGameTwelveVotingGameInfoByMyselfVO vo;

        List<GalGameTwelveVoting> votingList = galGameTwelveVotingMapper.selectList(
                new QueryWrapper<GalGameTwelveVoting>()
                        .eq("user_uin", uin)
                        .eq("galgame_subject_id", galGameSearchBySubjectIdDTO.getSubjectId())
                        .eq("edition", edition)
        );

        if(votingList.isEmpty()) {
            GalGame galGame = galGameMapper.selectOne(
                    new QueryWrapper<GalGame>().eq("subject_id", galGameSearchBySubjectIdDTO.getSubjectId())
            );
            vo = new GalGameTwelveVotingGameInfoByMyselfVO();
            BeanUtils.copyProperties(galGame, vo);
            vo.setEdition(edition);
        } else {
            vo = galGameTwelveVotingMapper.getGalGameTwelveVotingGameInfoByMyself(
                    uin, galGameSearchBySubjectIdDTO.getSubjectId(), edition
            );
        }

        List<GalGameTwelveVoting> totalVotingList = galGameTwelveVotingMapper.selectList(
                new QueryWrapper<GalGameTwelveVoting>()
                        .eq("galgame_subject_id", galGameSearchBySubjectIdDTO.getSubjectId())
                        .eq("edition", edition)
        );

        if(!totalVotingList.isEmpty()) {
            vo.setTotalRank(galGameTwelveVotingMapper.getGalGameTwelveVotingResultTotalRank(
                    galGameSearchBySubjectIdDTO.getSubjectId(), edition
            ));
            Long totalVotes = galGameTwelveVotingMapper.getGalGameTwelveVotingResultTotalVotes(
                    galGameSearchBySubjectIdDTO.getSubjectId(), edition
            );
            vo.setTotalVotes(totalVotes);
        }

        return vo;
    }

    @Override
    public Long getGalGameTwelveVotingResultTotalRank(Long subjectId, Integer edition) {
        return galGameTwelveVotingMapper.getGalGameTwelveVotingResultTotalRank(subjectId, edition);
    }

    @Override
    public void postGalGameTwelveVotingInitiateVote(GalGameTwelveVotingInitiateVoteDTO dto, Integer edition) throws Exception {
        String uin = BaseContext.getCurrentId();

        GalGameTwelveVoting existingVote = galGameTwelveVotingMapper.selectOne(
                new QueryWrapper<GalGameTwelveVoting>()
                        .eq("user_uin", uin)
                        .eq("galgame_subject_id", dto.getSubjectId())
                        .eq("edition", edition)
        );

        long leftover = 30 - getGalGameTwelveVotingVotesCastCountTotal(edition);

        if(existingVote == null) {
            if(dto.getVotesCastCount() != 0) {
                if(dto.getVotesCastCount() > leftover) throw new Exception("您的剩余票数不够哦,杂鱼~");
                User user = userMapper.selectOne(new QueryWrapper<User>().eq("uin", uin));
                GalGame galGame = galGameMapper.selectOne(new QueryWrapper<GalGame>().eq("subject_id", dto.getSubjectId()));
                GalGameTwelveVoting vote = new GalGameTwelveVoting();
                vote.setGalgameTranslatedName(galGame.getTranslatedName());
                vote.setVotesCastCount(dto.getVotesCastCount());
                vote.setUserUin(uin);
                vote.setGalgameSubjectId(dto.getSubjectId());
                vote.setUserNick(user.getNick());
                vote.setEdition(edition);
                galGameTwelveVotingMapper.insert(vote);
            }
        } else {
            if(dto.getVotesCastCount() != 0) {
                if(dto.getVotesCastCount() - existingVote.getVotesCastCount() > leftover) throw new Exception("您的剩余票数不够哦,杂鱼~");
                existingVote.setVotesCastCount(dto.getVotesCastCount());
                existingVote.setEdition(edition);
                galGameTwelveVotingMapper.updateById(existingVote);
            } else {
                galGameTwelveVotingMapper.delete(
                        new QueryWrapper<GalGameTwelveVoting>()
                                .eq("user_uin", uin)
                                .eq("galgame_subject_id", dto.getSubjectId())
                                .eq("edition", edition)
                );
            }
        }
    }
}
package galgamesite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import galgamesite.common.page.PageResponse;
import galgamesite.common.context.BaseContext;
import galgamesite.mapper.GameMapper;
import galgamesite.mapper.TwelveVotingMapper;
import galgamesite.mapper.UserMapper;
import galgamesite.pojo.entity.GalGameTwelveVoting;
import galgamesite.pojo.entity.GalGame;
import galgamesite.pojo.entity.User;
import galgamesite.pojo.request.activity.VoteSubmissionRequest;
import galgamesite.pojo.request.game.GameQueryRequest;
import galgamesite.pojo.response.activity.VoteBallotResponse;
import galgamesite.pojo.response.activity.VotingGameListItemResponse;
import galgamesite.pojo.response.activity.VotingRecordResponse;
import galgamesite.pojo.response.activity.VotingResultResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

@Service
public class TwelveVotingService extends ServiceImpl<TwelveVotingMapper, GalGameTwelveVoting> {

    @Autowired
    private TwelveVotingMapper twelveVotingMapper;

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VoteQuotaService voteQuotaService;

    public PageResponse<VotingGameListItemResponse> listVotingGames(GameQueryRequest queryRequest, Integer edition) {
        long pageNo = queryRequest.getPageNo();
        long pageSize = queryRequest.getPageSize();
        Page<VotingGameListItemResponse> page = Page.of(pageNo, pageSize);
        page.addOrder(new OrderItem());
        List<VotingGameListItemResponse> items = gameMapper.selectVotingGamePage(
                page,
                queryRequest.getTranslatedName(),
                edition
        );
        return new PageResponse<>(items, page.getTotal(), page.getCurrent(), page.getSize());
    }

    public List<VotingResultResponse> listVotingResults(Integer edition) {
        return twelveVotingMapper.selectResultsByEdition(edition);
    }

    public List<VotingRecordResponse> listCurrentUserVotingRecords(Integer edition) {
        String uin = BaseContext.getCurrentId();
        return twelveVotingMapper.selectRecordsByEdition(uin, edition);
    }

    public Long getCurrentUserUsedVotes(Integer edition) {
        String uin = BaseContext.getCurrentId();
        Long count = twelveVotingMapper.sumUsedVotesByEdition(uin, edition);
        return count == null ? 0L : count;
    }

    public VoteBallotResponse getCurrentUserBallot(Long subjectId, Integer edition) {
        String uin = BaseContext.getCurrentId();
        VoteBallotResponse response;

        List<GalGameTwelveVoting> votingList = twelveVotingMapper.selectList(
                new QueryWrapper<GalGameTwelveVoting>()
                        .eq("user_uin", uin)
                        .eq("galgame_subject_id", subjectId)
                        .eq("edition", edition)
        );

        if (votingList.isEmpty()) {
            GalGame game = gameMapper.selectOne(
                    new QueryWrapper<GalGame>().eq("subject_id", subjectId)
            );
            response = new VoteBallotResponse();
            BeanUtils.copyProperties(game, response);
            response.setEdition(edition);
        } else {
            response = twelveVotingMapper.selectBallotBySubjectAndEdition(uin, subjectId, edition);
        }

        List<GalGameTwelveVoting> totalVotingList = twelveVotingMapper.selectList(
                new QueryWrapper<GalGameTwelveVoting>()
                        .eq("galgame_subject_id", subjectId)
                        .eq("edition", edition)
        );

        if (!totalVotingList.isEmpty()) {
            response.setTotalRank(twelveVotingMapper.selectRankBySubjectAndEdition(subjectId, edition));
            Long totalVotes = twelveVotingMapper.sumVotesBySubjectAndEdition(subjectId, edition);
            response.setTotalVotes(totalVotes);
        }

        return response;
    }

    public Long getVotingRank(Long subjectId, Integer edition) {
        return twelveVotingMapper.selectRankBySubjectAndEdition(subjectId, edition);
    }

    public void submitVote(Long subjectId, Integer edition, VoteSubmissionRequest request) throws Exception {
        String uin = BaseContext.getCurrentId();

        GalGameTwelveVoting existingVote = twelveVotingMapper.selectOne(
                new QueryWrapper<GalGameTwelveVoting>()
                        .eq("user_uin", uin)
                        .eq("galgame_subject_id", subjectId)
                        .eq("edition", edition)
        );

        long leftover = voteQuotaService.getCurrentUserVoteQuota(edition)
                - getCurrentUserUsedVotes(edition);

        if (existingVote == null) {
            if (request.getVotesCastCount() != 0) {
                if (request.getVotesCastCount() > leftover) throw new Exception("您的剩余票数不够哦,杂鱼~");
                User user = userMapper.selectOne(new QueryWrapper<User>().eq("uin", uin));
                GalGame game = gameMapper.selectOne(new QueryWrapper<GalGame>().eq("subject_id", subjectId));
                GalGameTwelveVoting vote = new GalGameTwelveVoting();
                vote.setGalgameTranslatedName(game.getTranslatedName());
                vote.setVotesCastCount(request.getVotesCastCount());
                vote.setUserUin(uin);
                vote.setGalgameSubjectId(subjectId);
                vote.setUserNick(user.getNick());
                vote.setEdition(edition);
                twelveVotingMapper.insert(vote);
            }
        } else {
            if (request.getVotesCastCount() != 0) {
                if (request.getVotesCastCount() - existingVote.getVotesCastCount() > leftover) throw new Exception("您的剩余票数不够哦,杂鱼~");
                existingVote.setVotesCastCount(request.getVotesCastCount());
                existingVote.setEdition(edition);
                twelveVotingMapper.updateById(existingVote);
            } else {
                twelveVotingMapper.delete(
                        new QueryWrapper<GalGameTwelveVoting>()
                                .eq("user_uin", uin)
                                .eq("galgame_subject_id", subjectId)
                                .eq("edition", edition)
                );
            }
        }
    }
}

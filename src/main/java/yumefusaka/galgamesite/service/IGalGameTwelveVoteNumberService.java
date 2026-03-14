package yumefusaka.galgamesite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import yumefusaka.galgamesite.pojo.entity.GalGameTwelveVoteNumber;

public interface IGalGameTwelveVoteNumberService extends IService<GalGameTwelveVoteNumber> {

    /**
     * 获取用户的票数
     * @param edition 届次
     * @return 票数
     */
    Integer getUserVoteNumber(Integer edition);

    /**
     * 设置用户票数
     * @param edition 届次
     * @param number 票数
     */
    void setUserVoteNumber(Integer edition, Integer number);


}

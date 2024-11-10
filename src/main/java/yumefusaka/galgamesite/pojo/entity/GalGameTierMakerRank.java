package yumefusaka.galgamesite.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("galgame_tier_maker_rank")
public class GalGameTierMakerRank {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String userUin;

    private String rankName;

    private Long rankLevel;
}

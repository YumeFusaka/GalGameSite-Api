package yumefusaka.galgamesite.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("galgame_twelve_vote_number")
public class GalGameTwelveVoteNumber {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String uin;

    private Integer number;

    private Integer edition;
}

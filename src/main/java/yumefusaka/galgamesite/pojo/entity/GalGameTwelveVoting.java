package yumefusaka.galgamesite.pojo.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("galGame_twelve_voting")
public class GalGameTwelveVoting {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long galgameSubjectId;

    private Long votesCastCount;

    private String userUin;

    private String userNick;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private String galgameTranslatedName;
}
package yumefusaka.galgamesite.pojo.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("galgame_vote")
public class GalGameVote {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long subjectId;

    private Integer voteNum;

    private String qq;

    private String qqName;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private String gameName;
}
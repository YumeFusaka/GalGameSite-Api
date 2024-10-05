package yumefusaka.galgamesite.pojo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("galgame_vote")
public class GalGameVote {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long subjectId;

    private Integer voteNum;

    private Integer qq;

    private String nick;

    private LocalDateTime updateTime;

    private String name;
}
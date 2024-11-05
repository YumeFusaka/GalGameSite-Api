package yumefusaka.galgamesite.pojo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("galgame")
public class GalGame {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String translatedName;

    private String info;

    private Double score;

    @TableField("`rank`")
    private Long rank;

    private String numberOfRatings;

    private Long subjectId;

    private String originalName;

    private String imgUrl;
}

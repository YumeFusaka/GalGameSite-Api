package yumefusaka.galgamesite.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("galgame_tier_maker_subject")
public class GalGameTierMakerSubject {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String userUin;

    private Long galgameSubjectId;

    private Long rankLevel;

    private Long rankOrder;

    private String galgameImgUrl;
}

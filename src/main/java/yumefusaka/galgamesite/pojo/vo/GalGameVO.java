package yumefusaka.galgamesite.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

@Data
public class GalGameVO {

    Long id;

    String translatedName;

    String info;

    Double score;

    Long rank;

    String numberOfRatings;

    Long subjectId;

    String originalName;

    String imgUrl;
}

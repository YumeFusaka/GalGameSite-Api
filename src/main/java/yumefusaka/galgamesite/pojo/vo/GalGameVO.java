package yumefusaka.galgamesite.pojo.vo;

import lombok.Builder;
import lombok.Data;

@Data
public class GalGameVO {

    String name;

    String info;

    String score;

    String rank;

    String votes;

    String nick;

    Long subjectId;

    String url;
}

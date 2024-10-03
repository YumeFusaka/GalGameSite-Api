package yumefusaka.galgamesite.pojo.vo;

import lombok.Builder;
import lombok.Data;

@Data
public class GalGameVO {

    Long id;

    String name;

    String info;

    String score;

    String rank;

    String votes;

    String nick;

    Long subjectId;
}

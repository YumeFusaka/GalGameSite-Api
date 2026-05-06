package galgamesite.pojo.response.game;

import lombok.Data;

@Data
public class GameResponse {
    private Long id;
    private String translatedName;
    private String info;
    private Double score;
    private Long rank;
    private String numberOfRatings;
    private Long subjectId;
    private String originalName;
    private String imgUrl;
}

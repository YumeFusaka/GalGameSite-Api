package galgamesite.pojo.request.game;

import lombok.Data;
import lombok.EqualsAndHashCode;
import galgamesite.common.page.PageRequest;

@EqualsAndHashCode(callSuper = true)
@Data
public class GameQueryRequest extends PageRequest {
    private String keyword;
    private String translatedName;
}

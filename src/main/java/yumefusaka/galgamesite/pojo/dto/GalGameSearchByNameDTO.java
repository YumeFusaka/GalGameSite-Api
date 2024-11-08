package yumefusaka.galgamesite.pojo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import yumefusaka.galgamesite.common.page.Page;

@EqualsAndHashCode(callSuper = true)
@Data
public class GalGameSearchByNameDTO extends Page {
    String name;
}

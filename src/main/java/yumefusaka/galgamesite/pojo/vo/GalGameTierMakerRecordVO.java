package yumefusaka.galgamesite.pojo.vo;

import lombok.Data;


import java.util.List;

@Data
public class GalGameTierMakerRecordVO {

    public List<String> rankNameList;

    public List<List<GalGameTierMakerSubjectVO>> rankSubjectList;
}

package yumefusaka.galgamesite.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class GalGameTierMakerRecordDTO {

    public List<String> rankNameList;

    public List<List<GalGameTierMakerSubjectDTO>> rankSubjectList;
}

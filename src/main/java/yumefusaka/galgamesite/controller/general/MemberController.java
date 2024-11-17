package yumefusaka.galgamesite.controller.general;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yumefusaka.galgamesite.common.result.Result;
import yumefusaka.galgamesite.pojo.entity.Member;
import yumefusaka.galgamesite.pojo.vo.MemberVO;
import yumefusaka.galgamesite.service.IMemberService;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/member")
@Tag(name = "Member")
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @Operation(summary = "获取群成员列表")
    @GetMapping("/list")
    public Result<List<MemberVO>> getMemberList(){
        List<Member> memberList = memberService.list();
        List<MemberVO> memberVOList = new ArrayList<>();
        for(Member member:memberList){
            MemberVO memberInfoVO = new MemberVO();
            BeanUtils.copyProperties(member,memberInfoVO);
            memberVOList.add(memberInfoVO);
        }
        return Result.success(memberVOList);
    }
}

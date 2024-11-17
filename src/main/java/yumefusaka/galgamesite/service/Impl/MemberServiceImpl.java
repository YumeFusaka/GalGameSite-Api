package yumefusaka.galgamesite.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import yumefusaka.galgamesite.mapper.MemberMapper;
import yumefusaka.galgamesite.pojo.entity.Member;
import yumefusaka.galgamesite.service.IMemberService;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {
}

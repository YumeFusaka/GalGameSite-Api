package yumefusaka.galgamesite.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yumefusaka.galgamesite.mapper.GalGameMapper;

import yumefusaka.galgamesite.pojo.entity.GalGame;

import yumefusaka.galgamesite.service.IGalGameService;

@Service
public class GalGameServiceImpl extends ServiceImpl<GalGameMapper, GalGame> implements IGalGameService {

    @Autowired
    private GalGameMapper galGameMapper;

}

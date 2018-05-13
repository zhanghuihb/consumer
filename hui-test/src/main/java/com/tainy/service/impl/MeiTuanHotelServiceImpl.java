package com.tainy.service.impl;

import com.tainy.entity.MeiTuanHotel;
import com.tainy.mapper.MeiTuanHotelMapper;
import com.tainy.service.MeiTuanHotelService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Tainy
 * @date 2017/12/11
 */
public class MeiTuanHotelServiceImpl implements MeiTuanHotelService{

    @Autowired
    private MeiTuanHotelMapper meiTuanHotelMapper;

    @Override
    public int addMeituanHotel(MeiTuanHotel hotel) {
        return meiTuanHotelMapper.insertSelective(hotel);
    }
}

package com.tainy.service.impl;

import com.tainy.entity.NoticeInfo;
import com.tainy.mapper.NoticeInfoMapper;
import com.tainy.service.NoticeInfoService;
import com.tainy.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * NoticeInfo服务接口实现类
 * @Service("noticeInfoService")用于将当前类注释为一个Spring的bean，名为Service
 *
 * @author Tainy
 * @date 2017/11/27
 */
@Service
public class NoticeInfoServiceImpl implements NoticeInfoService {

    @Autowired
    private NoticeInfoMapper noticeInfoMapper;

    @Override
    public List<NoticeInfo> listAllNoticeInfo(Page<?> page, NoticeInfo noticeInfo) {
        return noticeInfoMapper.selectByPageListAllNoticeInfo(page, noticeInfo);
    }

    @Override
    public int addNoticeInfo(NoticeInfo noticeInfo) {
        return noticeInfoMapper.insertSelective(noticeInfo);
    }

    @Override
    public int editNoticeInfo(NoticeInfo noticeInfo) {
        return noticeInfoMapper.updateByPrimaryKeySelective(noticeInfo);
    }

    @Override
    public int deleteNoticeInfo(NoticeInfo noticeInfo) {
        return noticeInfoMapper.deleteByPrimaryKey(noticeInfo.getId());
    }
}

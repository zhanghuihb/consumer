package com.tainy.service;

import com.tainy.entity.NoticeInfo;
import com.tainy.util.page.Page;

import java.util.List;

/**
 * 公告服务层接口
 *
 * @author Tainy
 * @date 2017/11/27
 */
public interface NoticeInfoService {

    /**
     * 获得所有公告
     *
     * @return
     */
    List<NoticeInfo> listAllNoticeInfo(Page<?> page, NoticeInfo noticeInfo);

    /**
     * 新增公告
     * @param noticeInfo
     * @return
     */
    int addNoticeInfo(NoticeInfo noticeInfo);

    /**
     * 新增公告
     *
     * @param noticeInfo
     * @return
     */
    int editNoticeInfo(NoticeInfo noticeInfo);

    /**
     * 删除公告
     *
     * @param noticeInfo
     * @return
     */
    int deleteNoticeInfo(NoticeInfo noticeInfo);
}

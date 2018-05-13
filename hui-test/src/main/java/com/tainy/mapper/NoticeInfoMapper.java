package com.tainy.mapper;

import com.tainy.common.BaseMapper;
import com.tainy.entity.NoticeInfo;
import com.tainy.util.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeInfoMapper extends BaseMapper<NoticeInfo> {


    /**
     * 获得所有公告（分页查询）
     *
     * @return
     */
    List<NoticeInfo> selectByPageListAllNoticeInfo(@Param("page") Page<?> page, @Param("noticeInfo") NoticeInfo noticeInfo);
}
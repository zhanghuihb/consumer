package com.tainy.common.page;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/8/14 14:03
 */
@Data
public class PageParam implements Serializable{

    private int currentPage;

    private int pageSize;

}

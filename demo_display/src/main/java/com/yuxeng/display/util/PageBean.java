package com.yuxeng.display.util;

import java.util.List;

import lombok.Data;

/**
 * 实现分页功能的类
 */
@Data
public class PageBean<T> {

  /**
   * 第几页
   */
  private Integer pageOn;
  /**
   * 每页的条数
   */
  private Integer pageSize;
  /**
   * 数据
   */
  private List<T> datas;
  /**
   * 总共几页
   */
  private Integer totalPage;
  /**
   * 总的条数
   */
  private Integer totalSize;

  // 确保创建对象时页号和页面大小是有效的
  public PageBean(Integer pageOn, Integer pageSize) {
    if (pageOn <= 0) {
      this.pageOn = 1;
    } else {
      this.pageOn = pageOn;
    }

    if (pageSize <= 0) {
      this.pageSize = 10;
    } else {
      this.pageSize = pageSize;
    }
  }
  /**
   * 开始索引
   */
  public Integer getStartIndex(){return(this.pageOn - 1)*this.pageSize;}
}


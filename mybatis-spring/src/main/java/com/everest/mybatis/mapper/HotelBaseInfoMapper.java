package com.everest.mybatis.mapper;

import com.everest.mybatis.domain.HotelBaseInfoDO;

/**
 * @Date: 1/12/20
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
public interface HotelBaseInfoMapper {

  HotelBaseInfoDO findByEcomId(Long ecomId);
}

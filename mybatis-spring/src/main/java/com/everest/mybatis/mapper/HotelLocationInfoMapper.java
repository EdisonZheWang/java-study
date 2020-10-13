package com.everest.mybatis.mapper;

import com.everest.mybatis.domain.HotelLocationInfoDO;

/**
 * @Date: 1/12/20
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
public interface HotelLocationInfoMapper {

//  @Select("select * from hotel_location_info where ecom_id = #{ecomId}")
  HotelLocationInfoDO findByEcomId(Long ecomId);
}

package com.everest.mybatis;

import com.everest.mybatis.config.MybatisConfig;
import com.everest.mybatis.mapper.HotelBaseInfoMapper;
import com.everest.mybatis.mapper.HotelLocationInfoMapper;
import com.everest.mybatis.domain.HotelBaseInfoDO;
import com.everest.mybatis.domain.HotelLocationInfoDO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Date: 1/12/20
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
public class MybatisMainStarter {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(MybatisConfig.class);
    HotelBaseInfoMapper mapper = (HotelBaseInfoMapper) ctx.getBean("hotelBaseInfoMapper");
    HotelBaseInfoDO byEcomId = mapper.findByEcomId(1L);
    HotelLocationInfoMapper locationInfoMapper = (HotelLocationInfoMapper) ctx.getBean("hotelLocationInfoMapper");
    HotelLocationInfoDO locationInfoDO = locationInfoMapper.findByEcomId(1L);
    System.out.println(locationInfoDO);
    System.out.println(byEcomId);
  }
}

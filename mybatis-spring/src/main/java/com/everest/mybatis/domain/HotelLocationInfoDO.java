package com.everest.mybatis.domain;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @Date: 1/12/20
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
@Data
public class HotelLocationInfoDO {

  private Long id;
  private Long ecomId;
  private Long hcomId;
  private BigDecimal latitude;
  private BigDecimal longitude;
  private Integer regionId;
  private Integer zipCode;
  private Date createTime;
  private Date updateTime;
}

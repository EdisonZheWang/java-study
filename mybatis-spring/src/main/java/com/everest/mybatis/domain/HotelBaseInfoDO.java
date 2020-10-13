package com.everest.mybatis.domain;

import java.util.Date;
import lombok.Data;

/**
 * @Date: 1/12/20
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
@Data
public class HotelBaseInfoDO {

  private Long id;
  private Long ecomId;
  private Long hcomId;
  private String name;
  private Integer propertyTypeId;
  private String propertyTypeName;
  private String descriptionLocationTeaser;
  private String descriptionHotelTeaser;
  private String descriptionRoomTeaser;
  private Boolean smokingOption;
  private String thumbnailUrl;
  private String address1;
  private String address2;
  private String address3;
  private String city;
  private String province;
  private String country;
  private Boolean active;
  private Date createTime;
  private Date updateTime;
}

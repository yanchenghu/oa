package com.ruoyi.customer.mapper;

import com.ruoyi.customer.domain.Yxdemand;
import com.ruoyi.customer.domain.Yxrob;

import java.util.List;


public interface YxrobMapper {


     Yxrob selectById(Integer entryId);

     Yxrob selectYxrobById(Integer robId);

     List<Yxrob> selectYxrobList(Yxrob yxrob);

     int insertYxrob(Yxrob yxrob);

     int updateYxrob(Yxrob yxrob);

     int deleteYxrobByIds(Integer[] robIds);

     int deleteYxrobById(Integer robId);

     void insertPerRobPeople(Yxrob yx);
}

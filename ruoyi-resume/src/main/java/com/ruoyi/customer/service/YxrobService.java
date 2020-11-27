package com.ruoyi.customer.service;

import com.ruoyi.customer.domain.Yxrob;

import java.util.List;

public interface YxrobService {
    List<Yxrob> selectYxrobList(Yxrob yxrob);

    String selectYxrobById(Integer robId);

    int insertYxrob(Yxrob yxrob);

    int updateYxrob(Yxrob yxrob);

    int deleteYxrobByIds(Integer[] robIds);
}

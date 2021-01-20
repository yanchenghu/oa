package com.ruoyi.taskvolume.mapper;

import com.ruoyi.taskvolume.domain.ConTaskvolume;

import java.util.List;

public interface TaskVolumeMapper {

    List<ConTaskvolume> selectNumber(ConTaskvolume taskvolume);

    Integer update(ConTaskvolume taskvolume);
}

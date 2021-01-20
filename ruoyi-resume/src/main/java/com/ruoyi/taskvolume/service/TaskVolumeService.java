package com.ruoyi.taskvolume.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.taskvolume.domain.ConTaskvolume;

import java.util.List;

public interface TaskVolumeService {


    AjaxResult update(ConTaskvolume taskvolume);

    List<ConTaskvolume> selectNumber(ConTaskvolume taskvolume);
}

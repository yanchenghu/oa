package com.ruoyi.taskvolume.service.lmpl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.taskvolume.domain.ConTaskvolume;
import com.ruoyi.taskvolume.mapper.TaskVolumeMapper;
import com.ruoyi.taskvolume.service.TaskVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskVolumeServicelmpl implements TaskVolumeService {

    @Autowired
    private TaskVolumeMapper taskVolumeMapper;


    @Override
    public AjaxResult update(ConTaskvolume taskvolume) {
         taskVolumeMapper.update(taskvolume);
        return AjaxResult.success("修改成功");
    }

    @Override
    public List<ConTaskvolume> selectNumber(ConTaskvolume taskvolume) {
        return taskVolumeMapper.selectNumber(taskvolume);
    }
}

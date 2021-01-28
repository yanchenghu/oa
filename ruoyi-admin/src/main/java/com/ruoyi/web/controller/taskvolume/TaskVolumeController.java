package com.ruoyi.web.controller.taskvolume;


import com.ruoyi.common.core.domain.AjaxResult;

import com.ruoyi.taskvolume.domain.ConTaskvolume;
import com.ruoyi.taskvolume.service.TaskVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
*@Title:  人事任务量定制（配合任务管理器定制）
*@Author:ych
*@Date 2021-01-27
*@Version 1.0
*/
@RestController
@RequestMapping("/taskvolume")
public class TaskVolumeController {
    @Autowired
    private TaskVolumeService taskVolumeService;

    /**
     * 查询每日获取的数据量
     */
    @GetMapping("/list")
    public List<ConTaskvolume> list(ConTaskvolume taskvolume)
    {
        List<ConTaskvolume> list = taskVolumeService.selectNumber(taskvolume);
        return list;
    }

    /**
     * 修改数据
     */
    @PostMapping
    public AjaxResult edit(@RequestBody ConTaskvolume taskvolume)
    {
        return taskVolumeService.update(taskvolume);
    }


}

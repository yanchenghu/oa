package com.ruoyi.entrycontract.service;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.multipart.MultipartFile;

public interface MarEntrycontractService {

    AjaxResult addLaborcontract(String marcusId, MultipartFile[] list);
}

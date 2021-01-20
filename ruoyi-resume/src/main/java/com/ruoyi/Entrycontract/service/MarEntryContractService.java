package com.ruoyi.Entrycontract.service;

import com.ruoyi.Entrycontract.domain.MarEntryContract;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MarEntryContractService {

    AjaxResult addLaborcontract(String marcusId, MultipartFile[] list);
}

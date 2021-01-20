package com.ruoyi.Entrycontract.service.lmpl;

import com.ruoyi.Entrycontract.domain.MarEntryContract;
import com.ruoyi.Entrycontract.mapper.MarEntryContractMapper;
import com.ruoyi.Entrycontract.service.MarEntryContractService;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.util.Date;
import java.util.List;

@Service
public class MarEntryContractServicelmpl implements MarEntryContractService {

    @Autowired
    private MarEntryContractMapper marEntryContractMapper;


    @Override
    @Transactional
    public AjaxResult addLaborcontract(String marcusId, MultipartFile[] list) {


        for (MultipartFile s: list)
        {
            String avatar="";
            try {
                avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), s);
            } catch (IOException e) {
                e.printStackTrace();
                return AjaxResult.error("文件上传失败");
            }
            MarEntryContract marEntryContract = new MarEntryContract();
            marEntryContract.setMarcusId(marcusId);
            marEntryContract.setImgPath(avatar);
            marEntryContract.setAddTime(new Date());
            marEntryContractMapper.addLaborcontract(marEntryContract);
        }


        return AjaxResult.success("上传成功");
    }
}

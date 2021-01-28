package com.ruoyi.entrycontract.service.lmpl;

import com.ruoyi.entrycontract.domain.MarEntrycontract;
import com.ruoyi.entrycontract.mapper.MarEntrycontractMapper;
import com.ruoyi.entrycontract.service.MarEntrycontractService;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MarEntrycontractServicelmpl implements MarEntrycontractService {

    @Autowired
    private MarEntrycontractMapper marEntryContractMapper;


    @Override
    @Transactional
    public AjaxResult addLaborcontract(String marcusId,MultipartFile[] list) {
        List<MarEntrycontract> listMarEn=new ArrayList<>();
        for (MultipartFile s: list) {
            String avatar="";
            try {
                avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), s);
            } catch (IOException e) {
                e.printStackTrace();
                return AjaxResult.error("文件上传失败");
            }
            MarEntrycontract marEntryContract = new MarEntrycontract();
            marEntryContract.setMarcusId(marcusId);
            marEntryContract.setImgPath(avatar);
            marEntryContract.setAddTime(new Date());
            listMarEn.add(marEntryContract);
        }
        marEntryContractMapper.addLaborcontract(listMarEn);
        return AjaxResult.success("上传成功");
    }

    @Override
    public int deleteMarEntrycontractById(Integer id) {
        return marEntryContractMapper.deleteMarEntrycontractById(id);
    }
}

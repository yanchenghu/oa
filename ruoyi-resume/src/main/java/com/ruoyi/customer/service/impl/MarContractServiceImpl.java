package com.ruoyi.customer.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.customer.domain.MarContractfollow;
import com.ruoyi.customer.mapper.MarContractfollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.MarContractMapper;
import com.ruoyi.customer.domain.MarContract;
import com.ruoyi.customer.service.IMarContractService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 合同Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-11-27
 */
@Service
public class MarContractServiceImpl implements IMarContractService 
{
    @Autowired
    private MarContractMapper marContractMapper;

    @Autowired
    private MarContractfollowMapper marContractfollowMapper;

    /**
     * 查询合同
     * 
     * @param id 合同ID
     * @return 合同
     */
    @Override
    public MarContract selectMarContractById(Integer id)
    {
        return marContractMapper.selectMarContractById(id);
    }

    /**
     * 查询合同列表
     * 
     * @param marContract 合同
     * @return 合同
     */
    @Override
    public List<MarContract> selectMarContractList(MarContract marContract)
    {

        return marContractMapper.selectMarContractList(marContract);
    }

    /**
     * 新增合同
     * 
     * @param  file firstParty
     * @return 结果
     */
    @Override
    public AjaxResult insertMarContract(MultipartFile file, String firstParty,
                                        String party, Date startTime, Date endTime,String clientSigner, String companySigner,  LoginUser loginUser) throws IOException {
        String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
        MarContract marContract = new MarContract();
        marContract.setFirstParty(firstParty);
        marContract.setParty(party);
        marContract.setStartTime(startTime);
        marContract.setEndTime(endTime);
        marContract.setClientSigner(clientSigner);
        marContract.setCompanySigner(companySigner);
        marContract.setContractPreview(avatar);
//        marContract.setContractName(contractName);
//        marContract.setContractTime(contractTime);


        marContract.setContractName(loginUser.getUser().getNickName());
        marContract.setContractTime(new Date());
        marContractMapper.insertMarContract(marContract);
        MarContractfollow marContractfollow = new MarContractfollow();
        marContractfollow.setNickName(loginUser.getUsername());
        marContractfollow.setContactTime(new Date());
        marContractfollow.setContactDetail("添加合同");
        marContractfollow.setStatus(2);
        marContractfollowMapper.insertMarContractfollow(marContractfollow);
        return AjaxResult.success("添加成功");
    }

    /**
     * 修改合同
     * 
     * @param marContract 合同
     * @return 结果
     */
    @Override
    public int updateMarContract(MarContract marContract)
    {
        return marContractMapper.updateMarContract(marContract);
    }

    /**
     * 批量删除合同
     * 
     * @param ids 需要删除的合同ID
     * @return 结果
     */
    @Override
    public int deleteMarContractByIds(Integer[] ids)
    {
        return marContractMapper.deleteMarContractByIds(ids);
    }

    /**
     * 删除合同信息
     * 
     * @param id 合同ID
     * @return 结果
     */
    @Override
    public int deleteMarContractById(Integer id)
    {
        return marContractMapper.deleteMarContractById(id);
    }
}

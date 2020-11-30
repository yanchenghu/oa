package com.ruoyi.customer.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.customer.domain.MarContract;
import org.springframework.web.multipart.MultipartFile;

/**
 * 合同Service接口
 * 
 * @author ruoyi
 * @date 2020-11-27
 */
public interface IMarContractService 
{
    /**
     * 查询合同
     * 
     * @param id 合同ID
     * @return 合同
     */
    public MarContract selectMarContractById(Integer id);

    /**
     * 查询合同列表
     * 
     * @param marContract 合同
     * @return 合同集合
     */
    public List<MarContract> selectMarContractList(MarContract marContract);

    /**
     * 新增合同
     * 
     * @param marContract 合同
     * @return 结果
     */
    AjaxResult insertMarContract(MultipartFile file, String firstParty, String party, Date startTime, Date endTime, String clientSigner, String companySigner, LoginUser loginUser) throws IOException;

    /**
     * 修改合同
     * 
     * @param marContract 合同
     * @return 结果
     */
    public int updateMarContract(MarContract marContract);

    /**
     * 批量删除合同
     * 
     * @param ids 需要删除的合同ID
     * @return 结果
     */
    public int deleteMarContractByIds(Integer[] ids);

    /**
     * 删除合同信息
     * 
     * @param id 合同ID
     * @return 结果
     */
    public int deleteMarContractById(Integer id);


}

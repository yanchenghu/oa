package com.ruoyi.demand.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demand.mapper.MarCertificatesMapper;
import com.ruoyi.demand.domain.MarCertificates;
import com.ruoyi.demand.service.IMarCertificatesService;

/**
 * 证件信息Service业务层处理
 * 
 * @author ych
 * @date 2020-12-10
 */
@Service
public class MarCertificatesServiceImpl implements IMarCertificatesService 
{
    @Autowired
    private MarCertificatesMapper marCertificatesMapper;

    /**
     * 查询证件信息
     * 
     * @param id 证件信息ID
     * @return 证件信息
     */
    @Override
    public MarCertificates selectMarCertificatesById(Integer id)
    {
        return marCertificatesMapper.selectMarCertificatesById(id);
    }

    /**
     * 查询证件信息列表
     * 
     * @param marCertificates 证件信息
     * @return 证件信息
     */
    @Override
    public List<MarCertificates> selectMarCertificatesList(MarCertificates marCertificates)
    {
        return marCertificatesMapper.selectMarCertificatesList(marCertificates);
    }

    /**
     * 新增证件信息
     * 
     * @param marCertificates 证件信息
     * @return 结果
     */
    @Override
    public int insertMarCertificates(MarCertificates marCertificates)
    {
        return marCertificatesMapper.insertMarCertificates(marCertificates);
    }

    /**
     * 修改证件信息
     * 
     * @param marCertificates 证件信息
     * @return 结果
     */
    @Override
    public int updateMarCertificates(MarCertificates marCertificates)
    {
        return marCertificatesMapper.updateMarCertificates(marCertificates);
    }

    /**
     * 批量删除证件信息
     * 
     * @param ids 需要删除的证件信息ID
     * @return 结果
     */
    @Override
    public int deleteMarCertificatesByIds(Integer[] ids)
    {
        return marCertificatesMapper.deleteMarCertificatesByIds(ids);
    }

    /**
     * 删除证件信息信息
     * 
     * @param id 证件信息ID
     * @return 结果
     */
    @Override
    public int deleteMarCertificatesById(Integer id)
    {
        return marCertificatesMapper.deleteMarCertificatesById(id);
    }
}

package com.ruoyi.demand.mapper;

import java.util.List;
import com.ruoyi.demand.domain.MarCertificates;

/**
 * 证件信息Mapper接口
 * 
 * @author ych
 * @date 2020-12-10
 */
public interface MarCertificatesMapper 
{
    /**
     * 查询证件信息
     * 
     * @param id 证件信息ID
     * @return 证件信息
     */
    public MarCertificates selectMarCertificatesById(Integer id);

    /**
     * 查询证件信息列表
     * 
     * @param marCertificates 证件信息
     * @return 证件信息集合
     */
    public List<MarCertificates> selectMarCertificatesList(MarCertificates marCertificates);

    /**
     * 新增证件信息
     * 
     * @param marCertificates 证件信息
     * @return 结果
     */
    public int insertMarCertificates(MarCertificates marCertificates);

    /**
     * 修改证件信息
     * 
     * @param marCertificates 证件信息
     * @return 结果
     */
    public int updateMarCertificates(MarCertificates marCertificates);

    /**
     * 删除证件信息
     * 
     * @param id 证件信息ID
     * @return 结果
     */
    public int deleteMarCertificatesById(Integer id);

    /**
     * 批量删除证件信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarCertificatesByIds(Integer[] ids);
}

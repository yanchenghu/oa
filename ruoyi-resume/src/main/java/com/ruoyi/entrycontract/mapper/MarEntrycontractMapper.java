package com.ruoyi.entrycontract.mapper;

import com.ruoyi.entrycontract.domain.MarEntrycontract;

import java.util.List;

public interface MarEntrycontractMapper {

    /**
     * 查询入项合同
     *
     * @param id 入项合同ID
     * @return 入项合同
     */
    public MarEntrycontract selectMarEntrycontractById(Integer id);

    /**
     * 查询入项合同列表
     *
     * @param marEntrycontract 入项合同
     * @return 入项合同集合
     */
    public List<MarEntrycontract> selectMarEntrycontractList(MarEntrycontract marEntrycontract);

    /**
     * 新增入项合同
     *
     * @param marEntrycontract 入项合同
     * @return 结果
     */
    public int insertMarEntrycontract(MarEntrycontract marEntrycontract);

    /**
     * 修改入项合同
     *
     * @param marEntrycontract 入项合同
     * @return 结果
     */
    public int updateMarEntrycontract(MarEntrycontract marEntrycontract);

    /**
     * 删除入项合同
     *
     * @param id 入项合同ID
     * @return 结果
     */
    public int deleteMarEntrycontractById(Integer id);

    /**
     * 批量删除入项合同
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMarEntrycontractByIds(Integer[] ids);


    int addLaborcontract(List<MarEntrycontract> listMarEn);




}

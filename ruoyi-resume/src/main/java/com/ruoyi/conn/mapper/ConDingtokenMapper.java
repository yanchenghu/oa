package com.ruoyi.conn.mapper;

import java.util.List;
import com.ruoyi.conn.domain.ConDingtoken;

/**
 * 钉钉tokenMapper接口
 * 
 * @author ych
 * @date 2021-01-08
 */
public interface ConDingtokenMapper 
{
    /**
     * 查询钉钉token
     * 
     * @param id 钉钉tokenID
     * @return 钉钉token
     */
    public ConDingtoken selectConDingtokenById(Integer id);

    /**
     * 查询钉钉token列表
     * 
     * @param conDingtoken 钉钉token
     * @return 钉钉token集合
     */
    public List<ConDingtoken> selectConDingtokenList(ConDingtoken conDingtoken);

    /**
     * 新增钉钉token
     * 
     * @param conDingtoken 钉钉token
     * @return 结果
     */
    public int insertConDingtoken(ConDingtoken conDingtoken);

    /**
     * 修改钉钉token
     * 
     * @param conDingtoken 钉钉token
     * @return 结果
     */
    public int updateConDingtoken(ConDingtoken conDingtoken);

    /**
     * 删除钉钉token
     * 
     * @param id 钉钉tokenID
     * @return 结果
     */
    public int deleteConDingtokenById(Integer id);

    /**
     * 批量删除钉钉token
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConDingtokenByIds(Integer[] ids);
    /**
     * 批量删除钉钉token
     *
     * @param ding_type 需要查询的个人
     * @return 结果
     */
    ConDingtoken selectConDingtokenByType(Integer ding_type);

    int updateConDingByType(ConDingtoken conDingtoken);
}

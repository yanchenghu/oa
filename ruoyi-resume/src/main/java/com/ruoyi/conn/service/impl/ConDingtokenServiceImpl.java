package com.ruoyi.conn.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.conn.mapper.ConDingtokenMapper;
import com.ruoyi.conn.domain.ConDingtoken;
import com.ruoyi.conn.service.IConDingtokenService;

/**
 * 钉钉tokenService业务层处理
 * 
 * @author ych
 * @date 2021-01-08
 */
@Service
public class ConDingtokenServiceImpl implements IConDingtokenService 
{
    @Autowired
    private ConDingtokenMapper conDingtokenMapper;

    /**
     * 查询钉钉token
     * 
     * @param id 钉钉tokenID
     * @return 钉钉token
     */
    @Override
    public ConDingtoken selectConDingtokenById(Integer id)
    {
        return conDingtokenMapper.selectConDingtokenById(id);
    }

    /**
     * 查询钉钉token列表
     * 
     * @param conDingtoken 钉钉token
     * @return 钉钉token
     */
    @Override
    public List<ConDingtoken> selectConDingtokenList(ConDingtoken conDingtoken)
    {
        return conDingtokenMapper.selectConDingtokenList(conDingtoken);
    }

    /**
     * 新增钉钉token
     * 
     * @param conDingtoken 钉钉token
     * @return 结果
     */
    @Override
    public int insertConDingtoken(ConDingtoken conDingtoken)
    {
        return conDingtokenMapper.insertConDingtoken(conDingtoken);
    }

    /**
     * 修改钉钉token
     * 
     * @param conDingtoken 钉钉token
     * @return 结果
     */
    @Override
    public int updateConDingtoken(ConDingtoken conDingtoken)
    {
        return conDingtokenMapper.updateConDingtoken(conDingtoken);
    }

    /**
     * 批量删除钉钉token
     * 
     * @param ids 需要删除的钉钉tokenID
     * @return 结果
     */
    @Override
    public int deleteConDingtokenByIds(Integer[] ids)
    {
        return conDingtokenMapper.deleteConDingtokenByIds(ids);
    }

    /**
     * 删除钉钉token信息
     * 
     * @param id 钉钉tokenID
     * @return 结果
     */
    @Override
    public int deleteConDingtokenById(Integer id)
    {
        return conDingtokenMapper.deleteConDingtokenById(id);
    }
}

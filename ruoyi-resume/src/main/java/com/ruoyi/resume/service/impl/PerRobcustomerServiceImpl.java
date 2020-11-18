package com.ruoyi.resume.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.resume.mapper.PerRobcustomerMapper;
import com.ruoyi.resume.domain.PerRobcustomer;
import com.ruoyi.resume.service.IPerRobcustomerService;

/**
 * 抢占简历信息Service业务层处理
 * 
 * @author yan
 * @date 2020-10-30
 */
@Service
public class PerRobcustomerServiceImpl implements IPerRobcustomerService 
{
    @Autowired
    private PerRobcustomerMapper perRobcustomerMapper;

    /**
     * 查询抢占简历信息
     * 
     * @param seizeId 抢占简历信息ID
     * @return 抢占简历信息
     */
    @Override
    public PerRobcustomer selectPerRobcustomerById(String seizeId)
    {
        return perRobcustomerMapper.selectPerRobcustomerById(seizeId);
    }

    /**
     * 查询抢占简历信息列表
     * 
     * @param perRobcustomer 抢占简历信息
     * @return 抢占简历信息
     */
    @Override
    public List<PerRobcustomer> selectPerRobcustomerList(PerRobcustomer perRobcustomer)
    {
        return perRobcustomerMapper.selectPerRobcustomerList(perRobcustomer);
    }

    /**
     * 新增抢占简历信息
     * 
     * @param perRobcustomer 抢占简历信息
     * @return 结果
     */
    @Override
    public int insertPerRobcustomer(PerRobcustomer perRobcustomer)
    {
        return perRobcustomerMapper.insertPerRobcustomer(perRobcustomer);
    }

    /**
     * 修改抢占简历信息
     * 
     * @param perRobcustomer 抢占简历信息
     * @return 结果
     */
    @Override
    public int updatePerRobcustomer(PerRobcustomer perRobcustomer)
    {
        return perRobcustomerMapper.updatePerRobcustomer(perRobcustomer);
    }

    /**
     * 批量删除抢占简历信息
     * 
     * @param seizeIds 需要删除的抢占简历信息ID
     * @return 结果
     */
    @Override
    public int deletePerRobcustomerByIds(String[] seizeIds)
    {
        return perRobcustomerMapper.deletePerRobcustomerByIds(seizeIds);
    }

    /**
     * 删除抢占简历信息信息
     * 
     * @param seizeId 抢占简历信息ID
     * @return 结果
     */
    @Override
    public int deletePerRobcustomerById(String seizeId)
    {
        return perRobcustomerMapper.deletePerRobcustomerById(seizeId);
    }
    /**
     * 查询全部抢占记录，监测是否过期，过期则释放
     *
     * @param
     * @return
     */

    @Override
    public void initRobStatus() {
    //查询所有的过期抢占信息
    List<String>  list= perRobcustomerMapper.selectAllOverrob();
    if(list!=null && list.size()>0){
        perRobcustomerMapper.updatePerRobbylist(list);

    }

    }


}

package com.ruoyi.demand.service.impl;

import java.io.IOException;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.resume.SerialNumber;
import com.ruoyi.demand.domain.MarDemandresume;
import com.ruoyi.demand.domain.MarSign;
import com.ruoyi.demand.mapper.MarDemandresumeMapper;
import com.ruoyi.demand.mapper.MarDemandresumefollowMapper;
import com.ruoyi.demand.mapper.MarSignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demand.mapper.MarDemandMapper;
import com.ruoyi.demand.domain.MarDemand;
import com.ruoyi.demand.service.IMarDemandService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 需求Service业务层处理
 * 
 * @author ych
 * @date 2020-11-27
 */
@Service
public class MarDemandServiceImpl implements IMarDemandService 
{
    @Autowired
    private MarDemandMapper marDemandMapper;
    @Autowired
    private MarDemandresumeMapper marDemandresumeMapper;
    @Autowired
    private MarDemandresumefollowMapper marDemandresumefollowMapper;
    @Autowired
    private MarSignMapper marSignMapper;

    /**
     * 查询需求
     * 
     * @param
     * @return 需求
     */
    @Override
    public Map selectMarDemandById(String demandId)
    {
        Map map=new HashMap();
        MarSign marSign=new MarSign();
        marSign.setDemandId(demandId);
        List<MarSign> Signlis=marSignMapper.selectMarSignList(marSign);
        MarDemand marDeman=marDemandMapper.selectMarDemandById(demandId);
        map.put("Signlis",Signlis);
        map.put("marDeman",marDeman);
        return map;
    }

    /**
     * 查询需求列表
     * 
     * @param marDemand 需求
     * @return 需求
     */
    @Override
    public List<MarDemand> selectMarDemandList(MarDemand marDemand, LoginUser loginUser)
    {
        marDemand.setOperationuser(loginUser.getUsername());
        List<MarDemand>  list=marDemandMapper.selectMarDemandList(marDemand);
        MarDemandresume marDema=new MarDemandresume();
        for (MarDemand marDe:list){
        //查询当前需求绑定了多少简历
        marDema.setDemandId(marDe.getDemandId());
        List<MarDemandresume>  li= marDemandresumeMapper.selectMarDemandresumeList(marDema);
        marDe.setIfLook(li.size());
        //查询当前需求面试通过多少
        marDema.setDownloadStatus(5);
        List<MarDemandresume>  lifoll=marDemandresumefollowMapper.selectMarDemandList(marDema);
        marDe.setChsiFlag(lifoll.size());
        //查询当前需求入项
        marDema.setDownloadStatus(7);
        List<MarDemandresume>  lif=marDemandresumefollowMapper.selectMarDemandList(marDema);
        marDe.setCoopnature(lif.size());
        }
        return list;
    }

    /**
     * 新增需求
     * 
     * @param
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult insertMarDemand(String zm, MultipartFile demandPic, LoginUser loginUser)
    {
        String fsafsa="";
        try {
            fsafsa = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), demandPic);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Integer> liStr = JSON.parseArray(JSON.parseObject(zm).getString("list"), Integer.class);
        MarDemand marDemand = JSON.parseObject(JSON.parseObject(zm).getString("marDemand"), MarDemand.class);
        String demandId=SerialNumber.createSerial("demd-", 5);
        marDemand.setDemandPic(fsafsa);
        marDemand.setDemandId(demandId);
        marDemand.setOperationuser(loginUser.getUsername());
        marDemand.setOperUsername(loginUser.getUser().getNickName());
        marDemand.setAddTime(new Date());
        marDemandMapper.insertMarDemand(marDemand);
        List<MarSign> work=new ArrayList<MarSign>();
        for( Integer sk:liStr){
            MarSign marSi=new MarSign();
            marSi.setDemandId(demandId);
            marSi.setDeptId(sk);
            marSi.setAddTime(new Date());
            work.add(marSi);
        }
        if(work.size()>0){
            marSignMapper.insertMList(work);
        }else {
            return AjaxResult.error("没有选择下包商");
        }
        return AjaxResult.success("添加需求成功");
    }

    /**
     * 修改需求
     * 
     * @param zm 需求
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult updateMarDemand(String zm, MultipartFile demandPic, LoginUser loginUser)
    {
        List<Integer> liStr = JSON.parseArray(JSON.parseObject(zm).getString("list"), Integer.class);
        MarDemand marDemand = JSON.parseObject(JSON.parseObject(zm).getString("marDemand"), MarDemand.class);
        String demandId=marDemand.getDemandId();

        //如果图片不为空的话，则重新上传图片
        if(demandPic != null){
            String fsafsa="";
            try {
                fsafsa = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), demandPic);
            } catch (IOException e) {
                e.printStackTrace();
            }
            marDemand.setDemandPic(fsafsa);
        }
        int b=marSignMapper.deleteMarSignBy(demandId);
        if(b>0){
            List<MarSign> work=new ArrayList<MarSign>();
            for( Integer sk:liStr){
                MarSign marSi=new MarSign();
                marSi.setDemandId(demandId);
                marSi.setDeptId(sk);
                marSi.setAddTime(new Date());
                work.add(marSi);
            }
            if(work.size()>0){
                marSignMapper.insertMList(work);
            }else {
                return AjaxResult.error("没有选择下包商");
            }
        }
        int a=marDemandMapper.updateMarDemand(marDemand);
        if (a==1){
            return AjaxResult.success("修改成功");
        }else{
            return AjaxResult.error("修改成功");
        }


    }

    /**
     * 批量删除需求
     * 
     * @param ids 需要删除的需求ID
     * @return 结果
     */
    @Override
    public int deleteMarDemandByIds(String[] ids)
    {
        return marDemandMapper.deleteMarDemandByIds(ids);
    }

    /**
     * 删除需求信息
     * 
     * @param id 需求ID
     * @return 结果
     */
    @Override
    public int deleteMarDemandById(String id)
    {
        return marDemandMapper.deleteMarDemandById(id);
    }

    /**
     * 需求查重
     */
    @Override
    public int selDemandDuplicate(String projectName) {
       MarDemand sd=marDemandMapper.selectMarDemandByName(projectName);
        if(sd!=null){
            return 2;
        }
        return 1;
    }
    /**
     * 需求关闭
     */
    @Override
    @Transactional
    public AjaxResult demandClosure(String demandId, LoginUser loginUser) {
        MarDemand marDema= marDemandMapper.selectMarDemandById(demandId);
        if (marDema!=null){
            if(!marDema.getOperationuser().equals(loginUser.getUsername())){
                return AjaxResult.error("你不是该需求的持有人，不可以关闭或者开启需求");
            }
        }else{
            return AjaxResult.error("该需求不存在");
        }
        //需求下面是否存在未处理的
        List<String> liStr=marDemandMapper.seluntreatedResume();
        if(null != liStr && !liStr.isEmpty()){
            return AjaxResult.error("当前需求下的"+liStr.toString()+"还未完成操作，不可关闭");

        }
        MarDemand marDe=new MarDemand();
        marDe.setDemandId(demandId);
        marDe.setState(1);
        marDe.setCloseDate(new Date());
        int as=marDemandMapper.updateMarDemand(marDe);
        if(as!=1){
            return AjaxResult.error("关闭失败");

        }
        return AjaxResult.success("关闭成功");
    }
    /**
     * 需求绑定表的查询
     */
    @Override
    public List<MarDemand> selectMarDemandbindingList(MarDemand marDemand, LoginUser loginUser) {
        Map map=new HashMap();
        map.put("DeptId",loginUser.getUser().getDeptId());
        List<MarDemand> list= marDemandMapper.selectMarDemandbindingList(map);
        MarDemandresume marDema=new MarDemandresume();
        for (MarDemand marDe:list){
            //查询当前需求绑定了多少简历
            marDema.setDemandId(marDe.getDemandId());
            List<MarDemandresume>  li= marDemandresumeMapper.selectMarDemandresumeList(marDema);
            marDe.setIfLook(li.size());
            //查询当前需求面试通过多少
            marDema.setDownloadStatus(5);
            List<MarDemandresume>  lifoll=marDemandresumefollowMapper.selectMarDemandList(marDema);
            marDe.setChsiFlag(lifoll.size());
            //查询当前需求入项
            marDema.setDownloadStatus(7);
            List<MarDemandresume>  lif=marDemandresumefollowMapper.selectMarDemandList(marDema);
            marDe.setCoopnature(lif.size());
        }
        return list;
    }


}

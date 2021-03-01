package com.ruoyi.analysis.service.impl;
import com.ruoyi.analysis.domain.GeneralCollectionStatistics;
import com.ruoyi.analysis.domain.ReturnedMoney;
import com.ruoyi.analysis.mapper.ReturnedMoneyMapper;
import com.ruoyi.analysis.service.IReturnedMoneyService;
import com.ruoyi.common.utils.resume.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 绩效统计
 * 
 * @author ych
 * @date 2020-12-28
 */
@Service
public class ReturnedMoneyServiceImpl implements IReturnedMoneyService
{
    @Autowired
    private ReturnedMoneyMapper returnedMoneyMapper;

    @Override
    public List<ReturnedMoney> selectReturnedmoneyList(GeneralCollectionStatistics collectionStatistics) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("alltime", DateUtils.getNMonthlastDate(1));//上个月的最后一天
        /*---------------------获取历史入项过人的公司------------------*/
        List<ReturnedMoney> listRe=returnedMoneyMapper.selectReturnedmoneyList(map);

         for(int i=listRe.size()-1;i>=0;i--) {
           ReturnedMoney returne = listRe.get(i);
           String corpCode = returne.getCorpCode();
            ReturnedMoney returnedMoney1= returnedMoneyMapper.selectfinPaymentCollectionBycorpCode(corpCode);
           if(returnedMoney1!=null){
               returne.setAccumulatedCollection(returnedMoney1.getAccumulatedCollection());
               returne.setAccumulatedExpenditure(returnedMoney1.getAccumulatedExpenditure());
               returne.setAccumulatedArrears(returnedMoney1.getAccumulatedArrears());
               returne.setLatestcollectionMonth(returnedMoney1.getLatestcollectionMonth());
           }else{
               listRe.remove(i);
           }

        }
        return listRe;
    }

    @Override
    public List<GeneralCollectionStatistics> selectAccumulatedCollectionlist(GeneralCollectionStatistics collectionStatistics) {
        List<GeneralCollectionStatistics> list=returnedMoneyMapper.selectAccumulatedCollectionlist(collectionStatistics);
        return list;
    }

    @Override
    public List<GeneralCollectionStatistics> selectAccumulatedExpenditurelist(GeneralCollectionStatistics collectionStatistics) {
        List<GeneralCollectionStatistics> list=returnedMoneyMapper.selectAccumulatedExpenditurelist(collectionStatistics);

        return list;
    }

    @Override
    public List<GeneralCollectionStatistics> selectAccumulatedProfitlist(GeneralCollectionStatistics collectionStatistics) {
        List<GeneralCollectionStatistics> list=returnedMoneyMapper.selectAccumulatedProfitlist(collectionStatistics);

        return list;

    }

    @Override
    public List<GeneralCollectionStatistics> selectSamemonthProfitlist(GeneralCollectionStatistics collectionStatistics) {
        List<GeneralCollectionStatistics> list=returnedMoneyMapper.selectSamemonthProfitlist(collectionStatistics);
        return list;
    }
}

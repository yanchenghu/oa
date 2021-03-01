package com.ruoyi.analysis.mapper;

import com.ruoyi.analysis.domain.GeneralCollectionStatistics;
import com.ruoyi.analysis.domain.ReturnedMoney;

import java.util.List;
import java.util.Map;

/**
 * @Description: 绩效统计
 * @Author ych
 * @Date 2021/2/18
 */
public interface ReturnedMoneyMapper {

    List<ReturnedMoney> selectReturnedmoneyList(Map<String, Object> map);

    ReturnedMoney selectfinPaymentCollectionBycorpCode(String corpCode);

    List<GeneralCollectionStatistics> selectAccumulatedCollectionlist(GeneralCollectionStatistics collectionStatistics);

    List<GeneralCollectionStatistics> selectAccumulatedExpenditurelist(GeneralCollectionStatistics collectionStatistics);

    List<GeneralCollectionStatistics> selectAccumulatedProfitlist(GeneralCollectionStatistics collectionStatistics);

    List<GeneralCollectionStatistics> selectSamemonthProfitlist(GeneralCollectionStatistics collectionStatistics);
}

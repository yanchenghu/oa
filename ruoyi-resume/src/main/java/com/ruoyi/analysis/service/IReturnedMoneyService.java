package com.ruoyi.analysis.service;


import com.ruoyi.analysis.domain.GeneralCollectionStatistics;
import com.ruoyi.analysis.domain.ReturnedMoney;

import java.util.List;

/**
 *
 * 
 * @author ych
 * @date 2020-12-28
 */
public interface IReturnedMoneyService
{

    List<ReturnedMoney> selectReturnedmoneyList(GeneralCollectionStatistics collectionStatistics);

    List<GeneralCollectionStatistics> selectAccumulatedCollectionlist(GeneralCollectionStatistics collectionStatistics);

    List<GeneralCollectionStatistics> selectAccumulatedExpenditurelist(GeneralCollectionStatistics collectionStatistics);

    List<GeneralCollectionStatistics> selectAccumulatedProfitlist(GeneralCollectionStatistics collectionStatistics);

    List<GeneralCollectionStatistics> selectSamemonthProfitlist(GeneralCollectionStatistics collectionStatistics);
}

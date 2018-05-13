package com.tainy.console.service.impl;

import com.alibaba.fastjson.JSON;
import com.tainy.common.base.Constant;
import com.tainy.common.domain.console.ConsoleConsumerInfo;
import com.tainy.common.domain.console.ConsoleUser;
import com.tainy.common.util.CheckUserUtil;
import com.tainy.common.util.DateUtil;
import com.tainy.common.util.StringUtil;
import com.tainy.common.util.page.Page;
import com.tainy.console.mapper.ConsoleConsumerInfoMapper;
import com.tainy.console.service.ConsoleConsumerInfoService;
import com.tainy.console.vo.console.StatisticsConsumerDataRequest;
import com.tainy.console.vo.console.StatisticsConsumerDataResponse;
import com.tainy.console.vo.console.dto.QueryByTimeDTO;
import com.tainy.console.vo.console.dto.StatisticsConsumerDataDTO;
import com.tainy.console.vo.console.vo.AccountDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Tainy
 * @date 2018/1/13
 */
@Service
public class ConsoleConsumerInfoServiceImpl implements ConsoleConsumerInfoService{

    @Autowired
    private ConsoleConsumerInfoMapper consoleConsumerInfoMapper;

    @Override
    public List<ConsoleConsumerInfo> selectListPageGetConsumerInfos(Page<?> page, ConsoleConsumerInfo consoleConsumerInfo) {
        ConsoleUser user = CheckUserUtil.getUser();
        if(user != null){
            consoleConsumerInfo.setUserId(user.getId());
            return consoleConsumerInfoMapper.selectListPageGetConsumerInfos(page, consoleConsumerInfo);
        } else {
            return null;
        }
    }

    @Override
    public boolean addConsumerInfo(ConsoleConsumerInfo info) {
        if(info == null){
            return false;
        }

        ConsoleUser user = CheckUserUtil.getUser();
        Date now = new Date();
        info.setCreateUser(user == null ? "0" : user.getId().toString());
        info.setCreateTime(now);
        info.setUpdateUser(user == null ? "0" : user.getId().toString());
        info.setUpdateTime(now);
        info.setDelFlag(Constant.DEL_FLAG_NO);

        info.setUserId(user == null ? 0 : user.getId());

        int i = consoleConsumerInfoMapper.insertSelective(info);

        return i == 0 ? false : true;
    }

    @Override
    public boolean editConsumerInfo(ConsoleConsumerInfo info) {
        if(info == null){
            return false;
        }

        int i = consoleConsumerInfoMapper.updateByPrimaryKeySelective(info);

        return i == 0 ? false : true;
    }

    @Override
    public Integer deleteConsumerInfo(Integer id) {
        ConsoleConsumerInfo info = new ConsoleConsumerInfo();
        info.setUserId(id);
        info.setDelFlag(Constant.DEL_FLAG_YES);

        return consoleConsumerInfoMapper.updateByPrimaryKeySelective(info);
    }

    @Override
    public StatisticsConsumerDataResponse statisticsConsumerData(StatisticsConsumerDataRequest statisticsConsumerDataRequest) throws ParseException {
        Integer category = statisticsConsumerDataRequest.getCategory();

        ConsoleUser user = CheckUserUtil.getUser();
        if(user != null) {
            ConsoleConsumerInfo info = new ConsoleConsumerInfo();
            info.setUserId(user.getId());
            info.setDelFlag(Constant.DEL_FLAG_NO);
            StatisticsConsumerDataResponse  response = new StatisticsConsumerDataResponse();
            List<AccountDataVO> accountDataVOList = new ArrayList<>();
            List<StatisticsConsumerDataDTO> tempList = null;
            AccountDataVO accountDataVO = null;

            if(category == 1){// 按消费分类统计
                tempList = consoleConsumerInfoMapper.statisticsConsumerDataByCategory(info);
                if(!CollectionUtils.isEmpty(tempList)){
                    for(StatisticsConsumerDataDTO temp : tempList){
                        accountDataVO = new AccountDataVO();
                        accountDataVO.setName(temp.getCodeName());
                        accountDataVO.setValue(temp.getAmount()/100D);
                        accountDataVO.setSheets(temp.getSheets());

                        accountDataVOList.add(accountDataVO);
                        response.setTotalConsumer(new BigDecimal(Double.toString(response.getTotalConsumer())).add(new BigDecimal(Double.toString(accountDataVO.getValue()))).doubleValue());
                    }
                    System.out.println("消费总费用：" + response.getTotalConsumer());
                }
            }else if(category == 2) {// 按消费城市统计
                tempList = consoleConsumerInfoMapper.statisticsConsumerDataByCity(info);
                if(!CollectionUtils.isEmpty(tempList)){
                    for(StatisticsConsumerDataDTO temp : tempList){
                        accountDataVO = new AccountDataVO();
                        accountDataVO.setName(temp.getCity());
                        accountDataVO.setValue(temp.getAmount()/100D);
                        accountDataVO.setSheets(temp.getSheets());

                        accountDataVOList.add(accountDataVO);
                    }
                }
            }else if(category == 3) {// 按收入支出统计
                tempList = consoleConsumerInfoMapper.statisticsConsumerData(info);
                if(!CollectionUtils.isEmpty(tempList)){
                    for(StatisticsConsumerDataDTO temp : tempList){
                        accountDataVO = new AccountDataVO();
                        if(temp.getDigest() == 1){ // 收入
                            accountDataVO.setName("总收入");
                            accountDataVO.setValue(temp.getAmount()/100D);
                            response.setTotalIncome(temp.getAmount()/100D);
                        }else if(temp.getDigest() == 2){ // 支出
                            accountDataVO.setName("总支出");
                            accountDataVO.setValue(temp.getAmount()/100D);
                            response.setTotalConsumer(temp.getAmount()/100D);
                        }
                        accountDataVO.setSheets(temp.getSheets());

                        accountDataVOList.add(accountDataVO);
                    }

                    // 余额
                    BigDecimal in = new BigDecimal(response.getTotalIncome() == 0 ? "0" : Double.toString(response.getTotalIncome()));
                    BigDecimal out = new BigDecimal(response.getTotalConsumer() == 0 ? "0" : Double.toString(response.getTotalConsumer()));
                    response.setBalance(in.subtract(out).doubleValue());

                    accountDataVO = new AccountDataVO("余额", response.getBalance());
                    accountDataVOList.add(accountDataVO);
                }
            }else if(category == 4){
                tempList = consoleConsumerInfoMapper.statisticsConsumerDataByMonth(info);
                if(!CollectionUtils.isEmpty(tempList)){
                    for(StatisticsConsumerDataDTO temp : tempList){
                        accountDataVO = new AccountDataVO();
                        accountDataVO.setName(temp.getCodeName());
                        accountDataVO.setValue(temp.getAmount()/100D);
                        accountDataVO.setSheets(temp.getSheets());

                        accountDataVOList.add(accountDataVO);
                        if(accountDataVOList.size() >= 12){// 统计最近12个月
                            break;
                        }
                    }
                }
            }else if(category == 5){
                String queryTimeStart = statisticsConsumerDataRequest.getQueryTimeStart();
                String queryTimeEnd = statisticsConsumerDataRequest.getQueryTimeEnd();
                Date startTime = null;
                Date endTime = null;
                QueryByTimeDTO queryByTimeDTO = new QueryByTimeDTO();

                // 如果起始时间为空，则从现在结束，往前推30天
                if(StringUtils.isEmpty(queryTimeStart)){
                    endTime = new Date();
                    startTime = DateUtil.genOneNewTime(endTime, -30);
                    queryByTimeDTO.setStartTime(startTime);
                    queryByTimeDTO.setEndTime(endTime);
                }else{// 不为空
                    queryByTimeDTO.setStartTime(DateUtil.formatQueryTimeToData(queryTimeStart));
                    queryByTimeDTO.setEndTime(DateUtil.formatQueryTimeToData(queryTimeEnd));
                }

                tempList = consoleConsumerInfoMapper.statisticsConsumerDataByDay(queryByTimeDTO, user.getId());
                if(!CollectionUtils.isEmpty(tempList)){
                    for(StatisticsConsumerDataDTO temp : tempList){
                        accountDataVO = new AccountDataVO();
                        accountDataVO.setName(temp.getCodeName());
                        accountDataVO.setValue(temp.getAmount()/100D);
                        accountDataVO.setSheets(temp.getSheets());

                        accountDataVOList.add(accountDataVO);
                    }
                }
            }else if(category == 6){
                String queryTimeStart = statisticsConsumerDataRequest.getQueryTimeStart();
                String queryTimeEnd = statisticsConsumerDataRequest.getQueryTimeEnd();
                QueryByTimeDTO queryByTimeDTO = new QueryByTimeDTO();

                if(!StringUtil.isEmpty(queryTimeStart) && !StringUtil.isEmpty(queryTimeEnd)){
                    queryByTimeDTO.setStartTime(DateUtil.formatQueryTimeToData(queryTimeStart));
                    queryByTimeDTO.setEndTime(DateUtil.formatQueryTimeToData(queryTimeEnd));
                }

                tempList = consoleConsumerInfoMapper.statisticsConsumerDataByPerson(queryByTimeDTO, user.getId());
                if(!CollectionUtils.isEmpty(tempList)){
                    for(StatisticsConsumerDataDTO temp : tempList){
                        accountDataVO = new AccountDataVO();
                        accountDataVO.setName(temp.getCodeName());
                        accountDataVO.setValue(temp.getAmount()/100D);
                        accountDataVO.setSheets(temp.getSheets());

                        accountDataVOList.add(accountDataVO);
                    }
                }
            }

            response.setAccountDataVOList(accountDataVOList);
            return response;
        }else{
            return null;
        }
    }

}

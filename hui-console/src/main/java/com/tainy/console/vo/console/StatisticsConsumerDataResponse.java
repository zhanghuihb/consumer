package com.tainy.console.vo.console;

import com.tainy.console.vo.console.vo.AccountDataVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tainy
 * @date 2018/2/21
 */
public class StatisticsConsumerDataResponse implements Serializable{

    private double totalConsumer = 0;// 总消费

    private double totalIncome = 0; // 总收入

    private double balance = 0; //  余额

    private Integer category; // 统计类别1：按分类统计 2：按城市统计 3：按收入支出统计

    private List<AccountDataVO> accountDataVOList = new ArrayList<>(); // 统计结果

    public double getTotalConsumer() {
        return totalConsumer;
    }

    public void setTotalConsumer(double totalConsumer) {
        this.totalConsumer = totalConsumer;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public List<AccountDataVO> getAccountDataVOList() {
        return accountDataVOList;
    }

    public void setAccountDataVOList(List<AccountDataVO> accountDataVOList) {
        this.accountDataVOList = accountDataVOList;
    }
}

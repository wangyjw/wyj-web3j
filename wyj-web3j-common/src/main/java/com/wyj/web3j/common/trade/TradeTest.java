package com.wyj.web3j.common.trade;

import java.math.BigDecimal;

public class TradeTest {

    public static void main(String args[]){
        Trade trade = new Trade();
        trade.trasfer("账户a的hash码","abc123","账户b的hash码",new BigDecimal(100));
    }
}
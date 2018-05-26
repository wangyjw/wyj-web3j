package com.wyj.web3j.common.account;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.parity.Parity;
import org.web3j.protocol.parity.methods.response.NewAccountIdentifier;
import org.web3j.protocol.parity.methods.response.PersonalAccountsInfo;

import com.wyj.web3j.common.client.ParityClient;
import com.wyj.web3j.common.client.Web3JClient;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;

public class Account {

    private static Parity parity = ParityClient.getParity();

    private static Web3j web3j = Web3JClient.getClient();

    /**
     * Life
     * Like this
     * Like that
     * Also
     * It's not the same with you think
     * @Author lzh
     *
     */
    public List<String> getAccountlist(){
        try{
            return  parity.personalListAccounts().send().getAccountIds();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<String> getAccountlistByWeb3j(){
        try{
            return  web3j.ethAccounts().send().getAccounts();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }




    public String createAccount(String accountName,String password,AccountInfo accountInfo){
        try {
            NewAccountIdentifier newAccountIdentifier = parity.personalNewAccount(password).send();
            if(newAccountIdentifier!=null){
                String accountId = newAccountIdentifier.getAccountId();
                parity.personalSetAccountName(accountId,accountName);

                Map<String,Object> account = new HashMap<String,Object>();
                account.put(accountId,accountInfo);
                parity.personalSetAccountMeta(accountId,account);

                return  accountId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PersonalAccountsInfo.AccountsInfo getAccountInfo(String accountId){

        try{
            PersonalAccountsInfo personalAccountsInfo = parity.personalAccountsInfo().send();

            return  personalAccountsInfo.getAccountsInfo().get(accountId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public BigInteger getBalance(String accountId){
        try {
            DefaultBlockParameter defaultBlockParameter = new DefaultBlockParameterNumber(58);
            EthGetBalance ethGetBalance =  parity.ethGetBalance(accountId,defaultBlockParameter).send();
            if(ethGetBalance!=null){
                return ethGetBalance.getBalance();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
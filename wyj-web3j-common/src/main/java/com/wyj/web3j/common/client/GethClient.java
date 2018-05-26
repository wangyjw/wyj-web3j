package com.wyj.web3j.common.client;

import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;

public class GethClient {

    private static String ip = "http://ip地址:8545/";

    private GethClient(){}

    private static class ClientHolder{
        private static final Parity parity = Parity.build(new HttpService(ip));
    }

    public static final  Parity getParity(){
        return ClientHolder.parity;
    }
}
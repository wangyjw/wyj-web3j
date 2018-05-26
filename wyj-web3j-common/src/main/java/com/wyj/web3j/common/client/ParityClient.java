package com.wyj.web3j.common.client;

import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;

public class ParityClient {

//    private static String ip = "http://127.0.0.1:8545/";
    private static String ip = "https://mainnet.infura.io/G3DuwWyv1KSyc79rsl0x/";

    private ParityClient(){}

    private static class ClientHolder{
        private static final Parity parity = Parity.build(new HttpService(ip));
    }

    public static final  Parity getParity(){
        return ClientHolder.parity;
    }
}
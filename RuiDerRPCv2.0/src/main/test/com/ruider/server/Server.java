package com.ruider.server;

import com.ruider.networkCommunication.IOSelection.BIO.BIOService;
import com.ruider.networkCommunication.NetIO;

public class Server {
    public static void main(String[] args) {
        NetIO BIOService = new BIOService();
        BIOService.recv(9000);
    }
}



package com.ruider.networkCommunication.IOSelection.NIO;

/**
 * 使用 BIO 通信
 */

import com.ruider.common.ServiceInformation;
import com.ruider.networkCommunication.NetIO;

public class NIOService implements NetIO {

    @Override
    public Object send(ServiceInformation serviceInformation) {
        return null;
    }

    @Override
    public void recv(int port) {

    }
}

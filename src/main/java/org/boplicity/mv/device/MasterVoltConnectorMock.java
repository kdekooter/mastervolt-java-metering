package org.boplicity.mv.device;

import org.boplicity.mv.util.ByteHexUtil;
import static org.boplicity.mv.device.MasterVoltCommandType.*;

public class MasterVoltConnectorMock implements MasterVoltConnector {

    @Override
    public byte[] execute(Command command) throws DeviceUnavailableException, MasterVoltMessageException {

        switch (command.getCommandType()) {
            case CURRENT_VALUES_B6:
                return ByteHexUtil.hexStringToByteArray("FFFF0101B6DB0080A2000A008813E50012000B00F21C001A55410000000018");
            case DAILY_VALUES_9A:
                return ByteHexUtil.hexStringToByteArray("FFFF0101B6DB0080A2000A008813E50012000B00F21C001A55410000000018");
            default:
                return null;
        }
    }
}

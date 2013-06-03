package org.boplicity.mv.device;

import static org.boplicity.mv.device.MasterVoltCommandType.CURRENT_VALUES_B6;
import static org.boplicity.mv.device.MasterVoltCommandType.DAILY_VALUES_9A;

public class Command {

    private byte[] payload = new byte[9];
    private byte commandType;

    public Command(byte[] address, byte commandType) {
        this.commandType = commandType;

        payload[0] = address[0];
        payload[1] = address[1];
        payload[2] = (byte) 0xFF;
        payload[3] = (byte) 0xFF;
        payload[4] = commandType;
        payload[5] = (byte) 0x00;
        payload[6] = (byte) 0x00;
        payload[7] = (byte) 0x00;

        int sum = 0;

        for (int i = 0; i < 8; i++) {
            sum += payload[i];
        }

        byte checksum = (byte) (sum % 256);
        payload[8] = checksum;
    }

    public Command(byte[] address, byte commandType, Integer daysBack) {
        this.commandType = commandType;

        payload[0] = address[0];
        payload[1] = address[1];
        payload[2] = (byte) 0xFF;
        payload[3] = (byte) 0xFF;
        payload[4] = commandType;
        payload[5] = daysBack.byteValue();
        payload[6] = (byte) 0x00;
        payload[7] = (byte) 0x00;

        int sum = 0;

        for (int i = 0; i < 8; i++) {
            sum += payload[i];
        }

        byte checksum = (byte) (sum % 256);
        payload[8] = checksum;
    }

    public byte[] getPayload() {
        return payload;
    }

    public byte getCommandType() {
        return commandType;
    }

    public int getExpectedResponseLength() {
        switch (commandType) {
            case CURRENT_VALUES_B6:
                return 32;
            case DAILY_VALUES_9A:
                return 10;
            default:
                return 10;
        }
    }
}


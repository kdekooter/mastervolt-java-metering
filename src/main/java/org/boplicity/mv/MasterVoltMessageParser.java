package org.boplicity.mv;

import org.boplicity.mv.device.MasterVoltMessageException;
import org.boplicity.mv.model.MasterVoltMeasurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class MasterVoltMessageParser {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public MasterVoltMeasurement parseCurrentValues(byte[] value) throws MasterVoltMessageException {

        // byte 4 contains original command code
        // B6 == 182, expected result length: 31
        // C1 == 193, expected result length: 9
        // 9A == 154, expected result length: 9


        MasterVoltMeasurement measurement = null;

        validateMessage(value);

        try {
            measurement = new MasterVoltMeasurement();

            //            measurement.setDailyProductionKwh(new BigDecimal((value[7] & 0xFF) * 256 + (value[6] & 0xFF)));

            measurement.setVoltageSolar(new BigDecimal((value[9] & 0xFF) * 256 + (value[8] & 0xFF)));
            measurement.setCurrentSolar(new BigDecimal((value[11] & 0xFF) * 256 + (value[10] & 0xFF)));

            //            measurement.setPowerSolar(new BigDecimal((value[11] & 0xFF) * 256 + (value[10] & 0xFF)));
            measurement.setPowerGrid(new BigDecimal((value[19] & 0xFF) * 256 + (value[18] & 0xFF)));

            measurement.setInverterTemperature(new BigDecimal(value[23] & 0xFF));
            measurement.setTotalRuntime((value[16] & 0xFF) * 65536 + (value[25] & 0xFF) * 256 + (value[24] & 0xFF));
            measurement.setTotalProductionKwh(new BigDecimal((value[22] & 0xFF) * 65536 + (value[21] & 0xFF) * 256 + (value[20] & 0xFF)).divide(new BigDecimal(100)));
        } catch (Exception e) {
            logger.error(e.toString());
        }

        return measurement;
    }

    public MasterVoltMeasurement parseDailyValues(byte[] value) throws MasterVoltMessageException {

        validateMessage(value);

        MasterVoltMeasurement measurement = null;

        try {
            measurement = new MasterVoltMeasurement();

            measurement.setDailyProductionKwh(new BigDecimal((value[7] & 0xFF) * 256 + (value[6] & 0xFF)).divide(
                    new BigDecimal(100)));
            measurement.setDailyRuntime((value[5] & 0xFF));

        } catch (Exception e) {
            throw new MasterVoltMessageException(e.toString());
        }

        return measurement;
    }

    private void validateMessage(byte[] message) throws MasterVoltMessageException {

        if (message == null || message.length == 0) {
            throw new MasterVoltMessageException("Message is empty");
        }

//        if (containsError(message)) {
//            throw new MasterVoltMessageException("Message contains error code");
//        }

        if (!verifyChecksum(message)) {
            throw new MasterVoltMessageException("Checksum invalid");
        }
    }

    // Checks error state - byte 7 and 6 contain error code
    // TODO: only valid for B6 messages?
    private boolean containsError(byte[] message) {
        return (message[7] & 0xFF) * 256 + (message[6] & 0xFF) < 32768;
    }

    // Last byte contains the checksum
    public boolean verifyChecksum(byte[] message) {
        int sum = 0;
        for (int i = 0; i < message.length - 1; i++) {
            sum += message[i] & 0xFF;
        }
        int lastByte = message[message.length - 1] & 0xFF;
        return (sum % 256 == lastByte);
    }
}

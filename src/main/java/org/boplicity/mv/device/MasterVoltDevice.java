package org.boplicity.mv.device;

import org.boplicity.mv.MasterVoltMessageParser;
import org.boplicity.mv.model.MasterVoltMeasurement;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.boplicity.mv.device.MasterVoltCommandType.CURRENT_VALUES_B6;
import static org.boplicity.mv.device.MasterVoltCommandType.DAILY_VALUES_9A;

/**
 * Credits go to:
 * - Jorg Jansen www.zonnigdruten.nl - author of a Python script
 * - Marco Bakker zon.mbsoft.nl - editor of a bash script
 *
 * They did a lot of the dirty reverse engineer stuff for me ;-).
 */
public class MasterVoltDevice {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private byte[] address;
    private MasterVoltMessageParser parser = new MasterVoltMessageParser();
    private MasterVoltConnector connector;
    
    public MasterVoltDevice() {
        logger.info("Initializing MasterVoltDevice");
//        address = getInverterAddress();
        address = new byte[] {(byte)0x10 + (byte)0x01, (byte)0x01};
    }

    public byte[] getInverterAddress() throws DeviceUnavailableException, MasterVoltMessageException {

        // GetIdCMD="\x00\x00\xFF\xFF\xC1\x00\x00\x00\xBF"
//        byte[] command = new byte[]{(byte)0x00, (byte)0x00, (byte)0xFF, (byte)0xFF, (byte)0xC1, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xBF};
        Command command = new Command(new byte[] {(byte)0x11, (byte)0x01}, (byte)0xC1);

        // Result: ff ff 10 01 c1 db 00 00 ab
        byte[] commandResult = connector.execute(command);

        byte[] result = new byte[2];
        result[0] = (byte)(commandResult[2] + (byte)0x01);
        result[1] = commandResult[3];
        
        return result;
    }

    // Result: FF FF 11 01 B6 DB 00 80 FA 00 69 00 88 13 E6 00 65 00 E5 00 26 29 00 2D 04 58 00 00 00 00 27
    public MasterVoltMeasurement getCurrentValues() throws DeviceUnavailableException, MasterVoltMessageException {

        Command command = new Command(address, CURRENT_VALUES_B6);

        return parser.parseCurrentValues(connector.execute(command));
    }

    public MasterVoltMeasurement getDailyValues() throws DeviceUnavailableException, MasterVoltMessageException {

        Command command = new Command(address, DAILY_VALUES_9A);

        return parser.parseDailyValues(connector.execute(command));
    }

    public List<MasterVoltMeasurement> getMonthlyValues() throws DeviceUnavailableException, MasterVoltMessageException {

        List<MasterVoltMeasurement> result = new ArrayList();

        for (int i = 1; i < 30; i++) {
            Command command = new Command(address, DAILY_VALUES_9A, i);
            MasterVoltMeasurement measurement = parser.parseDailyValues(connector.execute(command));
            measurement.setTimeStamp(new LocalDate().minusDays(i).toDateTimeAtStartOfDay());
            result.add(measurement);
        }

        return result;
    }

    public void setConnector(MasterVoltConnector connector) {
        this.connector = connector;
    }
}

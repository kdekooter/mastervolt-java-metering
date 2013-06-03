package org.boplicity.mv.device;

import org.boplicity.mv.model.MasterVoltMeasurement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

@Test(groups = "integration")
public class MasterVoltDeviceIntegrationTest {

    private MasterVoltDevice device;

    @BeforeClass
    public void init() {
        device = new MasterVoltDevice();
        device.setConnector(new MasterVoltConnectorImpl());
    }

    public void testGetInverterId() throws DeviceUnavailableException, MasterVoltMessageException {
        byte[] inverterId = device.getInverterAddress();

        String result = getHex(inverterId);
        System.out.println(result);
    }

    public void testGetCurrentValues() throws DeviceUnavailableException, MasterVoltMessageException {
        MasterVoltMeasurement measurement = device.getCurrentValues();

        System.out.println(measurement.getPowerGrid());
    }

    public void testGetDailyValues() throws DeviceUnavailableException, MasterVoltMessageException {
        MasterVoltMeasurement measurement = device.getDailyValues();

        System.out.println(measurement.getDailyProductionKwh());
    }

    public void testGetMonthlyValues() throws DeviceUnavailableException, MasterVoltMessageException {
        List<MasterVoltMeasurement> measurements = device.getMonthlyValues();

        Assert.assertTrue(measurements.size() > 1);
    }

    public static String getHex(final byte [] raw) {
          if (raw == null) {
             return null;
          }
          final StringBuilder hex = new StringBuilder(2 * raw.length);
          for (final byte b : raw) {
             hex.append(' ');
             final int hiVal = (b & 0xF0) >> 4;
             final int loVal = b & 0x0F;
             hex.append((char) ('0' + (hiVal + (hiVal / 10 * 7))));
             hex.append((char) ('0' + (loVal + (loVal / 10 * 7))));
          }
          return hex.toString();
       }
}

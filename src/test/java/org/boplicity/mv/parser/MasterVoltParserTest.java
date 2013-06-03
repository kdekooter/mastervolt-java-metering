package org.boplicity.mv.parser;

import junit.framework.Assert;
import org.boplicity.mv.MasterVoltMessageParser;
import org.boplicity.mv.device.MasterVoltMessageException;
import org.boplicity.mv.model.MasterVoltMeasurement;
import org.boplicity.mv.util.ByteHexUtil;
import org.testng.annotations.Test;

import java.math.BigDecimal;

@Test
public class MasterVoltParserTest {

//    FF FF 01 01 B6 DB 00 80 A2 00 0A 00 88 13 E5 00 12 00 0B 00 F2 1C 00 1A 55 41 00 00 00 00 18
//                            162   10    5000  229   18    11    7410     26 16725
//                            VDc IDc*100 Fr*100VAc IAc*100 PAc WTot*100 Temp Runtime (min)

    private MasterVoltMessageParser parser = new MasterVoltMessageParser();
    private String testValue = "FFFF0101B6DB0080A2000A008813E50012000B00F21C001A55410000000018";
    private String testErrorB6Value = "FFFF0101B6DB0080A2000A008813E50012000B00F21C001A55410000000017";

    public void testParser() throws MasterVoltMessageException {

        MasterVoltMeasurement actualMeasurement = parser.parseCurrentValues(ByteHexUtil.hexStringToByteArray(testValue));

        MasterVoltMeasurement expectedMeasurement = new MasterVoltMeasurement();
        expectedMeasurement.setPowerGrid(new BigDecimal(11));
        expectedMeasurement.setVoltageSolar(new BigDecimal(162));
        expectedMeasurement.setCurrentSolar(new BigDecimal(10));
        expectedMeasurement.setInverterTemperature(new BigDecimal(26));
        expectedMeasurement.setTotalProductionKwh(new BigDecimal("74.1"));
        expectedMeasurement.setTotalRuntime(1196373);

        Assert.assertEquals(expectedMeasurement, actualMeasurement);
    }

    public void testConversion() {
        Assert.assertEquals(testValue,
                ByteHexUtil.byteArrayToHexString(ByteHexUtil.hexStringToByteArray(testValue)));
    }

    public void testChecksumCorrect() {
        Assert.assertTrue(parser.verifyChecksum(ByteHexUtil.hexStringToByteArray(testValue)));
        Assert.assertFalse(parser.verifyChecksum(ByteHexUtil.hexStringToByteArray(testErrorB6Value)));
    }

}

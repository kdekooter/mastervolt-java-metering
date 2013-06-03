package org.boplicity.mv.device;

public interface MasterVoltConnector {

    byte[] execute(Command command) throws DeviceUnavailableException, MasterVoltMessageException;
}

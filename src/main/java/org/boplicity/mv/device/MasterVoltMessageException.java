package org.boplicity.mv.device;

public class MasterVoltMessageException extends Throwable {
    public MasterVoltMessageException(int errorCode) {
        super("Error in MasterVoltMessage with code " + errorCode);
    }

    public MasterVoltMessageException(String s) {
        super(s);
    }
}

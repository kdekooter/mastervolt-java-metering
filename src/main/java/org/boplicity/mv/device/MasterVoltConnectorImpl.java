package org.boplicity.mv.device;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MasterVoltConnectorImpl implements MasterVoltConnector {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private String host = "10.10.10.7";
    private int port = 23;

    @Override
    public byte[] execute(Command command) throws DeviceUnavailableException, MasterVoltMessageException {

        byte[] result = null;

        ByteArrayOutputStream byteArrayOutputStream = null;
        Socket socket = null;

        try {
            socket = new Socket();
            byteArrayOutputStream = new ByteArrayOutputStream();
            socket.connect(new InetSocketAddress(InetAddress.getByName(host), port), 1000);
            socket.setSoTimeout(1000);
            socket.getOutputStream().write(command.getPayload());

            int b = -1;

            while ((b = socket.getInputStream().read()) > -1) {
                byteArrayOutputStream.write(b);
            }

            result = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            // TODO: is this the best way to terminate reading from the socket?
            if (e.getMessage().equals("Read timed out")) {
                result = byteArrayOutputStream.toByteArray();
            } else {
                throw new DeviceUnavailableException();
            }
        } finally {
            try {
                socket.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                //
            }
        }

        return result;
    }
}

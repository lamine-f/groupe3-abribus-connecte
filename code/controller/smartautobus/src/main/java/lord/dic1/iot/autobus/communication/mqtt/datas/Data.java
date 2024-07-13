package lord.dic1.iot.autobus.communication.mqtt.datas;

import java.io.Serializable;

public  interface Data extends Serializable {
    String toJson ();
}

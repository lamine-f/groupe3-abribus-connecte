package lord.dic1.iot.autobus.entities;


import lord.dic1.iot.autobus.communication.mqtt.datas.BusPosition;

public class Bus {
    private int id;
    private BusPosition position;
    private String number;

    public BusPosition getPosition() {
        return position;
    }

    public void setPosition(BusPosition position) {
        this.position = position;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return this.id;
    }

    public Bus ( int id,  String number) {
        this.id = id;
        this.number = number;
    }
}

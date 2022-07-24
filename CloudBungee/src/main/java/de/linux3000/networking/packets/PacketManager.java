package de.linux3000.networking.packets;

import de.linux3000.networking.packets.pkts.HelloPacket;
import de.linux3000.networking.packets.pkts.ProxyEnabledPacket;
import de.linux3000.networking.packets.pkts.ServerRegisterPacket;

import java.util.ArrayList;
import java.util.List;

public class PacketManager {


    public static Class<?>[] out = new Class<?>[1500];
    public static Class<?>[] in = new Class<?>[1500];

    public static void registerPackets() {

        in[15] = ServerRegisterPacket.class;


        out[0] = HelloPacket.class;
        out[1] = ProxyEnabledPacket.class;

    }





}

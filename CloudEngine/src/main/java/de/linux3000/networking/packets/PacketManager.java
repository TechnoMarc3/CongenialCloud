package de.linux3000.networking.packets;

import de.linux3000.networking.packets.pkts.*;

public class PacketManager {


    public static Class<?>[] out = new Class<?>[1500];
    public static Class<?>[] in = new Class<?>[1500];


    public static void registerPackets() {

        in[0] = HelloPacket.class;

        in[5] = CloudRegisterServerPacket.class;
        out[5] = CloudRegisterServerPacket.class;


    }




}

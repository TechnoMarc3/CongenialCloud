package de.linux3000.networking.packets;

import de.linux3000.networking.packets.pkts.*;

import java.util.ArrayList;
import java.util.List;

public class PacketManager {


    public static Class<?>[] out = new Class<?>[1500];
    public static Class<?>[] in = new Class<?>[1500];

    public static void registerPackets() {

        in[15] = ServerRegisteredPacket.class;
        in[2] = ServerEnabledPacket.class;

        out[0] = HelloPacket.class;
        out[4] = SendPlayerToServerPacket.class;
        out[6] = WarnPlayerPacket.class;
    out[3] = BanPlayerPacket.class;
       out[5] =  ShutdownCloudServerPacket.class;




    }





}

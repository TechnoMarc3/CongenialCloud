package de.linux3000.networking.packets;

import de.linux3000.networking.packets.pkts.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PacketManager {


    public static Class<?>[] out = new Class<?>[1500];
    public static Class<?>[] in = new Class<?>[1500];


    public static void registerPackets() {

        in[0] = HelloPacket.class;
        in[1] = ProxyEnabledPacket.class;
        in[2] = ServerEnabledPacket.class;
        in[3] = BanPlayerPacket.class;
        in[4] = SendPlayerToServerPacket.class;
        in[5] = ShutdownCloudServerPacket.class;
        in[6] = WarnPlayerPacket.class;

        out[15] = ServerRegisterPacket.class;



    }




}

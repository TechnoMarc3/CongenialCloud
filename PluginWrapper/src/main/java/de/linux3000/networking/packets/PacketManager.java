package de.linux3000.networking.packets;

import de.linux3000.networking.packets.pkts.*;

import java.util.ArrayList;
import java.util.List;

public class PacketManager {


    public static Class<?>[] out = new Class<?>[1500];
    public static Class<?>[] in = new Class<?>[1500];

    public static void registerPackets() {



        out[0] = HelloPacket.class;

        in[5] = CloudRegisterServerPacket.class;
        out[5] = CloudRegisterServerPacket.class;

        in[10] = CloudUpdateServerPacket.class;
        out[10] = CloudUpdateServerPacket.class;


        out[15] = CloudRegisterPlayerPacket.class;
        in[15] = CloudRegisterPlayerPacket.class;

        in[20] = CloudUpdatePlayerPacket.class;
        out[20] = CloudUpdatePlayerPacket.class;




    }





}

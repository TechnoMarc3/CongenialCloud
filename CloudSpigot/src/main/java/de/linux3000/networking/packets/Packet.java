package de.linux3000.networking.packets;

import io.netty.buffer.ByteBuf;


public interface Packet {
    void read(ByteBuf buf);
    void write(ByteBuf buf);

}

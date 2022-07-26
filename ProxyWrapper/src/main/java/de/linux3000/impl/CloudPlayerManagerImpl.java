package de.linux3000.impl;

import de.linux300.api.manager.impl.AbstractCloudPlayerManager;
import de.linux300.api.player.ICloudPlayer;
import de.linux300.api.server.ICloudServer;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.List;

public class CloudPlayerManagerImpl extends AbstractCloudPlayerManager {

    //TODO I/O Netty
    @Override
    public boolean sendPlayerToCloudServer(ICloudServer iCloudServer, ICloudPlayer iCloudPlayer) {
        getProxiedPlayerByCloudPlayer(iCloudPlayer).connect(getServerInfoByCloudServer(iCloudServer));
        return false;
    }

    private ProxiedPlayer getProxiedPlayerByCloudPlayer(ICloudPlayer player){
        return ProxyServer.getInstance().getPlayer(player.uuid());
    }

    private ServerInfo getServerInfoByCloudServer(ICloudServer server) {
        return ProxyServer.getInstance().getServerInfo(server.name());
    }

    @Override
    public boolean banPlayer(ICloudPlayer iCloudPlayer, String s) {
        //TODO
        return false;
    }

    @Override
    public boolean kickPlayer(ICloudPlayer iCloudPlayer, String s) {
        ProxyServer.getInstance().getPlayer(iCloudPlayer.uuid()).disconnect(s);
        return false;
    }

    @Override
    public void sendMessageToPlayer(ICloudPlayer iCloudPlayer, String s) {
        getProxiedPlayerByCloudPlayer(iCloudPlayer).sendMessage(TextComponent.fromLegacyText(s));
    }

    @Override
    public void sendActionBar(ICloudPlayer iCloudPlayer, String s) {
        getProxiedPlayerByCloudPlayer(iCloudPlayer).sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(s));
    }

    @Override
    public void sendTablist(ICloudPlayer iCloudPlayer, List<String> list, List<String> list1) {
        String header = "";
        String footers = "";
        for(String s : list) {
            header += s + "\n";
        }
        for(String s : list1) {
            footers += s + "\n";
        }
        getProxiedPlayerByCloudPlayer(iCloudPlayer).setTabHeader(TextComponent.fromLegacyText(header), TextComponent.fromLegacyText(footers));
    }

    @Override
    public boolean hasPermission(ICloudPlayer iCloudPlayer, String s) {
        return false;
    }

    @Override
    public void sendPlayerToLobby(ICloudPlayer iCloudPlayer) {
        //TODO
    }


}

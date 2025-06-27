package com.caffeineaddict.caffeineaddictmode.network;

import com.caffeineaddict.caffeineaddictmode.CaffeineAddictMode;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {

    private static final String PROTOCOL_VERSION = "1.0";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(CaffeineAddictMode.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        int id = 0;

        INSTANCE.registerMessage(
                id++,
                GiveWaterPacket.class,
                GiveWaterPacket::encode,
                GiveWaterPacket::decode,
                GiveWaterPacket::handle
        );
    }

    public static void sendToServer(Object msg) {
        INSTANCE.sendToServer(msg);
    }
}

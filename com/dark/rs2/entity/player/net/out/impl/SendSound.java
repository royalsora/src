package com.dark.rs2.entity.player.net.out.impl;

import com.dark.core.network.StreamBuffer;
import com.dark.rs2.entity.Sound;
import com.dark.rs2.entity.player.net.Client;
import com.dark.rs2.entity.player.net.out.OutgoingPacket;

public class SendSound extends OutgoingPacket {

    public static final int REAL = 10, HIGH = 8, MEDIUM_HIGH = 7, MEDIUM_LOW = 6, LOW = 5;

    private final int id;

    private final int type;

    private final int delay;

    private final int volume;

    public SendSound(int id, int type, int delay, int volume) {
        this.id = id;
        this.delay = delay;
        if (type == 0) {
            this.type = 10;
        } else {
            this.type = type;
        }
        this.volume = volume;
    }

    public SendSound(int id, int type, int delay) {
        this.id = id;
        this.delay = delay;
        if (type == 0) {
            this.type = 10;
        } else {
            this.type = type;
        }
        this.volume = REAL;
    }

    public SendSound(Sound sound) {
        this.id = sound.id;
        this.type = sound.type;
        this.delay = sound.delay;
        this.volume = REAL;
    }

    public SendSound(Sound sound, int volume) {
        this.id = sound.id;
        this.type = sound.type;
        this.delay = sound.delay;
        this.volume = volume;
    }

    @Override
    public void execute(Client client) {
        StreamBuffer.OutBuffer out = StreamBuffer.newOutBuffer(18);
        out.writeHeader(client.getEncryptor(), 174);
        out.writeShort(id);
        out.writeByte(type);
        out.writeShort(delay);
        out.writeShort(volume);
        client.send(out.getBuffer());
    }

    @Override
    public int getOpcode() {
        return 174;
    }

}

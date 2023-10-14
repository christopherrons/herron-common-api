package com.herron.exchange.common.api.common.model;

//FIXME: Handle as long instead
public record Volume(double volume) {

    public static Volume create(double volume) {
        return new Volume(volume);
    }

    public boolean gt(Volume otherVolume) {
        return this.volume > otherVolume.volume();
    }

    public boolean lt(Volume otherVolume) {
        return this.volume < otherVolume.volume();
    }

    public boolean ge(Volume otherVolume) {
        return this.volume >= otherVolume.volume();
    }

    public boolean le(Volume otherVolume) {
        return this.volume <= otherVolume.volume();
    }

    public Volume multiply(Volume otherVolume) {
        return new Volume(volume * otherVolume.volume);
    }

    public Volume divide(Volume otherVolume) {
        return new Volume(volume * (1 / otherVolume.volume));
    }

    public Volume add(Volume otherVolume) {
        return new Volume(volume + otherVolume.volume);
    }

    public Volume subtract(Volume otherVolume) {
        return new Volume(volume - otherVolume.volume);
    }
}

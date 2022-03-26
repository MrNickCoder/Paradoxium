package com.ncoder.paradoxium;

import com.ncoder.paradoxium.api.WarpConnections;

public final class ParadoxAPI {

    private static WarpConnections warpConnections;

    public ParadoxAPI() {
        warpConnections = new WarpConnections();
    }

    public static WarpConnections getWarpConnections() { return warpConnections; }

}

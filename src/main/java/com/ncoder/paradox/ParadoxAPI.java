package com.ncoder.paradox;

import com.ncoder.paradox.api.WarpConnections;

public class ParadoxAPI {

    private static WarpConnections warpConnections;

    public ParadoxAPI() {
        warpConnections = new WarpConnections();
    }

    public static WarpConnections getWarpConnections() { return warpConnections; }

}

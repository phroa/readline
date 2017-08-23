package net.phroa.readline;

import com.mumfrey.liteloader.LiteMod;

import java.io.File;
import java.util.Arrays;

public class LiteModReadline implements LiteMod {
    @Override
    public void init(File configPath) {
    }

    @Override
    public String getName() {
        return "@NAME@";
    }

    @Override
    public String getVersion() {
        return "@VERSION@";
    }

    @Override
    public void upgradeSettings(String version, File configPath, File oldConfigPath) {
    }
}




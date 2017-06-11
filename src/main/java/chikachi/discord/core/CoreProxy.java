/*
 * Copyright (C) 2017 Chikachi
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses.
 */

package chikachi.discord.core;

import chikachi.discord.core.config.Configuration;
import chikachi.discord.core.config.minecraft.MinecraftConfig;

import java.io.File;

public class CoreProxy {
    private static boolean preInit = false;
    private static boolean serverStopping = false;

    public void onPreInit(File configurationPath) {
        if (preInit) {
            return;
        }

        Configuration.onPreInit(configurationPath.getAbsolutePath() + File.separator + "Chikachi");

        preInit = true;
    }

    public void onServerStarting() {
        DiscordClient.getInstance().connect();
    }

    public void onServerStarted() {

    }

    public void onServerStopping() {
        if (serverStopping) {
            return;
        }

        MinecraftConfig minecraftConfig = Configuration.getConfig().minecraft;

        DiscordClient.getInstance().broadcast(
            minecraftConfig.messages.serverStop,
            minecraftConfig.dimensions.generic.relayServerStop.getChannels(
                minecraftConfig.dimensions.generic.discordChannel
            )
        );

        serverStopping = true;
    }

    public void onServerStopped() {
        if (!serverStopping) {
            MinecraftConfig minecraftConfig = Configuration.getConfig().minecraft;

            DiscordClient.getInstance().broadcast(
                minecraftConfig.messages.serverCrash,
                minecraftConfig.dimensions.generic.relayServerCrash.getChannels(
                    minecraftConfig.dimensions.generic.discordChannel
                )
            );
        }

        DiscordClient.getInstance().disconnect(true);
    }
}

package net.modekh.ussrefill;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.modekh.ussrefill.utils.Reference;
import org.slf4j.Logger;

@Mod(Reference.MOD_ID)
public class USSRefill {
    private static final Logger LOGGER = LogUtils.getLogger();

    public USSRefill() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
//        bus.addListener(this::commonSetup);
        bus.register(this);
    }

    /*private void commonSetup(final FMLCommonSetupEvent event) {
//        ConfigRegistry.register();
    }*/
}

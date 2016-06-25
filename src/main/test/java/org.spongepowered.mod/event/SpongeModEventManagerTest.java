package org.spongepowered.mod.event;

import org.junit.Before;
import org.mockito.Mockito;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.SpawnEntityEvent;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.plugin.PluginManager;
import org.spongepowered.common.event.ShouldFire;
import org.spongepowered.common.event.SpongeEventManager;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Optional;

public class SpongeModEventManagerTest {

    private EventManager eventManager;
    private Object plugin;
    private PluginContainer container;

    @Before
    public void init() throws Exception {
        PluginManager manager = Mockito.mock(PluginManager.class);
        this.eventManager = new SpongeEventManager(manager);

        this.plugin = new Object();
        this.container = Mockito.mock(PluginContainer.class);
        Mockito.when(manager.fromInstance(plugin)).thenReturn(Optional.of(this.container));

        this.resetStatics();
    }

    private void resetStatics() throws IllegalAccessException {
        for (Field field: ShouldFire.class.getDeclaredFields()) {
            if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
                field.set(null, false);
            }
        }
    }

    private class SpawnListener {

        @Listener
        public void onSpawn(SpawnEntityEvent event) {}
    }

    private class ForgeSpawnEventListener {

    }

}

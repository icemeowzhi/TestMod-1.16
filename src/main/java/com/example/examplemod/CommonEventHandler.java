package com.example.examplemod;

import com.example.examplemod.capability.*;
import com.example.examplemod.gui.PackSpeedLvl;
import com.example.examplemod.utils.Common;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Mod.EventBusSubscriber()
public class CommonEventHandler {

    @SubscribeEvent
    public static void onAttachCapabilityOnPlayerEvent(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (entity instanceof PlayerEntity) {
            event.addCapability(new ResourceLocation(Common.MOD_ID, "speedup"), new PlayerSpeedLevelCapabilityProvider((PlayerEntity) entity));
            System.out.println("attached");
        }
    }


    @SubscribeEvent
    public static void onAttachCapabilityOnItemStackEvent(AttachCapabilitiesEvent<ItemStack> event){
        ItemStack itemStack = event.getObject();
        if (itemStack.getItem().equals(ItemRegistry.greenAppleSword.get())){
            event.addCapability(new ResourceLocation(Common.MOD_ID,"level"),new ItemLevelCapabilityProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {//TODO
        if (!event.isWasDeath()) {
            System.out.println("player crossed portal");
            LazyOptional<IPlayerSpeedLevelCapability> oldSpeedCap = event.getOriginal().getCapability(ModCapability.PLAYER_SPEED_LEVEL_CAPABILITY);
            LazyOptional<IPlayerSpeedLevelCapability> newSpeedCap = event.getPlayer().getCapability(ModCapability.PLAYER_SPEED_LEVEL_CAPABILITY);
            System.out.println(oldSpeedCap.isPresent());
            System.out.println(newSpeedCap.isPresent());
            if (oldSpeedCap.isPresent() && newSpeedCap.isPresent()) {
                newSpeedCap.ifPresent((newCap) -> oldSpeedCap.ifPresent((oldCap) -> newCap.deserializeNBT(oldCap.serializeNBT())));
                System.out.println("synced");
            }
        }else {
            LazyOptional<IPlayerSpeedLevelCapability> newSpeedCap = event.getPlayer().getCapability(ModCapability.PLAYER_SPEED_LEVEL_CAPABILITY);
            Random random = new Random();
            IPlayerSpeedLevelCapability attached = new PlayerSpeedLevelCapability(random.nextInt(3) + 1);
            if (newSpeedCap.isPresent()){
                newSpeedCap.ifPresent((newCap) -> newCap.deserializeNBT(attached.serializeNBT())); //refresh level after death
            }
        }

    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinWorldEvent event){
        if (event.getEntity() instanceof PlayerEntity){
            LazyOptional<IPlayerSpeedLevelCapability> speedCap = event.getEntity().getCapability(ModCapability.PLAYER_SPEED_LEVEL_CAPABILITY);
            if (speedCap.isPresent()){
                speedCap.ifPresent((cap)->{
                    EffectInstance effectInstance = new EffectInstance(Effects.SPEED,Integer.MAX_VALUE,cap.getLevel()-1,false,true,true);
                    ((PlayerEntity)event.getEntity()).addPotionEffect(effectInstance); //add potion effect to player
                    if (!event.getWorld().isRemote) { // package to sync client and server,done in 6.14
                       NetworkHandler.INSTANCE.send(
                                PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getEntity()),
                                new PackSpeedLvl(cap.getLevel()));
                    }
                });
            }



            if (event.getWorld().isRemote){
                AtomicInteger clientLvl = new AtomicInteger();
                assert Minecraft.getInstance().player != null;
                Minecraft.getInstance().player.getCapability(ModCapability.PLAYER_SPEED_LEVEL_CAPABILITY).ifPresent((cap)-> clientLvl.set(cap.getLevel()));
                System.out.println("client : " + clientLvl.get());
            }else {
                speedCap.ifPresent((cap)-> System.out.println("server : " + cap.getLevel()));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerAttackEvent(AttackEntityEvent event){
        if (event.getEntity() instanceof PlayerEntity){
            LazyOptional<IItemLevelCapability> capability = event.getPlayer().getHeldItem(Hand.MAIN_HAND).getCapability(ModCapability.ITEM_LEVEL_CAPABILITY);
            capability.ifPresent((cap)->{
                float damage = 0f;
                switch (cap.getLevel()){
                    case 1:
                        damage = 1f;
                        event.getTarget().attackEntityFrom(DamageSource.GENERIC,damage);
                        break;
                    case 2:
                        damage = 4f;
                        event.getTarget().attackEntityFrom(DamageSource.GENERIC,damage);
                        break;
                    case 3:
                        damage = 8f;
                        event.getTarget().attackEntityFrom(DamageSource.GENERIC,damage);
                        break;
                }
                TranslationTextComponent component = new TranslationTextComponent("extra.damage.attack",damage);
                if (damage>0f){
                    event.getPlayer().sendStatusMessage(component,true);
                }
            });
        }
    }
}
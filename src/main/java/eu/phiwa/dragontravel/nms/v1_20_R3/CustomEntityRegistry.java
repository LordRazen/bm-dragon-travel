package eu.phiwa.dragontravel.nms.v1_20_R3;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import net.minecraft.core.DefaultedMappedRegistry;
import net.minecraft.core.RegistryMaterials;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.breeze.Breeze;
import net.minecraft.world.entity.ambient.EntityBat;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.Tadpole;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.*;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.boss.enderdragon.EntityEnderCrystal;
import net.minecraft.world.entity.boss.enderdragon.EntityEnderDragon;
import net.minecraft.world.entity.boss.wither.EntityWither;
import net.minecraft.world.entity.decoration.*;
import net.minecraft.world.entity.item.EntityFallingBlock;
import net.minecraft.world.entity.item.EntityItem;
import net.minecraft.world.entity.item.EntityTNTPrimed;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.hoglin.EntityHoglin;
import net.minecraft.world.entity.monster.piglin.EntityPiglin;
import net.minecraft.world.entity.monster.piglin.EntityPiglinBrute;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.EntityVillager;
import net.minecraft.world.entity.npc.EntityVillagerTrader;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.entity.vehicle.*;
import org.bukkit.entity.Camel;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings("rawtypes")
public class CustomEntityRegistry extends DefaultedMappedRegistry {
    private final BiMap<MinecraftKey, EntityTypes> entities = HashBiMap.create();
    private final BiMap<EntityTypes, MinecraftKey> entityClasses = this.entities.inverse();
    private final Map<EntityTypes, Integer> entityIds = Maps.newHashMap();
    private final RegistryMaterials<EntityTypes<?>> wrapped;

    public CustomEntityRegistry(DefaultedMappedRegistry<EntityTypes<?>> original) {
        //super(original.a().getNamespace(), null, null);
        //super(original.a().b(), null, null);
        super(original.a().b(), null, null, false);
        this.wrapped = original;
    }

    @Override
    public int a(Object key) {
        if (entityIds.containsKey(key)) {
            return entityIds.get(key);
        }
        //return key.hashCode();
        return wrapped.a((EntityTypes) key);
    }

    @Override
    public Optional a(RandomSource paramRandom) {
        return wrapped.a(paramRandom);
    }

    public EntityTypes findType(Class<?> search) {
        return minecraftClassMap.inverse().get(search);
        /*
        for (Object type : wrapped) {
            if (minecraftClassMap.get(type) == search) {
                return (EntityTypes) type;
            }
        }
        return null;
        */
    }

    //@Override
    public Object fromId(int var0) {
        //return this.wrapped.fromId(var0);
        return this.wrapped.a(var0);
    }

    //@Override
    public EntityTypes get(MinecraftKey key) {
        if (entities.containsKey(key)) {
            return entities.get(key);
        }

        //return wrapped.get(key);
        return wrapped.a(key);
    }

    //@Override
    public MinecraftKey getKey(Object value) {
        if (entityClasses.containsKey(value)) {
            return entityClasses.get(value);
        }

        //return wrapped.getKey((EntityTypes) value);
        return wrapped.b((EntityTypes) value);
    }

    //@Override
    public Optional getOptional(MinecraftKey var0) {
        if (entities.containsKey(var0)) {
            return Optional.of(entities.get(var0));
        }

        //return this.wrapped.getOptional(var0);
        return this.wrapped.b(var0);
    }

    public RegistryMaterials<EntityTypes<?>> getWrapped() {
        return wrapped;
    }

    @Override
    public Iterator<Object> iterator() {
        return (Iterator) wrapped.iterator();
    }

    //@Override
    public Set<Object> keySet() {
        //return (Set) wrapped.keySet();
        return (Set) wrapped.e();
    }

    public void put(int entityId, MinecraftKey key, EntityTypes entityClass) {
        entities.put(key, entityClass);
        entityIds.put(entityClass, entityId);
    }

    // replace regex
    // ([A-Z_]+).*?a\(E(.*?)::new.*?$
    // minecraftClassMap.put(EntityTypes.\1, E\2.class);
    private static final BiMap<EntityTypes, Class<?>> minecraftClassMap = HashBiMap.create();

    static {
        minecraftClassMap.put(EntityTypes.b, Allay.class);
        minecraftClassMap.put(EntityTypes.c, EntityAreaEffectCloud.class);
        minecraftClassMap.put(EntityTypes.d, EntityArmorStand.class);
        minecraftClassMap.put(EntityTypes.e, EntityTippedArrow.class);
        minecraftClassMap.put(EntityTypes.f, Axolotl.class);
        minecraftClassMap.put(EntityTypes.g, EntityBat.class);
        minecraftClassMap.put(EntityTypes.h, EntityBee.class);
        minecraftClassMap.put(EntityTypes.i, EntityBlaze.class);
        minecraftClassMap.put(EntityTypes.j, Display.BlockDisplay.class);
        minecraftClassMap.put(EntityTypes.k, EntityBoat.class);
        minecraftClassMap.put(EntityTypes.l, Breeze.class);
        minecraftClassMap.put(EntityTypes.m, Camel.class);
        minecraftClassMap.put(EntityTypes.n, EntityCat.class);
        minecraftClassMap.put(EntityTypes.o, EntityCaveSpider.class);
        minecraftClassMap.put(EntityTypes.p, ChestBoat.class);
        minecraftClassMap.put(EntityTypes.q, EntityMinecartChest.class);
        minecraftClassMap.put(EntityTypes.r, EntityChicken.class);
        minecraftClassMap.put(EntityTypes.s, EntityCod.class);
        minecraftClassMap.put(EntityTypes.t, EntityMinecartCommandBlock.class);
        minecraftClassMap.put(EntityTypes.u, EntityCow.class);
        minecraftClassMap.put(EntityTypes.v, EntityCreeper.class);
        minecraftClassMap.put(EntityTypes.w, EntityDolphin.class);
        minecraftClassMap.put(EntityTypes.x, EntityHorseDonkey.class);
        minecraftClassMap.put(EntityTypes.y, EntityDragonFireball.class);
        minecraftClassMap.put(EntityTypes.z, EntityDrowned.class);
        minecraftClassMap.put(EntityTypes.A, EntityEgg.class);
        minecraftClassMap.put(EntityTypes.B, EntityGuardianElder.class);
        minecraftClassMap.put(EntityTypes.C, EntityEnderCrystal.class);
        minecraftClassMap.put(EntityTypes.D, EntityEnderDragon.class);
        minecraftClassMap.put(EntityTypes.E, EntityEnderPearl.class);
        minecraftClassMap.put(EntityTypes.F, EntityEnderman.class);
        minecraftClassMap.put(EntityTypes.G, EntityEndermite.class);
        minecraftClassMap.put(EntityTypes.H, EntityEvoker.class);
        minecraftClassMap.put(EntityTypes.I, EntityEvokerFangs.class);
        minecraftClassMap.put(EntityTypes.J, EntityThrownExpBottle.class);
        minecraftClassMap.put(EntityTypes.K, EntityExperienceOrb.class);
        minecraftClassMap.put(EntityTypes.L, EntityEnderSignal.class);
        minecraftClassMap.put(EntityTypes.M, EntityFallingBlock.class);
        minecraftClassMap.put(EntityTypes.N, EntityFireworks.class);
        minecraftClassMap.put(EntityTypes.O, EntityFox.class);
        minecraftClassMap.put(EntityTypes.P, Frog.class);
        minecraftClassMap.put(EntityTypes.Q, EntityMinecartFurnace.class);
        minecraftClassMap.put(EntityTypes.R, EntityGhast.class);
        minecraftClassMap.put(EntityTypes.S, EntityGiantZombie.class);
        minecraftClassMap.put(EntityTypes.T, GlowItemFrame.class);
        minecraftClassMap.put(EntityTypes.U, GlowSquid.class);
        minecraftClassMap.put(EntityTypes.V, Goat.class);
        minecraftClassMap.put(EntityTypes.W, EntityGuardian.class);
        minecraftClassMap.put(EntityTypes.X, EntityHoglin.class);
        minecraftClassMap.put(EntityTypes.Y, EntityMinecartHopper.class);
        minecraftClassMap.put(EntityTypes.Z, EntityHorse.class);
        minecraftClassMap.put(EntityTypes.aa, EntityZombieHusk.class);
        minecraftClassMap.put(EntityTypes.ab, EntityIllagerIllusioner.class);
        minecraftClassMap.put(EntityTypes.ac, Interaction.class);
        minecraftClassMap.put(EntityTypes.ad, EntityIronGolem.class);
        minecraftClassMap.put(EntityTypes.ae, EntityItem.class);
        minecraftClassMap.put(EntityTypes.af, Display.ItemDisplay.class);
        minecraftClassMap.put(EntityTypes.ag, EntityItemFrame.class);
        minecraftClassMap.put(EntityTypes.ah, EntityLargeFireball.class);
        minecraftClassMap.put(EntityTypes.ai, EntityLeash.class);
        minecraftClassMap.put(EntityTypes.aj, EntityLightning.class);
        minecraftClassMap.put(EntityTypes.ak, EntityLlama.class);
        minecraftClassMap.put(EntityTypes.al, EntityLlamaSpit.class);
        minecraftClassMap.put(EntityTypes.am, EntityMagmaCube.class);
        minecraftClassMap.put(EntityTypes.an, Marker.class);
        minecraftClassMap.put(EntityTypes.ao, EntityMinecartRideable.class);
        minecraftClassMap.put(EntityTypes.ap, EntityMushroomCow.class);
        minecraftClassMap.put(EntityTypes.aq, EntityHorseMule.class);
        minecraftClassMap.put(EntityTypes.ar, EntityOcelot.class);
        minecraftClassMap.put(EntityTypes.as, EntityPainting.class);
        minecraftClassMap.put(EntityTypes.at, EntityPanda.class);
        minecraftClassMap.put(EntityTypes.au, EntityParrot.class);
        minecraftClassMap.put(EntityTypes.av, EntityPhantom.class);
        minecraftClassMap.put(EntityTypes.aw, EntityPig.class);
        minecraftClassMap.put(EntityTypes.ax, EntityPiglin.class);
        minecraftClassMap.put(EntityTypes.ay, EntityPiglinBrute.class);
        minecraftClassMap.put(EntityTypes.az, EntityPillager.class);
        minecraftClassMap.put(EntityTypes.aA, EntityPolarBear.class);
        minecraftClassMap.put(EntityTypes.aB, EntityPotion.class);
        minecraftClassMap.put(EntityTypes.aC, EntityPufferFish.class);
        minecraftClassMap.put(EntityTypes.aD, EntityRabbit.class);
        minecraftClassMap.put(EntityTypes.aE, EntityRavager.class);
        minecraftClassMap.put(EntityTypes.aF, EntitySalmon.class);
        minecraftClassMap.put(EntityTypes.aG, EntitySheep.class);
        minecraftClassMap.put(EntityTypes.aH, EntityShulker.class);
        minecraftClassMap.put(EntityTypes.aI, EntityShulkerBullet.class);
        minecraftClassMap.put(EntityTypes.aJ, EntitySilverfish.class);
        minecraftClassMap.put(EntityTypes.aK, EntitySkeleton.class);
        minecraftClassMap.put(EntityTypes.aL, EntityHorseSkeleton.class);
        minecraftClassMap.put(EntityTypes.aM, EntitySlime.class);
        minecraftClassMap.put(EntityTypes.aN, EntitySmallFireball.class);
        minecraftClassMap.put(EntityTypes.aO, Sniffer.class);
        minecraftClassMap.put(EntityTypes.aP, EntitySnowman.class);
        minecraftClassMap.put(EntityTypes.aQ, EntitySnowball.class);
        minecraftClassMap.put(EntityTypes.aR, EntityMinecartMobSpawner.class);
        minecraftClassMap.put(EntityTypes.aS, EntitySpectralArrow.class);
        minecraftClassMap.put(EntityTypes.aT, EntitySpider.class);
        minecraftClassMap.put(EntityTypes.aU, EntitySquid.class);
        minecraftClassMap.put(EntityTypes.aV, EntitySkeletonStray.class);
        minecraftClassMap.put(EntityTypes.aW, EntityStrider.class);
        minecraftClassMap.put(EntityTypes.aX, Tadpole.class);
        minecraftClassMap.put(EntityTypes.aY, Display.TextDisplay.class);
        minecraftClassMap.put(EntityTypes.aZ, EntityTNTPrimed.class);
        minecraftClassMap.put(EntityTypes.ba, EntityMinecartTNT.class);
        minecraftClassMap.put(EntityTypes.bb, EntityLlamaTrader.class);
        minecraftClassMap.put(EntityTypes.bc, EntityThrownTrident.class);
        minecraftClassMap.put(EntityTypes.bd, EntityTropicalFish.class);
        minecraftClassMap.put(EntityTypes.be, EntityTurtle.class);
        minecraftClassMap.put(EntityTypes.bf, EntityVex.class);
        minecraftClassMap.put(EntityTypes.bg, EntityVillager.class);
        minecraftClassMap.put(EntityTypes.bh, EntityVindicator.class);
        minecraftClassMap.put(EntityTypes.bi, EntityVillagerTrader.class);
        minecraftClassMap.put(EntityTypes.bj, Warden.class);
        minecraftClassMap.put(EntityTypes.bk, WindCharge.class);
        minecraftClassMap.put(EntityTypes.bl, EntityWitch.class);
        minecraftClassMap.put(EntityTypes.bm, EntityWither.class);
        minecraftClassMap.put(EntityTypes.bn, EntitySkeletonWither.class);
        minecraftClassMap.put(EntityTypes.bo, EntityWitherSkull.class);
        minecraftClassMap.put(EntityTypes.bp, EntityWolf.class);
        minecraftClassMap.put(EntityTypes.bq, EntityZoglin.class);
        minecraftClassMap.put(EntityTypes.br, EntityZombie.class);
        minecraftClassMap.put(EntityTypes.bs, EntityHorseZombie.class);
        minecraftClassMap.put(EntityTypes.bt, EntityZombieVillager.class);
        minecraftClassMap.put(EntityTypes.bu, EntityPigZombie.class);
        minecraftClassMap.put(EntityTypes.bv, EntityHuman.class);
        minecraftClassMap.put(EntityTypes.bw, EntityFishingHook.class);
    }
}
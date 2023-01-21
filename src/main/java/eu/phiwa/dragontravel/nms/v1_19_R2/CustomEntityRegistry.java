package eu.phiwa.dragontravel.nms.v1_19_R2;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import net.minecraft.core.DefaultedMappedRegistry;
import net.minecraft.core.RegistryMaterials;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ambient.EntityBat;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.Tadpole;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.*;
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
        minecraftClassMap.put(EntityTypes.j, EntityBoat.class);
        minecraftClassMap.put(EntityTypes.k, ChestBoat.class);
        minecraftClassMap.put(EntityTypes.l, EntityCat.class);
        minecraftClassMap.put(EntityTypes.m, Camel.class);
        minecraftClassMap.put(EntityTypes.n, EntityCaveSpider.class);
        minecraftClassMap.put(EntityTypes.o, EntityChicken.class);
        minecraftClassMap.put(EntityTypes.p, EntityCod.class);
        minecraftClassMap.put(EntityTypes.q, EntityCow.class);
        minecraftClassMap.put(EntityTypes.r, EntityCreeper.class);
        minecraftClassMap.put(EntityTypes.s, EntityDolphin.class);
        minecraftClassMap.put(EntityTypes.t, EntityHorseDonkey.class);
        minecraftClassMap.put(EntityTypes.u, EntityDragonFireball.class);
        minecraftClassMap.put(EntityTypes.v, EntityDrowned.class);
        minecraftClassMap.put(EntityTypes.w, EntityGuardianElder.class);
        minecraftClassMap.put(EntityTypes.x, EntityEnderCrystal.class);
        minecraftClassMap.put(EntityTypes.y, EntityEnderDragon.class);
        minecraftClassMap.put(EntityTypes.z, EntityEnderman.class);
        minecraftClassMap.put(EntityTypes.A, EntityEndermite.class);
        minecraftClassMap.put(EntityTypes.B, EntityEvoker.class);
        minecraftClassMap.put(EntityTypes.C, EntityEvokerFangs.class);
        minecraftClassMap.put(EntityTypes.D, EntityExperienceOrb.class);
        minecraftClassMap.put(EntityTypes.E, EntityEnderSignal.class);
        minecraftClassMap.put(EntityTypes.F, EntityFallingBlock.class);
        minecraftClassMap.put(EntityTypes.G, EntityFireworks.class);
        minecraftClassMap.put(EntityTypes.H, EntityFox.class);
        minecraftClassMap.put(EntityTypes.I, Frog.class);
        minecraftClassMap.put(EntityTypes.J, EntityGhast.class);
        minecraftClassMap.put(EntityTypes.K, EntityGiantZombie.class);
        minecraftClassMap.put(EntityTypes.L, GlowItemFrame.class);
        minecraftClassMap.put(EntityTypes.M, GlowSquid.class);
        minecraftClassMap.put(EntityTypes.N, Goat.class);
        minecraftClassMap.put(EntityTypes.O, EntityGuardian.class);
        minecraftClassMap.put(EntityTypes.P, EntityHoglin.class);
        minecraftClassMap.put(EntityTypes.Q, EntityHorse.class);
        minecraftClassMap.put(EntityTypes.R, EntityZombieHusk.class);
        minecraftClassMap.put(EntityTypes.S, EntityIllagerIllusioner.class);
        minecraftClassMap.put(EntityTypes.T, EntityIronGolem.class);
        minecraftClassMap.put(EntityTypes.U, EntityItem.class);
        minecraftClassMap.put(EntityTypes.V, EntityItemFrame.class);
        minecraftClassMap.put(EntityTypes.W, EntityLargeFireball.class);
        minecraftClassMap.put(EntityTypes.X, EntityLeash.class);
        minecraftClassMap.put(EntityTypes.Y, EntityLightning.class);
        minecraftClassMap.put(EntityTypes.Z, EntityLlama.class);
        minecraftClassMap.put(EntityTypes.aa, EntityLlamaSpit.class);
        minecraftClassMap.put(EntityTypes.ab, EntityMagmaCube.class);
        minecraftClassMap.put(EntityTypes.ac, Marker.class);
        minecraftClassMap.put(EntityTypes.ad, EntityMinecartRideable.class);
        minecraftClassMap.put(EntityTypes.ae, EntityMinecartChest.class);
        minecraftClassMap.put(EntityTypes.af, EntityMinecartCommandBlock.class);
        minecraftClassMap.put(EntityTypes.ag, EntityMinecartFurnace.class);
        minecraftClassMap.put(EntityTypes.ah, EntityMinecartHopper.class);
        minecraftClassMap.put(EntityTypes.ai, EntityMinecartMobSpawner.class);
        minecraftClassMap.put(EntityTypes.aj, EntityMinecartTNT.class);
        minecraftClassMap.put(EntityTypes.ak, EntityHorseMule.class);
        minecraftClassMap.put(EntityTypes.al, EntityMushroomCow.class);

        minecraftClassMap.put(EntityTypes.am, EntityOcelot.class);
        minecraftClassMap.put(EntityTypes.an, EntityPainting.class);
        minecraftClassMap.put(EntityTypes.ao, EntityPanda.class);
        minecraftClassMap.put(EntityTypes.ap, EntityParrot.class);
        minecraftClassMap.put(EntityTypes.aq, EntityPhantom.class);
        minecraftClassMap.put(EntityTypes.ar, EntityPig.class);
        minecraftClassMap.put(EntityTypes.as, EntityPiglin.class);
        minecraftClassMap.put(EntityTypes.at, EntityPiglinBrute.class);
        minecraftClassMap.put(EntityTypes.au, EntityPillager.class);
        minecraftClassMap.put(EntityTypes.av, EntityPolarBear.class);
        minecraftClassMap.put(EntityTypes.aw, EntityTNTPrimed.class);
        minecraftClassMap.put(EntityTypes.ax, EntityPufferFish.class);
        minecraftClassMap.put(EntityTypes.ay, EntityRabbit.class);
        minecraftClassMap.put(EntityTypes.az, EntityRavager.class);

        minecraftClassMap.put(EntityTypes.aA, EntitySalmon.class);
        minecraftClassMap.put(EntityTypes.aB, EntitySheep.class);
        minecraftClassMap.put(EntityTypes.aC, EntityShulker.class);
        minecraftClassMap.put(EntityTypes.aD, EntityShulkerBullet.class);
        minecraftClassMap.put(EntityTypes.aE, EntitySilverfish.class);
        minecraftClassMap.put(EntityTypes.aF, EntitySkeleton.class);
        minecraftClassMap.put(EntityTypes.aG, EntityHorseSkeleton.class);
        minecraftClassMap.put(EntityTypes.aH, EntitySlime.class);
        minecraftClassMap.put(EntityTypes.aI, EntitySmallFireball.class);
        minecraftClassMap.put(EntityTypes.aJ, EntitySnowman.class);
        minecraftClassMap.put(EntityTypes.aK, EntitySnowball.class);
        minecraftClassMap.put(EntityTypes.aL, EntitySpectralArrow.class);
        minecraftClassMap.put(EntityTypes.aM, EntitySpider.class);
        minecraftClassMap.put(EntityTypes.aN, EntitySquid.class);

        minecraftClassMap.put(EntityTypes.aO, EntitySkeletonStray.class);
        minecraftClassMap.put(EntityTypes.aP, EntityStrider.class);
        minecraftClassMap.put(EntityTypes.aQ, Tadpole.class);
        minecraftClassMap.put(EntityTypes.aR, EntityEgg.class);
        minecraftClassMap.put(EntityTypes.aS, EntityEnderPearl.class);
        minecraftClassMap.put(EntityTypes.aT, EntityThrownExpBottle.class);
        minecraftClassMap.put(EntityTypes.aU, EntityPotion.class);
        minecraftClassMap.put(EntityTypes.aV, EntityThrownTrident.class);
        minecraftClassMap.put(EntityTypes.aW, EntityLlamaTrader.class);
        minecraftClassMap.put(EntityTypes.aX, EntityTropicalFish.class);
        minecraftClassMap.put(EntityTypes.aY, EntityTurtle.class);
        minecraftClassMap.put(EntityTypes.aZ, EntityVex.class);

        minecraftClassMap.put(EntityTypes.ba, EntityVillager.class);
        minecraftClassMap.put(EntityTypes.bb, EntityVindicator.class);
        minecraftClassMap.put(EntityTypes.bc, EntityVillagerTrader.class);
        minecraftClassMap.put(EntityTypes.bd, Warden.class);
        minecraftClassMap.put(EntityTypes.be, EntityWitch.class);
        minecraftClassMap.put(EntityTypes.bf, EntityWither.class);
        minecraftClassMap.put(EntityTypes.bg, EntitySkeletonWither.class);
        minecraftClassMap.put(EntityTypes.bh, EntityWitherSkull.class);
        minecraftClassMap.put(EntityTypes.bi, EntityWolf.class);
        minecraftClassMap.put(EntityTypes.bj, EntityZoglin.class);
        minecraftClassMap.put(EntityTypes.bk, EntityZombie.class);
        minecraftClassMap.put(EntityTypes.bl, EntityHorseZombie.class);
        minecraftClassMap.put(EntityTypes.bm, EntityZombieVillager.class);
        minecraftClassMap.put(EntityTypes.bn, EntityPigZombie.class);
        minecraftClassMap.put(EntityTypes.bo, EntityHuman.class);
        minecraftClassMap.put(EntityTypes.bp, EntityFishingHook.class);
    }
}
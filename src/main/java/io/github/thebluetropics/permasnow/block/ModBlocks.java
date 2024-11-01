package io.github.thebluetropics.permasnow.block;

import io.github.thebluetropics.permasnow.PermasnowMod;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class ModBlocks {
  public static final Block ETERNAL_ICE = register(
    "eternal_ice",
    new EternalIceBlock(
      AbstractBlock.Settings.create()
        .mapColor(MapColor.PALE_PURPLE)
        .slipperiness(0.98f)
        .ticksRandomly()
        .strength(0.5f)
        .sounds(BlockSoundGroup.GLASS)
        .nonOpaque()
        .allowsSpawning((state, world, pos, entitType) -> Objects.equals(entitType, EntityType.POLAR_BEAR))
        .solidBlock(Blocks::never)
    )
  );
  public static final Block ETERNAL_SNOW = register(
    "eternal_snow",
    new EternalSnowBlock(
      AbstractBlock.Settings.create()
        .mapColor(MapColor.WHITE)
        .replaceable()
        .notSolid()
        .ticksRandomly()
        .strength(0.1f)
        .requiresTool()
        .sounds(BlockSoundGroup.SNOW)
        .blockVision((state, world, pos) -> (Integer) state.get(SnowBlock.LAYERS) >= 0)
        .pistonBehavior(PistonBehavior.DESTROY)
    )
  );

  public static <T extends Block> T register(String id, T block) {
    return Registry.register(Registries.BLOCK, new Identifier(PermasnowMod.ID, id), block);
  }

  public static void initialize() { /* ... */ }
}

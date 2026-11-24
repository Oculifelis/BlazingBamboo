# Blazing Bamboo pack settings

The following paths apply to Minecraft 26.3 on Fabric and NeoForge.

| File under `data/` | In-game effect |
| --- | --- |
| `blazingbamboo/context_int_provider/cooking/bamboo.json` and `bundle.json` in the same directory | Vanilla cooking-fuel providers for the stalk and bundle. They supply 800 and 8,000 ticks in a normal furnace and half in a smoker or blast furnace. A constant such as `325` replaces the whole provider. |
| `blazingbamboo/block_transformer/stripping.json` | Additional vanilla block-transformer rules for axes. The default matches the bundle and copies shared block properties, including the pillar axis, to the stripped bundle. |
| `blazingbamboo/tags/block/blazing_bamboo_plantable_on.json` | Blocks that support planted bamboo. |
| `blazingbamboo/tags/block/patch_soil.json` | Soils for wild patches. The default is crimson nylium. The soil must also support the plant. |
| `blazingbamboo/tags/block/freezes_growth.json` | Blocks beneath the planting soil that stop natural growth. Defaults to ice, packed ice and blue ice. |
| `blazingbamboo/tags/block/extinguishes_bamboo.json` | Nearby blocks that extinguish the plant. Defaults to water. Rain and waterlogged blocks also extinguish it. |
| `blazingbamboo/tags/worldgen/biome/grows_in.json` | Biomes with wild patches. Defaults to the crimson forest. |
| `blazingbamboo/worldgen/feature/blazing_bamboo.json` and `blazingbamboo/worldgen/placed_feature/blazing_bamboo_placed.json` | Patch attempts, spread, placement height and frequency. NeoForge also reads `blazingbamboo/neoforge/biome_modifier/blazing_bamboo.json`. |
| `blazingbamboo/recipe/`, `blazingbamboo/advancement/` and `blazingbamboo/loot_table/blocks/` | Crafting, recipe unlocks and block drops. |
| `blazingbamboo/damage_type/blazing_hot.json` and `minecraft/tags/damage_type/is_fire.json` | Contact damage type and its fire classification. |
| `thaumaturge/data_maps/item/base_aspects.json` | Item aspects on NeoForge with Thaumaturge installed. |

Use a higher-priority data pack with the same file path to override a fuel time or stripping result. Restart the world after changing registry entries or world generation. To add to a tag, supply a `values` list. Set `"replace":true` to provide the whole list.

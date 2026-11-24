# Blazing Bamboo pack settings

The following paths apply to Minecraft 1.21.1 on NeoForge.

| File under `data/` | In-game effect |
| --- | --- |
| `neoforge/data_maps/item/furnace_fuels.json` | Maps item identifiers to a positive `burn_time` in ticks. The stalk and bundle burn for 800 and 8,000 ticks. The `remove` list excludes these planks and slabs from the vanilla wood fuel groups. |
| `neoforge/data_maps/block/strippables.json` | Maps source block identifiers to a `stripped_block`. The bundle strips to `blazingbamboo:stripped_blazing_bamboo_bundle`, preserving the pillar axis. |
| `blazingbamboo/tags/block/blazing_bamboo_plantable_on.json` | Blocks that support planted bamboo. |
| `blazingbamboo/tags/block/patch_soil.json` | Soils for wild patches. The default is crimson nylium. The soil must also support the plant. |
| `blazingbamboo/tags/block/freezes_growth.json` | Blocks beneath the planting soil that stop natural growth. Defaults to ice, packed ice and blue ice. |
| `blazingbamboo/tags/block/extinguishes_bamboo.json` | Nearby blocks that extinguish the plant. Defaults to water. Rain and waterlogged blocks also extinguish it. |
| `blazingbamboo/tags/worldgen/biome/grows_in.json` | Biomes with wild patches. Defaults to the crimson forest. |
| `blazingbamboo/worldgen/configured_feature/blazing_bamboo.json` and `blazingbamboo/worldgen/placed_feature/blazing_bamboo_placed.json` | Patch attempts, spread, placement height and frequency. NeoForge also reads `blazingbamboo/neoforge/biome_modifier/blazing_bamboo.json`. |
| `blazingbamboo/recipe/`, `blazingbamboo/advancement/` and `blazingbamboo/loot_table/blocks/` | Crafting, recipe unlocks and block drops. |
| `blazingbamboo/damage_type/blazing_hot.json` and `minecraft/tags/damage_type/is_fire.json` | Contact damage type and its fire classification. |
| `thaumaturge/data_maps/item/base_aspects.json` | Item aspects on NeoForge with Thaumaturge installed. |

Override individual fuel or stripping entries under `values` in a higher-priority pack. List individual keys under `remove` to disable them. Restart the world after changing world generation. To add to a tag, supply a `values` list. Set `"replace":true` to provide the whole list.

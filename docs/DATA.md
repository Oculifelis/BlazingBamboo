# Blazing Bamboo pack settings

The following paths apply to Minecraft 26.2 on Fabric and NeoForge.

| File under `data/` | In-game effect |
| --- | --- |
| `<item namespace>/blazingbamboo/furnace_time/<item path>.json` | Sets the burn time in ticks. For example, `{"ticks":325}` burns for 325 ticks. Zero disables the fuel. The stalk burns for 800 ticks, the bundle for 8,000, and the planks and slab for zero. |
| `<block namespace>/blazingbamboo/stripping/<block path>.json` | Sets the result of using an axe on that block. The bundle uses `{"result":"blazingbamboo:stripped_blazing_bamboo_bundle"}`. The result keeps shared properties, including the pillar axis. |
| `blazingbamboo/tags/block/blazing_bamboo_plantable_on.json` | Blocks that support planted bamboo. |
| `blazingbamboo/tags/block/patch_soil.json` | Soils for wild patches. The default is crimson nylium. The soil must also support the plant. |
| `blazingbamboo/tags/block/freezes_growth.json` | Blocks beneath the planting soil that stop natural growth. Defaults to ice, packed ice and blue ice. |
| `blazingbamboo/tags/block/extinguishes_bamboo.json` | Nearby blocks that extinguish the plant. Defaults to water. Rain and waterlogged blocks also extinguish it. |
| `blazingbamboo/tags/worldgen/biome/grows_in.json` | Biomes with wild patches. Defaults to the crimson forest. |
| `blazingbamboo/worldgen/configured_feature/blazing_bamboo.json` and `blazingbamboo/worldgen/placed_feature/blazing_bamboo_placed.json` | Patch attempts, spread, placement height and frequency. NeoForge also reads `blazingbamboo/neoforge/biome_modifier/blazing_bamboo.json`. |
| `blazingbamboo/recipe/`, `blazingbamboo/advancement/` and `blazingbamboo/loot_table/blocks/` | Crafting, recipe unlocks and block drops. |
| `blazingbamboo/damage_type/blazing_hot.json` and `minecraft/tags/damage_type/is_fire.json` | Contact damage type and its fire classification. |
| `thaumaturge/data_maps/item/base_aspects.json` | Item aspects on NeoForge with Thaumaturge installed. |

Use a higher-priority data pack with the same file path to override a fuel time or stripping result. Restart the world after changing registry entries or world generation. To add to a tag, supply a `values` list. Set `"replace":true` to provide the whole list.

package meow0x7e.content

import mindustry.content.Items
import mindustry.content.Liquids
import mindustry.type.Category
import mindustry.type.ItemStack.with
import mindustry.world.Block
import mindustry.world.blocks.production.Drill
import mindustry.world.meta.Env

class Blocks {
    companion object {
        lateinit var mechanicalDrillSmall: Block
        lateinit var mechanicalDrillLarge: Block
        lateinit var mechanicalDrillExtraLarge: Block
        fun load() {
            mechanicalDrillSmall = Drill("mechanical-drill-small").apply {
                requirements(Category.production, with(Items.copper, 3))
                tier = 2
                drillTime = 600f
                size = 1
                itemCapacity = 2

                //mechanical drill doesn't work in space
                envEnabled = envEnabled xor Env.space
                researchCost = with(Items.copper, 1)

                consumeLiquid(Liquids.water, 0.05f).boost()
            }

            mechanicalDrillLarge = Drill("mechanical-drill-large").apply {
                requirements(Category.production, with(Items.copper, 27))
                tier = 2
                drillTime = 600f
                size = 3
                itemCapacity = 22

                //mechanical drill doesn't work in space
                envEnabled = envEnabled xor Env.space
                researchCost = with(Items.copper, 1)

                consumeLiquid(Liquids.water, 0.05f).boost()
            }

            mechanicalDrillExtraLarge = Drill("mechanical-drill-extra-large").apply {
                requirements(Category.production, with(Items.copper, 64))
                tier = 2
                drillTime = 600f
                size = 4
                itemCapacity = 40

                //mechanical drill doesn't work in space
                envEnabled = envEnabled xor Env.space
                researchCost = with(Items.copper, 1)

                consumeLiquid(Liquids.water, 0.05f).boost()
            }
        }
    }
}
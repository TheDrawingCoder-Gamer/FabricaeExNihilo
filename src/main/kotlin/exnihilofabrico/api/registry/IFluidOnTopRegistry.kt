package exnihilofabrico.api.registry

import alexiil.mc.lib.attributes.fluid.volume.FluidVolume
import exnihilofabrico.api.crafting.FluidIngredient
import exnihilofabrico.api.recipes.barrel.FluidOnTopRecipe
import exnihilofabrico.modules.barrels.modes.BarrelMode
import exnihilofabrico.modules.barrels.modes.FluidMode
import exnihilofabrico.modules.barrels.modes.ItemMode
import exnihilofabrico.util.asStack
import exnihilofabrico.util.asVolume
import net.minecraft.fluid.Fluid
import net.minecraft.item.ItemConvertible

interface IFluidOnTopRegistry: IRegistry<FluidOnTopRecipe> {
    fun register(contents: FluidIngredient, onTop: FluidIngredient, result: BarrelMode) =
        register(FluidOnTopRecipe(contents, onTop, result))
    fun register(contents: FluidIngredient, onTop: FluidIngredient, result: ItemConvertible) =
        register(FluidOnTopRecipe(contents, onTop, ItemMode(result.asStack())))
    fun register(contents: Fluid, onTop: Fluid, result: ItemConvertible) =
        register(FluidIngredient(contents), FluidIngredient(onTop), result)
    fun register(contents: FluidIngredient, onTop: FluidIngredient, result: Fluid) =
        register(FluidOnTopRecipe(contents, onTop, FluidMode(result.asVolume())))

    fun getResult(contents: Fluid, onTop: Fluid): BarrelMode?
    fun getResult(contents: FluidVolume, onTop: Fluid) = contents.rawFluid?.let { fluid -> getResult(fluid, onTop) }
}
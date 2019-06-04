package exnihilofabrico.registry

import exnihilofabrico.api.crafting.Lootable
import exnihilofabrico.api.recipes.SieveRecipe
import exnihilofabrico.api.registry.ISieveRegistry
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.Fluid
import net.minecraft.item.ItemStack
import java.util.*
import kotlin.collections.ArrayList

data class SieveRegistry(val registry: MutableList<SieveRecipe> = mutableListOf()): ISieveRegistry {

    override fun clear() = registry.clear()

    override fun getResult(mesh: ItemStack, fluid: Fluid?, sievable: ItemStack, player: PlayerEntity?, rand: Random): List<ItemStack> {
        val allResults = getAllResults(mesh, fluid, sievable)
        val tries = 1 +
                (EnchantmentHelper.getEnchantments(mesh)[Enchantments.FORTUNE] ?: 0) + // Fortune tries
                (player?.luck?.toInt() ?: 0) // Player's luck

        val results: MutableList<ItemStack> = ArrayList()
        for(i in 0 until tries)
            allResults.forEach { loot ->
                val num = loot.chance.count { rand.nextDouble() < it }
                if(num > 0) {
                    val stack = loot.stack.copy()
                    stack.amount = num
                    results.add(stack)
                }
            }
        return results
    }

    override fun getAllResults(mesh: ItemStack, fluid: Fluid?, sievable: ItemStack): List<Lootable> {
        return registry.filter { recipe ->
            recipe.mesh.test(mesh) && (recipe.fluid?.test(fluid) ?: true) && recipe.sievable.test(sievable)
        }.map { it.loot }.flatten()
    }

    override fun isValidRecipe(mesh: ItemStack, fluid: Fluid?, sievable: ItemStack): Boolean {
        return registry.any {recipe ->
            recipe.mesh.test(mesh) && (recipe.fluid?.test(fluid) ?: true) && recipe.sievable.test(sievable)
        }
    }

    override fun isValidMesh(mesh: ItemStack) = registry.any { recipe -> recipe.mesh.test(mesh)}

    override fun register(sieveRecipe: SieveRecipe) {
        registry.forEach {
            if(it.mesh == sieveRecipe.mesh &&
                    it.fluid == sieveRecipe.fluid &&
                    it.sievable == sieveRecipe.sievable){
                it.loot.addAll(sieveRecipe.loot)
                return
            }
        }
        registry.add(sieveRecipe)
    }

}

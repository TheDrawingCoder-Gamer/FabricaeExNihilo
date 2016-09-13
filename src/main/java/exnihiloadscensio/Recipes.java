package exnihiloadscensio;

import exnihiloadscensio.blocks.ENBlocks;
import exnihiloadscensio.config.Config;
import exnihiloadscensio.items.ENItems;
import exnihiloadscensio.items.ItemResource;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Recipes {
	
	public static void init()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(ENItems.hammerWood, new Object[] { " x ", " yx", "y  ", 'x', "plankWood", 'y', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ENItems.hammerStone, new Object[] { " x ", " yx", "y  ", 'x', "cobblestone", 'y', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ENItems.hammerIron, new Object[] { " x ", " yx", "y  ", 'x', "ingotIron", 'y', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ENItems.hammerGold, new Object[] { " x ", " yx", "y  ", 'x', "ingotGold", 'y', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ENItems.hammerDiamond, new Object[] { " x ", " yx", "y  ", 'x', "gemDiamond", 'y', "stickWood"}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(ENItems.crookWood, new Object[] { "xx"," x"," x", 'x', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ENItems.crookBone, new Object[] { "xx"," x"," x", 'x', Items.BONE}));
		
		if (Config.enableBarrels)
			GameRegistry.addRecipe(new ShapedOreRecipe(ENBlocks.barrelWood, new Object[] {"x x","x x", "xyx", 'x', "plankWood", 'y', "slabWood"}));
		if (Config.enableCrucible) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ENBlocks.crucible, 1, 0), new Object[] {"x x","x x","xxx", 'x', ItemResource.getResourceStack("porcelain_clay")}));
			FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(ENBlocks.crucible, 1, 0), new ItemStack(ENBlocks.crucible, 1, 1), 0.7f);
		}
		GameRegistry.addRecipe(new ShapedOreRecipe(Blocks.COBBLESTONE, new Object[] {"xx","xx", 'x', ItemResource.getResourceStack("stones")}));
		GameRegistry.addShapelessRecipe(ItemResource.getResourceStack("porcelain_clay"), new ItemStack(Items.CLAY_BALL), new ItemStack(Items.DYE, 1, 15));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(ENBlocks.sieve, new Object[] {"x x","xyx","z z", 'z', "plankWood", 'y', "slabWood", 'z', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ENItems.mesh, 1, 1), new Object[] {"xxx","xxx","xxx", 'x', Items.STRING}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ENItems.mesh, 1, 2), new Object[] {"x x","xyx","x x", 'x', Items.FLINT, 'y', new ItemStack(ENItems.mesh, 1, 1)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ENItems.mesh, 1, 3), new Object[] {"x x","xyx","x x", 'x', Items.IRON_INGOT, 'y', new ItemStack(ENItems.mesh, 1, 2)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ENItems.mesh, 1, 4), new Object[] {"x x","xyx","x x", 'x', Items.DIAMOND, 'y', new ItemStack(ENItems.mesh, 1, 3)}));
	}

}

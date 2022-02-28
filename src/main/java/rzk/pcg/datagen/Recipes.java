package rzk.pcg.datagen;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import rzk.pcg.registry.ModBlocks;
import rzk.pcg.registry.ModItems;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider
{
	public Recipes(DataGenerator generator)
	{
		super(generator);
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer)
	{
		ShapedRecipeBuilder.shaped(ModItems.BASE_PLATE.get(), 8)
				.pattern(" P ")
				.pattern("PSP")
				.pattern(" P ")
				.define('P', Blocks.SMOOTH_STONE_SLAB)
				.define('S', Blocks.STONE)
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(Blocks.SMOOTH_STONE_SLAB, Blocks.STONE))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.GATE_BUFFER.get())
				.pattern("R")
				.pattern("P")
				.pattern("R")
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.GATE_BUFFER_ALL.get())
				.pattern(" R ")
				.pattern("RPR")
				.pattern(" R ")
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.GATE_NOT.get())
				.pattern("T")
				.pattern("P")
				.pattern("R")
				.define('T', Blocks.REDSTONE_TORCH)
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.GATE_NOT_ALL.get())
				.pattern(" T ")
				.pattern("TPT")
				.pattern(" R ")
				.define('T', Blocks.REDSTONE_TORCH)
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.GATE_OR_2.get())
				.pattern(" R ")
				.pattern("RPR")
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.GATE_OR_3.get())
				.pattern(" R ")
				.pattern(" P ")
				.pattern("RRR")
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.GATE_NOR_2.get())
				.pattern(" T ")
				.pattern("RPR")
				.define('R', Items.REDSTONE)
				.define('T', Blocks.REDSTONE_TORCH)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.GATE_NOR_3.get())
				.pattern(" T ")
				.pattern(" P ")
				.pattern("RRR")
				.define('R', Items.REDSTONE)
				.define('T', Blocks.REDSTONE_TORCH)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.GATE_AND_2.get())
				.pattern(" R ")
				.pattern("PPP")
				.pattern("R R")
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.GATE_AND_3.get())
				.pattern(" R ")
				.pattern("PPP")
				.pattern("RRR")
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.GATE_NAND_2.get())
				.pattern(" T ")
				.pattern("PPP")
				.pattern("R R")
				.define('T', Blocks.REDSTONE_TORCH)
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.GATE_NAND_3.get())
				.pattern(" T ")
				.pattern("PPP")
				.pattern("RRR")
				.define('T', Blocks.REDSTONE_TORCH)
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.GATE_XOR_2.get())
				.pattern(" R ")
				.pattern("TPR")
				.define('T', Blocks.REDSTONE_TORCH)
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.GATE_XOR_3.get())
				.pattern(" R ")
				.pattern(" P ")
				.pattern("RTR")
				.define('T', Blocks.REDSTONE_TORCH)
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.GATE_XNOR_2.get())
				.pattern(" T ")
				.pattern("TPR")
				.define('T', Blocks.REDSTONE_TORCH)
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.GATE_XNOR_3.get())
				.pattern(" T ")
				.pattern(" P ")
				.pattern("RTR")
				.define('R', Items.REDSTONE)
				.define('T', Blocks.REDSTONE_TORCH)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.SENSOR_DAY.get())
				.pattern(" Q ")
				.pattern("GPG")
				.pattern(" R ")
				.define('Q', Items.QUARTZ)
				.define('G', Items.GLOWSTONE_DUST)
				.define('P', ModItems.BASE_PLATE.get())
				.define('R', Items.REDSTONE)
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.SENSOR_NIGHT.get())
				.pattern(" Q ")
				.pattern("GPG")
				.pattern(" T ")
				.define('Q', Items.QUARTZ)
				.define('G', Items.GLOWSTONE_DUST)
				.define('P', ModItems.BASE_PLATE.get())
				.define('T', Blocks.REDSTONE_TORCH)
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.SENSOR_RAIN.get())
				.pattern(" L ")
				.pattern("GPG")
				.pattern(" R ")
				.define('L', Items.LAPIS_LAZULI)
				.define('G', Items.GLOWSTONE_DUST)
				.define('P', ModItems.BASE_PLATE.get())
				.define('R', Items.REDSTONE)
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.SENSOR_THUNDER.get())
				.pattern(" I ")
				.pattern("GPG")
				.pattern(" R ")
				.define('I', Items.IRON_INGOT)
				.define('G', Items.GLOWSTONE_DUST)
				.define('P', ModItems.BASE_PLATE.get())
				.define('R', Items.REDSTONE)
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.SENSOR_CHEST_EMPTY.get())
				.pattern("R")
				.pattern("P")
				.pattern("C")
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.define('C', Blocks.CHEST)
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.SENSOR_CHEST_SPACE.get())
				.pattern(" R ")
				.pattern("CPC")
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.define('C', Blocks.CHEST)
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.SENSOR_CHEST_FULL.get())
				.pattern("T")
				.pattern("P")
				.pattern("C")
				.define('T', Blocks.REDSTONE_TORCH)
				.define('P', ModItems.BASE_PLATE.get())
				.define('C', Blocks.CHEST)
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.SENSOR_RISING_EDGE.get())
				.pattern(" R ")
				.pattern("RPT")
				.pattern(" Q ")
				.define('Q', Items.QUARTZ)
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.define('T', Blocks.REDSTONE_TORCH)
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.SENSOR_FALLING_EDGE.get())
				.pattern(" R ")
				.pattern("TPR")
				.pattern(" Q ")
				.define('Q', Items.QUARTZ)
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.define('T', Blocks.REDSTONE_TORCH)
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.LATCH_RS.get())
				.pattern(" L ")
				.pattern("RPR")
				.pattern(" R ")
				.define('L', Blocks.LEVER)
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.LATCH_SR.get())
				.pattern(" L ")
				.pattern("RPR")
				.pattern(" T ")
				.define('L', Blocks.LEVER)
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.define('T', Blocks.REDSTONE_TORCH)
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.LATCH_D.get())
				.pattern(" L ")
				.pattern("RPQ")
				.pattern(" R ")
				.define('L', Blocks.LEVER)
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.define('Q', Items.QUARTZ)
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.LATCH_TOGGLE.get())
				.pattern(" L ")
				.pattern("RPR")
				.pattern(" L ")
				.define('L', Blocks.LEVER)
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.TIMER.get())
				.pattern(" C ")
				.pattern("RPR")
				.pattern(" R ")
				.define('C', Items.CLOCK)
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.TIMER_ON_DELAY.get())
				.pattern(" C ")
				.pattern("RPR")
				.pattern(" D ")
				.define('C', Items.CLOCK)
				.define('R', Items.REDSTONE)
				.define('P', ModItems.BASE_PLATE.get())
				.define('D', Blocks.REPEATER)
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.TIMER_OFF_DELAY.get())
				.pattern(" C ")
				.pattern("TPT")
				.pattern(" R ")
				.define('C', Items.CLOCK)
				.define('T', Items.REDSTONE_TORCH)
				.define('P', ModItems.BASE_PLATE.get())
				.define('R', Blocks.REPEATER)
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(ModBlocks.COUNTER.get())
				.pattern(" R ")
				.pattern("QPQ")
				.pattern(" R ")
				.define('R', Items.REDSTONE)
				.define('Q', Items.QUARTZ)
				.define('P', ModItems.BASE_PLATE.get())
				.unlockedBy("item", InventoryChangeTrigger.Instance.hasItems(ModItems.BASE_PLATE.get()))
				.save(consumer);
	}
}

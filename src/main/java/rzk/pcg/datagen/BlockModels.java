package rzk.pcg.datagen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import rzk.pcg.registry.ModBlocks;

import java.util.function.Function;

public class BlockModels extends BlockStateProvider
{
	public BlockModels(DataGenerator generator, String modid, ExistingFileHelper fileHelper)
	{
		super(generator, modid, fileHelper);
	}

	private static String name(Block block)
	{
		return block.getRegistryName().getPath();
	}

	@Override
	protected void registerStatesAndModels()
	{
		ModBlocks.BLOCKS.getEntries().forEach(gate -> gate(gate.get()));
	}

	public void gate(Block block)
	{
		Function<BlockState, ModelFile> modelFunc = state ->
		{
			boolean powered = state.getValue(BlockStateProperties.POWERED);
			String name = name(block) + "_" + (powered ? "on" : "off");
			return models().getBuilder(name)
					.texture("top", modLoc("blocks/" + name))
					.parent(models().getExistingFile(modLoc("gate")));
		};

		getVariantBuilder(block)
				.forAllStates(state -> ConfiguredModel.builder()
						.modelFile(modelFunc.apply(state))
						.rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
						.build());
	}
}

package rzk.pcg.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import rzk.lib.mc.util.WorldUtils;
import rzk.pcg.gui.GuiCounter;
import rzk.pcg.tile.TileCounter;

import javax.annotation.Nullable;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class BlockCounter extends BlockGateEdgeBase
{
	protected void reset(World world, BlockPos pos)
	{
		WorldUtils.ifTilePresent(world, pos, TileCounter.class, TileCounter::reset);
	}

	protected void count(World world, BlockPos pos, int amount)
	{
		WorldUtils.ifTilePresent(world, pos, TileCounter.class, tile -> tile.count(amount));
	}

	@Override
	protected boolean isInputSide(BlockState state, Direction side)
	{
		return side.get2DDataValue() != -1 && !isOutputSide(state, side);
	}

	@Override
	protected boolean isOutputSide(BlockState state, Direction side)
	{
		return side == state.getValue(HORIZONTAL_FACING);
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult)
	{
		if (world.isClientSide)
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> GuiCounter.openGui(pos));
		return ActionResultType.SUCCESS;
	}

	@Override
	protected void onInputHigh(BlockState state, World world, BlockPos pos, Direction side)
	{
		if (!world.isClientSide)
		{
			Direction facing = world.getBlockState(pos).getValue(HORIZONTAL_FACING);

			if (side == facing.getOpposite())
				reset(world, pos);
			else if (side == facing.getClockWise())
				count(world, pos, -1);
			else if (side == facing.getCounterClockWise())
				count(world, pos, 1);
		}
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		return new TileCounter();
	}

	@Override
	public boolean shouldBePowered(BlockState state, World world, BlockPos pos)
	{
		return false;
	}
}

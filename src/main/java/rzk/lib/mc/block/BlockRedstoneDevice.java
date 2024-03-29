package rzk.lib.mc.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import rzk.lib.mc.util.Utils;

import javax.annotation.Nullable;
import java.util.EnumSet;

import static net.minecraft.state.properties.BlockStateProperties.POWERED;

public abstract class BlockRedstoneDevice extends Block
{
	public BlockRedstoneDevice(Properties properties)
	{
		super(properties);
		registerDefaultState(stateDefinition.any().setValue(POWERED, false));
	}

	protected boolean isInputSide(BlockState state, Direction side)
	{
		return false;
	}

	protected boolean isOutputSide(BlockState state, Direction side)
	{
		return false;
	}

	@Override
	public boolean canConnectRedstone(BlockState state, IBlockReader world, BlockPos pos, @Nullable Direction side)
	{
		if (side != null)
			return isInputSide(state, side.getOpposite()) || isOutputSide(state, side.getOpposite());

		return false;
	}

	@Override
	public boolean isSignalSource(BlockState p_149744_1_)
	{
		return true;
	}

	protected int getInputPower(World world, BlockPos pos, Direction side)
	{
		BlockPos blockpos = pos.relative(side);
		int i = world.getSignal(blockpos, side);
		if (i >= 15) return 15;

		BlockState state = world.getBlockState(blockpos);
		return Math.max(i, state.getBlock().equals(Blocks.REDSTONE_WIRE) ? state.getValue(RedstoneWireBlock.POWER) : 0);
	}

	protected boolean isPowered(World world, BlockPos pos, Direction... sides)
	{
		if (sides == null || sides.length == 0)
			return isPowered(world, pos, Direction.values());

		for (Direction side : sides)
			if (getInputPower(world, pos, side) > 0)
				return true;

		return false;
	}

	protected int getOutputPower(BlockState state, IBlockReader world, BlockPos pos, Direction side)
	{
		return state.getValue(POWERED) && isOutputSide(state, side) ? 15 : 0;
	}

	@Override
	public int getSignal(BlockState state, IBlockReader blockReader, BlockPos pos, Direction side)
	{
		return getOutputPower(state, blockReader, pos, side.getOpposite());
	}

	@Override
	public int getDirectSignal(BlockState state, IBlockReader blockReader, BlockPos pos, Direction side)
	{
		return getOutputPower(state, blockReader, pos, side.getOpposite());
	}

	protected void onInputChanged(BlockState state, World world, BlockPos pos, Direction side) {}

	protected void updateNeighborsInFront(BlockState state, World world, BlockPos pos, Direction side)
	{
		BlockPos blockpos = pos.relative(side);
		if (ForgeEventFactory.onNeighborNotify(world, pos, world.getBlockState(pos), EnumSet.of(side), false).isCanceled())
			return;
		world.neighborChanged(blockpos, this, pos);
		world.updateNeighborsAtExceptFromFacing(blockpos, this, side.getOpposite());
	}

	public void setPoweredState(BlockState state, World world, BlockPos pos, boolean powered)
	{
		world.setBlockAndUpdate(pos, state.setValue(POWERED, powered));
		for (Direction side : Direction.values())
			if (isOutputSide(state, side))
				updateNeighborsInFront(state, world, pos, side);
	}

	public void scheduleTickIfNotScheduled(World world, BlockPos pos, int delay)
	{
		if (!world.getBlockTicks().hasScheduledTick(pos, this))
			world.getBlockTicks().scheduleTick(pos, this, delay);
	}

	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos neighbor, boolean isMoving)
	{
		Direction side = Utils.getFromBlockPos(pos, neighbor);
		if (isInputSide(state, side))
			onInputChanged(state, world, pos, side);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(POWERED);
	}
}

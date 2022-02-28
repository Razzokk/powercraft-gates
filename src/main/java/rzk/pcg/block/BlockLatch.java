package rzk.pcg.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;
import static net.minecraft.state.properties.BlockStateProperties.POWERED;

public class BlockLatch extends BlockGateBase
{
	private final Type type;

	public BlockLatch(Type type)
	{
		this.type = type;
	}

	@Override
	public boolean isInputSide(BlockState state, Direction side)
	{
		Direction facing = state.getValue(HORIZONTAL_FACING);
		return side == facing.getCounterClockWise() || side == facing.getClockWise();
	}

	@Override
	public boolean isOutputSide(BlockState state, Direction side)
	{
		Direction facing = state.getValue(HORIZONTAL_FACING);
		return side == facing || side == facing.getOpposite();
	}

	@Override
	public boolean shouldBePowered(BlockState state, World world, BlockPos pos)
	{
		Direction facing = state.getValue(HORIZONTAL_FACING);
		boolean left = isPowered(world, pos, facing.getCounterClockWise());
		boolean right = isPowered(world, pos, facing.getClockWise());

		switch (type)
		{
			case RS:
				if (right) return false;
				if (left) return true;
				break;
			case SR:
				if (left) return true;
				if (right) return false;
				break;
		}
		return state.getValue(POWERED);
	}

	public enum Type
	{
		RS,
		SR,
		D,
		TOGGLE
	}

	public static class Edge extends BlockGateEdgeBase
	{
		private final Type type;

		public Edge(Type type)
		{
			this.type = type;
		}

		@Override
		public boolean isInputSide(BlockState state, Direction side)
		{
			Direction facing = state.getValue(HORIZONTAL_FACING);
			return side == facing.getCounterClockWise() || side == facing.getClockWise();
		}

		@Override
		public boolean isOutputSide(BlockState state, Direction side)
		{
			Direction facing = state.getValue(HORIZONTAL_FACING);
			return side == facing || side == facing.getOpposite();
		}

		@Override
		public void onInputHigh(BlockState state, World world, BlockPos pos, Direction side)
		{
			switch (type)
			{
				case D:
					if (side == state.getValue(HORIZONTAL_FACING).getClockWise()) scheduleTickIfNotScheduled(world, pos, 2);
					break;
				case TOGGLE:
					if (!isPowered(world, pos, side.getOpposite())) scheduleTickIfNotScheduled(world, pos, 2);
					break;
			}
		}

		@Override
		public boolean shouldBePowered(BlockState state, World world, BlockPos pos)
		{
			switch (type)
			{
				case D:
					return isPowered(world, pos, state.getValue(HORIZONTAL_FACING).getCounterClockWise());
				case TOGGLE:
					return !state.getValue(POWERED);
			}
			return state.getValue(POWERED);
		}
	}
}

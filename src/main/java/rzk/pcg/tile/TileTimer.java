package rzk.pcg.tile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraftforge.common.util.Constants;
import rzk.lib.mc.tile.TileRedstoneDevice;
import rzk.lib.mc.util.ObjectUtils;
import rzk.pcg.block.BlockTimer;
import rzk.pcg.registry.ModTiles;

import javax.annotation.Nullable;

public class TileTimer extends TileRedstoneDevice implements ITickableTileEntity
{
	private int neededTicks;
	private int currentTicks;
	private boolean enabled;

	public TileTimer()
	{
		super(ModTiles.TIMER.get());
		neededTicks = 20;
		currentTicks = 0;
		enabled = false;
	}

	public int getDelay()
	{
		return neededTicks;
	}

	public void setDelay(int delay)
	{
		neededTicks = delay;
		level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Constants.BlockFlags.DEFAULT);
		setChanged();
	}

	public void setEnabled(boolean state)
	{
		enabled = state;
		currentTicks = 0;
		setChanged();
	}

	private void setTimerState()
	{
		Block block = getBlockState().getBlock();
		if (block instanceof BlockTimer.Delay)
		{
			setEnabled(false);
			ObjectUtils.ifCastable(getBlockState().getBlock(), BlockTimer.Delay.class, blockTimer -> blockTimer.setPoweredState(getBlockState(), level, worldPosition, blockTimer.isOnTimer()));
		}
		else
		{
			ObjectUtils.ifCastable(getBlockState().getBlock(), BlockTimer.class, blockTimer ->
			{
				blockTimer.setPoweredState(getBlockState(), level, worldPosition, true);
				blockTimer.scheduleTickIfNotScheduled(level, worldPosition, 2);
			});
		}
	}

	@Override
	public void tick()
	{
		if (!level.isClientSide && enabled)
		{
			if (++currentTicks >= neededTicks)
			{
				setTimerState();
				currentTicks = 0;
			}
			setChanged();
		}
	}

	@Override
	@Nullable
	public SUpdateTileEntityPacket getUpdatePacket()
	{
		return new SUpdateTileEntityPacket(worldPosition, 3, getUpdateTag());
	}

	@Override
	public CompoundNBT getUpdateTag()
	{
		return save(new CompoundNBT());
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
	{
		super.onDataPacket(net, pkt);
		handleUpdateTag(getBlockState(), pkt.getTag());
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt)
	{
		super.load(state, nbt);
		neededTicks = nbt.getInt("neededTicks");
		currentTicks = nbt.getInt("currentTicks");
		enabled = nbt.getBoolean("enabled");
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt)
	{
		super.save(nbt);
		nbt.putInt("neededTicks", neededTicks);
		nbt.putInt("currentTicks", currentTicks);
		nbt.putBoolean("enabled", enabled);
		return nbt;
	}
}

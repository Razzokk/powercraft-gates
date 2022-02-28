package rzk.pcg.tile;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import rzk.lib.mc.tile.TileRedstoneDevice;
import rzk.lib.mc.util.ObjectUtils;
import rzk.lib.mc.util.Utils;
import rzk.pcg.block.BlockCounter;
import rzk.pcg.registry.ModTiles;

import javax.annotation.Nullable;

public class TileCounter extends TileRedstoneDevice
{
	private int maxCount;
	private int counter;

	public TileCounter()
	{
		super(ModTiles.COUNTER.get());
		maxCount = 10;
		counter = 0;
	}

	public void reset()
	{
		count(-counter);
		setChanged();
	}

	public void count(int amount)
	{
		counter = Utils.constrain(counter + amount, 0, maxCount);
		ObjectUtils.ifCastable(getBlockState().getBlock(), BlockCounter.class, blockCounter ->
		{
			if (counter >= maxCount && !getBlockState().getValue(BlockStateProperties.POWERED))
				blockCounter.setPoweredState(getBlockState(), level, worldPosition, true);
			else if (counter < maxCount && getBlockState().getValue(BlockStateProperties.POWERED))
				blockCounter.setPoweredState(getBlockState(), level, worldPosition, false);
			else
				level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 2);
		});
		setChanged();
	}

	public int getCounter()
	{
		return counter;
	}

	public int getMaxCount()
	{
		return maxCount;
	}

	public void setMaxCount(int maxCount)
	{
		if (maxCount >= 0)
		{
			this.maxCount = maxCount;
			count(0);
		}
		setChanged();
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
		maxCount = nbt.getInt("maxCount");
		counter = nbt.getInt("counter");
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt)
	{
		super.save(nbt);
		nbt.putInt("maxCount", maxCount);
		nbt.putInt("counter", counter);
		return nbt;
	}
}

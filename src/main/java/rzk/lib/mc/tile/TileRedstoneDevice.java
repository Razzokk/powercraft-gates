package rzk.lib.mc.tile;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import rzk.pcg.registry.ModTiles;

public class TileRedstoneDevice extends TileEntity implements ITileRedstoneStates
{
	private byte states;

	public TileRedstoneDevice()
	{
		super(ModTiles.REDSTONE_DEVICE.get());
	}

	public TileRedstoneDevice(TileEntityType<?> tileEntityType)
	{
		super(tileEntityType);
	}

	@Override
	public byte getRedstoneStates()
	{
		return states;
	}

	@Override
	public void setRedstoneStates(byte states)
	{
		this.states = states;
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt)
	{
		super.load(state, nbt);
		states = nbt.getByte("states");
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt)
	{
		super.save(nbt);
		nbt.putByte("states", states);
		return nbt;
	}
}

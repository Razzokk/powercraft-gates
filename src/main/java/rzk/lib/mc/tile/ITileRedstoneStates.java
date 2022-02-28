package rzk.lib.mc.tile;

import net.minecraft.util.Direction;

public interface ITileRedstoneStates
{
	byte getRedstoneStates();

	void setRedstoneStates(byte states);

	default boolean getRedstoneState(Direction side)
	{
		return (getRedstoneStates() & 1 << side.get3DDataValue()) >> side.get3DDataValue() == 1;
	}

	default void setRedstoneState(Direction side, boolean state)
	{
		byte states = getRedstoneStates();
		if (state)
			states |= 1 << side.get3DDataValue();
		else
			states &= ~(1 << side.get3DDataValue());
		setRedstoneStates(states);
	}
}

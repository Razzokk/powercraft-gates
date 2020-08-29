package rzk.pcg.packet;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;
import rzk.lib.mc.packet.Packet;
import rzk.lib.mc.util.Utils;
import rzk.pcg.tile.TileTimer;

import java.util.function.Supplier;

public class PacketTimer extends Packet
{
	private int delay;
	private BlockPos pos;

	public PacketTimer(int delay, BlockPos pos)
	{
		this.delay = delay;
		this.pos = pos;
	}

	PacketTimer(PacketBuffer buffer)
	{
		super(buffer);
		delay = buffer.readInt();
		pos = BlockPos.fromLong(buffer.readLong());
	}

	@Override
	public void toBytes(PacketBuffer buffer)
	{
		buffer.writeInt(delay);
		buffer.writeLong(pos.toLong());
	}

	@Override
	public void handle(Supplier<NetworkEvent.Context> ctx)
	{
		ctx.get().enqueueWork(() ->
		{
			ServerPlayerEntity player = ctx.get().getSender();
			ServerWorld world;
			if (player != null && (world = player.getServerWorld()).isBlockLoaded(pos))
			{
				Utils.getTile(world, pos, TileTimer.class).ifPresent(tile ->
				{
					tile.setDelay(delay);
					BlockState state = world.getBlockState(pos);
					world.notifyBlockUpdate(pos, state, state, 3);
				});
			}

		});
		ctx.get().setPacketHandled(true);
	}
}

package rzk.pcg.packet;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;
import rzk.lib.mc.packet.Packet;
import rzk.lib.mc.util.WorldUtils;
import rzk.pcg.tile.TileTimer;

import java.util.function.Supplier;

public class PacketTimer extends Packet
{
	private final int delay;
	private final BlockPos pos;

	public PacketTimer(int delay, BlockPos pos)
	{
		this.delay = delay;
		this.pos = pos;
	}

	PacketTimer(PacketBuffer buffer)
	{
		super(buffer);
		delay = buffer.readInt();
		pos = BlockPos.of(buffer.readLong());
	}

	@Override
	public void toBytes(PacketBuffer buffer)
	{
		buffer.writeInt(delay);
		buffer.writeLong(pos.asLong());
	}

	@Override
	public void handle(Supplier<NetworkEvent.Context> ctx)
	{
		ctx.get().enqueueWork(() ->
		{
			ServerPlayerEntity player = ctx.get().getSender();
			ServerWorld world;
			if (player != null && (world = player.getLevel()).isLoaded(pos))
				WorldUtils.ifTilePresent(world, pos, TileTimer.class, tile -> tile.setDelay(delay));
		});
		ctx.get().setPacketHandled(true);
	}
}

package rzk.pcg.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rzk.pcg.tile.TileCounter;

@OnlyIn(Dist.CLIENT)
public class CounterRenderer extends TileEntityRenderer<TileCounter>
{
	public CounterRenderer(TileEntityRendererDispatcher rendererDispatcher)
	{
		super(rendererDispatcher);
	}

	@Override
	public void render(TileCounter tile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay)
	{
		matrixStack.pushPose();
		FontRenderer fontrenderer = renderer.getFont();
		matrixStack.translate(0.5, 0.19, 0.5);
		matrixStack.mulPose(new Quaternion(270, 0, 180 - tile.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot(), true));
		matrixStack.scale(1f / 96, -1f / 96, 1f / 96);
		String s = tile.getCounter() + "/" + tile.getMaxCount();
		float f3 = (float) (-fontrenderer.width(s) / 2);
		fontrenderer.drawInBatch(s, f3, -4, 0, false, matrixStack.last().pose(), buffer, false, 0, combinedLight);
		matrixStack.popPose();
	}
}

package net.demo.client.renderer;

/* imports omitted */

public class UltraCreeperEntityRenderer extends MobRenderer<UltraCreeperEntity, CreeperRenderState, CreeperModel> {
	private UltraCreeperEntity entity = null;

	public UltraCreeperEntityRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(ModelLayers.CREEPER)), 0.5f);
		this.addLayer(new RenderLayer<>(this) {
			final ResourceLocation LAYER_TEXTURE = ResourceLocation.parse("demo_mod:textures/entities/ultracreeper_png.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, CreeperRenderState state, float headYaw, float headPitch) {
				VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(LAYER_TEXTURE));
				this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(state, 0));
			}
		});
	}

	@Override
	public CreeperRenderState createRenderState() {
		return new CreeperRenderState();
	}

	@Override
	public void extractRenderState(UltraCreeperEntity entity, CreeperRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
		if (this.model instanceof AnimatedModel) {
			((AnimatedModel) this.model).setEntity(entity);
		}
	}

	@Override
	public ResourceLocation getTextureLocation(CreeperRenderState state) {
		return ResourceLocation.parse("demo_mod:textures/entities/ultracreeper_png.png");
	}

	private static final class AnimatedModel extends CreeperModel {
		private UltraCreeperEntity entity = null;

		public AnimatedModel(ModelPart root) {
			super(root);
		}

		public void setEntity(UltraCreeperEntity entity) {
			this.entity = entity;
		}

		@Override
		public void setupAnim(CreeperRenderState state) {
			this.root().getAllParts().forEach(ModelPart::resetPose);
			this.animate(entity.animationState0, ArmadilloAnimation.ARMADILLO_PEEK, state.ageInTicks, 1f);
			super.setupAnim(state);
		}
	}
}

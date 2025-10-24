package net.kapitencraft.tutorial.client.model;// Made with Blockbench 5.0.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.kapitencraft.tutorial.TutorialMod;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class PaladinShieldModel extends Model {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(TutorialMod.res("paladin_shield"), "main");
	private final ModelPart plate;
	private final ModelPart handle;

	public PaladinShieldModel(ModelPart root) {
		super(RenderType::entitySolid);
		this.plate = root.getChild("plate");
		this.handle = root.getChild("handle");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition plate = partdefinition.addOrReplaceChild("plate", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -19.5F, -5.0F, 12.0F, 22.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-5.0F, 2.5F, -5.0F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 24).addBox(2.0F, -21.5F, -5.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(43, 0).addBox(-7.0F, -21.5F, -5.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 8.5F, 3.0F));

		PartDefinition handle = partdefinition.addOrReplaceChild("handle", CubeListBuilder.create().texOffs(26, 0).addBox(-1.0F, -27.0F, -1.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		plate.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		handle.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
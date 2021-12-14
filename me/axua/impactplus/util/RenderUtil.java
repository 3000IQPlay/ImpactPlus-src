//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.util;

import java.awt.Color;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public final class RenderUtil {
  private static final IntBuffer VIEWPORT = GLAllocation.createDirectIntBuffer(16);
  
  private static final FloatBuffer MODELVIEW = GLAllocation.createDirectFloatBuffer(16);
  
  private static final FloatBuffer PROJECTION = GLAllocation.createDirectFloatBuffer(16);
  
  public static void updateModelViewProjectionMatrix() {
    GL11.glGetFloat(2982, MODELVIEW);
    GL11.glGetFloat(2983, PROJECTION);
    GL11.glGetInteger(2978, VIEWPORT);
    ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
    GLUProjection.getInstance().updateMatrices(VIEWPORT, MODELVIEW, PROJECTION, (res.getScaledWidth() / (Minecraft.getMinecraft()).displayWidth), (res
        .getScaledHeight() / (Minecraft.getMinecraft()).displayHeight));
  }
  
  public static void DrawPolygon(double x, double y, int radius, int sides, int color) {
    GL11.glEnable(3042);
    GL11.glDisable(3553);
    GL11.glBlendFunc(770, 771);
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
    float alpha = (color >> 24 & 0xFF) / 255.0F;
    float red = (color >> 16 & 0xFF) / 255.0F;
    float green = (color >> 8 & 0xFF) / 255.0F;
    float blue = (color & 0xFF) / 255.0F;
    bufferbuilder.pos(x, y, 0.0D).endVertex();
    double TWICE_PI = 6.283185307179586D;
    for (int i = 0; i <= sides; i++) {
      double angle = 6.283185307179586D * i / sides + Math.toRadians(180.0D);
      bufferbuilder.pos(x + Math.sin(angle) * radius, y + Math.cos(angle) * radius, 0.0D).endVertex();
    } 
    tessellator.draw();
    GL11.glEnable(3553);
    GL11.glDisable(3042);
  }
  
  public static void drawRect(float x, float y, float w, float h, int color) {
    float alpha = (color >> 24 & 0xFF) / 255.0F;
    float red = (color >> 16 & 0xFF) / 255.0F;
    float green = (color >> 8 & 0xFF) / 255.0F;
    float blue = (color & 0xFF) / 255.0F;
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    GlStateManager.enableBlend();
    GlStateManager.disableTexture2D();
    GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
    bufferbuilder.pos(x, h, 0.0D).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(w, h, 0.0D).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(w, y, 0.0D).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(x, y, 0.0D).color(red, green, blue, alpha).endVertex();
    tessellator.draw();
    GlStateManager.enableTexture2D();
    GlStateManager.disableBlend();
  }
  
  public static void drawRect(float x, float y, float w, float h, int color, float alpha) {
    float red = (color >> 16 & 0xFF) / 255.0F;
    float green = (color >> 8 & 0xFF) / 255.0F;
    float blue = (color & 0xFF) / 255.0F;
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    GlStateManager.enableBlend();
    GlStateManager.disableTexture2D();
    GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
    bufferbuilder.pos(x, h, 0.0D).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(w, h, 0.0D).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(w, y, 0.0D).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(x, y, 0.0D).color(red, green, blue, alpha).endVertex();
    tessellator.draw();
    GlStateManager.enableTexture2D();
    GlStateManager.disableBlend();
  }
  
  public static void drawGradientRect(float left, float top, float right, float bottom, int startColor, int endColor) {
    float f = (startColor >> 24 & 0xFF) / 255.0F;
    float f1 = (startColor >> 16 & 0xFF) / 255.0F;
    float f2 = (startColor >> 8 & 0xFF) / 255.0F;
    float f3 = (startColor & 0xFF) / 255.0F;
    float f4 = (endColor >> 24 & 0xFF) / 255.0F;
    float f5 = (endColor >> 16 & 0xFF) / 255.0F;
    float f6 = (endColor >> 8 & 0xFF) / 255.0F;
    float f7 = (endColor & 0xFF) / 255.0F;
    GlStateManager.disableTexture2D();
    GlStateManager.enableBlend();
    GlStateManager.disableAlpha();
    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
    GlStateManager.shadeModel(7425);
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
    bufferbuilder.pos(right, top, 0.0D).color(f1, f2, f3, f).endVertex();
    bufferbuilder.pos(left, top, 0.0D).color(f1, f2, f3, f).endVertex();
    bufferbuilder.pos(left, bottom, 0.0D).color(f5, f6, f7, f4).endVertex();
    bufferbuilder.pos(right, bottom, 0.0D).color(f5, f6, f7, f4).endVertex();
    tessellator.draw();
    GlStateManager.shadeModel(7424);
    GlStateManager.disableBlend();
    GlStateManager.enableAlpha();
    GlStateManager.enableTexture2D();
  }
  
  public static void drawTriangle(float x, float y, float size, float theta, int color) {
    GL11.glTranslated(x, y, 0.0D);
    GL11.glRotatef(180.0F + theta, 0.0F, 0.0F, 1.0F);
    float alpha = (color >> 24 & 0xFF) / 255.0F;
    float red = (color >> 16 & 0xFF) / 255.0F;
    float green = (color >> 8 & 0xFF) / 255.0F;
    float blue = (color & 0xFF) / 255.0F;
    GL11.glColor4f(red, green, blue, alpha);
    GL11.glEnable(3042);
    GL11.glDisable(3553);
    GL11.glEnable(2848);
    GL11.glBlendFunc(770, 771);
    GL11.glLineWidth(1.0F);
    GL11.glBegin(6);
    GL11.glVertex2d(0.0D, (1.0F * size));
    GL11.glVertex2d((1.0F * size), -(1.0F * size));
    GL11.glVertex2d(-(1.0F * size), -(1.0F * size));
    GL11.glEnd();
    GL11.glDisable(2848);
    GL11.glEnable(3553);
    GL11.glDisable(3042);
    GL11.glRotatef(-180.0F - theta, 0.0F, 0.0F, 1.0F);
    GL11.glTranslated(-x, -y, 0.0D);
  }
  
  public static void drawOutlineRect(float x, float y, float w, float h, float thickness, int c) {
    drawRect(x, y, x - thickness, h, c);
    drawRect(w + thickness, y, w, h, c);
    drawRect(x, y, w, y - thickness, c);
    drawRect(x, h + thickness, w, h, c);
  }
  
  public static void drawLine(float x, float y, float x1, float y1, float thickness, int hex) {
    float red = (hex >> 16 & 0xFF) / 255.0F;
    float green = (hex >> 8 & 0xFF) / 255.0F;
    float blue = (hex & 0xFF) / 255.0F;
    float alpha = (hex >> 24 & 0xFF) / 255.0F;
    GlStateManager.pushMatrix();
    GlStateManager.disableTexture2D();
    GlStateManager.enableBlend();
    GlStateManager.disableAlpha();
    GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
    GlStateManager.shadeModel(7425);
    GL11.glLineWidth(thickness);
    GL11.glEnable(2848);
    GL11.glHint(3154, 4354);
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
    bufferbuilder.pos(x, y, 0.0D).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(x1, y1, 0.0D).color(red, green, blue, alpha).endVertex();
    tessellator.draw();
    GlStateManager.shadeModel(7424);
    GL11.glDisable(2848);
    GlStateManager.disableBlend();
    GlStateManager.enableAlpha();
    GlStateManager.enableTexture2D();
    GlStateManager.popMatrix();
  }
  
  public static void drawLine3D(float x, float y, float z, float x1, float y1, float z1, float thickness, int hex) {
    float red = (hex >> 16 & 0xFF) / 255.0F;
    float green = (hex >> 8 & 0xFF) / 255.0F;
    float blue = (hex & 0xFF) / 255.0F;
    float alpha = (hex >> 24 & 0xFF) / 255.0F;
    GlStateManager.pushMatrix();
    GlStateManager.disableTexture2D();
    GlStateManager.enableBlend();
    GlStateManager.disableAlpha();
    GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
    GlStateManager.shadeModel(7425);
    GL11.glLineWidth(thickness);
    GL11.glEnable(2848);
    GL11.glHint(3154, 4354);
    GlStateManager.disableDepth();
    GL11.glEnable(34383);
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    bufferbuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
    bufferbuilder.pos(x, y, z).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
    tessellator.draw();
    GlStateManager.shadeModel(7424);
    GL11.glDisable(2848);
    GlStateManager.enableDepth();
    GL11.glDisable(34383);
    GlStateManager.disableBlend();
    GlStateManager.enableAlpha();
    GlStateManager.enableTexture2D();
    GlStateManager.popMatrix();
  }
  
  public static void drawBoundingBox(AxisAlignedBB bb, float width, float red, float green, float blue, float alpha) {
    GlStateManager.pushMatrix();
    GlStateManager.enableBlend();
    GlStateManager.disableDepth();
    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
    GlStateManager.disableTexture2D();
    GlStateManager.depthMask(false);
    GL11.glEnable(2848);
    GL11.glHint(3154, 4354);
    GL11.glLineWidth(width);
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
    bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, 0.0F).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, 0.0F).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, 0.0F).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, 0.0F).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, 0.0F).endVertex();
    tessellator.draw();
    GL11.glDisable(2848);
    GlStateManager.depthMask(true);
    GlStateManager.enableDepth();
    GlStateManager.enableTexture2D();
    GlStateManager.disableBlend();
    GlStateManager.popMatrix();
  }
  
  public static void drawBoundingBox(AxisAlignedBB bb, float width, int color) {
    float alpha = (color >> 24 & 0xFF) / 255.0F;
    float red = (color >> 16 & 0xFF) / 255.0F;
    float green = (color >> 8 & 0xFF) / 255.0F;
    float blue = (color & 0xFF) / 255.0F;
    drawBoundingBox(bb, width, red, green, blue, alpha);
  }
  
  public static void drawPlane(double x, double y, double z, AxisAlignedBB bb, float width, int color) {
    GL11.glPushMatrix();
    GL11.glTranslated(x, y, z);
    drawPlane(bb, width, color);
    GL11.glPopMatrix();
  }
  
  public static void drawPlane(AxisAlignedBB axisalignedbb, float width, int color) {
    GlStateManager.pushMatrix();
    GlStateManager.glLineWidth(width);
    GlStateManager.enableBlend();
    GlStateManager.disableDepth();
    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
    GlStateManager.disableTexture2D();
    GlStateManager.depthMask(false);
    GL11.glEnable(2848);
    GL11.glHint(3154, 4354);
    drawPlane(axisalignedbb, color);
    GL11.glDisable(2848);
    GlStateManager.depthMask(true);
    GlStateManager.enableDepth();
    GlStateManager.enableTexture2D();
    GlStateManager.disableBlend();
    GlStateManager.popMatrix();
  }
  
  public static void drawPlane(AxisAlignedBB boundingBox, int color) {
    float alpha = (color >> 24 & 0xFF) / 255.0F;
    float red = (color >> 16 & 0xFF) / 255.0F;
    float green = (color >> 8 & 0xFF) / 255.0F;
    float blue = (color & 0xFF) / 255.0F;
    double minX = boundingBox.minX;
    double minY = boundingBox.minY;
    double minZ = boundingBox.minZ;
    double maxX = boundingBox.maxX;
    double maxY = boundingBox.maxY;
    double maxZ = boundingBox.maxZ;
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
    bufferbuilder.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(minX, minY, maxZ).color(red, green, blue, 0.0F).endVertex();
    bufferbuilder.pos(maxZ, minY, minZ).color(red, green, blue, alpha).endVertex();
    tessellator.draw();
  }
  
  public static void drawFilledBox(AxisAlignedBB bb, int color) {
    GlStateManager.pushMatrix();
    GlStateManager.enableBlend();
    GlStateManager.disableDepth();
    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
    GlStateManager.disableTexture2D();
    GlStateManager.depthMask(false);
    float alpha = (color >> 24 & 0xFF) / 255.0F;
    float red = (color >> 16 & 0xFF) / 255.0F;
    float green = (color >> 8 & 0xFF) / 255.0F;
    float blue = (color & 0xFF) / 255.0F;
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
    bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
    tessellator.draw();
    GlStateManager.depthMask(true);
    GlStateManager.enableDepth();
    GlStateManager.enableTexture2D();
    GlStateManager.disableBlend();
    GlStateManager.popMatrix();
  }
  
  public static void glScissor(float x, float y, float x1, float y1, ScaledResolution sr) {
    GL11.glScissor((int)(x * sr.getScaleFactor()), (int)((Minecraft.getMinecraft()).displayHeight - y1 * sr.getScaleFactor()), (int)((x1 - x) * sr.getScaleFactor()), 
        (int)((y1 - y) * sr.getScaleFactor()));
  }
  
  public static void glBillboard(float x, float y, float z) {
    float scale = 0.02666667F;
    GlStateManager.translate(x - (Minecraft.getMinecraft().getRenderManager()).renderPosX, y - (Minecraft.getMinecraft().getRenderManager()).renderPosY, z - 
        (Minecraft.getMinecraft().getRenderManager()).renderPosZ);
    GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
    GlStateManager.rotate(-(Minecraft.getMinecraft()).player.rotationYaw, 0.0F, 1.0F, 0.0F);
    GlStateManager.rotate((Minecraft.getMinecraft()).player.rotationPitch, ((Minecraft.getMinecraft()).gameSettings.thirdPersonView == 2) ? -1.0F : 1.0F, 0.0F, 0.0F);
    GlStateManager.scale(-scale, -scale, scale);
  }
  
  public static void glBillboardDistanceScaled(float x, float y, float z, EntityPlayer player, float scale) {
    glBillboard(x, y, z);
    int distance = (int)player.getDistance(x, y, z);
    float scaleDistance = distance / 2.0F / (2.0F + 2.0F - scale);
    if (scaleDistance < 1.0F)
      scaleDistance = 1.0F; 
    GlStateManager.scale(scaleDistance, scaleDistance, scaleDistance);
  }
  
  public static void drawTexture(float x, float y, float textureX, float textureY, float width, float height) {
    float f = 0.00390625F;
    float f1 = 0.00390625F;
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
    bufferbuilder.pos(x, (y + height), 0.0D).tex((textureX * f), ((textureY + height) * f1)).endVertex();
    bufferbuilder.pos((x + width), (y + height), 0.0D).tex(((textureX + width) * f), ((textureY + height) * f1)).endVertex();
    bufferbuilder.pos((x + width), y, 0.0D).tex(((textureX + width) * f), (textureY * f1)).endVertex();
    bufferbuilder.pos(x, y, 0.0D).tex((textureX * f), (textureY * f1)).endVertex();
    tessellator.draw();
  }
  
  public static void drawTexture(float x, float y, float width, float height, float u, float v, float t, float s) {
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    bufferbuilder.begin(4, DefaultVertexFormats.POSITION_TEX);
    bufferbuilder.pos((x + width), y, 0.0D).tex(t, v).endVertex();
    bufferbuilder.pos(x, y, 0.0D).tex(u, v).endVertex();
    bufferbuilder.pos(x, (y + height), 0.0D).tex(u, s).endVertex();
    bufferbuilder.pos(x, (y + height), 0.0D).tex(u, s).endVertex();
    bufferbuilder.pos((x + width), (y + height), 0.0D).tex(t, s).endVertex();
    bufferbuilder.pos((x + width), y, 0.0D).tex(t, v).endVertex();
    tessellator.draw();
  }
  
  public static final void DrawNodusBetterRect(double x, double y, double x1, double y1, int color2, int color) {
    GL11.glEnable(3042);
    GL11.glEnable(2848);
    drawRect((int)x, (int)y, (int)x1, (int)y1, color);
    GL11.glScalef(0.5F, 0.5F, 0.5F);
    drawRect(((int)x * 2 - 1), ((int)y * 2), ((int)x * 2), ((int)y1 * 2 - 1), color2);
    drawRect(((int)x * 2), ((int)y * 2 - 1), ((int)x1 * 2), ((int)y * 2), color2);
    drawRect(((int)x1 * 2), ((int)y * 2), ((int)x1 * 2 + 1), ((int)y1 * 2 - 1), color2);
    drawRect(((int)x * 2), ((int)y1 * 2 - 1), ((int)x1 * 2), ((int)y1 * 2), color2);
    GL11.glDisable(3042);
    GL11.glScalef(2.0F, 2.0F, 2.0F);
  }
  
  public static final void DrawNodusRect(float par0, float par1, float par2, float par3, int par4) {
    if (par0 < par2) {
      float var5 = par0;
      par0 = par2;
      par2 = var5;
    } 
    if (par1 < par3) {
      float var5 = par1;
      par1 = par3;
      par3 = var5;
    } 
    float var10 = (par4 >> 24 & 0xFF) / 255.0F;
    float var6 = (par4 >> 16 & 0xFF) / 255.0F;
    float var7 = (par4 >> 8 & 0xFF) / 255.0F;
    float var8 = (par4 & 0xFF) / 255.0F;
    Tessellator tessellator = Tessellator.getInstance();
    GL11.glEnable(3042);
    GL11.glDisable(3553);
    GL11.glDisable(2896);
    OpenGlHelper.glBlendFunc(770, 771, 1, 0);
    GL11.glColor4f(var6, var7, var8, var10);
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
    bufferbuilder.pos(par0, par3, 0.0D).color(var6, var7, var8, var10).endVertex();
    bufferbuilder.pos(par2, par3, 0.0D).color(var6, var7, var8, var10).endVertex();
    bufferbuilder.pos(par2, par1, 0.0D).color(var6, var7, var8, var10).endVertex();
    bufferbuilder.pos(par0, par1, 0.0D).color(var6, var7, var8, var10).endVertex();
    tessellator.draw();
    GL11.glEnable(3553);
    GL11.glDisable(3042);
  }
  
  public static void drawBorderedRect(int x, int y, int x1, int y1, int color, float lineWidth, int color1) {
    drawRect(x, y, x1, y1, color);
    setupOverlayRendering();
    disableDefaults();
    GL11.glColor4d(getRedFromHex(color1), getGreenFromHex(color1), getBlueFromHex(color1), getAlphaFromHex(color1));
    GL11.glLineWidth(lineWidth);
    GL11.glBegin(1);
    GL11.glVertex2d(x, y);
    GL11.glVertex2d(x, y1);
    GL11.glVertex2d(x1, y1);
    GL11.glVertex2d(x1, y);
    GL11.glVertex2d(x, y);
    GL11.glVertex2d(x1, y);
    GL11.glVertex2d(x, y1);
    GL11.glVertex2d(x1, y1);
    GL11.glEnd();
    enableDefaults();
  }
  
  public static void drawRect(int x, int y, int x1, int y1, int color, int p_CustomAlpha) {
    setupOverlayRendering();
    disableDefaults();
    GL11.glColor4d(getRedFromHex(color), getGreenFromHex(color), getBlueFromHex(color), (p_CustomAlpha > 0) ? p_CustomAlpha : getAlphaFromHex(color));
    GL11.glBegin(7);
    GL11.glVertex2i(x1, y);
    GL11.glVertex2i(x, y);
    GL11.glVertex2i(x, y1);
    GL11.glVertex2i(x1, y1);
    GL11.glEnd();
    enableDefaults();
  }
  
  public static void drawRoundedRect(int x, int y, int x1, int y1, int radius, int color, int p_CustomAlpha) {
    disableDefaults();
    float newX = Math.abs(x + radius);
    float newY = Math.abs(y + radius);
    float newX1 = Math.abs(x1 - radius);
    float newY1 = Math.abs(y1 - radius);
    drawRect(newX, newY, newX1, newY1, color);
    drawRect(x, newY, newX, newY1, color);
    drawRect(newX1, newY, x1, newY1, color);
    drawRect(newX, y, newX1, newY, color);
    drawRect(newX, newY1, newX1, y1, color);
    drawQuarterCircle((int)newX, (int)newY, radius, 0, color, p_CustomAlpha);
    drawQuarterCircle((int)newX1, (int)newY, radius, 1, color, p_CustomAlpha);
    drawQuarterCircle((int)newX, (int)newY1, radius, 2, color, p_CustomAlpha);
    drawQuarterCircle((int)newX1, (int)newY1, radius, 3, color, p_CustomAlpha);
    enableDefaults();
  }
  
  public static void drawLine2D(int x, int y, int x1, int y1, int color, float lineWidth) {
    setupOverlayRendering();
    disableDefaults();
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(2848);
    GL11.glColor4d(getRedFromHex(color), getGreenFromHex(color), getBlueFromHex(color), getAlphaFromHex(color));
    GL11.glBegin(1);
    GL11.glVertex2i(x, y);
    GL11.glVertex2i(x1, y1);
    GL11.glEnd();
    GL11.glDisable(2848);
    enableDefaults();
  }
  
  public static void drawBorderedCircle(int x, int y, int radius, int color, float lineWidth, int color1) {
    drawCircle(x, y, radius, color);
    drawUnfilledCircle(x, y, radius, lineWidth, color1);
  }
  
  public static void drawUnfilledCircle(int x, int y, int radius, float lineWidth, int color) {
    setupOverlayRendering();
    disableDefaults();
    GL11.glColor4d(getRedFromHex(color), getGreenFromHex(color), getBlueFromHex(color), getAlphaFromHex(color));
    GL11.glLineWidth(lineWidth);
    GL11.glEnable(2848);
    GL11.glBegin(2);
    for (int i = 0; i <= 360; i++)
      GL11.glVertex2d(x + Math.sin(i * 3.141526D / 180.0D) * radius, y + Math.cos(i * 3.141526D / 180.0D) * radius); 
    GL11.glEnd();
    GL11.glDisable(2848);
    enableDefaults();
  }
  
  public static void drawCircle(int x, int y, int radius, int color) {
    setupOverlayRendering();
    disableDefaults();
    GL11.glColor4d(getRedFromHex(color), getGreenFromHex(color), getBlueFromHex(color), getAlphaFromHex(color));
    GL11.glBegin(9);
    for (int i = 0; i <= 360; i++)
      GL11.glVertex2d(x + Math.sin(i * 3.141526D / 180.0D) * radius, y + Math.cos(i * 3.141526D / 180.0D) * radius); 
    GL11.glEnd();
    enableDefaults();
  }
  
  public static void drawQuarterCircle(int x, int y, int radius, int mode, int color, int p_CustomAlpha) {
    disableDefaults();
    GL11.glColor4d(getRedFromHex(color), getGreenFromHex(color), getBlueFromHex(color), (p_CustomAlpha > 0) ? p_CustomAlpha : getAlphaFromHex(color));
    GL11.glBegin(9);
    GL11.glVertex2d(x, y);
    if (mode == 0) {
      for (int i = 0; i <= 90; i++)
        GL11.glVertex2d(x + Math.sin(i * 3.141526D / 180.0D) * (radius * -1), y + Math.cos(i * 3.141526D / 180.0D) * (radius * -1)); 
    } else if (mode == 1) {
      for (int i = 90; i <= 180; i++)
        GL11.glVertex2d(x + Math.sin(i * 3.141526D / 180.0D) * radius, y + Math.cos(i * 3.141526D / 180.0D) * radius); 
    } else if (mode == 2) {
      for (int i = 90; i <= 180; i++)
        GL11.glVertex2d(x + Math.sin(i * 3.141526D / 180.0D) * (radius * -1), y + Math.cos(i * 3.141526D / 180.0D) * (radius * -1)); 
    } else if (mode == 3) {
      for (int i = 0; i <= 90; i++)
        GL11.glVertex2d(x + Math.sin(i * 3.141526D / 180.0D) * radius, y + Math.cos(i * 3.141526D / 180.0D) * radius); 
    } 
    GL11.glEnd();
    enableDefaults();
  }
  
  public static double getAlphaFromHex(int color) {
    return ((color >> 24 & 0xFF) / 255.0F);
  }
  
  public static double getRedFromHex(int color) {
    return ((color >> 16 & 0xFF) / 255.0F);
  }
  
  public static double getGreenFromHex(int color) {
    return ((color >> 8 & 0xFF) / 255.0F);
  }
  
  public static double getBlueFromHex(int color) {
    return ((color & 0xFF) / 255.0F);
  }
  
  public static int getScreenWidth() {
    IntBuffer viewport = BufferUtils.createIntBuffer(16);
    GL11.glGetInteger(2978, viewport);
    return Math.round(viewport.get(2));
  }
  
  public static int getScreenHeight() {
    IntBuffer viewport = BufferUtils.createIntBuffer(16);
    GL11.glGetInteger(2978, viewport);
    return Math.round(viewport.get(3));
  }
  
  public static void setupGradient() {
    GL11.glDisable(3553);
    GL11.glEnable(3042);
    GL11.glDisable(3008);
    GL11.glShadeModel(7425);
  }
  
  public static void unsetupGradient() {
    GL11.glShadeModel(7424);
    GL11.glDisable(3042);
    GL11.glEnable(3008);
    GL11.glEnable(3553);
  }
  
  public static void setupOverlayRendering() {
    GL11.glClear(256);
    GL11.glMatrixMode(5889);
    GL11.glLoadIdentity();
    GL11.glOrtho(0.0D, getScreenWidth(), getScreenHeight(), 0.0D, 1000.0D, 3000.0D);
    GL11.glMatrixMode(5888);
    GL11.glLoadIdentity();
    GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
  }
  
  public static void disableDefaults() {
    GL11.glEnable(3042);
    GL11.glDisable(2896);
    GL11.glDisable(3553);
  }
  
  public static void enableDefaults() {
    GL11.glDisable(3042);
    GL11.glEnable(3553);
    GL11.glEnable(2896);
  }
  
  public static void disableLighting() {
    GL11.glDisable(2896);
  }
  
  public static String trimStringToWidth(String substring, int width) {
    return (Minecraft.getMinecraft()).fontRenderer.trimStringToWidth(substring, width);
  }
  
  public static String trimStringToWidth(String text, int j, boolean b) {
    return (Minecraft.getMinecraft()).fontRenderer.trimStringToWidth(text, j, b);
  }
  
  public static void drawColorBox(AxisAlignedBB axisalignedbb, float red, float green, float blue, float alpha) {
    Tessellator ts = Tessellator.getInstance();
    BufferBuilder vb = ts.getBuffer();
    vb.begin(7, DefaultVertexFormats.POSITION_TEX);
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    ts.draw();
    vb.begin(7, DefaultVertexFormats.POSITION_TEX);
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    ts.draw();
    vb.begin(7, DefaultVertexFormats.POSITION_TEX);
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    ts.draw();
    vb.begin(7, DefaultVertexFormats.POSITION_TEX);
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    ts.draw();
    vb.begin(7, DefaultVertexFormats.POSITION_TEX);
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    ts.draw();
    vb.begin(7, DefaultVertexFormats.POSITION_TEX);
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
    ts.draw();
  }
  
  public static void drawColorBox(AxisAlignedBB axisalignedbb, Color c) {
    Tessellator ts = Tessellator.getInstance();
    BufferBuilder vb = ts.getBuffer();
    vb.begin(7, DefaultVertexFormats.POSITION_TEX);
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    ts.draw();
    vb.begin(7, DefaultVertexFormats.POSITION_TEX);
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    ts.draw();
    vb.begin(7, DefaultVertexFormats.POSITION_TEX);
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    ts.draw();
    vb.begin(7, DefaultVertexFormats.POSITION_TEX);
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    ts.draw();
    vb.begin(7, DefaultVertexFormats.POSITION_TEX);
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    ts.draw();
    vb.begin(7, DefaultVertexFormats.POSITION_TEX);
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    ts.draw();
  }
}

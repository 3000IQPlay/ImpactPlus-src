//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class PlayerUtil {
  private static Minecraft mc = Minecraft.getMinecraft();
  
  public static int GetItemSlot(Item input) {
    if (mc.player == null)
      return 0; 
    for (int i = 0; i < mc.player.inventoryContainer.getInventory().size(); i++) {
      if (i != 0 && i != 5 && i != 6 && i != 7 && i != 8) {
        ItemStack s = (ItemStack)mc.player.inventoryContainer.getInventory().get(i);
        if (!s.isEmpty())
          if (s.getItem() == input)
            return i;  
      } 
    } 
    return -1;
  }
  
  public static int GetItemSlotNotHotbar(Item input) {
    if (mc.player == null)
      return 0; 
    for (int i = 9; i < 36; i++) {
      Item item = mc.player.inventory.getStackInSlot(i).getItem();
      if (item == input)
        return i; 
    } 
    return -1;
  }
  
  public static int GetItemCount(Item input) {
    if (mc.player == null)
      return 0; 
    int items = 0;
    for (int i = 0; i < 45; i++) {
      ItemStack stack = mc.player.inventory.getStackInSlot(i);
      if (stack.getItem() == input)
        items += stack.getCount(); 
    } 
    return items;
  }
  
  public static boolean CanSeeBlock(BlockPos p_Pos) {
    if (mc.player == null)
      return false; 
    return (mc.world.rayTraceBlocks(new Vec3d(mc.player.posX, mc.player.posY + mc.player.getEyeHeight(), mc.player.posZ), new Vec3d(p_Pos.getX(), p_Pos.getY(), p_Pos.getZ()), false, true, false) == null);
  }
  
  public static boolean isCurrentViewEntity() {
    return (mc.getRenderViewEntity() == mc.player);
  }
  
  public static boolean IsEating() {
    return (mc.player != null && mc.player.getHeldItemMainhand().getItem() instanceof net.minecraft.item.ItemFood && mc.player.isHandActive());
  }
  
  public static int GetItemInHotbar(Item p_Item) {
    for (int l_I = 0; l_I < 9; l_I++) {
      ItemStack l_Stack = mc.player.inventory.getStackInSlot(l_I);
      if (l_Stack != ItemStack.EMPTY)
        if (l_Stack.getItem() == p_Item)
          return l_I;  
    } 
    return -1;
  }
  
  public static BlockPos GetLocalPlayerPosFloored() {
    return new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ));
  }
  
  public static void PacketFacePitchAndYaw(float p_Pitch, float p_Yaw) {
    boolean l_IsSprinting = mc.player.isSprinting();
    if (l_IsSprinting != mc.player.serverSprintState) {
      if (l_IsSprinting) {
        mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)mc.player, CPacketEntityAction.Action.START_SPRINTING));
      } else {
        mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
      } 
      mc.player.serverSprintState = l_IsSprinting;
    } 
    boolean l_IsSneaking = mc.player.isSneaking();
    if (l_IsSneaking != mc.player.serverSneakState) {
      if (l_IsSneaking) {
        mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)mc.player, CPacketEntityAction.Action.START_SNEAKING));
      } else {
        mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
      } 
      mc.player.serverSneakState = l_IsSneaking;
    } 
    if (isCurrentViewEntity()) {
      float l_Pitch = p_Pitch;
      float l_Yaw = p_Yaw;
      AxisAlignedBB axisalignedbb = mc.player.getEntityBoundingBox();
      double l_PosXDifference = mc.player.posX - mc.player.lastReportedPosX;
      double l_PosYDifference = axisalignedbb.minY - mc.player.lastReportedPosY;
      double l_PosZDifference = mc.player.posZ - mc.player.lastReportedPosZ;
      double l_YawDifference = (l_Yaw - mc.player.lastReportedYaw);
      double l_RotationDifference = (l_Pitch - mc.player.lastReportedPitch);
      mc.player.positionUpdateTicks++;
      boolean l_MovedXYZ = (l_PosXDifference * l_PosXDifference + l_PosYDifference * l_PosYDifference + l_PosZDifference * l_PosZDifference > 9.0E-4D || mc.player.positionUpdateTicks >= 20);
      boolean l_MovedRotation = (l_YawDifference != 0.0D || l_RotationDifference != 0.0D);
      if (mc.player.isRiding()) {
        mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(mc.player.motionX, -999.0D, mc.player.motionZ, l_Yaw, l_Pitch, mc.player.onGround));
        l_MovedXYZ = false;
      } else if (l_MovedXYZ && l_MovedRotation) {
        mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(mc.player.posX, axisalignedbb.minY, mc.player.posZ, l_Yaw, l_Pitch, mc.player.onGround));
      } else if (l_MovedXYZ) {
        mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(mc.player.posX, axisalignedbb.minY, mc.player.posZ, mc.player.onGround));
      } else if (l_MovedRotation) {
        mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(l_Yaw, l_Pitch, mc.player.onGround));
      } else if (mc.player.prevOnGround != mc.player.onGround) {
        mc.player.connection.sendPacket((Packet)new CPacketPlayer(mc.player.onGround));
      } 
      if (l_MovedXYZ) {
        mc.player.lastReportedPosX = mc.player.posX;
        mc.player.lastReportedPosY = axisalignedbb.minY;
        mc.player.lastReportedPosZ = mc.player.posZ;
        mc.player.positionUpdateTicks = 0;
      } 
      if (l_MovedRotation) {
        mc.player.lastReportedYaw = l_Yaw;
        mc.player.lastReportedPitch = l_Pitch;
      } 
      mc.player.prevOnGround = mc.player.onGround;
      mc.player.autoJumpEnabled = mc.player.mc.gameSettings.autoJump;
    } 
  }
}

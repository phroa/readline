package net.phroa.readline.mixin;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.phroa.readline.Binding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GuiTextField.class)
public abstract class MixinGuiTextField {
    @Inject(method = "textboxKeyTyped", at = @At(value = "HEAD"), cancellable = true)
    private void textboxKeyTyped(char typedChar, int keyCode, CallbackInfoReturnable<Boolean> cir) {
        for (Binding binding : Binding.values()) {
            if (binding.key == keyCode) {
                if (binding.control && !GuiScreen.isCtrlKeyDown()) {
                    continue;
                }
                if (binding.meta && !GuiScreen.isAltKeyDown()) {
                    continue;
                }

                binding.action.accept((GuiTextField) (Object) this);
                cir.setReturnValue(true);
                return;
            }
        }
    }
}

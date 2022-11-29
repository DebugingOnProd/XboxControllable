package org.lhq.controller;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lhq.utils.function.PairPredicate;
import org.lhq.utils.function.Predicates;

import java.util.*;


public class ButtonBinding {

    private int[] button;
    private final int[] defaultButton;
    private final String key;
    private final Text text;
    private KeyBinding mcKeyBinding = null;
    protected PairPredicate<MinecraftClient, ButtonBinding> filter;
    private final List<PressAction> actions = new ArrayList<>(Collections.singletonList(PressAction.DEFAULT_ACTION));
    private boolean hasCooldown;
    private int cooldownLength = 5;
    private int cooldown = 0;
    boolean pressed = false;




    public ButtonBinding(String key, int[] defaultButton, List<PressAction> actions, PairPredicate<MinecraftClient, ButtonBinding> filter, boolean hasCooldown) {
        this.setButton(this.defaultButton = defaultButton);
        this.key = key;
        this.text = Text.translatable(this.key);
        this.filter = filter;
        this.actions.addAll(actions);
        this.hasCooldown = hasCooldown;
    }
    public ButtonBinding(String key, int[] defaultButton, List<PressAction> actions, PairPredicate<MinecraftClient, ButtonBinding> filter, boolean hasCooldown, int cooldownLength) {
        this.setButton(this.defaultButton = defaultButton);
        this.key = key;
        this.text = Text.translatable(this.key);
        this.filter = filter;
        this.actions.addAll(actions);
        this.hasCooldown = hasCooldown;
        this.cooldownLength = cooldownLength;
    }

    public int[] getButton() {
        return this.button;
    }

    /**
     * 设置绑定按钮
     *
     * @param button the bound button
     */
    public void setButton(int[] button) {
        this.button = button;

        if (InputManager.hasBinding(this))
            InputManager.sortBindings();
    }

    public ButtonBinding(String key, int[] defaultButton, boolean hasCooldown) {
        this(key, defaultButton, Collections.emptyList(), Predicates.pairAlwaysTrue(), hasCooldown);
    }
    public ButtonBinding(String key, int[] defaultButton, boolean hasCooldown, int cooldownLength) {
        this(key, defaultButton, Collections.emptyList(), Predicates.pairAlwaysTrue(), hasCooldown, cooldownLength);
    }




    public static class Builder {
        private final String key;
        private int[] buttons = new int[0];
        private final List<PressAction> actions = new ArrayList<>();
        private PairPredicate<MinecraftClient, ButtonBinding> filter = Predicates.pairAlwaysTrue();
        private boolean cooldown = false;
        private int cooldownLength = 5;
        private ButtonCategory category = null;
        private KeyBinding mcBinding = null;

        /**
         * 这个构造方法不应该被其他mod使用
         * This constructor shouldn't be used for other mods.
         *
         * @param key the key with format {@code "<namespace>.<name>"}
         */
        public Builder(@NotNull String key) {
            this.key = key;
            this.unbound();
        }

        public Builder(@NotNull Identifier identifier) {
            this(identifier.getNamespace() + "." + identifier.getPath());
        }

        /**
         * 定义默认按键绑定
         * Defines the default buttons of the {@link ButtonBinding}.
         *
         * @param buttons the default buttons
         * @return the builder instance
         */
        public Builder buttons(int... buttons) {
            this.buttons = buttons;
            return this;
        }

        /**
         * 解绑
         * Sets the {@link ButtonBinding} to unbound.
         *
         * @return the builder instance
         */
        public Builder unbound() {
            return this.buttons(-1);
        }

        /**
         * 给按钮添加动作
         * Adds the actions to the {@link ButtonBinding}.
         *
         * @param actions the actions to add
         * @return the builder instance
         */
        public Builder actions(@NotNull PressAction... actions) {
            this.actions.addAll(Arrays.asList(actions));
            return this;
        }

        /**
         * 给按钮添加动作
         * Adds an action to the {@link ButtonBinding}.
         *
         * @param action the action to add
         * @return the builder instance
         */
        public Builder action(@NotNull PressAction action) {
            this.actions.add(action);
            return this;
        }

        /**
         * 设置过滤器
         * Sets a filter for the {@link ButtonBinding}.
         *
         * @param filter the filter
         * @return the builder instance
         */
        public Builder filter(@NotNull PairPredicate<MinecraftClient, ButtonBinding> filter) {
            this.filter = filter;
            return this;
        }

        /**
         * 设置过滤器
         * Sets the filter of {@link ButtonBinding} to only in game.
         *
         * @return the builder instance
         * @see #filter(PairPredicate)
         * @see InputHandlers#inGame(MinecraftClient, ButtonBinding)
         */
        public Builder onlyInGame() {
            return this.filter(InputHandlers::inGame);
        }

        /**
         * 设置过滤器
         * Sets the filter of {@link ButtonBinding} to only in inventory.
         *
         * @return the builder instance
         * @see #filter(PairPredicate)
         * @see InputHandlers#inInventory(MinecraftClient, ButtonBinding)
         */
        public Builder onlyInInventory() {
            return this.filter(InputHandlers::inInventory);
        }

        /**
         * 设置是否冷却
         * Sets whether the {@link ButtonBinding} has a cooldown or not.
         *
         * @param cooldown true if the {@link ButtonBinding} has a cooldown, else false
         * @return the builder instance
         */
        public Builder cooldown(boolean cooldown) {
            this.cooldown = cooldown;
            return this;
        }
        /**
         * 设置冷却时间
         * Sets the cooldown enabled with a custom duration for {@link ButtonBinding}.
         *
         * @param cooldownLength duration of {@link ButtonBinding} cooldown
         * @return the builder instance
         */
        public Builder cooldown(int cooldownLength) {
            this.cooldownLength = cooldownLength;
            this.cooldown = true;
            return this;
        }

        /**
         * 设置冷却
         * Puts a cooldown on the {@link ButtonBinding}.
         *
         * @return the builder instance
         * @since 1.5.0
         */
        public Builder cooldown() {
            return this.cooldown(true);
        }

        /**
         * 设置按钮类型
         * Sets the category of the {@link ButtonBinding}.
         *
         * @param category the category
         * @return the builder instance
         */
        public Builder category(@Nullable ButtonCategory category) {
            this.category = category;
            return this;
        }

        /**
         * 设置绑定
         * Sets the keybinding linked to the {@link ButtonBinding}.
         *
         * @param binding the keybinding to link
         * @return the builder instance
         */
        public Builder linkKeybind(@Nullable KeyBinding binding) {
            this.mcBinding = binding;
            return this;
        }

        /**
         * 构建按钮
         * Builds the {@link ButtonBinding}.
         *
         * @return the built {@link ButtonBinding}
         */
        public ButtonBinding build() {
            var binding = new ButtonBinding(this.key, this.buttons, this.actions, this.filter, this.cooldown, this.cooldownLength);
            if (this.category != null)
                this.category.registerBinding(binding);
            if (this.mcBinding != null)
                binding.setKeyBinding(this.mcBinding);
            return binding;
        }

        /**
         * Builds and registers the {@link ButtonBinding}.
         *
         * @return the built {@link ButtonBinding}
         * @see #build()
         */
        public ButtonBinding register() {
            return InputManager.registerBinding(this.build());
        }
    }

    public void setKeyBinding(@Nullable KeyBinding keyBinding) {
        this.mcKeyBinding = keyBinding;
    }





    public  Optional<KeyBinding> asKeyBinding() {
        return Optional.ofNullable(this.mcKeyBinding);
    }

    /**
     * 返回这个按钮是否被按下
     * @return
     */
    public boolean isButtonDown() {
        return this.pressed;
    }
}

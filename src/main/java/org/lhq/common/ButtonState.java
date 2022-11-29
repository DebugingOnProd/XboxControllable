package org.lhq.common;

public enum ButtonState {
    /**
     * 无状态
     */
    NONE(0),
    /**
     * 按下
     */
    PRESS(1),
    /**
     * 松开
     */
    RELEASE(2),
    /**
     * 重复
     */
    REPEAT(3);
    public final int id;

    ButtonState(int id) {
        this.id = id;
    }

    public boolean isPressed() {
        return this == PRESS || this == REPEAT;
    }

    public boolean isUnpressed() {
            return this == RELEASE || this == NONE;
    }


}

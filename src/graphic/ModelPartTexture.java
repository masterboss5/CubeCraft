package graphic;

import texture.Texture;

import static main.Main.TEXTURE_MANAGER;

public record ModelPartTexture(
        Texture top,
        long topPointer,
        Texture bottom,
        long bottomPointer,
        Texture front,
        long frontPointer,
        Texture back,
        long backPointer,
        Texture left,
        long leftPointer,
        Texture right,
        long rightPointer
) {
    public ModelPartTexture(Texture texture) {
        this(texture, texture, texture, texture, texture, texture);
    }

    public ModelPartTexture(Texture top, Texture bottom, Texture front, Texture back, Texture left, Texture right) {
        this(top, TEXTURE_MANAGER.registerOrGet(top),
             bottom, TEXTURE_MANAGER.registerOrGet(bottom),
             front, TEXTURE_MANAGER.registerOrGet(front),
             back, TEXTURE_MANAGER.registerOrGet(back),
             left, TEXTURE_MANAGER.registerOrGet(left),
             right, TEXTURE_MANAGER.registerOrGet(right));

/*        System.out.println(topPointer);
        System.out.println(bottomPointer);
        System.out.println(frontPointer);
        System.out.println(backPointer);
        System.out.println(leftPointer);
        System.out.println(rightPointer);*/
    }

    public long[] toLongArray() {
        return new long[] {
                topPointer,
                bottomPointer,
                frontPointer,
                backPointer,
                leftPointer,
                rightPointer
        };
    }
}

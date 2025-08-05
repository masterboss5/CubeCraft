package gpu;

public class GPUInfo {
    private final String name;
    private final Vendor vendor;
    private final int maxTextureSize;
    private final int vramMB;
    private int usage = 0;

    public GPUInfo(String name, Vendor vendor, int maxTextureSize, int vramMB) {
        this.name = name;
        this.vendor = vendor;
        this.maxTextureSize = maxTextureSize;
        this.vramMB = vramMB;
    }
}

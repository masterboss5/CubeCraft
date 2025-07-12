package gpu;

public enum Vendor {
    NVIDIA("nvidia"),
    AMD("amd"),
    INTEL("intel"),
    APPLE_INTEL("apple_intel"),
    APPLE_SILICONE("apple_silicon"),
    UNKNOWN("unknown");

    private final String name;

    Vendor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

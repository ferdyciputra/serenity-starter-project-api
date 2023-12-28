package enums;

public enum PathApi {

    CreateUsers("/api/users"),
    SingleUsers("/api/users");

    private final String path;

    PathApi(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return this.path;
    }
}

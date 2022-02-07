package personal.opensrcerer.duplex.payloads.records;

public record Result(Response res, Error err) {

    public Result(Response res) {
        this (res, null);
    }

    public Result(Error err) {
        this(null, err);
    }

    public boolean ok() {
        return this.res != null;
    }
}

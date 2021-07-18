package Logger;

public abstract class Logger {
    protected abstract String decode_this();
    public void log_this( String commit ){
        Log.add(decode_this (), commit);
    }
}

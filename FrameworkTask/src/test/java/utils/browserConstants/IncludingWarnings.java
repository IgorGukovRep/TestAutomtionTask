package utils.browserConstants;

public enum IncludingWarnings {

    SCROLL_BLOCKING_WARNING("[Violation] Added non-passive event listener to a scroll-blocking 'mousewheel' event.");

    private String warning;

    IncludingWarnings(String warning) {
        this.warning = warning;
    }

    public String getWarning() {
        return warning;
    }
}

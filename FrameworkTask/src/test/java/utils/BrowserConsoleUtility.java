package utils;

import org.openqa.selenium.logging.LogType;
import utils.constants.browserConstants.IncludingWarnings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.getWebDriverLogs;

public class BrowserConsoleUtility {

    private static final List<String> warningList = new ArrayList<>();

    public static List<String> analyzeBrowserConsoleLogWarning() {
        return getLog(Level.WARNING).stream().filter(warning -> getWarningListErrorsList().stream().
                anyMatch(warning::contains)).collect(Collectors.toList());
    }

    public static List<String> analyzeBrowserConsoleLogError() {
        return getLog(Level.SEVERE);
    }

    private static List<String> getLog(Level logLevel) {
        final ArrayList<String> consoleIssueList = new ArrayList<>();
        consoleIssueList.addAll(getWebDriverLogs(LogType.BROWSER, logLevel));
        return consoleIssueList;
    }


    private static List<String> getWarningListErrorsList() {
        if (warningList.isEmpty()) {
            Arrays.stream(IncludingWarnings.values()).forEach(warning -> warningList.add(warning.getWarning()));
        }
        return warningList;
    }
}

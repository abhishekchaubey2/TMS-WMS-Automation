package com.delhivery.core.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class MasterCheckListExporter {
    public static final Map<String, List<String>> scenarioListMap = new HashMap<>();
    public static final Map<String, String> decorators = new HashMap<>();
    private static int keyCounter = 0;

    public static void populateMasterCheckListScenario(String key, String value) {
        List<String> list = new ArrayList<>();
        if (key.contains(CoreConstants.SUITE_IDENTIFIER)) {
            list.add(getJobNameBySuiteName(value));
        } else {
            list.add(value);
        }
        scenarioListMap.put(key.contains(CoreConstants.SUITE_IDENTIFIER) ? key : "data" + keyCounter++, list);
    }

    private static String getJobNameBySuiteName(String suiteName) {
        switch (suiteName) {
            case "CMU Regression 1":
                return CoreConstants.REGRESSION_1_JOB_NAME;
            case "CMU Regression 2":
                return CoreConstants.REGRESSION_2_JOB_NAME;
            default:
                return "Please add jenkins job name manually for the Suite : " + suiteName;
        }
    }

    public static void populateHeaders() {
        List<String> list = new ArrayList<>();
        list.add("Job Name");
        list.add("Scenario");
        scenarioListMap.put("Header", list);
        populateDecorators();
    }

    private static void populateDecorators() {
        decorators.put(CoreConstants.SUITE_IDENTIFIER, "Job Name");
        decorators.put("should_merged_column", "True");
        decorators.put("merged_cell_column", "0");
        decorators.put("should_set_column_width", "True");
    }
}

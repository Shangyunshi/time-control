package com.shangyunshi.timecontrol.utils;

import com.shangyunshi.timecontrol.model.Label;
import java.util.ArrayList;

public final class StringUtils {

    private StringUtils() {

    }

    public static String getLabelsDigest(ArrayList<Label> labels) {
        StringBuilder builder = new StringBuilder();
        if (labels == null || labels.size() == 0) {
            return "";
        }
        for (int i = 0; i < labels.size(); i++) {
            if (i == 3) {
                builder.append("...");
                break;
            }
            Label label = labels.get(i);
            builder.append(label);
        }
        return builder.toString();
    }

}

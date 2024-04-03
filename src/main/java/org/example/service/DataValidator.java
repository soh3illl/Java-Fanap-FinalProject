package org.example.service;

import javax.servlet.http.HttpServletRequest;

public class DataValidator {
    public static boolean hasRequiredParams(HttpServletRequest req, String... parameterNames) {
        for (String paramName : parameterNames) {
            String paramValue = req.getParameter(paramName);
            if (paramValue == null || paramValue.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidAmount(String amount) {
        try {
            double value = Double.parseDouble(amount);
            return value >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isAccountTypeValid(String accountType) {
        return "checking".equals(accountType) || "savings".equals(accountType);
    }
}

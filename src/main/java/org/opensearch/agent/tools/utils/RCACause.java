/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.agent.tools.utils;

import static org.apache.commons.text.StringEscapeUtils.escapeJson;

import java.util.Map;

import lombok.Getter;

@Getter
public class RCACause {
    public RCACause() {}

    public String reason;
    public String apiUrl;
    public String expectedResponse;
    public String response;

    public static RCACause fromMap(Map<String, String> map) {
        RCACause cause = new RCACause();
        cause.reason = map.getOrDefault("reason", "");
        cause.apiUrl = map.getOrDefault("api_url", "");
        cause.expectedResponse = map.getOrDefault("expected_response", "");
        cause.response = map.getOrDefault("response", "");
        return cause;
    }

    @Override
    public String toString() {
        return "{"
            + "\"reason\": \""
            + escapeJson(reason)
            + "\","
            + "\"apiUrl\": \""
            + escapeJson(apiUrl)
            + "\","
            + "\"expectedResponse\": \""
            + escapeJson(expectedResponse)
            + "\","
            + "\"response\": \""
            + escapeJson(response)
            + "\""
            + "}";
    }
}

/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.agent.tools.utils;

import static org.apache.commons.text.StringEscapeUtils.escapeJson;
import static org.apache.commons.text.StringEscapeUtils.unescapeJson;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.opensearch.ml.common.utils.StringUtils;

import lombok.Getter;

@Getter
public class RCADoc {

    public String phenomenon;
    public List<RCACause> causes;

    @SuppressWarnings("unchecked")
    public RCADoc(String knowledge) {
        knowledge = unescapeJson(knowledge);
        Map<String, ?> knowledgeBase = StringUtils.gson.fromJson(knowledge, Map.class);
        this.phenomenon = (String) knowledgeBase.get("phenomenon");
        this.causes = ((List<Map<String, String>>) knowledgeBase.get("causes"))
            .stream()
            .map(RCACause::fromMap)
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"causes\": [");
        for (int i = 0; i < causes.size(); i++) {
            json.append(causes.get(i).toString());
            if (i < causes.size() - 1) {
                json.append(", ");
            }
        }

        json.append("]}");
        return json.toString();
    }
}

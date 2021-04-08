package template.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class TemplateParser {

    public String replaceVarsAndGetMsg(String templateMsg, Map<String, String> variables) {
        final String variablePattern = "\\$.+\\$";
        final String escapePattern = "(\\\\\\$)+";
        String[] words = templateMsg.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.matches(variablePattern)) {
                String variable = word.substring(1, word.length() - 1);
                if (variables.containsKey(variable)) {
                    word = variables.get(variable);
                }
            } else if (word.matches(escapePattern)) {
                word = word.replace("\\", "");
            }
            sb.append(word).append(" ");
        }
        return sb.toString();
    }
}

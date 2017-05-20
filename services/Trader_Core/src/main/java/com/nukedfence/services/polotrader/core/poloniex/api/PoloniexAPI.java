package com.nukedfence.services.polotrader.core.poloniex.api;

import net.sf.json.*;
import java.util.HashMap;
import java.util.Map;

public abstract class PoloniexAPI {
    private String url;

    PoloniexAPI(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    protected JSONObject getRequestedJSONObject(String command) {
        return getRequestedJSONObject(command, null);
    }

    protected JSONObject getRequestedJSONObject(String command, Map<String, String> params) {
        JSON json = getRequestedJSON(command, params);
        if (json instanceof JSONObject) {
            return (JSONObject)json;
        } else {
            System.out.println("Response for request '" + command + "' not a JSONObject.");
            return new JSONObject(true);
        }
    }

    protected JSONArray getRequestedJSONArray(String command) {
        return getRequestedJSONArray(command, null);
    }

    protected JSONArray getRequestedJSONArray(String command, Map<String, String> params) {
        JSON json = getRequestedJSON(command, params);
        if (json instanceof JSONArray) {
            return (JSONArray) json;
        }else {
            System.out.println("Response for request '" + command + "' not a JSONArray.");
            return new JSONArray();
        }
    }

    protected JSON getRequestedJSON(String command, Map<String, String> params) {
        if (command != null && !command.isEmpty()) {
            if (params == null) params = new HashMap<>();
            params.put("command", command);
            String data = getRequestedContent(getUrl(), params);
            //System.out.println("DATA: " + data);
            try {
                return JSONSerializer.toJSON(data);
            } catch (JSONException e) {
                System.out.println("ERROR converting requested content to JSON. ['" + getUrl() + "?command=" + command + "']");
            }
        } else {
            System.out.println("No command submitted with request.");
        }
        return JSONNull.getInstance();
    }

    protected abstract String getRequestedContent(String url, Map<String, String> params);

    protected String getQueryParamString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        if ( params != null ) {
            for ( Map.Entry<String, String> param : params.entrySet() ) {
                if ( param.getKey() != null && param.getValue() != null ) {
                    sb.append((sb.length() == 0)?"":"&").append(param.getKey()).append("=").append(param.getValue());
                }
            }
        }
        return sb.toString();
    }

}

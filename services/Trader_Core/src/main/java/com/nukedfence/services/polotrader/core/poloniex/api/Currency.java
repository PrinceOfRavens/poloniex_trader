package com.nukedfence.services.polotrader.core.poloniex.api;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Currency {

    private String identifier;
    private List<Market> associatedMarkets;

    private int poloId;
    private String name;
    private double txFee;
    private double minConf;
    private String depositAddress;
    private boolean disabled;
    private boolean delisted;
    private boolean frozen;

    private boolean outOfOrder = true;

    public Currency(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setCurrencyInfo(JSONObject info) {
        try {
            setCurrencyInfo(info.getInt("id"), info.getString("name"), info.getDouble("txFee"),
                    info.getDouble("minConf"), info.getString("depositAddress"),
                    Boolean.parseBoolean(info.getString("disabled")), Boolean.parseBoolean(info.getString("delisted")),
                    Boolean.parseBoolean(info.getString("frozen")));

        } catch (JSONException e) {
            System.out.println("Invalid JSON data for Currency: " + getIdentifier() + ".");
            e.printStackTrace();
            outOfOrder = true;
        }
    }

    public void setCurrencyInfo(int id, String name, double txFee, double minConf, String depositAddress, boolean disabled, boolean delisted, boolean frozen) {
        this.poloId = id;
        this.name = name;
        this.txFee = txFee;
        this.minConf = minConf;
        this.depositAddress = depositAddress;
        this.disabled = disabled;
        this.delisted = delisted;
        this.frozen = frozen;
        outOfOrder = false;
    }

    public List<Market> getMarketAssociations() {
        if (associatedMarkets == null) associatedMarkets = new ArrayList<>();
        return associatedMarkets;
    }

    public void addMarketAssociation(Market m) {
        if (!getMarketAssociations().contains(m)) {
            getMarketAssociations().add(m);
        }
    }
}

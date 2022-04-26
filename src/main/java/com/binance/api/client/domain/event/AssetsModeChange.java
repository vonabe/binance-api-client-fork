package com.binance.api.client.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author telegram @vonabe on 24.04.2022.
 * @project binance-api-client
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetsModeChange {

    @JsonProperty("j")
    private Boolean multiAssetsMode;

    public Boolean getMultiAssetsMode() {
        return multiAssetsMode;
    }

    public void setMultiAssetsMode(Boolean multiAssetsMode) {
        this.multiAssetsMode = multiAssetsMode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("multiAssetsMode", multiAssetsMode)
                .toString();
    }
}

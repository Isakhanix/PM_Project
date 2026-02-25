package com.project.security.config.common.util;

import com.uber.h3core.H3Core;
import org.springframework.stereotype.Component;

@Component
public class H3Util {

    private final H3Core h3;

    public H3Util(H3Core h3) {
        this.h3 = h3;
    }

    public String toH3(double lat, double lon, int res) {
        return h3.latLngToCellAddress(lat, lon, res);
    }

    public String latLngToCell(Double latitude, Double longitude, int resolvedH3Resolution) {
        if (latitude == null || longitude == null) {
            throw new IllegalArgumentException("latitude and longitude must not be null");
        }
        return h3.latLngToCellAddress(latitude, longitude, resolvedH3Resolution);
    }

    public String latLngToCell(double lat, double lng, int resolution) {
        return h3.latLngToCellAddress(lat, lng, resolution);
    }

    public boolean isValidCell(String cell) {
        return h3.isValidCell(cell);
    }
}

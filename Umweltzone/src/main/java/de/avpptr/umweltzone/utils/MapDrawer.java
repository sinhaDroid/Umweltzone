/*
 *  Copyright (C) 2018  Tobias Preuss
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.avpptr.umweltzone.utils;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.List;

import de.avpptr.umweltzone.models.Circuit;

public class MapDrawer {

    private final GoogleMap mMap;

    private List<Polygon> mZonePolygons;

    public MapDrawer(GoogleMap map) {
        mMap = map;
    }

    public void drawPolygons(
            List<Circuit> circuits,
            int fillColor,
            int strokeColor,
            int strokeWidth) {
        if (mZonePolygons != null && !mZonePolygons.isEmpty()) {
            mZonePolygons.clear();
            mZonePolygons = null;
        }

        int circuitsCount = circuits.size();
        mZonePolygons = new ArrayList<>(circuitsCount);
        for (int i = 0; i < circuitsCount; i++) {
            Circuit circuit = circuits.get(i);
            Polygon zonePolygon = drawPolygon(circuit, fillColor, strokeColor, strokeWidth);
            mZonePolygons.add(zonePolygon);
        }
    }

    private Polygon drawPolygon(
            Circuit circuit,
            int fillColor,
            int strokeColor,
            int strokeWidth) {
        return mMap.addPolygon(new PolygonOptions()
                .addAll(circuit.getCoordinates())
                .strokeWidth(strokeWidth)
                .fillColor(fillColor)
                .strokeColor(strokeColor));
    }

}

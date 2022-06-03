import React from "react";

import {
    ComposableMap,
    Geographies,
    Geography,
    Marker,
    useZoomPan
  } from "react-simple-maps";

  const geoUrl =
  "https://raw.githubusercontent.com/zcreativelabs/react-simple-maps/master/topojson-maps/world-50m.json";

const width = 800;
const height = 600;

const CustomZoomableGroup = ({ children, ...restProps }) => {
  const { mapRef, transformString, position } = useZoomPan(restProps);
  return (
    <g ref={mapRef}>
      <rect width={width} height={height} fill="transparent" />
      <g transform={transformString}>{children(position)}</g>
    </g>
  );
};

const MapFrag = (props) => {
    return(
        <div>
      <ComposableMap projection="geoMercator" 
        height={1200}
        style={{ width: "100%", height: "auto" }} 
         >
        <CustomZoomableGroup center={[123, 10]} zoom={25}>
          {(position) => (
            <>
              <Geographies geography={geoUrl}>
                {({ geographies }) =>
                  geographies
                    .filter((d) => d.properties.NAME === "Philippines")
                    .map((geo) => (
                      <Geography key={geo.rsmKey} geography={geo} fill="#0dbce2"/>
                    ))
                }
              </Geographies>
              <Marker coordinates={[120.8986, 14.5000]}>
                <circle r={8 / position.k} fill="#F53" />
              </Marker>

            </>
          )}
        </CustomZoomableGroup>
      </ComposableMap>
    </div>
    )
}

export default MapFrag
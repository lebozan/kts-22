package ftn.kts.service;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.api.GraphHopperWeb;
import com.graphhopper.util.Parameters;
import com.graphhopper.util.shapes.GHPoint;
import ftn.kts.dtos.LocationDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class GraphHopperService {

    private final GraphHopperWeb gh;

    public GraphHopperService(GraphHopperWeb gh) {
        this.gh = gh;
    }

    public GHResponse getGhDirectionsForCoords(List<LocationDTO> locations) {
        GHRequest req = new GHRequest();
        for (LocationDTO loc : locations) {
            req.addPoint(new GHPoint(loc.getLatitude(), loc.getLongitude()));
        }
        // Set profile like car, bike, foot, ...
        req.setProfile("car");
        // Optionally enable/disable elevation in output PointList, default is false
        req.putHint("elevation", false);
        // Optionally enable/disable turn instruction information, defaults is true
        req.putHint("instructions", true);
        // Optionally enable/disable path geometry information, default is true
        req.putHint("calc_points", true);
        // note: turn off instructions and calcPoints if you just need the distance or time
        // information to make calculation and transmission faster

        // Optionally set specific locale for instruction information, supports already over 25 languages,
        // defaults to English
        req.setLocale(Locale.ENGLISH);

        // Optionally add path details
        req.setPathDetails(Arrays.asList(
                Parameters.Details.STREET_NAME,
                Parameters.Details.AVERAGE_SPEED,
                Parameters.Details.EDGE_ID
        ));

        GHResponse fullRes = gh.route(req);
        if (fullRes.hasErrors())
            throw new RuntimeException(fullRes.getErrors().toString());

        return fullRes;

    }
}

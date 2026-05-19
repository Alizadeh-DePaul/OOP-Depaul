package interactiveOopCodes.designPatterns.adapter.realEstateAdapter;

import java.util.List;

/**
 * Adapter Pattern - Real Estate Aggregator (OBJECT ADAPTER, polymorphic swap).
 *
 *   The aggregator's Client code wants ONE uniform shape: PropertyListing. But
 *   each external source (Zillow, Redfin, ...) ships its own incompatible API:
 *
 *     - ZillowApi  returns a pipe-delimited String  "addr|price|bedrooms"
 *     - RedfinService returns a POJO with totally different field names
 *
 *   Each source gets its own Object Adapter. Once wrapped, the Client treats them
 *   ALL as PropertyListing - it never knows or cares which API the listing came
 *   from. Add a new source (Trulia, MLS) tomorrow? Write one more adapter, and
 *   the Client code does not change. That is the polymorphic-swap pro made
 *   concrete.
 */
public class RealEstateAdapter {

    public static void main(String[] args) {
        // Two real-world Adaptees with completely different shapes:
        ZillowApi      zillow = new ZillowApi();
        RedfinService  redfin = new RedfinService();

        // Each is wrapped by its own Object Adapter, and the Client treats both
        // uniformly as PropertyListing.
        List<PropertyListing> listings = List.of(
            new ZillowAdapter(zillow),
            new RedfinAdapter(redfin)
        );

        System.out.println("---- aggregated listings (polymorphic) ----");
        for (PropertyListing l : listings) {
            print(l);
        }
    }

    private static void print(PropertyListing l) {
        System.out.printf("%-30s | $%,d | %d BD | source=%s%n",
            l.getAddress(), l.getPriceUsd(), l.getBedrooms(), l.getSource());
    }
}

/** TARGET - the uniform interface every part of the aggregator was written against. */
interface PropertyListing {
    String getAddress();
    int    getPriceUsd();
    int    getBedrooms();
    String getSource();
}

/* ─────────────────────── Adaptee A: ZillowApi ─────────────────────── */

class ZillowApi {
    /** returns "addr|price|bedrooms" - a raw legacy format */
    public String fetchRaw() {
        return "123 Main St, Chicago|450000|3";
    }
}

class ZillowAdapter implements PropertyListing {
    private final String[] parts;

    public ZillowAdapter(ZillowApi api) {
        this.parts = api.fetchRaw().split("\\|");
    }

    @Override public String getAddress()  { return parts[0]; }
    @Override public int    getPriceUsd() { return Integer.parseInt(parts[1]); }
    @Override public int    getBedrooms() { return Integer.parseInt(parts[2]); }
    @Override public String getSource()   { return "Zillow"; }
}

/* ─────────────────────── Adaptee B: RedfinService ─────────────────────── */

class RedfinService {
    public RedfinListingDto getListing() {
        return new RedfinListingDto("789 Oak Ave, Evanston", 575_000, 4);
    }
}

/** Plain DTO, completely different field names from PropertyListing. */
class RedfinListingDto {
    final String streetAndCity;
    final int    askPrice;
    final int    numBedrooms;
    RedfinListingDto(String s, int p, int b) {
        this.streetAndCity = s;
        this.askPrice = p;
        this.numBedrooms = b;
    }
}

class RedfinAdapter implements PropertyListing {
    private final RedfinListingDto dto;

    public RedfinAdapter(RedfinService service) {
        this.dto = service.getListing();
    }

    @Override public String getAddress()  { return dto.streetAndCity; }
    @Override public int    getPriceUsd() { return dto.askPrice; }
    @Override public int    getBedrooms() { return dto.numBedrooms; }
    @Override public String getSource()   { return "Redfin"; }
}

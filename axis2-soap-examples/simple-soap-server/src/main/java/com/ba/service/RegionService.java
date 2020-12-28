package com.ba.service;

import com.ba.dto.Region;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegionService {

    private List<Region> regions = new ArrayList<Region>();

    public List<Region> getRegions() {
        if (regions.isEmpty()) {
            regions.addAll(Arrays.asList(new Region(1L, "Marmara"), new Region(2L, "Ege"), new Region(3L, "Doğu"), new Region(4L, "Karadeniz")));
        }

        return regions;
    }

}

package com.hubspot.testapi.models;

import org.joda.time.LocalDate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PartnerTest {

    @Test
    public void testFeasibleDatesNoContinuesDays() {
        List<LocalDate> ld = new ArrayList<>();
        ld.add(LocalDate.parse("2017-05-03"));
        ld.add(LocalDate.parse("2017-05-06"));
        Partner p = new Partner.Builder()
                .withAvailableDates(ld)
                .build();
        assertEquals(0, p.feasibleDates().size());
    }

    @Test
    public void testFeasibleDatesContinuesDays() {
        List<LocalDate> ld = new ArrayList<>();
        ld.add(LocalDate.parse("2017-05-03"));
        ld.add(LocalDate.parse("2017-05-04"));
        Partner p = new Partner.Builder()
                .withAvailableDates(ld)
                .build();
        assertEquals(1, p.feasibleDates().size());
        assertEquals(LocalDate.parse("2017-05-03"), p.feasibleDates().get(0));
    }

    @Test
    public void testFeasibleDatesMultipleContinuesDays() {
        List<LocalDate> ld = new ArrayList<>();
        ld.add(LocalDate.parse("2017-05-03"));
        ld.add(LocalDate.parse("2017-05-04"));
        ld.add(LocalDate.parse("2017-05-05"));
        ld.add(LocalDate.parse("2017-05-06"));
        Partner p = new Partner.Builder()
                .withAvailableDates(ld)
                .build();
        assertEquals(3, p.feasibleDates().size());
        assertEquals(LocalDate.parse("2017-05-03"), p.feasibleDates().get(0));
        assertEquals(LocalDate.parse("2017-05-04"), p.feasibleDates().get(1));
        assertEquals(LocalDate.parse("2017-05-05"), p.feasibleDates().get(2));
    }
}
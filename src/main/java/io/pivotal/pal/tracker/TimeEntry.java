package io.pivotal.pal.tracker;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Objects;

public class TimeEntry implements Comparable<TimeEntry>{

    private long id;

    public void setId(long id) {
        this.id = id;
    }

    private long projectId;
    private long userId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    private int hours;

    private static long uniqueKey = 0l;

    public TimeEntry(long id, long projectId, long userId, LocalDate date, int hours) {

        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public TimeEntry( long projectId, long userId, LocalDate date, int hours) {


        this(1L, projectId, userId, date, hours);
    }

    private static long updateKey() {
        uniqueKey ++;
        return uniqueKey;
    }


    public TimeEntry() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeEntry timeEntry = (TimeEntry) o;
        return
                id == timeEntry.id &&
                projectId == timeEntry.projectId &&
                userId == timeEntry.userId &&
                hours == timeEntry.hours &&
                Objects.equals(date, timeEntry.date);

      //  return true;
    }



    public long getId() {
        return this.id;
    }

    public long getProjectId() {
        return projectId;
    }

    public long getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    public static long getUniqueKey() {
        return uniqueKey;
    }

    @Override
    public int compareTo(TimeEntry o) {
        return 0;
    }
}

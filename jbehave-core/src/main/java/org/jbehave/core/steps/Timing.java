package org.jbehave.core.steps;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Timing {
    private long durationInMillis;
    private long start;
    private long end;

    public long getDurationInMillis() {
        return durationInMillis;
    }

    public void setTimings(Timer timer) {
        this.start = timer.getStart();
        this.end = timer.getEnd();
        this.durationInMillis = timer.getDuration();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

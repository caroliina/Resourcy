package com.resourcy.app.web.rest.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Created by kreete.peedo on 3/14/2016.
 */
public class AbstractAuditingEntityDTO implements Serializable {

    private ZonedDateTime createdDate;

    private ZonedDateTime lastModifiedDate;

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }




}

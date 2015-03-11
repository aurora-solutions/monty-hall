package io.aurorasolutions.montyhall.domain.common;

/**
 * Created by Rasheed on 2015-03-11.
 */
public interface IEntity<T> {

    /**
     * Entities compare by identity, not by attributes.
     *
     * @param other The other entity.
     * @return true if the identities are the same, regardless of other attributes.
     */
    boolean sameIdentityAs(T other);
}
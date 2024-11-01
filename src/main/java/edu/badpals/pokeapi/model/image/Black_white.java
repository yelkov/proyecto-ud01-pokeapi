package edu.badpals.pokeapi.model.image;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Black_white implements Serializable {
    // Versión para la serialización.
    private static final long serialVersionUID = 1L;

    @JsonProperty("animated")
    private Animated animated;

    public Black_white(){}

    public Animated getAnimated() {
        return animated;
    }

    public void setAnimated(Animated animated) {
        this.animated = animated;
    }

    @Override
    public String toString() {
        return "Black_white{" +
                "animated=" + animated +
                '}';
    }
}

package edu.badpals.pokeapi.model.image;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Generation_v implements Serializable {
    // Versión para la serialización.
    private static final long serialVersionUID = 1L;

    @JsonProperty("black-white")
    private Black_white black_white;

    public Generation_v(){}

    public Black_white getBlack_white() {
        return black_white;
    }

    public void setBlack_white(Black_white black_white) {
        this.black_white = black_white;
    }

    @Override
    public String toString() {
        return "Generation_v{" +
                "black_white=" + black_white +
                '}';
    }
}

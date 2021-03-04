package com.epam.jwd.core_final.domain;

import lombok.Getter;

import java.util.Objects;

/**
 * Expected fields:
 * <p>
 * location could be a simple class Point with 2 coordinates
 */
public class Planet extends AbstractBaseEntity {
    @Getter
    private final Point point;

    public static class Point {
        @Getter
        private final int x;
        @Getter
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public Planet(String name, Point point) {
        super(name);
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(point, planet.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "point=" + point +
                '}';
    }
}
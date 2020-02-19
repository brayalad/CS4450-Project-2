/***************************************************************
 * file: TransformationFactory.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Utility class to create Transformations
 *
 ****************************************************************/

package com.cpp.cs.cs4450.util.factory;

import com.cpp.cs.cs4450.models.transformations.Rotation;
import com.cpp.cs.cs4450.models.transformations.Scaling;
import com.cpp.cs.cs4450.models.transformations.Transformation;
import com.cpp.cs.cs4450.models.transformations.Translation;
import com.cpp.cs.cs4450.util.common.Utils;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class to create Transformations
 */
public final class TransformationFactory {
    /**
     * Error message for unsupported transformations
     */
    private static final String UNSUPPORTED_TRANSFORMATION_ERROR_FORMAT = "[%s] is not a supported transformation.";

    /**
     * String pair delimiter
     */
    private static final String PAIR_DELIMITER = " ";

    /**
     * Map of flags to classes
     */
    public static final Map<String, Class<? extends Transformation>> TRANSFORMATIONS = Collections.unmodifiableMap(
            Stream.of(
                    new SimpleImmutableEntry<>("r", Rotation.class),
                    new SimpleImmutableEntry<>("s", Scaling.class),
                    new SimpleImmutableEntry<>("t", Translation.class)
            ).collect(Collectors.toMap(Entry::getKey, Entry::getValue))
    );

    /**
     * Creates transformations
     *
     * @param transformations transformations to create
     *
     * @return transformations
     */
    public static Set<Transformation> createTransformations(final Collection<Entry<String, String>> transformations){
        return transformations.stream().map(t -> createTransformation(t.getKey(), t.getValue())).collect(Collectors.toSet());
    }

    /**
     * Creates transformations
     *
     * @param type transformation type
     * @param transformation transformation to create
     *
     * @return new transformation
     */
    public static Transformation createTransformation(final String type, final String transformation){
        if(!TRANSFORMATIONS.containsKey(type)){
            throw new IllegalArgumentException(String.format(UNSUPPORTED_TRANSFORMATION_ERROR_FORMAT, type));
        }

        return createTransformation(TRANSFORMATIONS.get(type), transformation);
    }

    /**
     * Creates transformations
     *
     * @param type transformation type
     * @param transformation transformation to create
     *
     * @return new transformation
     */
    public static Transformation createTransformation(final Class<? extends Transformation> type, final String transformation){
        if(type == Rotation.class) {
            return createRotation(transformation);
        } else if(type == Scaling.class) {
            return createScaling(transformation);
        } else if(type == Translation.class){
            return createTranslation(transformation);
        } else {
            throw new IllegalArgumentException(String.format(UNSUPPORTED_TRANSFORMATION_ERROR_FORMAT, type.toString()));
        }
    }

    /**
     * Creates rotation
     *
     * @param r string rotation
     *
     * @return new rotation
     */
    public static Rotation createRotation(final String r){
        final Double[] rotation = Arrays.stream(r.split(PAIR_DELIMITER)).map(Double::parseDouble).toArray(Double[]::new);
        return createRotation(rotation[0], rotation[1], rotation[2]);
    }

    /**
     * Creates rotation
     *
     * @param angle rotation angle
     * @param px pivot x
     * @param py pivot y
     *
     * @return new rotation
     */
    public static Rotation createRotation(final double angle, final double px, final double py){
        return new Rotation(angle, px, py);
    }

    /**
     * Creates Scaling
     *
     * @param s scaling string
     *
     * @return new scaling
     */
    public static Scaling createScaling(final String s){
        final Double[] scaling = Arrays.stream(s.split(PAIR_DELIMITER)).map(Double::parseDouble).toArray(Double[]::new);
        return createScaling(scaling[0], scaling[1], scaling[2], scaling[3]);
    }

    /**
     * Creates scaling
     *
     * @param x scaling x value
     * @param y scaling y value
     * @param px pivot x value
     * @param py pivot y value
     *
     * @return new scaling
     */
    public static Scaling createScaling(final double x, final double y, final double px, final double py){
        return new Scaling(x, y, px, py);
    }

    /**
     * Creates Translation
     *
     * @param t translation string
     *
     * @return new translation
     */
    public static Translation createTranslation(final String t){
        return createTranslation(Utils.parsePairToDouble(t, PAIR_DELIMITER));
    }

    /**
     * Creates Translation
     *
     * @param entry pivot
     *
     * @return new translation
     */
    public static Translation createTranslation(final Entry<Double, Double> entry){
        return new Translation(entry);
    }


}

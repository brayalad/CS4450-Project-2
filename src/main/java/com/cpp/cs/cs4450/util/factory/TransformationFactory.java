package com.cpp.cs.cs4450.util.factory;

import com.cpp.cs.cs4450.models.transformations.Rotation;
import com.cpp.cs.cs4450.models.transformations.Scaling;
import com.cpp.cs.cs4450.models.transformations.Transformation;
import com.cpp.cs.cs4450.models.transformations.Translation;
import com.cpp.cs.cs4450.util.common.CommonUtils;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TransformationFactory {
    private static final String UNSUPPORTED_TRANSFORMATION_ERROR_FORMAT = "[%s] is not a supported transformation.";


    private static final String PAIR_DELIMITER = " ";

    public static final Map<String, Class<? extends Transformation>> TRANSFORMATIONS = Collections.unmodifiableMap(
            Stream.of(
                    new SimpleImmutableEntry<>("r", Rotation.class),
                    new SimpleImmutableEntry<>("s", Scaling.class),
                    new SimpleImmutableEntry<>("t", Translation.class)
            ).collect(Collectors.toMap(Entry::getKey, Entry::getValue))
    );

    public static Set<Transformation> createTransformations(final Collection<Entry<String, String>> transformations){
        return transformations.stream().map(t -> createTransformation(t.getKey(), t.getValue())).collect(Collectors.toSet());
    }

    public static Transformation createTransformation(final String type, final String transformation){
        if(!TRANSFORMATIONS.containsKey(type)){
            throw new IllegalArgumentException(String.format(UNSUPPORTED_TRANSFORMATION_ERROR_FORMAT, type));
        }

        return createTransformation(TRANSFORMATIONS.get(type), transformation);
    }

    public static Transformation createTransformation( final Class<? extends Transformation> type, final String transformation){
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

    public static Rotation createRotation(final String r){
        final Double[] rotation = Arrays.stream(r.split(PAIR_DELIMITER)).map(Double::parseDouble).toArray(Double[]::new);
        return createRotation(rotation[0], rotation[1], rotation[2]);
    }

    public static Rotation createRotation(final double angle, final Entry<Double, Double> pivot){
        return new Rotation(angle, pivot);
    }

    public static Rotation createRotation(final double angle, final double px, final double py){
        return new Rotation(angle, px, py);
    }

    public static Scaling createScaling(final String s){
        final Double[] scaling = Arrays.stream(s.split(PAIR_DELIMITER)).map(Double::parseDouble).toArray(Double[]::new);
        return createScaling(scaling[0], scaling[1], scaling[2], scaling[3]);
    }

    public static Scaling createScaling(final double x, final double y, final Entry<Double, Double> pivot){
        return new Scaling(x, y, pivot);
    }

    public static Scaling createScaling(final double x, final double y, final double px, final double py){
        return new Scaling(x, y, px, py);
    }

    public static Translation createTranslation(final String t){
        return createTranslation(CommonUtils.parsePairToDouble(t, PAIR_DELIMITER));
    }

    public static Translation createTranslation(final Entry<Double, Double> entry){
        return new Translation(entry);
    }

    public static Translation createTranslation(final double x, final double y){
        return new Translation(x, y);
    }

}

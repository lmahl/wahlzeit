package org.wahlzeit.utils;

import java.lang.annotation.Repeatable;

/**
 * Annotation to describe a used Design Pattern
 */
@Repeatable(PatternInstances.class)
public @interface PatternInstance {
	String name();
	String[] participants();
}
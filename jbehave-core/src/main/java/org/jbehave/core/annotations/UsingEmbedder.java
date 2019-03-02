package org.jbehave.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.jbehave.core.embedder.Embedder;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Inherited
@Documented
public @interface UsingEmbedder {

    Class<?> embedder() default Embedder.class;
    boolean batch()  default false;
	boolean skip()  default false;
	boolean generateViewAfterStories()  default true;
	boolean ignoreFailureInStories()  default false;
	boolean ignoreFailureInView()  default false;
	boolean verboseFailures() default false;
    boolean verboseFiltering() default false;
    String storyTimeouts() default "";
    /** @deprecated Use storyTimeouts() */
    @Deprecated long storyTimeoutInSecs() default 300;
    /** @deprecated Use storyTimeouts() */
    @Deprecated String storyTimeoutInSecsByPath() default "";
    boolean failOnStoryTimeout() default false;
	int threads() default 1;
	boolean stepsFactory() default true;
    String[] metaFilters() default {};
    String systemProperties() default "";

}

package com.thoughtworks.jbehave.core.minimock;

import com.thoughtworks.jbehave.core.exception.PendingException;
import com.thoughtworks.jbehave.core.exception.VerificationException;

/**
 * Support for constraint-based verification
 */
public abstract class UsingConstraints {
	/** ensures object equals expected value */
	public ConstraintSupport eq(final Object expectedArg) {
	    return new ConstraintSupport() {
	        public boolean matches(Object arg) {
	            return arg == null ? expectedArg == null : arg.equals(expectedArg);
	        }
	        public String toString() {
	            return "equal to <" + expectedArg + ">";
	        }
	    };
	}

	/** eq(primitive) for float and double */
	public ConstraintSupport eq(final double expectedArg, final double delta) {
	    return new ConstraintSupport() {
	        public boolean matches(Object arg) {
	            double value = ((Number) arg).doubleValue();
				return Math.abs(expectedArg - value) <= delta;
	        }          
	        public String toString() {
	            return "floating point number equal to " + expectedArg;
	        }
	    };
	}

	/** eq(primitive) for float and double */
	public ConstraintSupport eq(final double expectedArg) {
		return eq(expectedArg, 0.0);
	}
	
	/** eq(primitive) for byte, short, integer and long */
	public ConstraintSupport eq(final long expectedArg) {
	    return new ConstraintSupport() {
	        public boolean matches(Object arg) {
	            Number n = (Number)arg;
	            return n.longValue() == expectedArg;
	        }          
	        public String toString() {
	            return "integer type equal to " + expectedArg;
	        }
	    };
	}

	/** eq(primitive) for char - note {@link Character} is not a {@link Number} */
	public ConstraintSupport eq(final char expectedArg) {
	    return new ConstraintSupport() {
	        public boolean matches(Object arg) {
	            Character n = (Character)arg;
	            return n.charValue() == expectedArg;
	        }          
	        public String toString() {
	            return "character equal to '" + expectedArg + "'";
	        }
	    };
	}

	/** eq(primitive) for boolean */
	public ConstraintSupport eq(final boolean expectedArg) {
	    return new ConstraintSupport() {
	        public boolean matches(Object arg) {
	            Boolean n = (Boolean)arg;
	            return n.booleanValue() == expectedArg;
	        }          
	        public String toString() {
	            return "boolean " + expectedArg;
	        }
	    };
	}

	public ConstraintSupport sameInstanceAs(final Object expectedArg) {
	    return new ConstraintSupport() {
	        public boolean matches(Object arg) {
	            return expectedArg == arg;
	        }
	        public String toString() {
	            return "same instance as <" + expectedArg.toString() + ">";
	        }
	    };
	}

	public ConstraintSupport anything() {
	    return new ConstraintSupport() {
	        public boolean matches(Object arg) {
	            return true;
	        }
	        public String toString() {
	            return "anything";
	        }
	    };
	}

	public ConstraintSupport a(final Class type) {
	    return isA(type);
	}

	public ConstraintSupport isA(final Class type) {
	    return new ConstraintSupport() {
	        public boolean matches(Object arg) {
	            return type.isInstance(arg);
	        }
	        public String toString() {
	            return "object of type " + type.getName();
	        }
	    };
	}
	
	public ConstraintSupport startsWith(final String fragment) {
	    return new ConstraintSupport() {
	        public boolean matches(Object arg) {
	            return ((String)arg).startsWith(fragment);
	        }
	        public String toString() {
	            return "string starting with <" + fragment + ">";
	        }
	    };
	}
	
	public ConstraintSupport endsWith(final String fragment) {
		return new ConstraintSupport() {
			public boolean matches(Object arg) {
				return ((String)arg).endsWith(fragment);
			}
			public String toString() {
				return "string ending with <" + fragment + ">";
			}
		};
	}
	
	public ConstraintSupport contains(final String fragment) {
		return new ConstraintSupport() {
			public boolean matches(Object arg) {
				return ((String)arg).indexOf(fragment) != -1;
			}
			public String toString() {
				return "string ending with <" + fragment + ">";
			}
		};
	}

	public ConstraintSupport and(final Constraint a, final Constraint b) {
	    return new ConstraintSupport() {
	        public boolean matches(Object arg) {
	            return a.matches(arg) && b.matches(arg);
	        }
	        public String toString() {
	            return "(" + a + " and " + b + ")";
	        }
	    };
	}

	public ConstraintSupport both(final Constraint a, final Constraint b) {
	    return and(a, b);
	}

	public ConstraintSupport or(final Constraint a, final Constraint b) {
	    return new ConstraintSupport() {
	        public boolean matches(Object arg) {
	            return a.matches(arg) || b.matches(arg);
	        }
	        public String toString() {
	            return "(" + a + " or " + b + ")";
	        }
	    };
	}

	public ConstraintSupport either(final Constraint a, final Constraint b) {
	    return either(a, b);
	}

	public ConstraintSupport not(final Constraint c) {
	    return new ConstraintSupport() {
	        public boolean matches(Object arg) {
	            return !c.matches(arg);
	        }
	        public String toString() {
	            return "not (" + c + ")";
	        }
	    };
	}
	
    public void ensureThat(Object arg, Constraint constraint, String message) {
    	if (!constraint.matches(arg)) {
    		fail("\nExpected: " +
    				(message != null ? "[" + message + "] " : "") +
    				constraint +
    				"\nbut got:  " + arg);
    	}
	}
	public void ensureThat(Object arg, Constraint constraint) {
		ensureThat(arg, constraint, null);
	}
	
	public void ensureThat(long arg, Constraint constraint, String message) {
		ensureThat(arg, constraint, message);
	}
	public void ensureThat(long arg, Constraint constraint) {
		ensureThat(new Long(arg), constraint, null);
	}
    
    public void ensureThat(double arg, Constraint constraint, String message) {
    	ensureThat(new Double(arg), constraint, message);
    }
    public void ensureThat(double arg, Constraint constraint) {
    	ensureThat(arg, constraint, null);
    }
    
    public void ensureThat(char arg, Constraint constraint, String message) {
    	ensureThat(new Character(arg), constraint, message);
    }
    public void ensureThat(char arg, Constraint constraint) {
    	ensureThat(arg, constraint, null);
    }
    
    public void ensureThat(boolean arg, Constraint constraint, String message) {
    	ensureThat(Boolean.valueOf(arg), constraint, message);
    }
    public void ensureThat(boolean arg, Constraint constraint) {
    	ensureThat(arg, constraint, null);
    }
    
    /** ensure(...) without constraints */
    public void ensureThat(boolean condition, String message) {
    	if (!condition) {
    		fail(message + ": expected condition was not met");
    	}
    }

    /** ensure(...) without constraints */
    public void ensureThat(boolean condition) {
        if (!condition) {
        	fail("Expected condition was not met");
        }
    }

    public void fail(String message) {
        throw new VerificationException(message);
    }

    public void fail(String message, Object expected, Object actual) {
        throw new VerificationException(message, expected, actual);
    }

	public void todo(String message) {
		throw new PendingException(message);
	}
	
	public void todo() {
		todo("TODO");
	}
}

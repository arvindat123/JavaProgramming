package org.Interface;

@FunctionalInterface
public interface ChildFunctionalInterface extends ParentFunctionalInterface {

	default int childMethod(){
		return 0;
	}
}

package com.cleancoder.args;

import java.util.Iterator;

public interface ArgumentInterface {
  void set(Iterator<String> currentArgument) throws ArgsException;
}

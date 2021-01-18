package org.nta.lessons.lesson8;

public interface Service {
  @Cache(cacheType = CacheType.IN_MEMORY, limit = 20, zip = false)
  double doHardWorkInMemory(@This String name, Integer value);

  @Cache(cacheType = CacheType.FILE, zip = false)
  double doHardWorkFile(String name, @This Integer value);

  @Cache(name = "One", cacheType = CacheType.FILE, limit = 100_000, zip = true)
  double doHardWorkZip(@This String name, @This Integer value);
}

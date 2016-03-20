[![Build Status](https://travis-ci.org/synapticloop/simplelogger.svg?branch=master)](https://travis-ci.org/synapticloop/simplelogger) [![Download](https://api.bintray.com/packages/synapticloop/maven/simplelogger/images/download.svg)](https://bintray.com/synapticloop/maven/simplelogger/_latestVersion) [![GitHub Release](https://img.shields.io/github/release/synapticloop/simplelogger.svg)](https://github.com/synapticloop/simplelogger/releases) 

# simplelogger



> a simple, light-weight logging framework



A small (~9k) Simple Logger - one class,, no dependencies 

For when you really only need the simplest of loggers.

# Usage

Include the dependency (see below), instantiate the logger, and start logging...

```
import synapticloop.util.simplelogger.Logger;

...

private static final Logger LOGGER = Logger.getLogger(SimpleLoggerTest.class);

// then you can log information
LOGGER.debug("message");
LOGGER.info("message");
LOGGER.warn("message");
LOGGER.error("message");
LOGGER.fatal("message");

// or throwable exceptions as well
LOGGER.debug("message", exception);
LOGGER.warn("message", exception);
LOGGER.info("message", exception);
LOGGER.error("message", exception);
LOGGER.fatal("message", exception);
```

## Configuration

You may set logging levels globally, either through the static methods, or by including a `simplelogger.properties` file at the root of the classpath

### Static methods

```
LOGGER.setShouldLogDebug(true);
LOGGER.setShouldLogInfo(true);
LOGGER.setShouldLogWarn(true);
LOGGER.setShouldLogError(true);
LOGGER.setShouldLogFatal(true);
```

### `simplelogger.properties`



```
info=true
debug=true
warn=true
fatal=false
error=false
```



# Code size



```

Line number report (NumberTextReporter)
=======================================
  File type    #    Code(      %)    Comment(      %)    Blank(      %)    Total(      %)  
-----------  ---  ---------------  ------------------  ----------------  ----------------  
      .java    2     524( 69.40%)        124( 16.42%)      107( 14.17%)      755(100.00%)  
-----------  ---  ---------------  ------------------  ----------------  ----------------  
    1 types    2     524( 69.40%)        124( 16.42%)      107( 14.17%)      755(100.00%)  
===========  ===  ===============  ==================  ================  ================  
```



```

Line number report (CumulativeBarTextReporter)
==============================================
  File type  
-----------  0                   25                  50                   75                  100
             +--------------------------------------------------------+-------------+-----------+
      .java  |########################################################|:::::::::::::|           |
             +--------------------------------------------------------+-------------+-----------+

Key:
----
  '#' code
  ':' comment
  ' ' blank
```



# Building the Package

## *NIX/Mac OS X

From the root of the project, simply run

`./gradlew build`


## Windows

`./gradlew.bat build`


This will compile and assemble the artefacts into the `build/libs/` directory.

Note that this may also run tests (if applicable see the Testing notes)

# Running the Tests

## *NIX/Mac OS X

From the root of the project, simply run

`gradle --info test`

if you do not have gradle installed, try:

`gradlew --info test`

## Windows

From the root of the project, simply run

`gradle --info test`

if you do not have gradle installed, try:

`./gradlew.bat --info test`


The `--info` switch will also output logging for the tests

# Artefact Publishing - Github

This project publishes artefacts to [GitHib](https://github.com/)

> Note that the latest version can be found [https://github.com/synapticloop/simplelogger/releases](https://github.com/synapticloop/simplelogger/releases)

As such, this is not a repository, but a location to download files from.

# Dependency Management Maven

This project publishes artefacts to [Maven Central](https://search.maven.org/)

> Note that the latest version can be found [mvn central](http://search.maven.org/#artifactdetails|synapticloop|simplelogger|1.1.1|jar)

## maven setup

No setup is required

## gradle setup

Repository

```
repositories {
	mavenCentral()
}
```

## Dependencies - Gradle

```
dependencies {
	runtime(group: 'synapticloop', name: 'simplelogger', version: '1.1.1', ext: 'jar')

	compile(group: 'synapticloop', name: 'simplelogger', version: '1.1.1', ext: 'jar')
}
```

or, more simply for versions of gradle greater than 2.1

```
dependencies {
	runtime 'synapticloop:simplelogger:1.1.1'

	compile 'synapticloop:simplelogger:1.1.1'
}
```

## Dependencies - Maven

```
<dependency>
	<groupId>synapticloop</groupId>
	<artifactId>simplelogger</artifactId>
	<version>1.1.1</version>
	<type>jar</type>
</dependency>
```

## Dependencies - Downloads


You will also need to download the following dependencies:



### cobertura dependencies

  - net.sourceforge.cobertura:cobertura:2.0.3: (It may be available on one of: [bintray](https://bintray.com/net.sourceforge.cobertura/maven/cobertura/2.0.3/view#files/net.sourceforge.cobertura/cobertura/2.0.3) [mvn central](http://search.maven.org/#artifactdetails|net.sourceforge.cobertura|cobertura|2.0.3|jar))


### testCompile dependencies

  - junit:junit:4.12: (It may be available on one of: [bintray](https://bintray.com/junit/maven/junit/4.12/view#files/junit/junit/4.12) [mvn central](http://search.maven.org/#artifactdetails|junit|junit|4.12|jar))


### testRuntime dependencies

  - junit:junit:4.12: (It may be available on one of: [bintray](https://bintray.com/junit/maven/junit/4.12/view#files/junit/junit/4.12) [mvn central](http://search.maven.org/#artifactdetails|junit|junit|4.12|jar))

**NOTE:** You may need to download any dependencies of the above dependencies in turn (i.e. the transitive dependencies)

# License

```
The MIT License (MIT)

Copyright (c) 2016 synapticloop

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```


--

> `This README.md file was hand-crafted with care utilising synapticloop`[`templar`](https://github.com/synapticloop/templar/)`->`[`documentr`](https://github.com/synapticloop/documentr/)

--


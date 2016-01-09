<img src="https://travis-ci.org/synapticloop/simplelogger.svg?branch=master" />

A small (~3k) Simple Logger


# Dependency Management

> Note that the latest version can be found [https://bintray.com/synapticloop/maven/simplelogger/view](https://bintray.com/synapticloop/maven/simplelogger/view)

Include the dependency

## maven

this comes from the jcenter bintray, to set up your repository:

    <?xml version="1.0" encoding="UTF-8" ?>
    <settings xsi:schemaLocation='http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd' xmlns='http://maven.apache.org/SETTINGS/1.0.0' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>
      <profiles>
        <profile>
          <repositories>
            <repository>
              <snapshots>
                <enabled>false</enabled>
              </snapshots>
              <id>central</id>
              <name>bintray</name>
              <url>http://jcenter.bintray.com</url>
            </repository>
          </repositories>
          <pluginRepositories>
            <pluginRepository>
              <snapshots>
                <enabled>false</enabled>
              </snapshots>
              <id>central</id>
              <name>bintray-plugins</name>
              <url>http://jcenter.bintray.com</url>
            </pluginRepository>
          </pluginRepositories>
          <id>bintray</id>
        </profile>
      </profiles>
      <activeProfiles>
        <activeProfile>bintray</activeProfile>
      </activeProfiles>
    </settings>

And now for the dependency

    <dependency>
      <groupId>synapticloop</groupId>
      <artifactId>simplelogger</artifactId>
      <version>v1.0.2</version>
      <type>jar</type>
    </dependency>
 
 
## gradle

Repository

    repositories {
        maven {
            url  "http://jcenter.bintray.com" 
        }
    }
 
 or just
 
    repositories {
      jcenter()
    }

and then include the dependency:

    runtime(group: 'synapticloop', name: 'simplelogger', version: 'v1.0.2', ext: 'jar')

    compile(group: 'synapticloop', name: 'simplelogger', version: 'v1.0.2', ext: 'jar')
 
or 

    runtime 'synapticloop:simplelogger:v1.0.2'

    compile 'synapticloop:simplelogger:v1.0.2'
    
## Other

You may either download the files from [https://bintray.com/synapticloop/maven/simplelogger/](https://bintray.com/synapticloop/maven/simplelogger/) or from [https://github.com/synapticloop/simplelogger/releases](https://github.com/synapticloop/simplelogger/releases)
